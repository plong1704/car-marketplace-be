package com.market.carmarketservice.service.order;

import com.market.carmarketservice.model.order.OrderDetail;
import com.market.carmarketservice.response.order.OrderDetailResponse;

import java.util.List;

public interface OrderDetailService {
    public List<OrderDetail> getAllOrderDetail();

    public OrderDetailResponse getOrderDetail(int orderID);
}
