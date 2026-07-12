package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.model.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    // Find all records for a particular operation
    List<QuantityMeasurementEntity> findByOperation(String operation);

    // Find all records by measurement type
    List<QuantityMeasurementEntity> findByThisMeasurementType(String measurementType);

    // Find all records created after a specific date
    List<QuantityMeasurementEntity> findByCreatedAtAfter(LocalDateTime date);

    // Custom JPQL Query
    @Query("""
            SELECT e
            FROM QuantityMeasurementEntity e
            WHERE e.operation = :operation
            AND e.isError = false
            """)
    List<QuantityMeasurementEntity> findSuccessfulOperations(
            @Param("operation") String operation
    );

    // Count successful operations
    long countByOperationAndIsErrorFalse(String operation);

    // Find only failed operations
    List<QuantityMeasurementEntity> findByIsErrorTrue();

}