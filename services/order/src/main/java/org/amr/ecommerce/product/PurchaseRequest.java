package org.amr.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Product is required")
        Integer productId,
        @Positive(message = "Quantity must be greater than zero")
        Double quantity
) {
}