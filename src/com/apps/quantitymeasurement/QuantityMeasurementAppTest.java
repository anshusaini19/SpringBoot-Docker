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

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_InchesToInches_SameValue() {

        Length l1 = new Length(1.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_FeetToInches() {

        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches));
    }

    @Test
    public void testEquality_YardToFeet() {

        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);

        assertTrue(yard.equals(feet));
    }

    @Test
    public void testEquality_CmToInches() {

        Length cm = new Length(2.54, LengthUnit.CENTIMETERS);
        Length inch = new Length(1.0, LengthUnit.INCHES);

        assertTrue(cm.equals(inch));
    }

    @Test
    public void testEquality_NullComparison() {

        Length feet = new Length(1.0, LengthUnit.FEET);

        assertFalse(feet.equals(null));
    }

    @Test
    public void testEquality_DifferentClass() {

        Length feet = new Length(1.0, LengthUnit.FEET);

        assertFalse(feet.equals("Hello"));
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
    public void testConversion_CmToInches() {

        double result = Length.convert(
                2.54,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES
        );

        assertEquals(1.0, result, EPSILON);
    }

    @Test
    public void testConversion_RoundTrip() {

        double value = 5.0;

        double inch = Length.convert(
                value,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        double feet = Length.convert(
                inch,
                LengthUnit.INCHES,
                LengthUnit.FEET
        );

        assertEquals(value, feet, EPSILON);
    }

    @Test
    public void testConversion_InvalidUnit() {

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

    // =========================
    // UC6 ADDITION TESTS
    // =========================

    @Test
    public void testAddition_FeetPlusFeet() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertTrue(
                result.equals(
                        new Length(
                                3.0,
                                LengthUnit.FEET
                        )
                )
        );
    }

    @Test
    public void testAddition_FeetPlusInches() {

        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        Length result = feet.add(inches);

        assertTrue(
                result.equals(
                        new Length(
                                2.0,
                                LengthUnit.FEET
                        )
                )
        );
    }

    @Test
    public void testAddition_InchesPlusFeet() {

        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length feet = new Length(1.0, LengthUnit.FEET);

        Length result = inches.add(feet);

        assertTrue(
                result.equals(
                        new Length(
                                24.0,
                                LengthUnit.INCHES
                        )
                )
        );
    }

    @Test
    public void testAddition_YardPlusFeet() {

        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);

        Length result = yard.add(feet);

        assertTrue(
                result.equals(
                        new Length(
                                2.0,
                                LengthUnit.YARDS
                        )
                )
        );
    }

    @Test
    public void testAddition_WithZero() {

        Length feet = new Length(5.0, LengthUnit.FEET);
        Length zero = new Length(0.0, LengthUnit.INCHES);

        Length result = feet.add(zero);

        assertTrue(
                result.equals(
                        new Length(
                                5.0,
                                LengthUnit.FEET
                        )
                )
        );
    }

    @Test
    public void testAddition_NullOperand() {

        Length feet = new Length(1.0, LengthUnit.FEET);

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> feet.add(null)
        );

        assertEquals(
                "Length cannot be null",
                exception.getMessage()
        );
    }

    // =========================
    // UC7 EXPLICIT TARGET UNIT TESTS
    // =========================

    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length inches =
                new Length(12.0, LengthUnit.INCHES);

        Length result =
                feet.add(
                        inches,
                        LengthUnit.FEET
                );

        assertTrue(
                result.equals(
                        new Length(
                                2.0,
                                LengthUnit.FEET
                        )
                )
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length inches =
                new Length(12.0, LengthUnit.INCHES);

        Length result =
                feet.add(
                        inches,
                        LengthUnit.INCHES
                );

        assertTrue(
                result.equals(
                        new Length(
                                24.0,
                                LengthUnit.INCHES
                        )
                )
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length inches =
                new Length(12.0, LengthUnit.INCHES);

        Length result =
                feet.add(
                        inches,
                        LengthUnit.YARDS
                );

        assertTrue(
                result.equals(
                        new Length(
                                0.666666,
                                LengthUnit.YARDS
                        )
                )
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters() {

        Length inch1 =
                new Length(
                        1.0,
                        LengthUnit.INCHES
                );

        Length inch2 =
                new Length(
                        1.0,
                        LengthUnit.INCHES
                );

        Length result =
                inch1.add(
                        inch2,
                        LengthUnit.CENTIMETERS
                );

        assertTrue(
                result.equals(
                        new Length(
                                5.08,
                                LengthUnit.CENTIMETERS
                        )
                )
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length inches =
                new Length(12.0, LengthUnit.INCHES);

        Length result1 =
                feet.add(
                        inches,
                        LengthUnit.YARDS
                );

        Length result2 =
                inches.add(
                        feet,
                        LengthUnit.YARDS
                );

        assertTrue(result1.equals(result2));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_WithZero() {

        Length feet =
                new Length(5.0, LengthUnit.FEET);

        Length zero =
                new Length(0.0, LengthUnit.INCHES);

        Length result =
                feet.add(
                        zero,
                        LengthUnit.YARDS
                );

        assertTrue(
                result.equals(
                        new Length(
                                1.666666,
                                LengthUnit.YARDS
                        )
                )
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValue() {

        Length l1 =
                new Length(5.0, LengthUnit.FEET);

        Length l2 =
                new Length(-2.0, LengthUnit.FEET);

        Length result =
                l1.add(
                        l2,
                        LengthUnit.INCHES
                );

        assertTrue(
                result.equals(
                        new Length(
                                36.0,
                                LengthUnit.INCHES
                        )
                )
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NullTargetUnit() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length inches =
                new Length(12.0, LengthUnit.INCHES);

        Exception exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> feet.add(
                                inches,
                                null
                        )
                );

        assertEquals(
                "Target unit cannot be null",
                exception.getMessage()
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {

        Length l1 =
                new Length(1000.0, LengthUnit.FEET);

        Length l2 =
                new Length(500.0, LengthUnit.FEET);

        Length result =
                l1.add(
                        l2,
                        LengthUnit.INCHES
                );

        assertTrue(
                result.equals(
                        new Length(
                                18000.0,
                                LengthUnit.INCHES
                        )
                )
        );
    }
    // SAME AS FIRST OPERAND
@Test
public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {

    Length yard =
            new Length(
                    2.0,
                    LengthUnit.YARDS
            );

    Length feet =
            new Length(
                    3.0,
                    LengthUnit.FEET
            );

    Length result =
            yard.add(
                    feet,
                    LengthUnit.YARDS
            );

    assertTrue(
            result.equals(
                    new Length(
                            3.0,
                            LengthUnit.YARDS
                    )
            )
    );
}


// SAME AS SECOND OPERAND
@Test
public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {

    Length yard =
            new Length(
                    2.0,
                    LengthUnit.YARDS
            );

    Length feet =
            new Length(
                    3.0,
                    LengthUnit.FEET
            );

    Length result =
            yard.add(
                    feet,
                    LengthUnit.FEET
            );

    assertTrue(
            result.equals(
                    new Length(
                            9.0,
                            LengthUnit.FEET
                    )
            )
    );
}


// SMALL TO LARGE SCALE
@Test
public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {

    Length inch1 =
            new Length(
                    12.0,
                    LengthUnit.INCHES
            );

    Length inch2 =
            new Length(
                    12.0,
                    LengthUnit.INCHES
            );

    Length result =
            inch1.add(
                    inch2,
                    LengthUnit.YARDS
            );

    assertTrue(
            result.equals(
                    new Length(
                            0.666666,
                            LengthUnit.YARDS
                    )
            )
    );
}


// PRECISION TOLERANCE
@Test
public void testAddition_ExplicitTargetUnit_PrecisionTolerance() {

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

    Length result =
            cm.add(
                    inch,
                    LengthUnit.CENTIMETERS
            );

    Length expected =
            new Length(
                    5.08,
                    LengthUnit.CENTIMETERS
            );

    assertTrue(result.equals(expected));
}


// ALL UNIT COMBINATIONS
@Test
public void testAddition_ExplicitTargetUnit_AllUnitCombinations() {

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

    Length yards =
            new Length(
                    1.0,
                    LengthUnit.YARDS
            );

    Length cm =
            new Length(
                    2.54,
                    LengthUnit.CENTIMETERS
            );

    assertTrue(
            feet.add(
                    inches,
                    LengthUnit.FEET
            ).equals(
                    new Length(
                            2.0,
                            LengthUnit.FEET
                    )
            )
    );

    assertTrue(
            yards.add(
                    feet,
                    LengthUnit.YARDS
            ).equals(
                    new Length(
                            1.333333,
                            LengthUnit.YARDS
                    )
            )
    );

    assertTrue(
            cm.add(
                    inches,
                    LengthUnit.CENTIMETERS
            ).equals(
                    new Length(
                            33.02,
                            LengthUnit.CENTIMETERS
                    )
            )
    );
}
}