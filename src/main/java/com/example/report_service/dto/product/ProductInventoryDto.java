package com.example.report_service.dto.product;

import com.example.report_service.dto.warehouse.WarehouseQuantityDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInventoryDto {
    List<WarehouseQuantityDto> quantities;

    private int productId;
    
    private int totalQuantity;
}
