package com.example.dream_shop_connectingsql.service.cart;

import com.example.dream_shop_connectingsql.model.Cart;
import com.example.dream_shop_connectingsql.model.CartItem;

import java.math.BigDecimal;

public interface ICartItemService {
    void addItemToCart(Long cartId, Long productId, int quantity);
    void removeItemFromCart(Long cartId, Long productId);
    void updateItemQuantity(Long cartId, Long productId, int quantity);

    CartItem getCartItem(Long cartId, Long productId);
}
