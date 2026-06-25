package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;

import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository
        implements IQuantityMeasurementRepository {

    // ==========================================
    // Singleton Instance
    // ==========================================

    private static QuantityMeasurementCacheRepository instance;

    // ==========================================
    // In-Memory Cache
    // ==========================================

    private final List<QuantityMeasurementEntity> measurements;

    // ==========================================
    // Private Constructor
    // ==========================================

    private QuantityMeasurementCacheRepository() {
        measurements = new ArrayList<>();
    }

    // ==========================================
    // Singleton Access Method
    // ==========================================

    public static QuantityMeasurementCacheRepository getInstance() {

        if (instance == null) {
            instance = new QuantityMeasurementCacheRepository();
        }

        return instance;
    }

    // ==========================================
    // Save Entity
    // ==========================================

    @Override
    public void save(QuantityMeasurementEntity entity) {

        measurements.add(entity);

    }

    // ==========================================
    // Return History
    // ==========================================

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {

        return measurements;

    }
}