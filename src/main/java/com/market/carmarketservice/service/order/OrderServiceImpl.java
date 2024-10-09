package com.market.carmarketservice.service.order;

import com.market.carmarketservice.model.cart.Cart;
import com.market.carmarketservice.model.cart.CartRepository;
import com.market.carmarketservice.model.order.Order;
import com.market.carmarketservice.model.order.OrderDetail;
import com.market.carmarketservice.model.order.OrderDetailRepository;
import com.market.carmarketservice.model.order.OrderRepository;
import com.market.carmarketservice.model.user.User;
import com.market.carmarketservice.model.user.UserRepository;
import com.market.carmarketservice.response.order.OrderResponse;
import com.market.carmarketservice.response.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public List<Order> getAllOrder() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public boolean order(int uid) {
        try {
            List<Cart> carts = cartRepository.getCartsByUserId(uid);
            Optional<User> user = userRepository.findById(uid);
            var order = Order.builder()
                    .user(user.get())
                    .status("Confirming")
                    .createDate(new Timestamp(System.currentTimeMillis()))
                    .updateDate(new Timestamp(System.currentTimeMillis()))
                    .build();
            orderRepository.save(order);
            for (Cart c : carts) {
                Optional<Order> optional = orderRepository.findById(order.getId());
                var orderDetail = OrderDetail.builder()
                        .order(optional.get())
                        .product(c.getProduct())
                        .price(c.getProduct().getPrice() * c.getQuantity())
                        .quantity(c.getQuantity())
                        .build();
                orderDetailRepository.save(orderDetail);
                cartRepository.deleteById(c.getId());
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<OrderResponse> getOrders(int uid) {
        List<OrderResponse> response = new ArrayList<>();
        List<Order> order = orderRepository.getOrderByUserId(uid);
        for (Order o : order) {
            OrderResponse or = new OrderResponse();
            or.setId(o.getId());
            or.setUser(new UserResponse(o.getUser()));
            or.setStatus(o.getStatus());
            or.setCreateDate(o.getCreateDate());
            or.setUpdateDate(o.getUpdateDate());
            response.add(or);
        }
        return response;
    }

    @Override
    public boolean cancelOrder(int id) {
        try {
            Optional<Order> optional = orderRepository.findById(id);
            Order order = optional.get();
            order.setStatus("Cancelled");
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateStatus(int id, Order order) {
        try {
            Optional<Order> optional = orderRepository.findById(id);
            Order orderOld = optional.get();
            orderOld.setStatus(order.getStatus());
            orderRepository.save(orderOld);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public OrderResponse getOrder(int oid) {
        Optional<Order> optional = orderRepository.findById(oid);
        Order order = optional.get();
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setUser(new UserResponse(order.getUser()));
        response.setStatus(order.getStatus());
        response.setCreateDate(order.getCreateDate());
        response.setUpdateDate(order.getUpdateDate());
        return response;
    }
}
