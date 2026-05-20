package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // EPSILON FOR FLOATING POINT COMPARISON
    private static final double EPSILON = 0.0001;

    // ENUM FOR LENGTH UNITS
    

    // GENERIC LENGTH CLASS
    public static class Length {

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

            // RESULT IN FIRST OPERAND UNIT
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