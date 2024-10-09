package com.market.carmarketservice.response.order;

import com.market.carmarketservice.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {
    private Product product;
    private Integer quantity;
}
