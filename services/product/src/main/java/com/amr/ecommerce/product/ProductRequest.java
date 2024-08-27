package com.amr.ecommerce.product;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @NotNull(message = "Product available quantity should be greater than 0")
        Double availableQuantity,
        @NotNull(message = "Product price should be greater than 0")
        BigDecimal price,
        @NotNull(message = "Product category id is required")
        Integer categoryId
) {
}
