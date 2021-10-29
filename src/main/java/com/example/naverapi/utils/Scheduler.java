package com.example.naverapi.utils;

import com.example.naverapi.dto.ItemDto;
import com.example.naverapi.entity.Product;
import com.example.naverapi.repo.ProductRepository;
import com.example.naverapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class Scheduler {
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final NaverShopSearch naverShopSearch;

    //초, 분, 시, 일, 월, 주 순서
    @Scheduled (cron = "0 0 0 * * *")
    public void updatePrice () throws InterruptedException {
        System.out.println("가격 업데이트 실행");

        List<Product> productList = productRepository.findAll();
        for (int i = 0; i < productList.size(); i++) {
            TimeUnit.SECONDS.sleep(1);
            Product p = productList.get(i);
            String title = p.getTitle();
            String resultString = naverShopSearch.search(title);

            List<ItemDto> itemDtoList = naverShopSearch.fromJSONtoItems(resultString);
//            ItemDto itemDto = itemDtoList.get(i);
            ItemDto itemDto = itemDtoList.get(0);
            Long id = p.getId();
            productService.updateBySearch(id, itemDto);
        }
    }
}
