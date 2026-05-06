package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // FEET CLASS
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double toInches() {
            return this.value * 12;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (obj == null) return false;

            // Compare with Feet
            if (obj instanceof Feet) {
                Feet other = (Feet) obj;
                return Double.compare(this.value, other.value) == 0;
            }

            // Compare with Inches
            if (obj instanceof Inches) {
                Inches other = (Inches) obj;
                return Double.compare(this.toInches(), other.value) == 0;
            }

            return false;
        }
    }

    // INCHES CLASS (NEW)
    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        public double toFeet() {
            return this.value / 12;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (obj == null) return false;

            // Compare with Inches
            if (obj instanceof Inches) {
                Inches other = (Inches) obj;
                return Double.compare(this.value, other.value) == 0;
            }

            // Compare with Feet
            if (obj instanceof Feet) {
                Feet other = (Feet) obj;
                return Double.compare(this.value, other.toInches()) == 0;
            }

            return false;
        }
    }

    public static void main(String[] args) {
        Feet f = new Feet(1);
        Inches i = new Inches(12);

        System.out.println(f.equals(i)); // true
    }
}