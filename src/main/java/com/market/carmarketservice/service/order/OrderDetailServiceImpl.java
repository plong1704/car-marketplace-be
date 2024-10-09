package com.market.carmarketservice.service.order;

import com.market.carmarketservice.response.order.OrderDetailResponse;
import com.market.carmarketservice.response.order.ProductInfo;
import com.market.carmarketservice.response.user.UserResponse;
import com.market.carmarketservice.model.order.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> getAllOrderDetail() {
        return (List<OrderDetail>) orderDetailRepository.findAll();
    }

    @Override
    public OrderDetailResponse getOrderDetail(int orderID) {
        try {
            List<OrderDetail> orderDetails = orderDetailRepository.getOrderDetailsByOrder_Id(orderID);
            OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
            UserResponse user = new UserResponse(orderDetails.get(0).getOrder().getUser());
            List<ProductInfo> products = new ArrayList<>();
            int total = 0;
            for (OrderDetail p : orderDetails) {
                products.add(new ProductInfo(p.getProduct(), p.getQuantity()));
                total += (p.getQuantity() * p.getProduct().getPrice());
            }
            orderDetailResponse.setId(orderID);
            orderDetailResponse.setUser(user);
            orderDetailResponse.setProducts(products);
            orderDetailResponse.setTotal(total);
            return orderDetailResponse;
        } catch (Exception e) {
            return null;
        }
    }
}
