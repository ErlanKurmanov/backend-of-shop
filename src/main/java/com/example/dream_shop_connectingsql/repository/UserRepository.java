package com.example.dream_shop_connectingsql.repository;

import com.example.dream_shop_connectingsql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
