/*package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Length;
import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest {

    // FEET TO FEET EQUALITY
    @Test
    public void testEquality_FeetToFeet_SameValue() {

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(1.0, LengthUnit.FEET);

        assertTrue(length1.equals(length2));
    }

    // INCH TO INCH EQUALITY
    @Test
    public void testEquality_InchToInch_SameValue() {

        Length length1 = new Length(1.0, LengthUnit.INCHES);
        Length length2 = new Length(1.0, LengthUnit.INCHES);

        assertTrue(length1.equals(length2));
    }

    // FEET TO INCHES EQUALITY
    @Test
    public void testEquality_FeetToInch_EquivalentValue() {

        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches));
    }

    // INCHES TO FEET EQUALITY
    @Test
    public void testEquality_InchToFeet_EquivalentValue() {

        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length feet = new Length(1.0, LengthUnit.FEET);

        assertTrue(inches.equals(feet));
    }

    // FEET INEQUALITY
    @Test
    public void testEquality_FeetToFeet_DifferentValue() {

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(2.0, LengthUnit.FEET);

        assertFalse(length1.equals(length2));
    }

    // INCH INEQUALITY
    @Test
    public void testEquality_InchToInch_DifferentValue() {

        Length length1 = new Length(1.0, LengthUnit.INCHES);
        Length length2 = new Length(2.0, LengthUnit.INCHES);

        assertFalse(length1.equals(length2));
    }

    // SAME REFERENCE
    @Test
    public void testEquality_SameReference() {

        Length length = new Length(1.0, LengthUnit.FEET);

        assertTrue(length.equals(length));
    }

    // NULL COMPARISON
    @Test
    public void testEquality_NullComparison() {

        Length length = new Length(1.0, LengthUnit.FEET);

        assertFalse(length.equals(null));
    }

    // DIFFERENT CLASS
    @Test
    public void testEquality_DifferentClass() {

        Length length = new Length(1.0, LengthUnit.FEET);

        assertFalse(length.equals("Not a Length object"));
    }

    // NULL UNIT CHECK
    @Test
    public void testEquality_NullUnit() {

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Length(1.0, null)
        );

        assertEquals("Unit cannot be null", exception.getMessage());
    }
}*/

package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Length;
import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    // FEET TO FEET EQUALITY
    @Test
    public void testEquality_FeetToFeet_SameValue() {

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(1.0, LengthUnit.FEET);

        assertTrue(length1.equals(length2));
    }

    // INCH TO INCH EQUALITY
    @Test
    public void testEquality_InchToInch_SameValue() {

        Length length1 = new Length(1.0, LengthUnit.INCHES);
        Length length2 = new Length(1.0, LengthUnit.INCHES);

        assertTrue(length1.equals(length2));
    }

    // FEET TO INCHES EQUALITY
    @Test
    public void testEquality_FeetToInch_EquivalentValue() {

        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches));
    }

    // INCHES TO FEET EQUALITY
    @Test
    public void testEquality_InchToFeet_EquivalentValue() {

        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length feet = new Length(1.0, LengthUnit.FEET);

        assertTrue(inches.equals(feet));
    }

    // FEET INEQUALITY
    @Test
    public void testEquality_FeetToFeet_DifferentValue() {

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(2.0, LengthUnit.FEET);

        assertFalse(length1.equals(length2));
    }

    // INCH INEQUALITY
    @Test
    public void testEquality_InchToInch_DifferentValue() {

        Length length1 = new Length(1.0, LengthUnit.INCHES);
        Length length2 = new Length(2.0, LengthUnit.INCHES);

        assertFalse(length1.equals(length2));
    }

    // YARD TO FEET EQUALITY
    @Test
    public void testEquality_YardToFeet_EquivalentValue() {

        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);

        assertTrue(yard.equals(feet));
    }

    // YARD TO INCHES EQUALITY
    @Test
    public void testEquality_YardToInches_EquivalentValue() {

        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length inches = new Length(36.0, LengthUnit.INCHES);

        assertTrue(yard.equals(inches));
    }

    // CENTIMETERS TO INCHES EQUALITY
    @Test
    public void testEquality_CentimetersToInches_EquivalentValue() {

        Length centimeters = new Length(2.54, LengthUnit.CENTIMETERS);
        Length inches = new Length(1.0, LengthUnit.INCHES);

        assertTrue(centimeters.equals(inches));
    }

    // CENTIMETERS TO FEET EQUALITY
    @Test
    public void testEquality_CentimetersToFeet_EquivalentValue() {

        Length centimeters = new Length(30.48, LengthUnit.CENTIMETERS);
        Length feet = new Length(1.0, LengthUnit.FEET);

        assertTrue(centimeters.equals(feet));
    }

    // SAME REFERENCE
    @Test
    public void testEquality_SameReference() {

        Length length = new Length(1.0, LengthUnit.FEET);

        assertTrue(length.equals(length));
    }

    // NULL COMPARISON
    @Test
    public void testEquality_NullComparison() {

        Length length = new Length(1.0, LengthUnit.FEET);

        assertFalse(length.equals(null));
    }

    // DIFFERENT CLASS
    @Test
    public void testEquality_DifferentClass() {

        Length length = new Length(1.0, LengthUnit.FEET);

        assertFalse(length.equals("Not a Length object"));
    }

    // NULL UNIT CHECK
    @Test
    public void testEquality_NullUnit() {

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Length(1.0, null)
        );

        assertEquals("Unit cannot be null", exception.getMessage());
    }

    // FEET TO INCHES CONVERSION
    @Test
    public void testConversion_FeetToInches() {

        double result = Length.convert(
                1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        assertEquals(12.0, result, EPSILON);
    }

    // INCHES TO FEET CONVERSION
    @Test
    public void testConversion_InchesToFeet() {

        double result = Length.convert(
                24.0,
                LengthUnit.INCHES,
                LengthUnit.FEET
        );

        assertEquals(2.0, result, EPSILON);
    }

    // YARDS TO INCHES CONVERSION
    @Test
    public void testConversion_YardsToInches() {

        double result = Length.convert(
                1.0,
                LengthUnit.YARDS,
                LengthUnit.INCHES
        );

        assertEquals(36.0, result, EPSILON);
    }

    // INCHES TO YARDS CONVERSION
    @Test
    public void testConversion_InchesToYards() {

        double result = Length.convert(
                72.0,
                LengthUnit.INCHES,
                LengthUnit.YARDS
        );

        assertEquals(2.0, result, EPSILON);
    }

    // CENTIMETERS TO INCHES CONVERSION
    @Test
    public void testConversion_CentimetersToInches() {

        double result = Length.convert(
                2.54,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES
        );

        assertEquals(1.0, result, EPSILON);
    }

    // FEET TO YARDS CONVERSION
    @Test
    public void testConversion_FeetToYards() {

        double result = Length.convert(
                6.0,
                LengthUnit.FEET,
                LengthUnit.YARDS
        );

        assertEquals(2.0, result, EPSILON);
    }

    // ROUND TRIP CONVERSION
    @Test
    public void testConversion_RoundTrip_PreservesValue() {

        double originalValue = 5.0;

        double converted = Length.convert(
                originalValue,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        double result = Length.convert(
                converted,
                LengthUnit.INCHES,
                LengthUnit.FEET
        );

        assertEquals(originalValue, result, EPSILON);
    }

    // ZERO VALUE CONVERSION
    @Test
    public void testConversion_ZeroValue() {

        double result = Length.convert(
                0.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        assertEquals(0.0, result, EPSILON);
    }

    // NEGATIVE VALUE CONVERSION
    @Test
    public void testConversion_NegativeValue() {

        double result = Length.convert(
                -1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        assertEquals(-12.0, result, EPSILON);
    }

    // INVALID UNIT CHECK
    @Test
    public void testConversion_InvalidUnit_Throws() {

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> Length.convert(
                        1.0,
                        null,
                        LengthUnit.FEET
                )
        );

        assertEquals(
                "Units cannot be null",
                exception.getMessage()
        );
    }

    // NAN VALUE CHECK
    @Test
    public void testConversion_NaNOrInfinite_Throws() {

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> Length.convert(
                        Double.NaN,
                        LengthUnit.FEET,
                        LengthUnit.INCHES
                )
        );

        assertEquals(
                "Value must be finite",
                exception.getMessage()
        );
    }

    // PRECISION TOLERANCE
    @Test
    public void testConversion_PrecisionTolerance() {

        double result = Length.convert(
                1.0,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES
        );

        assertEquals(0.393701, result, EPSILON);
    }
}