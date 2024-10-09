package com.market.carmarketservice.api;

import com.market.carmarketservice.request.cart.CartRequest;
import com.market.carmarketservice.request.cart.UpdateCartRequest;
import com.market.carmarketservice.request.order.OrderIDRequest;
import com.market.carmarketservice.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@PropertySource("classpath:notify.properties")
@RequestMapping(value = "/api/auth")
public class CartController {
    private final CartService cartService;
    private final Environment env;

    @RequestMapping(value = "/add-to-cart", method = RequestMethod.POST)
    public ResponseEntity<Object> addToCart(@RequestBody CartRequest cart) {
        if (cartService.addToCart(cart))
            return new ResponseEntity<>(env.getProperty("Success"), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/remove-from-cart/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> removeFromCart(@PathVariable("id") int id) {
        if (cartService.removeFromCart(id))
            return new ResponseEntity<>(env.getProperty("Success"), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/cart/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getCart(@PathVariable("id") int uid) {
        return new ResponseEntity<>(cartService.getCart(uid), HttpStatus.OK);
    }

    @RequestMapping(value = "/cart", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCart(@RequestBody UpdateCartRequest updateCartRequest) {
        return new ResponseEntity<>(cartService.updateCart(updateCartRequest), HttpStatus.OK);
    }

    @RequestMapping(value = "/repurchase", method = RequestMethod.POST)
    public ResponseEntity<Object> repurchase(@RequestBody OrderIDRequest request) {
        return new ResponseEntity<>(cartService.repurchase(request.getId()), HttpStatus.OK);
    }
}
