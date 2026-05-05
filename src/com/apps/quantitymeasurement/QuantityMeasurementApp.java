package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // Inner class to represent Feet
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            // 1. Same reference
            if (this == obj) return true;

            // 2. Null check
            if (obj == null) return false;

            // 3. Type check
            if (getClass() != obj.getClass()) return false;

            // 4. Cast
            Feet other = (Feet) obj;

            // 5. Compare values
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // Main method (just for demo)
    public static void main(String[] args) {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        System.out.println("Are equal? " + f1.equals(f2));
    }
}