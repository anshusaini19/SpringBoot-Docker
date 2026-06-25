package com.apps.quantitymeasurement;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS,
    FAHRENHEIT,
    KELVIN;

    // =====================================================
    // UC14 NEW
    // Temperature does NOT support arithmetic
    // =====================================================

    private final SupportsArithmetic supportsArithmetic =
            () -> false;

    @Override
    public String getUnitName() {

        switch (this) {

            case CELSIUS:
                return "Celsius";

            case FAHRENHEIT:
                return "Fahrenheit";

            case KELVIN:
                return "Kelvin";

            default:
                return "";
        }
    }

    @Override
    public double getConversionFactor() {

        // Temperature conversions are non-linear,
        // so conversion factor is not used.
        return 1.0;
    }

    // =====================================================
    // Convert to Base Unit (Celsius)
    // =====================================================

    @Override
    public double convertToBaseUnit(double value) {

        switch (this) {

            case CELSIUS:
                return value;

            case FAHRENHEIT:
                return (value - 32) * 5 / 9;

            case KELVIN:
                return value - 273.15;

            default:
                return value;
        }
    }

    // =====================================================
    // Convert from Base Unit (Celsius)
    // =====================================================

    @Override
    public double convertFromBaseUnit(double baseValue) {

        switch (this) {

            case CELSIUS:
                return baseValue;

            case FAHRENHEIT:
                return (baseValue * 9 / 5) + 32;

            case KELVIN:
                return baseValue + 273.15;

            default:
                return baseValue;
        }
    }

    // =====================================================
    // UC14 NEW
    // Special temperature conversion helper
    // =====================================================

    public double convertTo(
            double value,
            TemperatureUnit targetUnit
    ) {

        double celsius =
                convertToBaseUnit(value);

        return targetUnit.convertFromBaseUnit(
                celsius
        );
    }

    // =====================================================
    // UC14 NEW
    // Arithmetic Support
    // =====================================================

    @Override
    public boolean supportsArithmetic() {

        return supportsArithmetic.isSupported();
    }

    @Override
    public void validateOperationSupport(
            String operation
    ) {

        if (!supportsArithmetic()) {

            throw new UnsupportedOperationException(
                    "Temperature does not support "
                            + operation
                            + " operation."
            );
        }
    }
    // =====================================================
    // UC15 NEW
    // =====================================================

    @Override
    public String getMeasurementType() {
        return "Temperature";
    }

    @Override
    public IMeasurable getUnitInstance(String unitName) {
        return TemperatureUnit.valueOf(unitName.toUpperCase());
    }

    @Override
    public String toString() {

        return getUnitName();
    }
}