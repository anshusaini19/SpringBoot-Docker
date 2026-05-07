package com.apps.quantitymeasurement;

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
}