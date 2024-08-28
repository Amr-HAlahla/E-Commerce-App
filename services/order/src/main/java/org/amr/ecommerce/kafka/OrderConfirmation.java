package org.amr.ecommerce.kafka;

import org.amr.ecommerce.customer.CustomerResponse;
import org.amr.ecommerce.order.PaymentMethod;
import org.amr.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
