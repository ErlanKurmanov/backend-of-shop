package com.example.dream_shop_connectingsql.repository;

import com.example.dream_shop_connectingsql.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

    boolean existsByName(String name);
}
