package com.market.carmarketservice.response.order;

import com.market.carmarketservice.response.user.UserResponse;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Integer id;
    @ManyToOne
    private UserResponse user;
    private String status;

    private Timestamp createDate;
    private Timestamp updateDate;
}
