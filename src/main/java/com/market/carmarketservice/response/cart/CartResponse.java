package com.market.carmarketservice.response.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private Integer id;
    private String lastName;
    private List<ProductInfo> products;
    private Integer total;
}
