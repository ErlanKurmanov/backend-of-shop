package com.example.practicew;


import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    private final Repository repo;

    public ProductService(Repository repo) {
        this.repo = repo;
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return repo.save(product);
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
