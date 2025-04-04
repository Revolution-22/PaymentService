package com.revolution.payment.service.infrastructure.adapter;

import com.revolution.payment.service.api.command.PaymentCommand;
import com.revolution.payment.service.api.dto.PaymentDto;
import com.revolution.payment.service.api.port.ProviderService;
import com.revolution.payment.service.api.response.LinkResponse;
import com.revolution.payment.service.infrastructure.configuration.StripeConfiguration;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.net.Webhook;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProviderServiceAdapter implements ProviderService {

    private static final String CURRENCY = "PLN";

    private final StripeConfiguration configuration;

    @Override
    public LinkResponse generatePaymentLink(PaymentCommand command) {
        Stripe.apiKey = configuration.getApiKey();

        try {
            PaymentLinkCreateParams params = PaymentLinkCreateParams.builder()
                    .addAllLineItem(
                            command.items().stream()
                                            .map(item -> PaymentLinkCreateParams.LineItem.builder()
                                                    .setPrice(createPrice(item.price().doubleValue(), CURRENCY, item.name()).getId())
                                                    .setQuantity(1L)
                                                    .build()).toList()
                            )
                    .setAfterCompletion(
                            PaymentLinkCreateParams.AfterCompletion.builder()
                                    .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                    .setRedirect(
                                            PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                    .setUrl(configuration.getRedirectUrl())
                                                    .build())
                                    .build())
                    .setPaymentIntentData(
                            PaymentLinkCreateParams.PaymentIntentData.builder()
                                    .putAllMetadata(Map.of(
                                            "transaction_id", String.valueOf(command.transactionId()),
                                            "order_id", String.valueOf(command.orderId()),
                                            "receiver_id", String.valueOf(command.receiverId())
                                    ))
                                    .build()
                    )
                    .build();

            PaymentLink paymentLink = PaymentLink.create(params);

            return new LinkResponse(paymentLink.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LinkResponse("");
    }

    private Price createPrice(Double amount, String currency, String name) {
        PriceCreateParams params = PriceCreateParams.builder()
                .setCurrency(currency)
                .setProductData(PriceCreateParams.ProductData.builder()
                        .setName(name)
                        .build())
                .setUnitAmount(Math.round(amount * 100))
                .build();

        try {
            return Price.create(params);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PaymentDto handlePayment(String payload, String sigHeader) {
        Event event;

        try {
            event = Webhook.constructEvent(payload, sigHeader, configuration.getSecret());
        } catch (SignatureVerificationException e) {
            throw new RuntimeException(e);
        }
        switch (event.getType()) {
            case "payment_intent.succeeded":
                PaymentIntent paymentIntent = (PaymentIntent) event.getDataObjectDeserializer()
                        .getObject().orElse(null);
                if (paymentIntent != null) {
                    Map<String, String> data = paymentIntent.getMetadata();
                    return new PaymentDto(
                            Long.parseLong(data.get("transaction_id")),
                            Long.parseLong(data.get("order_id")),
                            Long.parseLong(data.get("receiver_id")), 2);
                }
                break;
            case "payment_intent.payment_failed":
                PaymentIntent failedPaymentIntent = (PaymentIntent) event.getDataObjectDeserializer()
                        .getObject().orElse(null);
                if (failedPaymentIntent != null) {
                    Map<String, String> data = failedPaymentIntent.getMetadata();
                    return new PaymentDto(
                            Long.parseLong(data.get("transaction_id")),
                            Long.parseLong(data.get("order_id")),
                            Long.parseLong(data.get("receiver_id")), 3);
                }
                break;
            case "payment_intent.canceled":
                PaymentIntent canceledPaymentIntent = (PaymentIntent) event.getDataObjectDeserializer()
                        .getObject().orElse(null);
                if (canceledPaymentIntent != null) {
                    Map<String, String> data = canceledPaymentIntent.getMetadata();
                    return new PaymentDto(
                            Long.parseLong(data.get("transaction_id")),
                            Long.parseLong(data.get("order_id")),
                            Long.parseLong(data.get("receiver_id")), 3);
                }
                break;
            default:
                log.info("Payment event type not supported");

        }
        throw new RuntimeException("Payment event type not supported");
    }
}
