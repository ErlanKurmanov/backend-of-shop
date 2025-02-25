package com.example.dream_shop_connectingsql.repository;

import com.example.dream_shop_connectingsql.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByUserId(Long userId);
}
