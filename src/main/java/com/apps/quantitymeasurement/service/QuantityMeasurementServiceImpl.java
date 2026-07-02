package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.entity.QuantityDTO;
import com.apps.quantitymeasurement.exception.QuantityMeasurementException;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.enums.LengthUnit;
import com.apps.quantitymeasurement.Quantity;
import com.apps.quantitymeasurement.enums.TemperatureUnit;
import com.apps.quantitymeasurement.enums.VolumeUnit;
import com.apps.quantitymeasurement.enums.WeightUnit;
import com.apps.quantitymeasurement.IMeasurable;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(
            IQuantityMeasurementRepository repository) {

        this.repository = repository;
    }

    @Override
    public boolean compare(
            QuantityDTO first,
            QuantityDTO second) {

        try {

            switch (first.getMeasurementType()) {

                case "Length":
                    return getLengthQuantity(first)
                            .equals(getLengthQuantity(second));

                case "Weight":
                    return getWeightQuantity(first)
                            .equals(getWeightQuantity(second));

                case "Volume":
                    return getVolumeQuantity(first)
                            .equals(getVolumeQuantity(second));

                case "Temperature":
                    return getTemperatureQuantity(first)
                            .equals(getTemperatureQuantity(second));

                default:
                    throw new QuantityMeasurementException(
                            "Invalid Measurement Type"
                    );
            }

        } catch (Exception e) {

            throw new QuantityMeasurementException(
                    "Comparison Failed",
                    e
            );
        }
    }

    @Override
    public QuantityDTO convert(
            QuantityDTO quantity,
            String targetUnit) {

        try {

            switch (quantity.getMeasurementType()) {

                case "Length": {

                    Quantity<LengthUnit> result =
                            getLengthQuantity(quantity)
                                    .convertTo(
                                            (LengthUnit) LengthUnit.FEET
                                                    .getUnitInstance(targetUnit)
                                    );

                    QuantityDTO dto =
                            convertQuantityToDTO(result);

                    saveOperation(
                            quantity,
                            null,
                            "CONVERT",
                            dto
                    );

                    return dto;
                }

                case "Weight": {

                    Quantity<WeightUnit> result =
                            getWeightQuantity(quantity)
                                    .convertTo(
                                            (WeightUnit) WeightUnit.GRAM
                                                    .getUnitInstance(targetUnit)
                                    );

                    QuantityDTO dto =
                            convertQuantityToDTO(result);

                    saveOperation(
                            quantity,
                            null,
                            "CONVERT",
                            dto
                    );

                    return dto;
                }

                case "Volume": {

                    Quantity<VolumeUnit> result =
                            getVolumeQuantity(quantity)
                                    .convertTo(
                                            (VolumeUnit) VolumeUnit.LITRE
                                                    .getUnitInstance(targetUnit)
                                    );

                    QuantityDTO dto =
                            convertQuantityToDTO(result);

                    saveOperation(
                            quantity,
                            null,
                            "CONVERT",
                            dto
                    );

                    return dto;
                }

                case "Temperature": {

                    Quantity<TemperatureUnit> result =
                            getTemperatureQuantity(quantity)
                                    .convertTo(
                                            (TemperatureUnit) TemperatureUnit.CELSIUS
                                                    .getUnitInstance(targetUnit)
                                    );

                    QuantityDTO dto =
                            convertQuantityToDTO(result);

                    saveOperation(
                            quantity,
                            null,
                            "CONVERT",
                            dto
                    );

                    return dto;
                }

                default:
                    throw new QuantityMeasurementException(
                            "Invalid Measurement Type"
                    );
            }

        } catch (Exception e) {

            throw new QuantityMeasurementException(
                    "Conversion Failed",
                    e
            );
        }
    }

    @Override
    public QuantityDTO add(
            QuantityDTO first,
            QuantityDTO second,
            String targetUnit) {

        try {

            switch (first.getMeasurementType()) {

                case "Length": {

                    Quantity<LengthUnit> result =
                            getLengthQuantity(first)
                                    .add(
                                            getLengthQuantity(second),
                                            (LengthUnit) LengthUnit.FEET
                                                    .getUnitInstance(targetUnit)
                                    );

                    QuantityDTO dto =
                            convertQuantityToDTO(result);

                    saveOperation(
                            first,
                            second,
                            "ADD",
                            dto
                    );

                    return dto;
                }

                case "Weight": {

                    Quantity<WeightUnit> result =
                            getWeightQuantity(first)
                                    .add(
                                            getWeightQuantity(second),
                                            (WeightUnit) WeightUnit.GRAM
                                                    .getUnitInstance(targetUnit)
                                    );

                    QuantityDTO dto =
                            convertQuantityToDTO(result);

                    saveOperation(
                            first,
                            second,
                            "ADD",
                            dto
                    );

                    return dto;
                }

                case "Volume": {

                    Quantity<VolumeUnit> result =
                            getVolumeQuantity(first)
                                    .add(
                                            getVolumeQuantity(second),
                                            (VolumeUnit) VolumeUnit.LITRE
                                                    .getUnitInstance(targetUnit)
                                    );

                    QuantityDTO dto =
                            convertQuantityToDTO(result);

                    saveOperation(
                            first,
                            second,
                            "ADD",
                            dto
                    );

                    return dto;
                }

                case "Temperature":
                    throw new QuantityMeasurementException(
                            "Temperature does not support addition."
                    );

                default:
                    throw new QuantityMeasurementException(
                            "Invalid Measurement Type"
                    );
            }

        } catch (Exception e) {

            throw new QuantityMeasurementException(
                    "Addition Failed",
                    e
            );
        }
    }

    @Override
    public QuantityDTO subtract(
            QuantityDTO first,
            QuantityDTO second,
            String targetUnit) {

        try {

            switch (first.getMeasurementType()) {

                case "Length": {

                    Quantity<LengthUnit> result =
                            getLengthQuantity(first)
                                    .subtract(
                                            getLengthQuantity(second),
                                            (LengthUnit) LengthUnit.FEET
                                                    .getUnitInstance(targetUnit)
                                    );

                    QuantityDTO dto =
                            convertQuantityToDTO(result);

                    saveOperation(
                            first,
                            second,
                            "SUBTRACT",
                            dto
                    );

                    return dto;
                }

                case "Weight": {

                    Quantity<WeightUnit> result =
                            getWeightQuantity(first)
                                    .subtract(
                                            getWeightQuantity(second),
                                            (WeightUnit) WeightUnit.GRAM
                                                    .getUnitInstance(targetUnit)
                                    );

                    QuantityDTO dto =
                            convertQuantityToDTO(result);

                    saveOperation(
                            first,
                            second,
                            "SUBTRACT",
                            dto
                    );

                    return dto;
                }

                case "Volume": {

                    Quantity<VolumeUnit> result =
                            getVolumeQuantity(first)
                                    .subtract(
                                            getVolumeQuantity(second),
                                            (VolumeUnit) VolumeUnit.LITRE
                                                    .getUnitInstance(targetUnit)
                                    );

                    QuantityDTO dto =
                            convertQuantityToDTO(result);

                    saveOperation(
                            first,
                            second,
                            "SUBTRACT",
                            dto
                    );

                    return dto;
                }

                case "Temperature":
                    throw new QuantityMeasurementException(
                            "Temperature does not support subtraction."
                    );

                default:
                    throw new QuantityMeasurementException(
                            "Invalid Measurement Type"
                    );
            }

        } catch (Exception e) {

            throw new QuantityMeasurementException(
                    "Subtraction Failed",
                    e
            );
        }
    }

    @Override
    public double divide(
            QuantityDTO first,
            QuantityDTO second) {

        try {

            switch (first.getMeasurementType()) {

                case "Length":
                    return getLengthQuantity(first)
                            .divide(getLengthQuantity(second));

                case "Weight":
                    return getWeightQuantity(first)
                            .divide(getWeightQuantity(second));

                case "Volume":
                    return getVolumeQuantity(first)
                            .divide(getVolumeQuantity(second));

                case "Temperature":
                    throw new QuantityMeasurementException(
                            "Temperature does not support division."
                    );

                default:
                    throw new QuantityMeasurementException(
                            "Invalid Measurement Type"
                    );
            }

        } catch (Exception e) {

            throw new QuantityMeasurementException(
                    "Division Failed",
                    e
            );
        }
    }
    private Quantity<LengthUnit> getLengthQuantity(
            QuantityDTO dto) {

        return new Quantity<>(
                dto.getValue(),
                (LengthUnit) LengthUnit.FEET
                        .getUnitInstance(dto.getUnit())
        );
    }
    private Quantity<WeightUnit> getWeightQuantity(
            QuantityDTO dto) {

        return new Quantity<>(
                dto.getValue(),
                (WeightUnit) WeightUnit.GRAM
                        .getUnitInstance(dto.getUnit())
        );
    }
    private Quantity<VolumeUnit> getVolumeQuantity(
            QuantityDTO dto) {

        return new Quantity<>(
                dto.getValue(),
                (VolumeUnit) VolumeUnit.LITRE
                        .getUnitInstance(dto.getUnit())
        );
    }
    private Quantity<TemperatureUnit> getTemperatureQuantity(
            QuantityDTO dto) {

        return new Quantity<>(
                dto.getValue(),
                (TemperatureUnit) TemperatureUnit.CELSIUS
                        .getUnitInstance(dto.getUnit())
        );
    }
    private QuantityDTO convertQuantityToDTO(
            Quantity<? extends IMeasurable> quantity) {

        return new QuantityDTO(
                quantity.getValue(),
                quantity.getUnit().getUnitName(),
                quantity.getUnit().getMeasurementType()
        );
    }
    private void saveOperation(
            QuantityDTO first,
            QuantityDTO second,
            String operation,
            QuantityDTO result) {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        first,
                        second,
                        operation,
                        result
                );

        repository.save(entity);
    }
}