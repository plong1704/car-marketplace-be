package com.market.carmarketservice.model.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> getOrderDetailsByOrder_Id(int orderID);
}
