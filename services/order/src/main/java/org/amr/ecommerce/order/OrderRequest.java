package org.amr.ecommerce.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.amr.ecommerce.product.PurchaseRequest;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Amount must be greater than zero")
        BigDecimal amount,
        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer Id is required")
        @NotEmpty(message = "Customer Id is required")
        @NotBlank(message = "Customer Id is required")
        String customerId,
        @NotEmpty(message = "You should provide at least one product")
        List<PurchaseRequest> products
) {
}
