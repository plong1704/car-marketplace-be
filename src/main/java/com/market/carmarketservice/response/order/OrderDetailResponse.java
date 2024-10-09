package com.market.carmarketservice.response.order;

import com.market.carmarketservice.response.order.ProductInfo;
import com.market.carmarketservice.response.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {
    private Integer id;
    private UserResponse user;
    private List<ProductInfo> products;
    private Integer total;
}
