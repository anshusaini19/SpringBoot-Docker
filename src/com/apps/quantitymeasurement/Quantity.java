package com.apps.quantitymeasurement;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

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

    // =====================================
    // UC13 : ADD METHODS REFACTORED
    // =====================================

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

        validateArithmeticOperands(
                other,
                targetUnit,
                true
        );

        double resultBase =
                performBaseArithmetic(
                        other,
                        ArithmeticOperation.ADD
                );

        double result =
                targetUnit.convertFromBaseUnit(
                        resultBase
                );

        return new Quantity<>(
                result,
                targetUnit
        );
    }

    // =====================================================
    // UC13 NEW CODE START
    // Centralized Validation Helper (DRY)
    // =====================================================

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetUnitRequired
    ) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "Quantity cannot be null"
            );
        }

        if (targetUnitRequired && targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        if (unit.getClass() !=
                other.unit.getClass()) {

            throw new IllegalArgumentException(
                    "Different measurement categories"
            );
        }

        if (!Double.isFinite(value)
                || !Double.isFinite(other.value)) {

            throw new IllegalArgumentException(
                    "Values must be finite"
            );
        }
    }

    // =====================================================
    // UC13 NEW CODE END
    // =====================================================


    // =====================================================
    // UC13 NEW CODE START
    // Centralized Arithmetic Helper
    // =====================================================

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation
    ) {

        double thisBase =
                unit.convertToBaseUnit(value);

        double otherBase =
                other.unit.convertToBaseUnit(
                        other.value
                );

        return operation.compute(
                thisBase,
                otherBase
        );
    }

    // =====================================================
    // UC13 NEW CODE END
    // =====================================================
        // =====================================
    // UC13 : SUBTRACT METHODS REFACTORED
    // =====================================

    public Quantity<U> subtract(
            Quantity<U> other
    ) {

        return subtract(
                other,
                this.unit
        );
    }

    public Quantity<U> subtract(
            Quantity<U> other,
            U targetUnit
    ) {

        validateArithmeticOperands(
                other,
                targetUnit,
                true
        );

        double resultBase =
                performBaseArithmetic(
                        other,
                        ArithmeticOperation.SUBTRACT
                );

        double result =
                targetUnit.convertFromBaseUnit(
                        resultBase
                );

        return new Quantity<>(
                result,
                targetUnit
        );
    }

    // =====================================
    // UC13 : DIVIDE METHOD REFACTORED
    // =====================================

    public double divide(
            Quantity<U> other
    ) {

        validateArithmeticOperands(
                other,
                null,
                false
        );

        return performBaseArithmetic(
                other,
                ArithmeticOperation.DIVIDE
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

    // ==========================================================
    // UC13 NEW CODE START
    // Enum-based centralized arithmetic using Lambda Expressions
    // ==========================================================

    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {

            if (b == 0) {
                throw new ArithmeticException(
                        "Division by zero"
                );
            }

            return a / b;
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(
                DoubleBinaryOperator operator
        ) {

            this.operator = operator;
        }

        public double compute(
                double left,
                double right
        ) {

            return operator.applyAsDouble(
                    left,
                    right
            );
        }
    }

    // ==========================================================
    // UC13 NEW CODE END
    // ==========================================================
}