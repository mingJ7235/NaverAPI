package com.example.naverapi.controller;

import com.example.naverapi.dto.ItemDto;
import com.example.naverapi.utils.NaverShopSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchRequestController {
    private final NaverShopSearch naverShopSearch;

    @GetMapping ("/api/search")
    public List<ItemDto> getItems (@RequestParam String query) {
        String resultString = naverShopSearch.search(query);
        return naverShopSearch.fromJSONtoItems(resultString);
    }
}
