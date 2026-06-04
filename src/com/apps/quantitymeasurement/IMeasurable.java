package com.apps.quantitymeasurement;

public interface IMeasurable {

    // Conversion factor relative to base unit
    double getConversionFactor();

    // Convert value to base unit
    double convertToBaseUnit(double value);

    // Convert base unit value to this unit
    double convertFromBaseUnit(double baseValue);

    // Return unit name
    String getUnitName();
}