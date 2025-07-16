package com.example.report_service.service;

import com.example.report_service.dto.product.ProductInventoryDto;

import java.util.List;

public interface ProductService {
    ProductInventoryDto getSingleProductInventory(int productId);

    List<ProductInventoryDto> getAllProductInventories();
}
