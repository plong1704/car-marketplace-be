package com.market.carmarketservice.api;

import com.market.carmarketservice.service.order.OrderDetailService;
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
public class OrderDetailController {
    private final OrderDetailService orderDetailService;
    private final Environment env;

    @RequestMapping(value = "/order/{id}/detail", method = RequestMethod.GET)
    public ResponseEntity<Object> getOrderDetail(@PathVariable("id") int orderId) {
        if (orderDetailService.getOrderDetail(orderId) != null)
            return new ResponseEntity<>(orderDetailService.getOrderDetail(orderId), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.OK);
    }
}
