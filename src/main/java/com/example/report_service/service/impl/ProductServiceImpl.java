package com.example.report_service.service.impl;

import com.example.report_service.data.repository.MissionRepository;
import com.example.report_service.dto.mission.MissionRecord;
import com.example.report_service.dto.product.ProductInventoryDto;
import com.example.report_service.dto.warehouse.WarehouseQuantityDto;
import com.example.report_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final MissionRepository missionRepository;

    private static ProductInventoryDto buildProductInventory(int productId, List<WarehouseQuantityDto> quantities) {
        return ProductInventoryDto.builder()
                .productId(productId)
                .quantities(quantities)
                .totalQuantity(quantities.stream().map(WarehouseQuantityDto::getCount).reduce(0, Integer::sum))
                .build();
    }

    private static WarehouseQuantityDto buildWarehouseQuantity(int count, int warehouseId) {
        return WarehouseQuantityDto
                .builder()
                .count(count)
                .warehouseId(warehouseId)
                .build();
    }

    @Override
    public ProductInventoryDto getSingleProductInventory(int productId) {
        var missionRecords = missionRepository.findByProductId(productId);
        var quantities = missionRecords.stream()
                .collect(Collectors.groupingBy(MissionRecord::warehouseId))
                .entrySet().stream()
                .map(missionPair ->
                        buildWarehouseQuantity(missionPair.getValue().getFirst().count(), missionPair.getKey()))
                .toList();

        return buildProductInventory(productId, quantities);
    }

    @Override
    public List<ProductInventoryDto> getAllProductInventories() {
        var missionRecords = missionRepository.findAllAsRecords();

        var productInventories = new ArrayList<ProductInventoryDto>();

        for (var entry : missionRecords.stream().collect(Collectors.groupingBy(MissionRecord::productId)).entrySet()) {
            var quantities = entry.getValue().stream()
                    .map(missionRecord ->
                            buildWarehouseQuantity(missionRecord.count(), missionRecord.warehouseId()))
                    .toList();

            productInventories.add(buildProductInventory(entry.getKey(), quantities));
        }

        return productInventories;
    }
}
