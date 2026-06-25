package com.apps.quantitymeasurement.entity;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private QuantityDTO firstQuantity;
    private QuantityDTO secondQuantity;
    private String operation;
    private QuantityDTO result;
    private boolean error;
    private String errorMessage;

    public QuantityMeasurementEntity() {
    }

    public QuantityMeasurementEntity(
            QuantityDTO firstQuantity,
            QuantityDTO secondQuantity,
            String operation,
            QuantityDTO result) {

        this.firstQuantity = firstQuantity;
        this.secondQuantity = secondQuantity;
        this.operation = operation;
        this.result = result;
        this.error = false;
    }

    public QuantityMeasurementEntity(
            String operation,
            String errorMessage) {

        this.operation = operation;
        this.errorMessage = errorMessage;
        this.error = true;
    }

    public QuantityDTO getFirstQuantity() {
        return firstQuantity;
    }

    public void setFirstQuantity(QuantityDTO firstQuantity) {
        this.firstQuantity = firstQuantity;
    }

    public QuantityDTO getSecondQuantity() {
        return secondQuantity;
    }

    public void setSecondQuantity(QuantityDTO secondQuantity) {
        this.secondQuantity = secondQuantity;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public QuantityDTO getResult() {
        return result;
    }

    public void setResult(QuantityDTO result) {
        this.result = result;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "QuantityMeasurementEntity{" +
                "operation='" + operation + '\'' +
                ", result=" + result +
                ", error=" + error +
                '}';
    }

}