package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // DEMONSTRATE EQUALITY
    public static void demonstrateLengthEquality(
            Length length1,
            Length length2
    ) {

        System.out.println(
                "Are lengths equal? "
                        + length1.equals(length2)
        );
    }

    // OVERLOADED METHOD 1
    public static void demonstrateLengthConversion(
            double value,
            LengthUnit from,
            LengthUnit to
    ) {

        double result =
                Length.convert(
                        value,
                        from,
                        to
                );

        System.out.println(
                "Convert "
                        + value
                        + " "
                        + from
                        + " to "
                        + to
                        + " = "
                        + result
        );
    }

    // OVERLOADED METHOD 2
    public static void demonstrateLengthConversion(
            Length length,
            LengthUnit target
    ) {

        Length converted =
                length.convertTo(target);

        System.out.println(
                "Converted Length = "
                        + converted
        );
    }

    // UC6 ADDITION DEMO
    public static void demonstrateLengthAddition(
            Length length1,
            Length length2
    ) {

        Length result =
                length1.add(length2);

        System.out.println(
                length1
                        + " + "
                        + length2
                        + " = "
                        + result
        );
    }

    // UC7 ADDITION DEMO
    public static void demonstrateLengthAddition(
            Length length1,
            Length length2,
            LengthUnit targetUnit
    ) {

        Length result =
                length1.add(
                        length2,
                        targetUnit
                );

        System.out.println(
                length1
                        + " + "
                        + length2
                        + " = "
                        + result
        );
    }

    // MAIN METHOD
    public static void main(String[] args) {

        // UC5 CONVERSIONS
        demonstrateLengthConversion(
                1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        demonstrateLengthConversion(
                3.0,
                LengthUnit.YARDS,
                LengthUnit.FEET
        );

        // UC6 ADDITION
        Length feet =
                new Length(
                        1.0,
                        LengthUnit.FEET
                );

        Length inches =
                new Length(
                        12.0,
                        LengthUnit.INCHES
                );

        demonstrateLengthAddition(
                feet,
                inches
        );

        // UC7 ADDITION WITH TARGET UNIT

        // RESULT IN FEET
        demonstrateLengthAddition(
                feet,
                inches,
                LengthUnit.FEET
        );

        // RESULT IN INCHES
        demonstrateLengthAddition(
                feet,
                inches,
                LengthUnit.INCHES
        );

        // RESULT IN YARDS
        demonstrateLengthAddition(
                feet,
                inches,
                LengthUnit.YARDS
        );

        // YARDS + FEET
        Length yard =
                new Length(
                        1.0,
                        LengthUnit.YARDS
                );

        Length feet2 =
                new Length(
                        3.0,
                        LengthUnit.FEET
                );

        demonstrateLengthAddition(
                yard,
                feet2,
                LengthUnit.YARDS
        );

        // CM + INCH
        Length cm =
                new Length(
                        2.54,
                        LengthUnit.CENTIMETERS
                );

        Length inch =
                new Length(
                        1.0,
                        LengthUnit.INCHES
                );

        demonstrateLengthAddition(
                cm,
                inch,
                LengthUnit.CENTIMETERS
        );
    }
}