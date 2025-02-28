package com.example.shop_connectingsql.service.cart;

import com.example.shop_connectingsql.exceptions.ResourceNotFoundException;
import com.example.shop_connectingsql.model.Cart;
import com.example.shop_connectingsql.model.User;
import com.example.shop_connectingsql.repository.CartItemRepository;
import com.example.shop_connectingsql.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CartService implements ICartService{

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final AtomicLong cartIdGenerator = new AtomicLong(0);

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Cart getCart(Long id) {
        //Get cart instance from repository, and set to a memory
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        //refresh total price
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);

        //saving it to cart and returning this instance
        return cartRepository.save(cart);
    }

// it tries to transfer info in one transaction, if it fails, it stops a whole transaction
    @Transactional
    @Override
    public void clearCart(Long id) {
        Cart cart = getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        cart.getItems().clear();
        cartRepository.deleteById(id);
    }

    //getting total price from getCart
    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart = getCart(id);
        return cart.getTotalAmount();
    }

    @Override
    public Cart initializeNewCart(User user) {
        return Optional.ofNullable(getCartByUserId(user.getId()))
                .orElseGet(()->{
                    Cart cart = new Cart(user);
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}