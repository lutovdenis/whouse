package com.mycompany.myproject.restcontrollers;

import com.mycompany.myproject.model.Product;
import com.mycompany.myproject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired(required = true)
    private ProductRepository productRepository;

    @GetMapping("/products")
    public StringBuilder getAvailableProducts() {
        return productRepository.getAvailableProducts();
    }

    @PostMapping("/products")
    void addProduct(@RequestBody Product product) {
        productRepository.addProduct(product);
    }
}