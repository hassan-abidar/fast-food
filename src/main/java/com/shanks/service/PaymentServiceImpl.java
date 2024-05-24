package com.shanks.service;

import com.shanks.model.Order;
import com.shanks.response.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

public class PaymentServiceImpl implements PaymentService{

    @Value("${stripe.api.key}")
    private String stripeSectretKey;
    @Override
    public PaymentResponse createPaymentLink(Order order) throws StripeException {
        Stripe.apiKey=stripeSectretKey;
        SessionCreateParams params = SessionCreateParams.builder().addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payment/success/"+order.getId())
                .setCancelUrl("http://localhost:3000/payment/failed")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L).setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("MAD")
                                .setUnitAmount((long) order.getTotalPrice()* 100)
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("Krisp").build()).build()
                ).build()
        ).build();

        Session sessionn = Session.create(params);
        PaymentResponse response = new PaymentResponse();
        response.setPayment_url(sessionn.getUrl());
        return response;
    }
}
