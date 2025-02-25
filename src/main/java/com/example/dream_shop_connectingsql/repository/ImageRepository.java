package com.example.dream_shop_connectingsql.repository;

import com.example.dream_shop_connectingsql.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProductId(Long id);
}