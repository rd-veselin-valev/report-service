package com.example.report_service.data.repository;

import com.example.report_service.data.entity.Mission;
import com.example.report_service.dto.mission.MissionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Integer> {
    @Query("SELECT m.id FROM Mission m WHERE m.userId = :userId AND m.warehouseId = :warehouseId")
    List<Integer> findMissionIds(@Param("userId") int userId, @Param("warehouseId") int warehouseId);

    @Query("SELECT new com.example.report_service.dto.mission.MissionRecord(m.id, m.userId, m.warehouseId, m.productId, m.finalCount) FROM Mission m WHERE m.warehouseId = :warehouseId")
    List<MissionRecord> findByWarehouseId(@Param("warehouseId") int warehouseId);

    @Query(value = """
            SELECT DISTINCT ON (m.product_id) m.id, m.user_id, m.warehouse_id, m.product_id, m.final_count
            FROM mission m
            ORDER BY m.product_id, m.updated DESC 
            """, nativeQuery = true)
    List<MissionRecord> findAllAsRecords();

    @Query(value = """
            SELECT DISTINCT ON (m.warehouse_id) m.id, m.user_id, m.warehouse_id, m.product_id, m.final_count
            FROM mission m
            WHERE m.product_id = :productId
            ORDER BY m.warehouse_id, m.updated DESC 
            """, nativeQuery = true)
    List<MissionRecord> findByProductId(@Param("productId") int productId);
}