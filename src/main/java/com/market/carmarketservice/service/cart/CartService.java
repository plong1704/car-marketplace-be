package com.market.carmarketservice.service.cart;

import com.market.carmarketservice.request.cart.CartRequest;
import com.market.carmarketservice.request.cart.UpdateCartRequest;
import com.market.carmarketservice.response.cart.CartResponse;

public interface CartService {

    CartResponse getCart(int uid);

    boolean addToCart(CartRequest cartRequest);

    boolean removeFromCart(int id);

    boolean updateCart(UpdateCartRequest updateCartRequest);

    boolean repurchase(int oid);
}
