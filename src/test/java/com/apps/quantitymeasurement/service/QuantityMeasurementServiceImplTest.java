package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.model.QuantityDTO;
import com.apps.quantitymeasurement.model.QuantityMeasurementDTO;
import com.apps.quantitymeasurement.model.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.repository.QuantityMeasurementRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuantityMeasurementServiceImplTest {

    @Mock
    private QuantityMeasurementRepository repository;

    @InjectMocks
    private QuantityMeasurementServiceImpl service;

    @Test
    void shouldCompareEqualLength() {

        when(repository.save(any(QuantityMeasurementEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        QuantityDTO first =
                new QuantityDTO(2.0, "FEET", "Length");

        QuantityDTO second =
                new QuantityDTO(24.0, "INCHES", "Length");

        QuantityMeasurementDTO result =
                service.compare(first, second);

        assertFalse(result.isError());
        assertEquals("COMPARE", result.getOperation());
        assertEquals("Equal", result.getResultString());
    }

    @Test
    void shouldConvertFeetToInches() {

        when(repository.save(any(QuantityMeasurementEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        QuantityDTO quantity =
                new QuantityDTO(2.0, "FEET", "Length");

        QuantityDTO target =
                new QuantityDTO();

        target.setUnit("INCHES");
        target.setMeasurementType("Length");

        QuantityMeasurementDTO result =
                service.convert(quantity, target);

        assertFalse(result.isError());
        assertEquals(24.0, result.getResultValue());
        assertEquals("INCHES", result.getResultUnit());
    }

    @Test
    void shouldAddLengths() {

        when(repository.save(any(QuantityMeasurementEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        QuantityDTO first =
                new QuantityDTO(2.0, "FEET", "Length");

        QuantityDTO second =
                new QuantityDTO(24.0, "INCHES", "Length");

        QuantityDTO target =
                new QuantityDTO();

        target.setUnit("FEET");
        target.setMeasurementType("Length");

        QuantityMeasurementDTO result =
                service.add(first, second, target);

        assertFalse(result.isError());
        assertEquals(4.0, result.getResultValue());
    }

    @Test
    void shouldSubtractLengths() {

        when(repository.save(any(QuantityMeasurementEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        QuantityDTO first =
                new QuantityDTO(3.0, "FEET", "Length");

        QuantityDTO second =
                new QuantityDTO(12.0, "INCHES", "Length");

        QuantityDTO target =
                new QuantityDTO();

        target.setUnit("FEET");
        target.setMeasurementType("Length");

        QuantityMeasurementDTO result =
                service.subtract(first, second, target);

        assertFalse(result.isError());
        assertEquals(2.0, result.getResultValue());
    }

    @Test
    void shouldDivideLengths() {

        when(repository.save(any(QuantityMeasurementEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        QuantityDTO first =
                new QuantityDTO(24.0, "INCHES", "Length");

        QuantityDTO second =
                new QuantityDTO(12.0, "INCHES", "Length");

        QuantityMeasurementDTO result =
                service.divide(first, second);

        assertFalse(result.isError());
        assertEquals("2.0", result.getResultString());
    }
}