package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.enums.LengthUnit;
import com.apps.quantitymeasurement.enums.TemperatureUnit;
import com.apps.quantitymeasurement.enums.VolumeUnit;
import com.apps.quantitymeasurement.enums.WeightUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // =========================
    // LENGTH EQUALITY TESTS
    // =========================

    @Test
    public void feetEqualsFeet() {
        assertEquals(
                new Quantity<>(1.0, LengthUnit.FEET),
                new Quantity<>(1.0, LengthUnit.FEET)
        );
    }

    @Test
    public void inchesEqualsInches() {
        assertEquals(
                new Quantity<>(12.0, LengthUnit.INCHES),
                new Quantity<>(12.0, LengthUnit.INCHES)
        );
    }

    @Test
    public void feetEqualsInches() {
        assertEquals(
                new Quantity<>(1.0, LengthUnit.FEET),
                new Quantity<>(12.0, LengthUnit.INCHES)
        );
    }

    @Test
    public void feetNotEqualsFeet() {
        assertNotEquals(
                new Quantity<>(1.0, LengthUnit.FEET),
                new Quantity<>(2.0, LengthUnit.FEET)
        );
    }

    @Test
    public void inchesNotEqualsInches() {
        assertNotEquals(
                new Quantity<>(12.0, LengthUnit.INCHES),
                new Quantity<>(24.0, LengthUnit.INCHES)
        );
    }

    @Test
    public void feetNotEquals24Inches() {
        assertNotEquals(
                new Quantity<>(1.0, LengthUnit.FEET),
                new Quantity<>(24.0, LengthUnit.INCHES)
        );
    }

    @Test
    public void threeFeetEqualsOneYard() {
        assertEquals(
                new Quantity<>(3.0, LengthUnit.FEET),
                new Quantity<>(1.0, LengthUnit.YARDS)
        );
    }

    @Test
    public void yardEquals36Inches() {
        assertEquals(
                new Quantity<>(1.0, LengthUnit.YARDS),
                new Quantity<>(36.0, LengthUnit.INCHES)
        );
    }

    @Test
    public void hundredCmEquals39Point3701Inches() {
        assertEquals(
                new Quantity<>(100.0, LengthUnit.CENTIMETERS),
                new Quantity<>(39.3701, LengthUnit.INCHES)
        );
    }

    @Test
    public void thirtyPoint48CmEqualsOneFoot() {
        assertEquals(
                new Quantity<>(30.48, LengthUnit.CENTIMETERS),
                new Quantity<>(1.0, LengthUnit.FEET)
        );
    }

    @Test
    public void yardNotEqual24Inches() {
        assertNotEquals(
                new Quantity<>(1.0, LengthUnit.YARDS),
                new Quantity<>(24.0, LengthUnit.INCHES)
        );
    }

    @Test
    public void referenceEquality() {
        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertEquals(feet, feet);
    }

    @Test
    public void equalsNull() {
        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertNotEquals(feet, null);
    }

    @Test
    public void transitiveProperty() {

        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> c =
                new Quantity<>(0.333333333, LengthUnit.YARDS);

        assertEquals(a, b);
        assertEquals(b, c);
        assertEquals(a, c);
    }

    @Test
    public void differentValuesSameUnitNotEqual() {
        assertNotEquals(
                new Quantity<>(1.0, LengthUnit.FEET),
                new Quantity<>(5.0, LengthUnit.FEET)
        );
    }

    // =========================
    // CONVERSION TESTS
    // =========================

    @Test
    public void convertFeetToInches() {

        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .convertTo(LengthUnit.INCHES);

        assertEquals(
                new Quantity<>(12.0, LengthUnit.INCHES),
                result
        );
    }

    @Test
    public void convertYardsToInches() {

        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.YARDS)
                        .convertTo(LengthUnit.INCHES);

        assertEquals(
                new Quantity<>(36.0, LengthUnit.INCHES),
                result
        );
    }

    // =========================
    // ADDITION TESTS
    // =========================

    @Test
    public void addFeetAndInches() {

        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .add(
                                new Quantity<>(
                                        12.0,
                                        LengthUnit.INCHES
                                )
                        );

        assertEquals(
                new Quantity<>(2.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    public void addFeetAndInchesTargetInches() {

        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .add(
                                new Quantity<>(
                                        12.0,
                                        LengthUnit.INCHES
                                ),
                                LengthUnit.INCHES
                        );

        assertEquals(
                new Quantity<>(24.0, LengthUnit.INCHES),
                result
        );
    }

    @Test
    public void addFeetAndInchesTargetFeet() {

        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .add(
                                new Quantity<>(
                                        12.0,
                                        LengthUnit.INCHES
                                ),
                                LengthUnit.FEET
                        );

        assertEquals(
                new Quantity<>(2.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    public void addYardAndFeetTargetYard() {

        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.YARDS)
                        .add(
                                new Quantity<>(
                                        3.0,
                                        LengthUnit.FEET
                                ),
                                LengthUnit.YARDS
                        );

        assertEquals(
                new Quantity<>(2.0, LengthUnit.YARDS),
                result
        );
    }

    @Test
    public void addCmAndInchTargetCm() {

        Quantity<LengthUnit> result =
                new Quantity<>(2.54, LengthUnit.CENTIMETERS)
                        .add(
                                new Quantity<>(
                                        1.0,
                                        LengthUnit.INCHES
                                ),
                                LengthUnit.CENTIMETERS
                        );

        assertEquals(
                new Quantity<>(5.08, LengthUnit.CENTIMETERS),
                result
        );
    }

    // =========================
    // WEIGHT TESTS
    // =========================

    @Test
    public void kilogramEqualsGram() {
        assertEquals(
                new Quantity<>(1.0, WeightUnit.KILOGRAM),
                new Quantity<>(1000.0, WeightUnit.GRAM)
        );
    }

    @Test
    public void poundEqualsGram() {
        assertEquals(
                new Quantity<>(1.0, WeightUnit.POUND),
                new Quantity<>(453.592, WeightUnit.GRAM)
        );
    }

    @Test
    public void kilogramNotEqualPound() {
        assertNotEquals(
                new Quantity<>(1.0, WeightUnit.KILOGRAM),
                new Quantity<>(1.0, WeightUnit.POUND)
        );
    }

    @Test
    public void weightReferenceEquality() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertEquals(kg, kg);
    }

    @Test
    public void weightEqualsNull() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(kg, null);
    }

    @Test
    public void convertKgToGram() {

        Quantity<WeightUnit> result =
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);

        assertEquals(
                new Quantity<>(1000.0, WeightUnit.GRAM),
                result
        );
    }

    @Test
    public void convertPoundToKg() {

        Quantity<WeightUnit> result =
                new Quantity<>(2.20462, WeightUnit.POUND)
                        .convertTo(WeightUnit.KILOGRAM);

        assertEquals(
                new Quantity<>(1.0, WeightUnit.KILOGRAM),
                result
        );
    }

    @Test
    public void addKgAndGram() {

        Quantity<WeightUnit> result =
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .add(
                                new Quantity<>(
                                        1000.0,
                                        WeightUnit.GRAM
                                )
                        );

        assertEquals(
                new Quantity<>(2.0, WeightUnit.KILOGRAM),
                result
        );
    }
        @Test
    public void addKgAndGramTargetGram() {

        Quantity<WeightUnit> result =
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .add(
                                new Quantity<>(
                                        1000.0,
                                        WeightUnit.GRAM
                                ),
                                WeightUnit.GRAM
                        );

        assertEquals(
                new Quantity<>(2000.0, WeightUnit.GRAM),
                result
        );
    }

    @Test
    public void negativeWeightAddition() {

        Quantity<WeightUnit> result =
                new Quantity<>(5.0, WeightUnit.KILOGRAM)
                        .add(
                                new Quantity<>(
                                        -2.0,
                                        WeightUnit.KILOGRAM
                                )
                        );

        assertEquals(
                new Quantity<>(3.0, WeightUnit.KILOGRAM),
                result
        );
    }

    @Test
    public void zeroWeightAddition() {

        Quantity<WeightUnit> result =
                new Quantity<>(5.0, WeightUnit.KILOGRAM)
                        .add(
                                new Quantity<>(
                                        0.0,
                                        WeightUnit.GRAM
                                )
                        );

        assertEquals(
                new Quantity<>(5.0, WeightUnit.KILOGRAM),
                result
        );
    }

    @Test
    public void kilogramDifferentValue() {

        assertNotEquals(
                new Quantity<>(1.0, WeightUnit.KILOGRAM),
                new Quantity<>(2.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    public void gramEquivalentKilogram() {

        assertEquals(
                new Quantity<>(1000.0, WeightUnit.GRAM),
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    public void weightTransitiveProperty() {

        Quantity<WeightUnit> a =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> b =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> c =
                new Quantity<>(2.20462, WeightUnit.POUND);

        assertEquals(a, b);
        assertEquals(b, c);
        assertEquals(a, c);
    }

    @Test
    public void zeroWeightEquality() {

        assertEquals(
                new Quantity<>(0.0, WeightUnit.KILOGRAM),
                new Quantity<>(0.0, WeightUnit.GRAM)
        );
    }

    @Test
    public void negativeWeightEquality() {

        assertEquals(
                new Quantity<>(-1.0, WeightUnit.KILOGRAM),
                new Quantity<>(-1000.0, WeightUnit.GRAM)
        );
    }

    @Test
    public void largeWeightEquality() {

        assertEquals(
                new Quantity<>(1000000.0, WeightUnit.GRAM),
                new Quantity<>(1000.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    public void smallWeightEquality() {

        assertEquals(
                new Quantity<>(0.001, WeightUnit.KILOGRAM),
                new Quantity<>(1.0, WeightUnit.GRAM)
        );
    }

    @Test
public void convertKgToPound() {

    Quantity<WeightUnit> result =
            new Quantity<>(1.0, WeightUnit.KILOGRAM)
                    .convertTo(WeightUnit.POUND);

    assertEquals(
            new Quantity<>(2.2, WeightUnit.POUND),
            result
    );
}

    @Test
    public void sameUnitConversion() {

        Quantity<WeightUnit> kg =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertEquals(
                kg,
                kg.convertTo(
                        WeightUnit.KILOGRAM
                )
        );
    }

    @Test
    public void roundTripConversion() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.5, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                kg.convertTo(
                        WeightUnit.GRAM
                );

        Quantity<WeightUnit> result =
                gram.convertTo(
                        WeightUnit.KILOGRAM
                );

        assertEquals(
                kg,
                result
        );
    }

    @Test
    public void sameUnitAddition() {

        Quantity<WeightUnit> result =
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .add(
                                new Quantity<>(
                                        2.0,
                                        WeightUnit.KILOGRAM
                                )
                        );

        assertEquals(
                new Quantity<>(3.0, WeightUnit.KILOGRAM),
                result
        );
    }

    @Test
public void poundPlusKilogramAddition() {

    Quantity<WeightUnit> result =
            new Quantity<>(2.20462, WeightUnit.POUND)
                    .add(
                            new Quantity<>(
                                    1.0,
                                    WeightUnit.KILOGRAM
                            ),
                            WeightUnit.POUND
                    );

    assertEquals(
            new Quantity<>(4.41, WeightUnit.POUND),
            result
    );
}

    @Test
    public void largeWeightAddition() {

        Quantity<WeightUnit> result =
                new Quantity<>(1000000.0, WeightUnit.KILOGRAM)
                        .add(
                                new Quantity<>(
                                        1000000.0,
                                        WeightUnit.KILOGRAM
                                )
                        );

        assertEquals(
                new Quantity<>(2000000.0, WeightUnit.KILOGRAM),
                result
        );
    }

    // =========================
    // UC10 SPECIFIC TESTS
    // =========================

    @Test
    public void nullUnitThrowsException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null)
        );
    }

    @Test
    public void nanThrowsException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(
                        Double.NaN,
                        LengthUnit.FEET
                )
        );
    }

    @Test
    public void infinityThrowsException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(
                        Double.POSITIVE_INFINITY,
                        LengthUnit.FEET
                )
        );
    }

    @Test
    public void lengthNotEqualWeight() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(
                length.equals(weight)
        );
    }

    @Test
    public void equalObjectsSameHashCode() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(
                feet.hashCode(),
                inches.hashCode()
        );
    }

    @Test
    public void toStringNotNull() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertNotNull(
                feet.toString()
        );
    }

    @Test
    public void convertToNullThrowsException() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> feet.convertTo(null)
        );
    }

    @Test
    public void addNullThrowsException() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> feet.add(null)
        );
    }

    @Test
    public void getValueAndGetUnit() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertEquals(
                1.0,
                feet.getValue()
        );

        assertEquals(
                LengthUnit.FEET,
                feet.getUnit()
        );
    }
    @Test
public void volumeLiterEqualMilliLiter() {

    assertEquals(
            new Quantity<>(1.0, VolumeUnit.LITRE),
            new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
    );
}

@Test
public void convertVolumeLitersToMilliliters() {

    Quantity<VolumeUnit> result =
            new Quantity<>(1.0, VolumeUnit.LITRE)
                    .convertTo(
                            VolumeUnit.MILLILITRE
                    );

    assertEquals(
            new Quantity<>(1000.0,
                    VolumeUnit.MILLILITRE),
            result
    );
}

@Test
public void addVolumeLitersAndMilliliters() {

    Quantity<VolumeUnit> result =
            new Quantity<>(1.0, VolumeUnit.LITRE)
                    .add(
                            new Quantity<>(
                                    1000.0,
                                    VolumeUnit.MILLILITRE
                            )
                    );

    assertEquals(
            new Quantity<>(2.0,
                    VolumeUnit.LITRE),
            result
    );
}
@Test
public void convertMillilitersToLiters() {

    Quantity<VolumeUnit> result =
            new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                    .convertTo(VolumeUnit.LITRE);

    assertEquals(
            new Quantity<>(1.0, VolumeUnit.LITRE),
            result
    );
}

@Test
public void litreNotEqualTwoLitres() {

    assertNotEquals(
            new Quantity<>(1.0, VolumeUnit.LITRE),
            new Quantity<>(2.0, VolumeUnit.LITRE)
    );
}

@Test
public void volumeReferenceEquality() {

    Quantity<VolumeUnit> litre =
            new Quantity<>(1.0, VolumeUnit.LITRE);

    assertEquals(litre, litre);
}

@Test
public void volumeEqualsNull() {

    Quantity<VolumeUnit> litre =
            new Quantity<>(1.0, VolumeUnit.LITRE);

    assertNotEquals(litre, null);
}

@Test
public void addVolumeTargetMilliLitre() {

    Quantity<VolumeUnit> result =
            new Quantity<>(1.0, VolumeUnit.LITRE)
                    .add(
                            new Quantity<>(
                                    1000.0,
                                    VolumeUnit.MILLILITRE
                            ),
                            VolumeUnit.MILLILITRE
                    );

    assertEquals(
            new Quantity<>(2000.0,
                    VolumeUnit.MILLILITRE),
            result
    );
}

@Test
public void gallonEqualsLitres() {

    assertEquals(
            new Quantity<>(1.0, VolumeUnit.GALLON),
            new Quantity<>(3.78541, VolumeUnit.LITRE)
    );
}

@Test
public void convertGallonToLitre() {

    Quantity<VolumeUnit> result =
            new Quantity<>(1.0, VolumeUnit.GALLON)
                    .convertTo(VolumeUnit.LITRE);

    assertEquals(
            new Quantity<>(3.78541, VolumeUnit.LITRE),
            result
    );
}

@Test
public void volumeTransitiveProperty() {

    Quantity<VolumeUnit> a =
            new Quantity<>(1.0, VolumeUnit.LITRE);

    Quantity<VolumeUnit> b =
            new Quantity<>(1000.0,
                    VolumeUnit.MILLILITRE);

    /*Quantity<VolumeUnit> c =
            new Quantity<>(0.264172,
                    VolumeUnit.GALLON);*/

    assertEquals(a, b);
}
@Test
public void subtractionSameUnitFeetMinusFeet() {

    Quantity<LengthUnit> result =
            new Quantity<>(10.0, LengthUnit.FEET)
                    .subtract(
                            new Quantity<>(5.0, LengthUnit.FEET)
                    );

    assertEquals(
            new Quantity<>(5.0, LengthUnit.FEET),
            result
    );
}

@Test
public void subtractionSameUnitLitreMinusLitre() {

    Quantity<VolumeUnit> result =
            new Quantity<>(10.0, VolumeUnit.LITRE)
                    .subtract(
                            new Quantity<>(3.0, VolumeUnit.LITRE)
                    );

    assertEquals(
            new Quantity<>(7.0, VolumeUnit.LITRE),
            result
    );
}

@Test
public void subtractionCrossUnitFeetMinusInches() {

    Quantity<LengthUnit> result =
            new Quantity<>(10.0, LengthUnit.FEET)
                    .subtract(
                            new Quantity<>(6.0, LengthUnit.INCHES)
                    );

    assertEquals(
            new Quantity<>(9.5, LengthUnit.FEET),
            result
    );
}

@Test
public void subtractionCrossUnitInchesMinusFeet() {

    Quantity<LengthUnit> result =
            new Quantity<>(120.0, LengthUnit.INCHES)
                    .subtract(
                            new Quantity<>(5.0, LengthUnit.FEET)
                    );

    assertEquals(
            new Quantity<>(60.0, LengthUnit.INCHES),
            result
    );
}

@Test
public void subtractionTargetUnitInches() {

    Quantity<LengthUnit> result =
            new Quantity<>(10.0, LengthUnit.FEET)
                    .subtract(
                            new Quantity<>(6.0, LengthUnit.INCHES),
                            LengthUnit.INCHES
                    );

    assertEquals(
            new Quantity<>(114.0, LengthUnit.INCHES),
            result
    );
}

@Test
public void subtractionTargetUnitMillilitre() {

    Quantity<VolumeUnit> result =
            new Quantity<>(5.0, VolumeUnit.LITRE)
                    .subtract(
                            new Quantity<>(2.0, VolumeUnit.LITRE),
                            VolumeUnit.MILLILITRE
                    );

    assertEquals(
            new Quantity<>(3000.0,
                    VolumeUnit.MILLILITRE),
            result
    );
}

@Test
public void subtractionNegativeResult() {

    Quantity<LengthUnit> result =
            new Quantity<>(5.0, LengthUnit.FEET)
                    .subtract(
                            new Quantity<>(10.0, LengthUnit.FEET)
                    );

    assertEquals(
            new Quantity<>(-5.0, LengthUnit.FEET),
            result
    );
}

@Test
public void subtractionZeroResult() {

    Quantity<LengthUnit> result =
            new Quantity<>(10.0, LengthUnit.FEET)
                    .subtract(
                            new Quantity<>(120.0,
                                    LengthUnit.INCHES)
                    );

    assertEquals(
            new Quantity<>(0.0, LengthUnit.FEET),
            result
    );
}

@Test
public void subtractionNullOperand() {

    assertThrows(
            IllegalArgumentException.class,
            () -> new Quantity<>(10.0, LengthUnit.FEET)
                    .subtract(null)
    );
}

@Test
public void subtractionNullTargetUnit() {

    assertThrows(
            IllegalArgumentException.class,
            () -> new Quantity<>(10.0, LengthUnit.FEET)
                    .subtract(
                            new Quantity<>(5.0,
                                    LengthUnit.FEET),
                            null
                    )
    );
}
@Test
public void divisionSameUnitFeetByFeet() {

    double result =
            new Quantity<>(10.0, LengthUnit.FEET)
                    .divide(
                            new Quantity<>(2.0,
                                    LengthUnit.FEET)
                    );

    assertEquals(
            5.0,
            result
    );
}

@Test
public void divisionSameUnitLitreByLitre() {

    double result =
            new Quantity<>(10.0, VolumeUnit.LITRE)
                    .divide(
                            new Quantity<>(5.0,
                                    VolumeUnit.LITRE)
                    );

    assertEquals(
            2.0,
            result
    );
}

@Test
public void divisionCrossUnitFeetByInches() {

    double result =
            new Quantity<>(24.0, LengthUnit.INCHES)
                    .divide(
                            new Quantity<>(2.0,
                                    LengthUnit.FEET)
                    );

    assertEquals(
            1.0,
            result
    );
}

@Test
public void divisionCrossUnitKilogramByGram() {

    double result =
            new Quantity<>(2.0, WeightUnit.KILOGRAM)
                    .divide(
                            new Quantity<>(2000.0,
                                    WeightUnit.GRAM)
                    );

    assertEquals(
            1.0,
            result
    );
}

@Test
public void divisionRatioGreaterThanOne() {

    double result =
            new Quantity<>(10.0, LengthUnit.FEET)
                    .divide(
                            new Quantity<>(2.0,
                                    LengthUnit.FEET)
                    );

    assertEquals(
            5.0,
            result
    );
}

@Test
public void divisionRatioLessThanOne() {

    double result =
            new Quantity<>(5.0, LengthUnit.FEET)
                    .divide(
                            new Quantity<>(10.0,
                                    LengthUnit.FEET)
                    );

    assertEquals(
            0.5,
            result
    );
}

@Test
public void divisionRatioEqualOne() {

    double result =
            new Quantity<>(10.0, LengthUnit.FEET)
                    .divide(
                            new Quantity<>(10.0,
                                    LengthUnit.FEET)
                    );

    assertEquals(
            1.0,
            result
    );
}

@Test
public void divisionNonCommutative() {

    double result1 =
            new Quantity<>(10.0, LengthUnit.FEET)
                    .divide(
                            new Quantity<>(5.0,
                                    LengthUnit.FEET)
                    );

    double result2 =
            new Quantity<>(5.0, LengthUnit.FEET)
                    .divide(
                            new Quantity<>(10.0,
                                    LengthUnit.FEET)
                    );

    assertNotEquals(
            result1,
            result2
    );
}

@Test
public void divisionByZero() {

    assertThrows(
            ArithmeticException.class,
            () -> new Quantity<>(10.0, LengthUnit.FEET)
                    .divide(
                            new Quantity<>(0.0,
                                    LengthUnit.FEET)
                    )
    );
}

@Test
public void divisionNullOperand() {

    assertThrows(
            IllegalArgumentException.class,
            () -> new Quantity<>(10.0, LengthUnit.FEET)
                    .divide(null)
    );
}
// ===========================
// UC13 TEST CASES
// ===========================

@Test
public void testAdd_UC12_BehaviorPreserved() {

    assertEquals(
            new Quantity<>(2.0, LengthUnit.FEET),
            new Quantity<>(1.0, LengthUnit.FEET)
                    .add(new Quantity<>(12.0, LengthUnit.INCHES))
    );
}

@Test
public void testSubtract_UC12_BehaviorPreserved() {

    assertEquals(
            new Quantity<>(9.5, LengthUnit.FEET),
            new Quantity<>(10.0, LengthUnit.FEET)
                    .subtract(new Quantity<>(6.0, LengthUnit.INCHES))
    );
}

@Test
public void testDivide_UC12_BehaviorPreserved() {

    assertEquals(
            1.0,
            new Quantity<>(24.0, LengthUnit.INCHES)
                    .divide(new Quantity<>(2.0, LengthUnit.FEET))
    );
}

@Test
public void testValidation_NullOperand_ConsistentAcrossOperations() {

    Quantity<LengthUnit> feet =
            new Quantity<>(1.0, LengthUnit.FEET);

    assertThrows(
            IllegalArgumentException.class,
            () -> feet.add(null)
    );

    assertThrows(
            IllegalArgumentException.class,
            () -> feet.subtract(null)
    );

    assertThrows(
            IllegalArgumentException.class,
            () -> feet.divide(null)
    );
}

@Test
public void testValidation_NullTargetUnit_AddSubtractReject() {

    Quantity<LengthUnit> feet =
            new Quantity<>(10.0, LengthUnit.FEET);

    assertThrows(
            IllegalArgumentException.class,
            () -> feet.add(
                    new Quantity<>(12.0, LengthUnit.INCHES),
                    null
            )
    );

    assertThrows(
            IllegalArgumentException.class,
            () -> feet.subtract(
                    new Quantity<>(12.0, LengthUnit.INCHES),
                    null
            )
    );
}

@Test
public void testValidation_FiniteValue_ConsistentAcrossOperations() {

    assertThrows(
            IllegalArgumentException.class,
            () -> new Quantity<>(
                    Double.POSITIVE_INFINITY,
                    LengthUnit.FEET
            )
    );

    assertThrows(
            IllegalArgumentException.class,
            () -> new Quantity<>(
                    Double.NaN,
                    LengthUnit.FEET
            )
    );
}

@Test
public void testImmutability_AfterAdd_ViaCentralizedHelper() {

    Quantity<LengthUnit> feet =
            new Quantity<>(1.0, LengthUnit.FEET);

    feet.add(
            new Quantity<>(12.0, LengthUnit.INCHES)
    );

    assertEquals(
            new Quantity<>(1.0, LengthUnit.FEET),
            feet
    );
}

@Test
public void testImmutability_AfterSubtract_ViaCentralizedHelper() {

    Quantity<LengthUnit> feet =
            new Quantity<>(10.0, LengthUnit.FEET);

    feet.subtract(
            new Quantity<>(6.0, LengthUnit.INCHES)
    );

    assertEquals(
            new Quantity<>(10.0, LengthUnit.FEET),
            feet
    );
}

@Test
public void testImmutability_AfterDivide_ViaCentralizedHelper() {

    Quantity<LengthUnit> feet =
            new Quantity<>(24.0, LengthUnit.INCHES);

    feet.divide(
            new Quantity<>(2.0, LengthUnit.FEET)
    );

    assertEquals(
            new Quantity<>(24.0, LengthUnit.INCHES),
            feet
    );
}

@Test
public void testAllOperations_AcrossAllCategories() {

    assertEquals(
            new Quantity<>(2.0, LengthUnit.FEET),
            new Quantity<>(1.0, LengthUnit.FEET)
                    .add(new Quantity<>(12.0, LengthUnit.INCHES))
    );

    assertEquals(
            new Quantity<>(5.0, WeightUnit.KILOGRAM),
            new Quantity<>(10.0, WeightUnit.KILOGRAM)
                    .subtract(new Quantity<>(5000.0, WeightUnit.GRAM))
    );

    assertEquals(
            1.0,
            new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                    .divide(new Quantity<>(1.0, VolumeUnit.LITRE))
    );
}

@Test
public void testImplicitTargetUnit_AddSubtract() {

    Quantity<LengthUnit> result =
            new Quantity<>(10.0, LengthUnit.FEET)
                    .subtract(
                            new Quantity<>(6.0, LengthUnit.INCHES)
                    );

    assertEquals(
            LengthUnit.FEET,
            result.getUnit()
    );
}

@Test
public void testExplicitTargetUnit_AddSubtract_Overrides() {

    Quantity<LengthUnit> result =
            new Quantity<>(10.0, LengthUnit.FEET)
                    .subtract(
                            new Quantity<>(6.0, LengthUnit.INCHES),
                            LengthUnit.INCHES
                    );

    assertEquals(
            LengthUnit.INCHES,
            result.getUnit()
    );
}

@Test
public void testArithmetic_Chain_Operations() {

    double result =
            new Quantity<>(10.0, LengthUnit.FEET)
                    .add(new Quantity<>(2.0, LengthUnit.FEET))
                    .subtract(new Quantity<>(6.0, LengthUnit.INCHES))
                    .divide(new Quantity<>(23.5, LengthUnit.FEET));

    assertEquals(
            0.4893617,
            result,
            0.0001
    );
}

@Test
public void testDivisionByZero() {

    assertThrows(
            ArithmeticException.class,
            () -> new Quantity<>(10.0, LengthUnit.FEET)
                    .divide(
                            new Quantity<>(0.0, LengthUnit.FEET)
                    )
    );
}

@Test
public void testCrossCategoryValidation() {

    Quantity<LengthUnit> length =
            new Quantity<>(1.0, LengthUnit.FEET);

    assertFalse(
            length.equals(
                    new Quantity<>(1.0, WeightUnit.KILOGRAM)
            )
    );
}

@Test
public void testAdditionWeightCategory() {

    assertEquals(
            new Quantity<>(2.0, WeightUnit.KILOGRAM),
            new Quantity<>(1.0, WeightUnit.KILOGRAM)
                    .add(new Quantity<>(1000.0, WeightUnit.GRAM))
    );
}

@Test
public void testAdditionVolumeCategory() {

    assertEquals(
            new Quantity<>(2.0, VolumeUnit.LITRE),
            new Quantity<>(1.0, VolumeUnit.LITRE)
                    .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE))
    );
}

@Test
public void testSubtractionVolumeCategory() {

    assertEquals(
            new Quantity<>(4.5, VolumeUnit.LITRE),
            new Quantity<>(5.0, VolumeUnit.LITRE)
                    .subtract(
                            new Quantity<>(500.0, VolumeUnit.MILLILITRE)
                    )
    );
}

@Test
public void testDivisionWeightCategory() {

    assertEquals(
            2.0,
            new Quantity<>(10.0, WeightUnit.KILOGRAM)
                    .divide(
                            new Quantity<>(5.0, WeightUnit.KILOGRAM)
                    )
    );
}

@Test
public void testRefactoring_NoBehaviorChange_LargeDataset() {

    for (int i = 1; i <= 1000; i++) {

        Quantity<LengthUnit> feet =
                new Quantity<>(i, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12 * i, LengthUnit.INCHES);

        assertEquals(
                feet,
                inches
        );
    }
}
// =========================================
// UC14 TEMPERATURE TESTS
// =========================================

@Test
public void temperatureEquality_CelsiusToCelsius() {

    assertEquals(
            new Quantity<>(0.0,
                    TemperatureUnit.CELSIUS),
            new Quantity<>(0.0,
                    TemperatureUnit.CELSIUS)
    );
}

@Test
public void temperatureEquality_CelsiusToFahrenheit() {

    assertEquals(
            new Quantity<>(0.0,
                    TemperatureUnit.CELSIUS),
            new Quantity<>(32.0,
                    TemperatureUnit.FAHRENHEIT)
    );
}

@Test
public void temperatureEquality_CelsiusToKelvin() {

    assertEquals(
            new Quantity<>(0.0,
                    TemperatureUnit.CELSIUS),
            new Quantity<>(273.15,
                    TemperatureUnit.KELVIN)
    );
}

@Test
public void temperatureEquality_BoilingPoint() {

    assertEquals(
            new Quantity<>(100.0,
                    TemperatureUnit.CELSIUS),
            new Quantity<>(212.0,
                    TemperatureUnit.FAHRENHEIT)
    );
}

@Test
public void temperatureEquality_Negative40() {

    assertEquals(
            new Quantity<>(-40.0,
                    TemperatureUnit.CELSIUS),
            new Quantity<>(-40.0,
                    TemperatureUnit.FAHRENHEIT)
    );
}

@Test
public void convertCelsiusToFahrenheit() {

    Quantity<TemperatureUnit> result =
            new Quantity<>(
                    100.0,
                    TemperatureUnit.CELSIUS
            ).convertTo(
                    TemperatureUnit.FAHRENHEIT
            );

    assertEquals(
            new Quantity<>(
                    212.0,
                    TemperatureUnit.FAHRENHEIT
            ),
            result
    );
}

@Test
public void convertFahrenheitToCelsius() {

    Quantity<TemperatureUnit> result =
            new Quantity<>(
                    32.0,
                    TemperatureUnit.FAHRENHEIT
            ).convertTo(
                    TemperatureUnit.CELSIUS
            );

    assertEquals(
            new Quantity<>(
                    0.0,
                    TemperatureUnit.CELSIUS
            ),
            result
    );
}

@Test
public void convertKelvinToCelsius() {

    Quantity<TemperatureUnit> result =
            new Quantity<>(
                    273.15,
                    TemperatureUnit.KELVIN
            ).convertTo(
                    TemperatureUnit.CELSIUS
            );

    assertEquals(
            new Quantity<>(
                    0.0,
                    TemperatureUnit.CELSIUS
            ),
            result
    );
}

@Test
public void convertCelsiusToKelvin() {

    Quantity<TemperatureUnit> result =
            new Quantity<>(
                    0.0,
                    TemperatureUnit.CELSIUS
            ).convertTo(
                    TemperatureUnit.KELVIN
            );

    assertEquals(
            new Quantity<>(
                    273.15,
                    TemperatureUnit.KELVIN
            ),
            result
    );
}

@Test
public void temperatureAdditionNotSupported() {

    assertThrows(
            UnsupportedOperationException.class,
            () -> new Quantity<>(
                    100.0,
                    TemperatureUnit.CELSIUS
            ).add(
                    new Quantity<>(
                            50.0,
                            TemperatureUnit.CELSIUS
                    )
            )
    );
}

@Test
public void temperatureSubtractionNotSupported() {

    assertThrows(
            UnsupportedOperationException.class,
            () -> new Quantity<>(
                    100.0,
                    TemperatureUnit.CELSIUS
            ).subtract(
                    new Quantity<>(
                            50.0,
                            TemperatureUnit.CELSIUS
                    )
            )
    );
}

@Test
public void temperatureDivisionNotSupported() {

    assertThrows(
            UnsupportedOperationException.class,
            () -> new Quantity<>(
                    100.0,
                    TemperatureUnit.CELSIUS
            ).divide(
                    new Quantity<>(
                            50.0,
                            TemperatureUnit.CELSIUS
                    )
            )
    );
}

@Test
public void temperatureSupportsArithmeticFalse() {

    assertFalse(
            TemperatureUnit.CELSIUS
                    .supportsArithmetic()
    );
}

@Test
public void temperatureReferenceEquality() {

    Quantity<TemperatureUnit> temp =
            new Quantity<>(
                    25.0,
                    TemperatureUnit.CELSIUS
            );

    assertEquals(temp, temp);
}

@Test
public void temperatureNotEqualNull() {

    Quantity<TemperatureUnit> temp =
            new Quantity<>(
                    25.0,
                    TemperatureUnit.CELSIUS
            );

    assertNotEquals(temp, null);
}

@Test
public void temperatureDifferentValuesNotEqual() {

    assertNotEquals(
            new Quantity<>(
                    25.0,
                    TemperatureUnit.CELSIUS
            ),
            new Quantity<>(
                    50.0,
                    TemperatureUnit.CELSIUS
            )
    );
}
}