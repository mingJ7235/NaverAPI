package com.example.naverapi.service;

import com.example.naverapi.dto.ProductMypriceRequestDto;
import com.example.naverapi.entity.Product;
import com.example.naverapi.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public Long update (Long id, ProductMypriceRequestDto requestDto) {
        Product product = productRepository.findById(id).orElseThrow(NullPointerException::new);
        product.update(requestDto);
        return id;
    }
}
