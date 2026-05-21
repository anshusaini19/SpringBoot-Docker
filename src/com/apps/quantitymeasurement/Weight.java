package com.apps.quantitymeasurement;

public class Weight {

    private static final double EPSILON = 0.0001;

    private final double value;
    private final WeightUnit unit;

    // CONSTRUCTOR
    public Weight(double value, WeightUnit unit) {

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

        return value *
                unit.getConversionFactor();
    }

    // STATIC CONVERT METHOD
    public static double convert(
            double value,
            WeightUnit source,
            WeightUnit target
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

        double baseValue =
                value *
                source.getConversionFactor();

        return baseValue /
                target.getConversionFactor();
    }

    // INSTANCE CONVERT METHOD
    public Weight convertTo(
            WeightUnit targetUnit
    ) {

        double convertedValue =
                convert(
                        this.value,
                        this.unit,
                        targetUnit
                );

        return new Weight(
                convertedValue,
                targetUnit
        );
    }

    // ADD METHOD
    public Weight add(Weight other) {

        if (other == null) {

            throw new IllegalArgumentException(
                    "Weight cannot be null"
            );
        }

        return performAddition(
                other,
                this.unit
        );
    }

    // ADD WITH TARGET UNIT
    public Weight add(
            Weight other,
            WeightUnit targetUnit
    ) {

        if (other == null) {

            throw new IllegalArgumentException(
                    "Weight cannot be null"
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

    // PRIVATE HELPER METHOD
    private Weight performAddition(
            Weight other,
            WeightUnit targetUnit
    ) {

        double thisBase =
                this.convertToBaseUnit();

        double otherBase =
                other.convertToBaseUnit();

        double sumBase =
                thisBase + otherBase;

        double result =
                sumBase /
                targetUnit.getConversionFactor();

        return new Weight(
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

        Weight other = (Weight) obj;

        return Math.abs(
                this.convertToBaseUnit()
                        - other.convertToBaseUnit()
        ) < EPSILON;
    }

    // TOSTRING
    @Override
    public String toString() {

        return value + " " + unit;
    }
}