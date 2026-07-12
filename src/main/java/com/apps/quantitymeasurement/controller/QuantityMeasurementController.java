package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.model.QuantityDTO;
import com.apps.quantitymeasurement.model.QuantityMeasurementDTO;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/quantity")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/compare")
    public ResponseEntity<QuantityMeasurementDTO> compare(
            @Valid @RequestBody QuantityDTO first,
            @RequestParam String secondValue,
            @RequestParam String secondUnit,
            @RequestParam String secondMeasurementType) {

        QuantityDTO second = new QuantityDTO(
                Double.parseDouble(secondValue),
                secondUnit,
                secondMeasurementType
        );

        return ResponseEntity.ok(
                service.compare(first, second)
        );
    }

    @PostMapping("/convert")
    public ResponseEntity<QuantityMeasurementDTO> convert(
            @Valid @RequestBody QuantityDTO quantity,
            @RequestParam String targetUnit) {

        QuantityDTO target = new QuantityDTO();
        target.setUnit(targetUnit);
        target.setMeasurementType(quantity.getMeasurementType());

        return ResponseEntity.ok(
                service.convert(quantity, target)
        );
    }

    @PostMapping("/add")
    public ResponseEntity<QuantityMeasurementDTO> add(
            @Valid @RequestBody QuantityDTO first,
            @RequestParam String secondValue,
            @RequestParam String secondUnit,
            @RequestParam String secondMeasurementType,
            @RequestParam String targetUnit) {

        QuantityDTO second = new QuantityDTO(
                Double.parseDouble(secondValue),
                secondUnit,
                secondMeasurementType
        );

        QuantityDTO target = new QuantityDTO();
        target.setUnit(targetUnit);
        target.setMeasurementType(first.getMeasurementType());

        return ResponseEntity.ok(
                service.add(first, second, target)
        );
    }

    @PostMapping("/subtract")
    public ResponseEntity<QuantityMeasurementDTO> subtract(
            @Valid @RequestBody QuantityDTO first,
            @RequestParam String secondValue,
            @RequestParam String secondUnit,
            @RequestParam String secondMeasurementType,
            @RequestParam String targetUnit) {

        QuantityDTO second = new QuantityDTO(
                Double.parseDouble(secondValue),
                secondUnit,
                secondMeasurementType
        );

        QuantityDTO target = new QuantityDTO();
        target.setUnit(targetUnit);
        target.setMeasurementType(first.getMeasurementType());

        return ResponseEntity.ok(
                service.subtract(first, second, target)
        );
    }

    @PostMapping("/divide")
    public ResponseEntity<QuantityMeasurementDTO> divide(
            @Valid @RequestBody QuantityDTO first,
            @RequestParam String secondValue,
            @RequestParam String secondUnit,
            @RequestParam String secondMeasurementType) {

        QuantityDTO second = new QuantityDTO(
                Double.parseDouble(secondValue),
                secondUnit,
                secondMeasurementType
        );

        return ResponseEntity.ok(
                service.divide(first, second)
        );
    }
}