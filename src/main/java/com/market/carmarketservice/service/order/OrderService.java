package com.market.carmarketservice.service.order;

import com.market.carmarketservice.model.order.Order;
import com.market.carmarketservice.response.order.OrderResponse;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrder();

    boolean order(int uid);

    List<OrderResponse> getOrders(int uid);

    boolean cancelOrder(int id);

    boolean updateStatus(int id, Order order);

    OrderResponse getOrder(int oid);
}
