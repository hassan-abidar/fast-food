package com.shanks.service;

import com.shanks.model.Order;
import com.shanks.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {
    public PaymentResponse createPaymentLink(Order order) throws StripeException;
}
