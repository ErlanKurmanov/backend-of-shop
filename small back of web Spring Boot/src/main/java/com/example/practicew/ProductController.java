package com.example.practicew;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    //Get all products
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    //Get the product by id
    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable int id){
        return service.getProductById(id);
    }

    @PostMapping("/product")
    public Product addProduct (@RequestBody Product product){
        try {
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    //Deleting product
    @DeleteMapping("/product/{id}")
    public void deleteById(@PathVariable int id){
         service.deleteById(id);
    }

}
