package com.revolution.payment.service.infrastructure.adapter;

import com.revolution.payment.service.api.dto.PaymentDto;
import com.revolution.payment.service.api.port.ProviderService;
import com.revolution.payment.service.api.request.PaymentRequest;
import com.revolution.payment.service.api.response.LinkResponse;
import com.revolution.payment.service.infrastructure.configuration.StripeConfiguration;
import com.stripe.Stripe;
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

    private static final String PRODUCT_NAME = "Oplata za zakupy na stronie Revolution-22";

    private final StripeConfiguration configuration;

    @Override
    public LinkResponse generatePaymentLink(PaymentRequest request) {
        Stripe.apiKey = configuration.getApiKey();

        try {
            Price price = createPrice(request.amount().doubleValue(), "PLN");
            PaymentLinkCreateParams params = PaymentLinkCreateParams.builder()
                    .addLineItem(
                            PaymentLinkCreateParams.LineItem.builder()
                                    .setPrice(price.getId())
                                    .setQuantity(1L)
                                    .build())
                    .setAfterCompletion(
                            PaymentLinkCreateParams.AfterCompletion.builder()
                                    .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                    .setRedirect(
                                            PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                    .setUrl(configuration.getRedirectUrl())
                                                    .build())
                                    .build())
                    .putAllMetadata(Map.of(
                            "order_id", String.valueOf(request.orderId()),
                            "receiver_id", String.valueOf(request.receiverId())
                    ))
                    .build();

            PaymentLink paymentLink = PaymentLink.create(params);

            return new LinkResponse(paymentLink.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LinkResponse("");
    }

    private Price createPrice(Double amount, String currency) throws Exception {
        PriceCreateParams params = PriceCreateParams.builder()
                .setCurrency(currency)
                .setProductData(PriceCreateParams.ProductData.builder()
                        .setName(PRODUCT_NAME)
                        .build())
                .setUnitAmount(Math.round(amount * 100))
                .build();

        return Price.create(params);
    }

    @Override
    public PaymentDto handlePayment(String payload) {
        return null;
    }
}
