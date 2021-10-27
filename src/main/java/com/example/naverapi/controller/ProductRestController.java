package com.example.naverapi.controller;

import com.example.naverapi.dto.ProductRequestDto;
import com.example.naverapi.entity.Product;
import com.example.naverapi.repo.ProductRepository;
import com.example.naverapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    //상품 조회
    @GetMapping ("/api/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    //상품 등록
    @PostMapping ("/api/products")
    public Product createProduct (@RequestBody ProductRequestDto requestDto) {
        Product product = new Product(requestDto);
        productRepository.save(product);
        return product;
    }
}
