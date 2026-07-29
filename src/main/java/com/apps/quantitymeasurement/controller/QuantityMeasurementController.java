package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.model.QuantityDTO;
import com.apps.quantitymeasurement.model.QuantityMeasurementDTO;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import com.apps.quantitymeasurement.model.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.repository.QuantityMeasurementRepository;

@RestController
@RequestMapping("/api/quantity")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;
    @Autowired
    private QuantityMeasurementRepository repository;

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
    @GetMapping("/history/all")
    public ResponseEntity<List<QuantityMeasurementDTO>> getAllHistory() {

        return ResponseEntity.ok(
                QuantityMeasurementDTO.fromList(
                        repository.findAll()
                )
        );
    }

    @GetMapping("/history/operation/{operation}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistoryByOperation(
            @PathVariable String operation) {

        return ResponseEntity.ok(
                service.getOperationHistory(operation)
        );
    }

    @GetMapping("/history/type/{measurementType}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistoryByType(
            @PathVariable String measurementType) {

        return ResponseEntity.ok(
                service.getMeasurementsByType(measurementType)
        );
    }

    @GetMapping("/history/count/{operation}")
    public ResponseEntity<Long> getOperationCount(
            @PathVariable String operation) {

        return ResponseEntity.ok(
                service.getOperationCount(operation)
        );
    }

    @GetMapping("/history/errors")
    public ResponseEntity<List<QuantityMeasurementDTO>> getErrorHistory() {

        return ResponseEntity.ok(
                service.getErrorHistory()
        );
    }

    @DeleteMapping("/history/{id}")
    public ResponseEntity<Void> deleteHistory(
            @PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}