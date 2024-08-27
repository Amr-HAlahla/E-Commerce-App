package com.amr.ecommerce.product;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Integer productId,
        String productName,
        String description,
        BigDecimal price,
        Double quantity
) {
}
