package com.revolution.payment.service.infrastructure.adapter;

import com.revolution.payment.service.api.command.PaymentCommand;
import com.revolution.payment.service.api.dto.PaymentDto;
import com.revolution.payment.service.api.port.ProviderService;
import com.revolution.payment.service.api.response.LinkResponse;
import com.revolution.payment.service.infrastructure.configuration.StripeConfiguration;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@RequiredArgsConstructor
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
                    .putAllMetadata(Map.of(
                            "order_id", String.valueOf(command.orderId()),
                            "receiver_id", String.valueOf(command.receiverId())
                    ))
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
    public PaymentDto handlePayment(String payload) {
        return null;
    }
}
