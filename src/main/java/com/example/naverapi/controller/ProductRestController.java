package com.example.naverapi.controller;

import com.example.naverapi.dto.ProductMypriceRequestDto;
import com.example.naverapi.dto.ProductRequestDto;
import com.example.naverapi.entity.Product;
import com.example.naverapi.repo.ProductRepository;
import com.example.naverapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    //설정 가격 변경
    @PutMapping ("/api/products/{id}")
    public Long updateProduct (@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) {
        return productService.update(id, requestDto);
    }
}
