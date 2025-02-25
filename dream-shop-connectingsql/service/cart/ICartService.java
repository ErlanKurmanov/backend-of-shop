package com.example.dream_shop_connectingsql.service.cart;

import com.example.dream_shop_connectingsql.model.Cart;
import com.example.dream_shop_connectingsql.model.CartItem;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Long initializeNewCart();

    Cart getCartByUserId(Long userId);
}
