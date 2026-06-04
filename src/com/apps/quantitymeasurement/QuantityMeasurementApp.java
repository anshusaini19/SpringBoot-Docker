package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // GENERIC EQUALITY DEMO
    public static <U extends IMeasurable> boolean demonstrateEquality(
            Quantity<U> quantity1,
            Quantity<U> quantity2
    ) {

        return quantity1.equals(quantity2);
    }

    // GENERIC CONVERSION DEMO
    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(
            Quantity<U> quantity,
            U targetUnit
    ) {

        return quantity.convertTo(targetUnit);
    }

    // UC6 STYLE ADDITION
    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> quantity1,
            Quantity<U> quantity2
    ) {

        return quantity1.add(quantity2);
    }

    // UC7 STYLE ADDITION WITH TARGET UNIT
    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> quantity1,
            Quantity<U> quantity2,
            U targetUnit
    ) {

        return quantity1.add(
                quantity2,
                targetUnit
        );
    }

    public static void main(String[] args) {

        // ==========================
        // LENGTH EXAMPLES
        // ==========================

        Quantity<LengthUnit> feet =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        Quantity<LengthUnit> inches =
                new Quantity<>(
                        12.0,
                        LengthUnit.INCHES
                );

        System.out.println(
                "Length Equality : "
                        + demonstrateEquality(
                        feet,
                        inches
                )
        );

        System.out.println(
                "Length Conversion : "
                        + demonstrateConversion(
                        feet,
                        LengthUnit.INCHES
                )
        );

        System.out.println(
                "Length Addition : "
                        + demonstrateAddition(
                        feet,
                        inches,
                        LengthUnit.FEET
                )
        );

        // ==========================
        // WEIGHT EXAMPLES
        // ==========================

        Quantity<WeightUnit> kilogram =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        Quantity<WeightUnit> gram =
                new Quantity<>(
                        1000.0,
                        WeightUnit.GRAM
                );

        System.out.println(
                "Weight Equality : "
                        + demonstrateEquality(
                        kilogram,
                        gram
                )
        );

        System.out.println(
                "Weight Conversion : "
                        + demonstrateConversion(
                        kilogram,
                        WeightUnit.GRAM
                )
        );

        System.out.println(
                "Weight Addition : "
                        + demonstrateAddition(
                        kilogram,
                        gram,
                        WeightUnit.KILOGRAM
                )
        );
    }
}