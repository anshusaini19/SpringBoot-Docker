package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Inches;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality_SameValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        assertTrue(f1.equals(f2));
    }

    @Test
    public void testFeetEquality_DifferentValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(2.0);

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testFeetEquality_NullComparison() {
        Feet f1 = new Feet(1.0);

        assertFalse(f1.equals(null));
    }

    @Test
    public void testFeetEquality_DifferentClass() {
        Feet f1 = new Feet(1.0);

        assertFalse(f1.equals("Not a Feet object"));
    }

    @Test
    public void testFeetEquality_SameReference() {
        Feet f1 = new Feet(1.0);

        assertTrue(f1.equals(f1));
    }
    @Test
public void testInchesEquality_SameValue() {
    Inches i1 = new Inches(12.0);
    Inches i2 = new Inches(12.0);

    assertTrue(i1.equals(i2));
}

@Test
public void testInchesEquality_DifferentValue() {
    Inches i1 = new Inches(12.0);
    Inches i2 = new Inches(24.0);

    assertFalse(i1.equals(i2));
}

@Test
public void testFeetToInchesEquality() {
    Feet f = new Feet(1.0);
    Inches i = new Inches(12.0);

    assertTrue(f.equals(i));
}

@Test
public void testInchesToFeetEquality() {
    Inches i = new Inches(12.0);
    Feet f = new Feet(1.0);

    assertTrue(i.equals(f));
}
}