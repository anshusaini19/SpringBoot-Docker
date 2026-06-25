package com.apps.quantitymeasurement;

public interface IMeasurable {

    // ==================================================
    // UC14
    // ==================================================

    SupportsArithmetic supportsArithmetic = () -> true;

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();

    // ==================================================
    // UC15 NEW
    // Returns measurement category
    // Example:
    // Length
    // Weight
    // Volume
    // Temperature
    // ==================================================

    String getMeasurementType();

    // ==================================================
    // UC15 NEW
    // Returns enum instance from unit name
    // Example:
    // "FEET" -> LengthUnit.FEET
    // ==================================================

    IMeasurable getUnitInstance(String unitName);

    // ==================================================
    // UC14
    // ==================================================

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    default void validateOperationSupport(String operation) {
        // Length, Weight and Volume inherit this.
        // Temperature overrides it.
    }
}