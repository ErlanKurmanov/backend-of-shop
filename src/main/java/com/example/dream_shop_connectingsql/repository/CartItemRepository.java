package com.example.dream_shop_connectingsql.repository;

import com.example.dream_shop_connectingsql.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long id);
}
