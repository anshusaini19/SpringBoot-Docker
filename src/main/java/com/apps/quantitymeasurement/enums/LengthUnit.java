package com.apps.quantitymeasurement.enums;

import com.apps.quantitymeasurement.IMeasurable;

public enum LengthUnit implements IMeasurable {

    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return Math.round(
                value * conversionFactor * 100.0
        ) / 100.0;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return Math.round(
                (baseValue / conversionFactor) * 100.0
        ) / 100.0;
    }

    @Override
    public String getUnitName() {
        return name();
    }

    // ===========================
    // UC15 NEW
    // ===========================

    @Override
    public String getMeasurementType() {
        return "Length";
    }

    @Override
    public IMeasurable getUnitInstance(String unitName) {
        return LengthUnit.valueOf(unitName.toUpperCase());
    }
}