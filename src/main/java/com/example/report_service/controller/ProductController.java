package com.example.report_service.controller;

import com.example.report_service.dto.product.ProductInventoryDto;
import com.example.report_service.service.ProductService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductInventoryDto> getSingleProductInventory(@PathVariable @Positive int productId) {
        return ResponseEntity.ok(productService.getSingleProductInventory(productId));
    }

    @GetMapping()
    public ResponseEntity<List<ProductInventoryDto>> getAllProductInventories() {
        return ResponseEntity.ok(productService.getAllProductInventories());
    }
}
