package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Length;
import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    // =========================
    // UC1 - UC4 EQUALITY TESTS
    // =========================

    @Test
    public void testEquality_FeetToFeet_SameValue() {

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(1.0, LengthUnit.FEET);

        assertTrue(length1.equals(length2));
    }

    @Test
    public void testEquality_InchToInch_SameValue() {

        Length length1 = new Length(1.0, LengthUnit.INCHES);
        Length length2 = new Length(1.0, LengthUnit.INCHES);

        assertTrue(length1.equals(length2));
    }

    @Test
    public void testEquality_FeetToInch_EquivalentValue() {

        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches));
    }

    @Test
    public void testEquality_InchToFeet_EquivalentValue() {

        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length feet = new Length(1.0, LengthUnit.FEET);

        assertTrue(inches.equals(feet));
    }

    @Test
    public void testEquality_FeetToFeet_DifferentValue() {

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(2.0, LengthUnit.FEET);

        assertFalse(length1.equals(length2));
    }

    @Test
    public void testEquality_InchToInch_DifferentValue() {

        Length length1 = new Length(1.0, LengthUnit.INCHES);
        Length length2 = new Length(2.0, LengthUnit.INCHES);

        assertFalse(length1.equals(length2));
    }

    @Test
    public void testEquality_YardToFeet_EquivalentValue() {

        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);

        assertTrue(yard.equals(feet));
    }

    @Test
    public void testEquality_YardToInches_EquivalentValue() {

        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length inches = new Length(36.0, LengthUnit.INCHES);

        assertTrue(yard.equals(inches));
    }

    @Test
    public void testEquality_CentimetersToInches_EquivalentValue() {

        Length centimeters = new Length(2.54, LengthUnit.CENTIMETERS);
        Length inches = new Length(1.0, LengthUnit.INCHES);

        assertTrue(centimeters.equals(inches));
    }

    @Test
    public void testEquality_CentimetersToFeet_EquivalentValue() {

        Length centimeters = new Length(30.48, LengthUnit.CENTIMETERS);
        Length feet = new Length(1.0, LengthUnit.FEET);

        assertTrue(centimeters.equals(feet));
    }

    @Test
    public void testEquality_SameReference() {

        Length length = new Length(1.0, LengthUnit.FEET);

        assertTrue(length.equals(length));
    }

    @Test
    public void testEquality_NullComparison() {

        Length length = new Length(1.0, LengthUnit.FEET);

        assertFalse(length.equals(null));
    }

    @Test
    public void testEquality_DifferentClass() {

        Length length = new Length(1.0, LengthUnit.FEET);

        assertFalse(length.equals("Not a Length object"));
    }

    @Test
    public void testEquality_NullUnit() {

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Length(1.0, null)
        );

        assertEquals("Unit cannot be null", exception.getMessage());
    }

    // =========================
    // UC5 CONVERSION TESTS
    // =========================

    @Test
    public void testConversion_FeetToInches() {

        double result = Length.convert(
                1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        assertEquals(12.0, result, EPSILON);
    }

    @Test
    public void testConversion_InchesToFeet() {

        double result = Length.convert(
                24.0,
                LengthUnit.INCHES,
                LengthUnit.FEET
        );

        assertEquals(2.0, result, EPSILON);
    }

    @Test
    public void testConversion_YardsToInches() {

        double result = Length.convert(
                1.0,
                LengthUnit.YARDS,
                LengthUnit.INCHES
        );

        assertEquals(36.0, result, EPSILON);
    }

    @Test
    public void testConversion_InchesToYards() {

        double result = Length.convert(
                72.0,
                LengthUnit.INCHES,
                LengthUnit.YARDS
        );

        assertEquals(2.0, result, EPSILON);
    }

    @Test
    public void testConversion_CentimetersToInches() {

        double result = Length.convert(
                2.54,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES
        );

        assertEquals(1.0, result, EPSILON);
    }

    @Test
    public void testConversion_FeetToYards() {

        double result = Length.convert(
                6.0,
                LengthUnit.FEET,
                LengthUnit.YARDS
        );

        assertEquals(2.0, result, EPSILON);
    }

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

    @Test
    public void testConversion_ZeroValue() {

        double result = Length.convert(
                0.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        assertEquals(0.0, result, EPSILON);
    }

    @Test
    public void testConversion_NegativeValue() {

        double result = Length.convert(
                -1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        assertEquals(-12.0, result, EPSILON);
    }

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

    @Test
    public void testConversion_PrecisionTolerance() {

        double result = Length.convert(
                1.0,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES
        );

        assertEquals(0.393701, result, EPSILON);
    }

    // =========================
    // UC6 ADDITION TESTS
    // =========================

    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertTrue(result.equals(
                new Length(3.0, LengthUnit.FEET)
        ));
    }

    @Test
    public void testAddition_SameUnit_InchPlusInch() {

        Length l1 = new Length(6.0, LengthUnit.INCHES);
        Length l2 = new Length(6.0, LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertTrue(result.equals(
                new Length(12.0, LengthUnit.INCHES)
        ));
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {

        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        Length result = feet.add(inches);

        assertTrue(result.equals(
                new Length(2.0, LengthUnit.FEET)
        ));
    }

    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {

        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length feet = new Length(1.0, LengthUnit.FEET);

        Length result = inches.add(feet);

        assertTrue(result.equals(
                new Length(24.0, LengthUnit.INCHES)
        ));
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet() {

        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);

        Length result = yard.add(feet);

        assertTrue(result.equals(
                new Length(2.0, LengthUnit.YARDS)
        ));
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch() {

        Length cm = new Length(2.54, LengthUnit.CENTIMETERS);
        Length inch = new Length(1.0, LengthUnit.INCHES);

        Length result = cm.add(inch);

        assertTrue(result.equals(
                new Length(5.08, LengthUnit.CENTIMETERS)
        ));
    }

    @Test
    public void testAddition_Commutativity() {

        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        assertTrue(feet.add(inches).equals(
                new Length(2.0, LengthUnit.FEET)
        ));

        assertTrue(inches.add(feet).equals(
                new Length(24.0, LengthUnit.INCHES)
        ));
    }

    @Test
    public void testAddition_WithZero() {

        Length feet = new Length(5.0, LengthUnit.FEET);
        Length zero = new Length(0.0, LengthUnit.INCHES);

        Length result = feet.add(zero);

        assertTrue(result.equals(
                new Length(5.0, LengthUnit.FEET)
        ));
    }

    @Test
    public void testAddition_NegativeValues() {

        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(-2.0, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertTrue(result.equals(
                new Length(3.0, LengthUnit.FEET)
        ));
    }

    @Test
    public void testAddition_NullSecondOperand() {

        Length l1 = new Length(1.0, LengthUnit.FEET);

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> l1.add(null)
        );

        assertEquals(
                "Length cannot be null",
                exception.getMessage()
        );
    }

    @Test
    public void testAddition_LargeValues() {

        Length l1 = new Length(1e6, LengthUnit.FEET);
        Length l2 = new Length(1e6, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertTrue(result.equals(
                new Length(2e6, LengthUnit.FEET)
        ));
    }

    @Test
    public void testAddition_SmallValues() {

        Length l1 = new Length(0.001, LengthUnit.FEET);
        Length l2 = new Length(0.002, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertTrue(result.equals(
                new Length(0.003, LengthUnit.FEET)
        ));
    }
}