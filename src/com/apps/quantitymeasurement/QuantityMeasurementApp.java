package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // DEMONSTRATE LENGTH EQUALITY
    public static boolean demonstrateLengthEquality(
        Length length1,
        Length length2
) {

    return length1.equals(length2);
}

    // LENGTH CONVERSION METHOD 1
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

    // LENGTH CONVERSION METHOD 2
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

    // UC6 LENGTH ADDITION
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

    // UC7 LENGTH ADDITION WITH TARGET UNIT
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

    // WEIGHT CONVERSION DEMO
    public static void demonstrateWeightConversion(
            double value,
            WeightUnit from,
            WeightUnit to
    ) {

        double result =
                Weight.convert(
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

    // WEIGHT ADDITION DEMO
    public static void demonstrateWeightAddition(
            Weight weight1,
            Weight weight2,
            WeightUnit targetUnit
    ) {

        Weight result =
                weight1.add(
                        weight2,
                        targetUnit
                );

        System.out.println(
                weight1
                        + " + "
                        + weight2
                        + " = "
                        + result
        );
    }

    // MAIN METHOD
    public static void main(String[] args) {

        // UC5 LENGTH CONVERSIONS
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

        // UC6 LENGTH ADDITION
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

        // UC9 WEIGHT CONVERSION
        demonstrateWeightConversion(
                1.0,
                WeightUnit.KILOGRAM,
                WeightUnit.GRAM
        );

        // UC9 WEIGHT ADDITION
        Weight kilogram =
                new Weight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        Weight gram =
                new Weight(
                        1000.0,
                        WeightUnit.GRAM
                );

        demonstrateWeightAddition(
                kilogram,
                gram,
                WeightUnit.KILOGRAM
        );
    }
}