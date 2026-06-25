package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.entity.QuantityDTO;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(
            IQuantityMeasurementService service) {

        this.service = service;
    }

    public boolean compare(
            QuantityDTO first,
            QuantityDTO second) {

        return service.compare(first, second);
    }

    public QuantityDTO convert(
            QuantityDTO quantity,
            String targetUnit) {

        return service.convert(quantity, targetUnit);
    }

    public QuantityDTO add(
            QuantityDTO first,
            QuantityDTO second,
            String targetUnit) {

        return service.add(first, second, targetUnit);
    }

    public QuantityDTO subtract(
            QuantityDTO first,
            QuantityDTO second,
            String targetUnit) {

        return service.subtract(first, second, targetUnit);
    }

    public double divide(
            QuantityDTO first,
            QuantityDTO second) {

        return service.divide(first, second);
    }
}