package com.apps.quantitymeasurement;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

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

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double baseValue =
                unit.convertToBaseUnit(value);

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(
                convertedValue,
                targetUnit
        );
    }

    public Quantity<U> add(
            Quantity<U> other
    ) {

        return add(
                other,
                this.unit
        );
    }

    public Quantity<U> add(
            Quantity<U> other,
            U targetUnit
    ) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "Quantity cannot be null"
            );
        }

        double thisBase =
                unit.convertToBaseUnit(value);

        double otherBase =
                other.unit.convertToBaseUnit(
                        other.value
                );

        double sumBase =
                thisBase + otherBase;

        double result =
                targetUnit.convertFromBaseUnit(
                        sumBase
                );

        return new Quantity<>(
                result,
                targetUnit
        );
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null ||
                getClass() != obj.getClass()) {
            return false;
        }

        Quantity<?> that =
                (Quantity<?>) obj;

        if (this.unit.getClass() !=
                that.unit.getClass()) {
            return false;
        }

        double thisBase =
                unit.convertToBaseUnit(value);

        double thatBase =
                that.unit.convertToBaseUnit(
                        that.value
                );

        return Double.compare(
                thisBase,
                thatBase
        ) == 0;
    }

    @Override
    public int hashCode() {

        double baseValue =
                unit.convertToBaseUnit(value);

        return Objects.hash(
                baseValue,
                unit.getClass()
        );
    }

    @Override
    public String toString() {

        return "Quantity(" +
                value +
                ", " +
                unit.getUnitName() +
                ")";
    }
}