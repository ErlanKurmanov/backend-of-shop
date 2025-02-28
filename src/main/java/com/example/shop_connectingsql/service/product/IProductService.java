package com.example.shop_connectingsql.service.product;

import com.example.shop_connectingsql.dto.ProductDto;
import com.example.shop_connectingsql.model.Product;
import com.example.shop_connectingsql.request.AddProductRequest;
import com.example.shop_connectingsql.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest request, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String category, String name);
    Long countProductsByBrandAndName(String brand, String name);

    List<ProductDto> getConvertedProducts(List<Product> product);

    public ProductDto convertToDto(Product product);



}
