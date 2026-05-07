package com.apps.quantitymeasurement;

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
}