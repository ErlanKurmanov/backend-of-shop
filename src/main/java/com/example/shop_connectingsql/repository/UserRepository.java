package com.example.shop_connectingsql.repository;

import com.example.shop_connectingsql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
