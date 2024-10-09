package com.market.carmarketservice.api;

import com.market.carmarketservice.model.order.Order;
import com.market.carmarketservice.request.order.OrderIDRequest;
import com.market.carmarketservice.request.order.OrderRequest;
import com.market.carmarketservice.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@PropertySource("classpath:notify.properties")
@RequestMapping(value = "/api/auth")
public class OrderController {
    private final OrderService orderService;
    private final Environment env;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<Object> order(@RequestBody OrderRequest request) {
        if (orderService.order(request.getId()))
            return new ResponseEntity<>(env.getProperty("Success"), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllOrder() {
        if (orderService.getAllOrder() != null)
            return new ResponseEntity<>(orderService.getAllOrder(), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getOrders(@PathVariable("id") int uid) {
        if (orderService.getOrders(uid) != null)
            return new ResponseEntity<>(orderService.getOrders(uid), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/user/{id}/orders", method = RequestMethod.GET)
    public ResponseEntity<Object> getOrder(@PathVariable("id") int oid) {
        if (orderService.getOrders(oid) != null)
            return new ResponseEntity<>(orderService.getOrder(oid), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/cancel-order", method = RequestMethod.PUT)
    public ResponseEntity<Object> cancelOrder(@RequestBody OrderIDRequest request) {
        return new ResponseEntity<>(orderService.cancelOrder(request.getId()), HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{id}/status", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateStatus(@PathVariable("id") int id, @RequestBody Order order) {
        if (orderService.updateStatus(id, order))
            return new ResponseEntity<>(env.getProperty("Success"), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.BAD_REQUEST);
    }
}
