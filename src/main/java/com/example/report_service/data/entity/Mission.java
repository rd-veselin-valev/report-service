package com.example.report_service.data.entity;

import com.example.report_service.data.enums.OperationType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Mission extends BaseEntity {
    @Id
    protected int id;

    private int finalCount;

    private int originalCount;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    private int userId;

    private int productId;

    private int warehouseId;
}
