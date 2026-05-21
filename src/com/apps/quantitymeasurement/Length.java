package com.apps.quantitymeasurement;

public class Length {

    // EPSILON
    private static final double EPSILON = 0.0001;

    private final double value;
    private final LengthUnit unit;

    // CONSTRUCTOR
    public Length(double value, LengthUnit unit) {

        if (unit == null) {
            throw new IllegalArgumentException(
                    "Unit cannot be null"
            );
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Value must be finite"
            );
        }

        this.value = value;
        this.unit = unit;
    }

    // CONVERT TO BASE UNIT
    private double convertToBaseUnit() {
        return value * unit.getConversionFactor();
    }

    // STATIC CONVERSION METHOD
    public static double convert(
            double value,
            LengthUnit source,
            LengthUnit target
    ) {

        if (source == null || target == null) {
            throw new IllegalArgumentException(
                    "Units cannot be null"
            );
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Value must be finite"
            );
        }

        // CONVERT TO BASE UNIT
        double baseValue =
                value *
                source.getConversionFactor();

        // CONVERT TO TARGET UNIT
        return baseValue /
                target.getConversionFactor();
    }

    // INSTANCE CONVERSION METHOD
    public Length convertTo(
            LengthUnit targetUnit
    ) {

        double convertedValue =
                convert(
                        this.value,
                        this.unit,
                        targetUnit
                );

        return new Length(
                convertedValue,
                targetUnit
        );
    }

    // UC6 ADD METHOD
    public Length add(Length other) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "Length cannot be null"
            );
        }

        return performAddition(
                other,
                this.unit
        );
    }

    // UC7 ADD METHOD
    public Length add(
            Length other,
            LengthUnit targetUnit
    ) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "Length cannot be null"
            );
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        return performAddition(
                other,
                targetUnit
        );
    }

    // PRIVATE UTILITY METHOD
    private Length performAddition(
            Length other,
            LengthUnit targetUnit
    ) {

        // CONVERT BOTH TO BASE UNIT
        double thisBase =
                this.convertToBaseUnit();

        double otherBase =
                other.convertToBaseUnit();

        // ADD
        double sumBase =
                thisBase + otherBase;

        // CONVERT TO TARGET UNIT
        double result =
                sumBase /
                targetUnit.getConversionFactor();

        return new Length(
                result,
                targetUnit
        );
    }

    // EQUALS METHOD
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Length other = (Length) obj;

        return Math.abs(
                this.convertToBaseUnit()
                        - other.convertToBaseUnit()
        ) < EPSILON;
    }

    // TOSTRING METHOD
    @Override
    public String toString() {
        return value + " " + unit;
    }
}