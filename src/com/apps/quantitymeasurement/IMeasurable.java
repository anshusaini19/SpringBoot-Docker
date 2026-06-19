package com.apps.quantitymeasurement;



public interface IMeasurable {

    // ==================================================
    // UC14 NEW
    // By default every measurable unit supports arithmetic.
    // TemperatureUnit will override this behavior.
    // ==================================================

    SupportsArithmetic supportsArithmetic = () -> true;

    // ==================================================
    // Existing mandatory methods
    // ==================================================

    // Conversion factor relative to base unit
    double getConversionFactor();

    // Convert value to base unit
    double convertToBaseUnit(double value);

    // Convert base unit value to this unit
    double convertFromBaseUnit(double baseValue);

    // Return unit name
    String getUnitName();

    // ==================================================
    // UC14 NEW
    // Default method to check whether arithmetic
    // operations are supported.
    // ==================================================

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    // ==================================================
    // UC14 NEW
    // Default validation method.
    // Existing measurement categories inherit this
    // implementation without any changes.
    // TemperatureUnit overrides this method.
    // ==================================================

    default void validateOperationSupport(String operation) {
        // Default implementation does nothing.
        // Length, Weight and Volume support arithmetic.
    }
}