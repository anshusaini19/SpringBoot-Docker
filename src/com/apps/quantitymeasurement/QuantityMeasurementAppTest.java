package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // =========================
    // UC1 / UC2 LENGTH EQUALITY TESTS
    // =========================

    @Test
    public void testFeetEquality() {

        Length feet1 =
                new Length(
                        1.0,
                        LengthUnit.FEET
                );

        Length feet2 =
                new Length(
                        1.0,
                        LengthUnit.FEET
                );

        assertTrue(feet1.equals(feet2));
    }

    @Test
    public void testInchesEquality() {

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

        assertTrue(inch1.equals(inch2));
    }

    @Test
    public void testFeetInchesComparison() {

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

        assertTrue(feet.equals(inches));
    }

    @Test
    public void testFeetInequality() {

        Length feet1 =
                new Length(
                        1.0,
                        LengthUnit.FEET
                );

        Length feet2 =
                new Length(
                        2.0,
                        LengthUnit.FEET
                );

        assertFalse(feet1.equals(feet2));
    }

    @Test
    public void testInchesInequality() {

        Length inch1 =
                new Length(
                        12.0,
                        LengthUnit.INCHES
                );

        Length inch2 =
                new Length(
                        24.0,
                        LengthUnit.INCHES
                );

        assertFalse(inch1.equals(inch2));
    }

    @Test
    public void testCrossUnitInequality() {

        Length feet =
                new Length(
                        1.0,
                        LengthUnit.FEET
                );

        Length inches =
                new Length(
                        24.0,
                        LengthUnit.INCHES
                );

        assertFalse(feet.equals(inches));
    }

    @Test
    public void testMultipleFeetComparison() {

        Length feet1 =
                new Length(
                        3.0,
                        LengthUnit.FEET
                );

        Length yard =
                new Length(
                        1.0,
                        LengthUnit.YARDS
                );

        assertTrue(feet1.equals(yard));
    }

    @Test
    public void yardEquals36Inches() {

        Length yard =
                new Length(
                        1.0,
                        LengthUnit.YARDS
                );

        Length inches =
                new Length(
                        36.0,
                        LengthUnit.INCHES
                );

        assertTrue(yard.equals(inches));
    }

    @Test
    public void centimeterEquals39Point3701Inches() {

        Length cm =
                new Length(
                        100.0,
                        LengthUnit.CENTIMETERS
                );

        Length inches =
                new Length(
                        39.3701,
                        LengthUnit.INCHES
                );

        assertTrue(cm.equals(inches));
    }

    @Test
    public void threeFeetEqualsOneYard() {

        Length feet =
                new Length(
                        3.0,
                        LengthUnit.FEET
                );

        Length yard =
                new Length(
                        1.0,
                        LengthUnit.YARDS
                );

        assertTrue(feet.equals(yard));
    }

    @Test
    public void thirtyPoint48CmEqualsOneFoot() {

        Length cm =
                new Length(
                        30.48,
                        LengthUnit.CENTIMETERS
                );

        Length foot =
                new Length(
                        1.0,
                        LengthUnit.FEET
                );

        assertTrue(cm.equals(foot));
    }

    @Test
    public void yardNotEqualToInches() {

        Length yard =
                new Length(
                        1.0,
                        LengthUnit.YARDS
                );

        Length inches =
                new Length(
                        24.0,
                        LengthUnit.INCHES
                );

        assertFalse(yard.equals(inches));
    }

    @Test
    public void referenceEqualitySameObject() {

        Length feet =
                new Length(
                        1.0,
                        LengthUnit.FEET
                );

        assertTrue(feet.equals(feet));
    }

    @Test
    public void equalsReturnsFalseForNull() {

        Length feet =
                new Length(
                        1.0,
                        LengthUnit.FEET
                );

        assertFalse(feet.equals(null));
    }

    @Test
    public void reflexiveSymmetricAndTransitiveProperty() {

        Length length1 =
                new Length(
                        1.0,
                        LengthUnit.FEET
                );

        Length length2 =
                new Length(
                        12.0,
                        LengthUnit.INCHES
                );

        Length length3 =
                new Length(
                        0.333333,
                        LengthUnit.YARDS
                );

        assertTrue(length1.equals(length2));
        assertTrue(length2.equals(length3));
        assertTrue(length1.equals(length3));
    }

    @Test
    public void differentValuesSameUnitNotEqual() {

        Length feet1 =
                new Length(
                        1.0,
                        LengthUnit.FEET
                );

        Length feet2 =
                new Length(
                        5.0,
                        LengthUnit.FEET
                );

        assertFalse(feet1.equals(feet2));
    }

    @Test
    public void crossUnitEqualityDemonstrateMethod() {

        assertTrue(
                QuantityMeasurementApp
                        .demonstrateLengthEquality(
                                new Length(
                                        1.0,
                                        LengthUnit.FEET
                                ),
                                new Length(
                                        12.0,
                                        LengthUnit.INCHES
                                )
                        )
        );
    }

    // =========================
    // UC5 CONVERSION TESTS
    // =========================

    @Test
    public void convertFeetToInches() {

        Length feet =
                new Length(
                        1.0,
                        LengthUnit.FEET
                );

        Length result =
                feet.convertTo(
                        LengthUnit.INCHES
                );

        Length expected =
                new Length(
                        12.0,
                        LengthUnit.INCHES
                );

        assertTrue(result.equals(expected));
    }

    @Test
    public void convertYardsToInchesUsingOverloadedMethod() {

        Length yard =
                new Length(
                        1.0,
                        LengthUnit.YARDS
                );

        Length result =
                yard.convertTo(
                        LengthUnit.INCHES
                );

        Length expected =
                new Length(
                        36.0,
                        LengthUnit.INCHES
                );

        assertTrue(result.equals(expected));
    }

    // =========================
    // UC6 ADDITION TESTS
    // =========================

    @Test
    public void addFeetAndInches() {

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

        Length result =
                feet.add(inches);

        Length expected =
                new Length(
                        2.0,
                        LengthUnit.FEET
                );

        assertTrue(result.equals(expected));
    }

    // =========================
    // UC7 TARGET UNIT ADDITION TESTS
    // =========================

    @Test
    public void addFeetAndInchesWithTargetUnitInches() {

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

        Length result =
                feet.add(
                        inches,
                        LengthUnit.INCHES
                );

        Length expected =
                new Length(
                        24.0,
                        LengthUnit.INCHES
                );

        assertTrue(result.equals(expected));
    }

    // =========================
    // UC9 WEIGHT TESTS
    // =========================

    @Test
    public void kilogramEquals1000Grams() {

        Weight kilogram =
                new Weight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        Weight gram =
                new Weight(
                        1000.0,
                        WeightUnit.GRAM
                );

        assertTrue(kilogram.equals(gram));
    }

    @Test
    public void poundEquals453Point592Grams() {

        Weight pound =
                new Weight(
                        1.0,
                        WeightUnit.POUND
                );

        Weight gram =
                new Weight(
                        453.592,
                        WeightUnit.GRAM
                );

        assertTrue(pound.equals(gram));
    }

    @Test
    public void kilogramNotEqualToPound() {

        Weight kilogram =
                new Weight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        Weight pound =
                new Weight(
                        1.0,
                        WeightUnit.POUND
                );

        assertFalse(kilogram.equals(pound));
    }

    @Test
    public void weightReferenceEquality() {

        Weight kilogram =
                new Weight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        assertTrue(kilogram.equals(kilogram));
    }

    @Test
    public void weightEqualsReturnsFalseForNull() {

        Weight kilogram =
                new Weight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        assertFalse(kilogram.equals(null));
    }

    @Test
    public void convertKilogramToGram() {

        Weight kilogram =
                new Weight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        Weight result =
                kilogram.convertTo(
                        WeightUnit.GRAM
                );

        Weight expected =
                new Weight(
                        1000.0,
                        WeightUnit.GRAM
                );

        assertTrue(result.equals(expected));
    }

    @Test
    public void convertPoundToKilogram() {

        Weight pound =
                new Weight(
                        2.20462,
                        WeightUnit.POUND
                );

        Weight result =
                pound.convertTo(
                        WeightUnit.KILOGRAM
                );

        Weight expected =
                new Weight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        assertTrue(result.equals(expected));
    }

    @Test
    public void additionOfWeightsEqualsExpected() {

        Weight kilogram =
                new Weight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        Weight gram =
                new Weight(
                        1000.0,
                        WeightUnit.GRAM
                );

        Weight result =
                kilogram.add(
                        gram
                );

        Weight expected =
                new Weight(
                        2.0,
                        WeightUnit.KILOGRAM
                );

        assertTrue(result.equals(expected));
    }

    @Test
    public void additionOfWeightsWithTargetUnitGram() {

        Weight kilogram =
                new Weight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        Weight gram =
                new Weight(
                        1000.0,
                        WeightUnit.GRAM
                );

        Weight result =
                kilogram.add(
                        gram,
                        WeightUnit.GRAM
                );

        Weight expected =
                new Weight(
                        2000.0,
                        WeightUnit.GRAM
                );

        assertTrue(result.equals(expected));
    }

    @Test
    public void weightAdditionWithNegativeValues() {

        Weight kilogram =
                new Weight(
                        5.0,
                        WeightUnit.KILOGRAM
                );

        Weight negative =
                new Weight(
                        -2.0,
                        WeightUnit.KILOGRAM
                );

        Weight result =
                kilogram.add(negative);

        Weight expected =
                new Weight(
                        3.0,
                        WeightUnit.KILOGRAM
                );

        assertTrue(result.equals(expected));
    }

    @Test
    public void weightAdditionWithZero() {

        Weight kilogram =
                new Weight(
                        5.0,
                        WeightUnit.KILOGRAM
                );

        Weight zero =
                new Weight(
                        0.0,
                        WeightUnit.GRAM
                );

        Weight result =
                kilogram.add(zero);

        Weight expected =
                new Weight(
                        5.0,
                        WeightUnit.KILOGRAM
                );

        assertTrue(result.equals(expected));
    }

    @Test
    public void nullWeightUnitThrowsException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Weight(
                        1.0,
                        null
                )
        );
    }

    @Test
    public void nullLengthUnitThrowsException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Length(
                        1.0,
                        null
                )
        );
    }
    @Test
public void kilogramToKilogramDifferentValue() {

    Weight w1 =
            new Weight(
                    1.0,
                    WeightUnit.KILOGRAM
            );

    Weight w2 =
            new Weight(
                    2.0,
                    WeightUnit.KILOGRAM
            );

    assertFalse(w1.equals(w2));
}

@Test
public void gramToKilogramEquivalentValue() {

    Weight gram =
            new Weight(
                    1000.0,
                    WeightUnit.GRAM
            );

    Weight kilogram =
            new Weight(
                    1.0,
                    WeightUnit.KILOGRAM
            );

    assertTrue(gram.equals(kilogram));
}

@Test
public void weightVsLengthIncompatible() {

    Weight weight =
            new Weight(
                    1.0,
                    WeightUnit.KILOGRAM
            );

    Length length =
            new Length(
                    1.0,
                    LengthUnit.FEET
            );

    assertFalse(weight.equals(length));
}

@Test
public void weightTransitiveProperty() {

    Weight w1 =
            new Weight(
                    1.0,
                    WeightUnit.KILOGRAM
            );

    Weight w2 =
            new Weight(
                    1000.0,
                    WeightUnit.GRAM
            );

    Weight w3 =
            new Weight(
                    2.20462,
                    WeightUnit.POUND
            );

    assertTrue(w1.equals(w2));
    assertTrue(w2.equals(w3));
    assertTrue(w1.equals(w3));
}

@Test
public void zeroWeightEquality() {

    Weight kilogram =
            new Weight(
                    0.0,
                    WeightUnit.KILOGRAM
            );

    Weight gram =
            new Weight(
                    0.0,
                    WeightUnit.GRAM
            );

    assertTrue(kilogram.equals(gram));
}

@Test
public void negativeWeightEquality() {

    Weight kilogram =
            new Weight(
                    -1.0,
                    WeightUnit.KILOGRAM
            );

    Weight gram =
            new Weight(
                    -1000.0,
                    WeightUnit.GRAM
            );

    assertTrue(kilogram.equals(gram));
}

@Test
public void largeWeightValueEquality() {

    Weight gram =
            new Weight(
                    1000000.0,
                    WeightUnit.GRAM
            );

    Weight kilogram =
            new Weight(
                    1000.0,
                    WeightUnit.KILOGRAM
            );

    assertTrue(gram.equals(kilogram));
}

@Test
public void smallWeightValueEquality() {

    Weight kilogram =
            new Weight(
                    0.001,
                    WeightUnit.KILOGRAM
            );

    Weight gram =
            new Weight(
                    1.0,
                    WeightUnit.GRAM
            );

    assertTrue(kilogram.equals(gram));
}

@Test
public void convertKilogramToPound() {

    Weight kilogram =
            new Weight(
                    1.0,
                    WeightUnit.KILOGRAM
            );

    Weight result =
            kilogram.convertTo(
                    WeightUnit.POUND
            );

    Weight expected =
            new Weight(
                    2.20462,
                    WeightUnit.POUND
            );

    assertTrue(result.equals(expected));
}

@Test
public void conversionSameUnit() {

    Weight kilogram =
            new Weight(
                    5.0,
                    WeightUnit.KILOGRAM
            );

    Weight result =
            kilogram.convertTo(
                    WeightUnit.KILOGRAM
            );

    assertTrue(result.equals(kilogram));
}

@Test
public void roundTripConversion() {

    Weight kilogram =
            new Weight(
                    1.5,
                    WeightUnit.KILOGRAM
            );

    Weight gram =
            kilogram.convertTo(
                    WeightUnit.GRAM
            );

    Weight result =
            gram.convertTo(
                    WeightUnit.KILOGRAM
            );

    assertTrue(result.equals(kilogram));
}

@Test
public void sameUnitAdditionKilogram() {

    Weight w1 =
            new Weight(
                    1.0,
                    WeightUnit.KILOGRAM
            );

    Weight w2 =
            new Weight(
                    2.0,
                    WeightUnit.KILOGRAM
            );

    Weight expected =
            new Weight(
                    3.0,
                    WeightUnit.KILOGRAM
            );

    assertTrue(w1.add(w2).equals(expected));
}

@Test
public void poundPlusKilogramAddition() {

    Weight pound =
            new Weight(
                    2.20462,
                    WeightUnit.POUND
            );

    Weight kilogram =
            new Weight(
                    1.0,
                    WeightUnit.KILOGRAM
            );

    Weight result =
            pound.add(
                    kilogram,
                    WeightUnit.POUND
            );

    Weight expected =
            new Weight(
                    4.40924,
                    WeightUnit.POUND
            );

    assertTrue(result.equals(expected));
}

@Test
public void additionCommutativity() {

    Weight kilogram =
            new Weight(
                    1.0,
                    WeightUnit.KILOGRAM
            );

    Weight gram =
            new Weight(
                    1000.0,
                    WeightUnit.GRAM
            );

    assertTrue(
            kilogram.add(gram)
                    .equals(
                            gram.add(
                                    kilogram,
                                    WeightUnit.GRAM
                            )
                    )
    );
}

@Test
public void largeWeightAddition() {

    Weight w1 =
            new Weight(
                    1000000.0,
                    WeightUnit.KILOGRAM
            );

    Weight w2 =
            new Weight(
                    1000000.0,
                    WeightUnit.KILOGRAM
            );

    Weight expected =
            new Weight(
                    2000000.0,
                    WeightUnit.KILOGRAM
            );

    assertTrue(w1.add(w2).equals(expected));
}
}