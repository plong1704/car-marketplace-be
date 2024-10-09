package com.market.carmarketservice.response.cart;

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
    private Integer cartID;
    private Product product;
    private Integer quantity;
    private Integer temporaryPrice;
}
