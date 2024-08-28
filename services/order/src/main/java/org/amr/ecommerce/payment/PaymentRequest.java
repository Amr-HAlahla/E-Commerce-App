package org.amr.ecommerce.payment;

import org.amr.ecommerce.customer.CustomerResponse;
import org.amr.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
