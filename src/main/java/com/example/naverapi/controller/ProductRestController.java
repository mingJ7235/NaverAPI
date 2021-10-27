package com.example.naverapi.controller;

import com.example.naverapi.entity.Product;
import com.example.naverapi.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductRepository productRepository;

    @GetMapping ("/api/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
