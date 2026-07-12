package com.apps.quantitymeasurement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class QuantityMeasurementDTO {

    private double thisValue;
    private String thisUnit;
    private String thisMeasurementType;

    private Double thatValue;
    private String thatUnit;
    private String thatMeasurementType;

    private String operation;

    private String resultString;

    private Double resultValue;
    private String resultUnit;
    private String resultMeasurementType;

    private String errorMessage;

    @JsonProperty("error")
    private boolean error;

    public static QuantityMeasurementDTO from(QuantityMeasurementEntity entity) {

        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        dto.setThisValue(entity.getThisValue());
        dto.setThisUnit(entity.getThisUnit());
        dto.setThisMeasurementType(entity.getThisMeasurementType());

        dto.setThatValue(entity.getThatValue());
        dto.setThatUnit(entity.getThatUnit());
        dto.setThatMeasurementType(entity.getThatMeasurementType());

        dto.setOperation(entity.getOperation());

        dto.setResultString(entity.getResultString());

        dto.setResultValue(entity.getResultValue());
        dto.setResultUnit(entity.getResultUnit());
        dto.setResultMeasurementType(entity.getResultMeasurementType());

        dto.setError(entity.isError());
        dto.setErrorMessage(entity.getErrorMessage());

        return dto;
    }

    public QuantityMeasurementEntity toEntity() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setThisValue(this.getThisValue());
        entity.setThisUnit(this.getThisUnit());
        entity.setThisMeasurementType(this.getThisMeasurementType());

        entity.setThatValue(this.getThatValue());
        entity.setThatUnit(this.getThatUnit());
        entity.setThatMeasurementType(this.getThatMeasurementType());

        entity.setOperation(this.getOperation());

        entity.setResultString(this.getResultString());

        entity.setResultValue(this.getResultValue());
        entity.setResultUnit(this.getResultUnit());
        entity.setResultMeasurementType(this.getResultMeasurementType());

        entity.setError(this.isError());
        entity.setErrorMessage(this.getErrorMessage());

        return entity;
    }

    public static List<QuantityMeasurementDTO> fromList(
            List<QuantityMeasurementEntity> entities) {

        return entities.stream()
                .map(QuantityMeasurementDTO::from)
                .collect(Collectors.toList());
    }

    public static List<QuantityMeasurementEntity> toEntityList(
            List<QuantityMeasurementDTO> dtos) {

        return dtos.stream()
                .map(QuantityMeasurementDTO::toEntity)
                .collect(Collectors.toList());
    }
}