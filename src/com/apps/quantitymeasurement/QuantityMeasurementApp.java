/*package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // ENUM FOR LENGTH UNITS
    public enum LengthUnit {

        FEET(12.0),
        INCHES(1.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // GENERIC LENGTH CLASS
    public static class Length {

        private final double value;
        private final LengthUnit unit;

        public Length(double value, LengthUnit unit) {

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        // CONVERT TO BASE UNIT (INCHES)
        private double toBaseUnit() {
            return value * unit.getConversionFactor();
        }

        // EQUALS METHOD
        @Override
        public boolean equals(Object obj) {

            // SAME REFERENCE
            if (this == obj) {
                return true;
            }

            // NULL CHECK
            if (obj == null) {
                return false;
            }

            // TYPE CHECK
            if (getClass() != obj.getClass()) {
                return false;
            }

            // TYPE CASTING
            Length other = (Length) obj;

            // VALUE COMPARISON
            return Double.compare(
                    this.toBaseUnit(),
                    other.toBaseUnit()
            ) == 0;
        }
    }

    // MAIN METHOD
    public static void main(String[] args) {

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println(length1.equals(length2)); // true
    }
}*/

package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // EPSILON FOR FLOATING POINT COMPARISON
    private static final double EPSILON = 0.0001;

    // ENUM FOR LENGTH UNITS
    public enum LengthUnit {

        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // GENERIC LENGTH CLASS
    public static class Length {

        private final double value;
        private final LengthUnit unit;

        // CONSTRUCTOR
        public Length(double value, LengthUnit unit) {

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be finite");
            }

            this.value = value;
            this.unit = unit;
        }

        // CONVERT TO BASE UNIT (INCHES)
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
                throw new IllegalArgumentException("Units cannot be null");
            }

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be finite");
            }

            // CONVERT TO BASE UNIT
            double baseValue = value * source.getConversionFactor();

            // CONVERT TO TARGET UNIT
            return baseValue / target.getConversionFactor();
        }

        // INSTANCE CONVERSION METHOD
        public Length convertTo(LengthUnit targetUnit) {

            double convertedValue = convert(
                    this.value,
                    this.unit,
                    targetUnit
            );

            return new Length(convertedValue, targetUnit);
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

        double result = Length.convert(value, from, to);

        System.out.println(
                "Convert " + value + " " + from
                        + " to " + to
                        + " = " + result
        );
    }

    // OVERLOADED METHOD 2
    public static void demonstrateLengthConversion(
            Length length,
            LengthUnit target
    ) {

        Length converted = length.convertTo(target);

        System.out.println(
                "Converted Length = " + converted
        );
    }

    // MAIN METHOD
    public static void main(String[] args) {

        // FEET TO INCHES
        demonstrateLengthConversion(
                1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        // YARDS TO FEET
        demonstrateLengthConversion(
                3.0,
                LengthUnit.YARDS,
                LengthUnit.FEET
        );

        // INCHES TO YARDS
        demonstrateLengthConversion(
                36.0,
                LengthUnit.INCHES,
                LengthUnit.YARDS
        );

        // CM TO INCHES
        demonstrateLengthConversion(
                1.0,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES
        );

        // ZERO VALUE
        demonstrateLengthConversion(
                0.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        // INSTANCE METHOD
        Length yard = new Length(1.0, LengthUnit.YARDS);

        demonstrateLengthConversion(
                yard,
                LengthUnit.INCHES
        );
    }
}