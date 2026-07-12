package com.apps.quantitymeasurement.service;
import com.apps.quantitymeasurement.enums.OperationType;
import com.apps.quantitymeasurement.exception.QuantityMeasurementException;
import com.apps.quantitymeasurement.model.QuantityDTO;
import com.apps.quantitymeasurement.model.QuantityMeasurementDTO;
import com.apps.quantitymeasurement.model.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.apps.quantitymeasurement.Quantity;
import com.apps.quantitymeasurement.IMeasurable;

import com.apps.quantitymeasurement.enums.LengthUnit;
import com.apps.quantitymeasurement.enums.WeightUnit;
import com.apps.quantitymeasurement.enums.VolumeUnit;
import com.apps.quantitymeasurement.enums.TemperatureUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;
    private QuantityMeasurementDTO saveSuccess(
            QuantityMeasurementEntity entity
    ) {

        QuantityMeasurementEntity saved =
                repository.save(entity);

        return QuantityMeasurementDTO.from(saved);
    }

    private QuantityMeasurementDTO saveError(
            String operation,
            String message
    ) {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setOperation(operation);
        entity.setError(true);
        entity.setErrorMessage(message);

        QuantityMeasurementEntity saved =
                repository.save(entity);

        return QuantityMeasurementDTO.from(saved);
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
    @Override
    public QuantityMeasurementDTO convert(
            QuantityDTO quantity,
            QuantityDTO targetUnitDTO) {

        try {

            QuantityDTO dto;

            switch (quantity.getMeasurementType()) {

                case "Length":

                    dto = convertQuantityToDTO(
                            getLengthQuantity(quantity)
                                    .convertTo(
                                            (LengthUnit) LengthUnit.FEET
                                                    .getUnitInstance(
                                                            targetUnitDTO.getUnit()
                                                    )
                                    )
                    );
                    break;

                case "Weight":

                    dto = convertQuantityToDTO(
                            getWeightQuantity(quantity)
                                    .convertTo(
                                            (WeightUnit) WeightUnit.GRAM
                                                    .getUnitInstance(
                                                            targetUnitDTO.getUnit()
                                                    )
                                    )
                    );
                    break;

                case "Volume":

                    dto = convertQuantityToDTO(
                            getVolumeQuantity(quantity)
                                    .convertTo(
                                            (VolumeUnit) VolumeUnit.LITRE
                                                    .getUnitInstance(
                                                            targetUnitDTO.getUnit()
                                                    )
                                    )
                    );
                    break;

                case "Temperature":

                    dto = convertQuantityToDTO(
                            getTemperatureQuantity(quantity)
                                    .convertTo(
                                            (TemperatureUnit) TemperatureUnit.CELSIUS
                                                    .getUnitInstance(
                                                            targetUnitDTO.getUnit()
                                                    )
                                    )
                    );
                    break;

                default:
                    throw new QuantityMeasurementException(
                            "Invalid Measurement Type"
                    );
            }

            QuantityMeasurementEntity entity =
                    new QuantityMeasurementEntity();

            entity.setThisValue(quantity.getValue());
            entity.setThisUnit(quantity.getUnit());
            entity.setThisMeasurementType(quantity.getMeasurementType());

            entity.setOperation(OperationType.CONVERT.name());

            entity.setResultValue(dto.getValue());
            entity.setResultUnit(dto.getUnit());
            entity.setResultMeasurementType(dto.getMeasurementType());

            entity.setError(false);

            return saveSuccess(entity);

        } catch (Exception e) {

            return saveError(
                    OperationType.CONVERT.name(),
                    e.getMessage()
            );
        }
    }
    @Override
    public QuantityMeasurementDTO compare(
            QuantityDTO first,
            QuantityDTO second) {

        try {

            boolean result;

            switch (first.getMeasurementType()) {

                case "Length":
                    result = getLengthQuantity(first)
                            .equals(getLengthQuantity(second));
                    break;

                case "Weight":
                    result = getWeightQuantity(first)
                            .equals(getWeightQuantity(second));
                    break;

                case "Volume":
                    result = getVolumeQuantity(first)
                            .equals(getVolumeQuantity(second));
                    break;

                case "Temperature":
                    result = getTemperatureQuantity(first)
                            .equals(getTemperatureQuantity(second));
                    break;

                default:
                    throw new QuantityMeasurementException(
                            "Invalid Measurement Type"
                    );
            }

            QuantityMeasurementEntity entity =
                    new QuantityMeasurementEntity();

            entity.setThisValue(first.getValue());
            entity.setThisUnit(first.getUnit());
            entity.setThisMeasurementType(first.getMeasurementType());

            entity.setThatValue(second.getValue());
            entity.setThatUnit(second.getUnit());
            entity.setThatMeasurementType(second.getMeasurementType());

            entity.setOperation(OperationType.COMPARE.name());
            entity.setResultString(result ? "Equal" : "Not Equal");
            entity.setError(false);

            return saveSuccess(entity);

        } catch (Exception e) {

            return saveError(
                    OperationType.COMPARE.name(),
                    e.getMessage()
            );
        }
    }
    @Override
    public QuantityMeasurementDTO add(
            QuantityDTO first,
            QuantityDTO second,
            QuantityDTO targetUnitDTO) {

        try {

            QuantityDTO dto;

            switch (first.getMeasurementType()) {

                case "Length":

                    dto = convertQuantityToDTO(
                            getLengthQuantity(first)
                                    .add(
                                            getLengthQuantity(second),
                                            (LengthUnit) LengthUnit.FEET
                                                    .getUnitInstance(targetUnitDTO.getUnit())
                                    )
                    );
                    break;

                case "Weight":

                    dto = convertQuantityToDTO(
                            getWeightQuantity(first)
                                    .add(
                                            getWeightQuantity(second),
                                            (WeightUnit) WeightUnit.GRAM
                                                    .getUnitInstance(targetUnitDTO.getUnit())
                                    )
                    );
                    break;

                case "Volume":

                    dto = convertQuantityToDTO(
                            getVolumeQuantity(first)
                                    .add(
                                            getVolumeQuantity(second),
                                            (VolumeUnit) VolumeUnit.LITRE
                                                    .getUnitInstance(targetUnitDTO.getUnit())
                                    )
                    );
                    break;

                default:
                    throw new QuantityMeasurementException(
                            "Addition not supported."
                    );
            }

            QuantityMeasurementEntity entity =
                    new QuantityMeasurementEntity();

            entity.setThisValue(first.getValue());
            entity.setThisUnit(first.getUnit());
            entity.setThisMeasurementType(first.getMeasurementType());

            entity.setThatValue(second.getValue());
            entity.setThatUnit(second.getUnit());
            entity.setThatMeasurementType(second.getMeasurementType());

            entity.setOperation(OperationType.ADD.name());

            entity.setResultValue(dto.getValue());
            entity.setResultUnit(dto.getUnit());
            entity.setResultMeasurementType(dto.getMeasurementType());

            entity.setError(false);

            return saveSuccess(entity);

        } catch (Exception e) {

            return saveError(
                    OperationType.ADD.name(),
                    e.getMessage()
            );
        }
    }

    @Override
    public QuantityMeasurementDTO subtract(
            QuantityDTO first,
            QuantityDTO second,
            QuantityDTO targetUnitDTO) {

        try {

            QuantityDTO dto;

            switch (first.getMeasurementType()) {

                case "Length":

                    dto = convertQuantityToDTO(
                            getLengthQuantity(first)
                                    .subtract(
                                            getLengthQuantity(second),
                                            (LengthUnit) LengthUnit.FEET
                                                    .getUnitInstance(targetUnitDTO.getUnit())
                                    )
                    );
                    break;

                case "Weight":

                    dto = convertQuantityToDTO(
                            getWeightQuantity(first)
                                    .subtract(
                                            getWeightQuantity(second),
                                            (WeightUnit) WeightUnit.GRAM
                                                    .getUnitInstance(targetUnitDTO.getUnit())
                                    )
                    );
                    break;

                case "Volume":

                    dto = convertQuantityToDTO(
                            getVolumeQuantity(first)
                                    .subtract(
                                            getVolumeQuantity(second),
                                            (VolumeUnit) VolumeUnit.LITRE
                                                    .getUnitInstance(targetUnitDTO.getUnit())
                                    )
                    );
                    break;

                default:
                    throw new QuantityMeasurementException(
                            "Subtraction not supported."
                    );
            }

            QuantityMeasurementEntity entity =
                    new QuantityMeasurementEntity();

            entity.setThisValue(first.getValue());
            entity.setThisUnit(first.getUnit());
            entity.setThisMeasurementType(first.getMeasurementType());

            entity.setThatValue(second.getValue());
            entity.setThatUnit(second.getUnit());
            entity.setThatMeasurementType(second.getMeasurementType());

            entity.setOperation(OperationType.SUBTRACT.name());

            entity.setResultValue(dto.getValue());
            entity.setResultUnit(dto.getUnit());
            entity.setResultMeasurementType(dto.getMeasurementType());

            entity.setError(false);

            return saveSuccess(entity);

        } catch (Exception e) {

            return saveError(
                    OperationType.SUBTRACT.name(),
                    e.getMessage()
            );
        }
    }
    @Override
    public QuantityMeasurementDTO subtract(
            QuantityDTO first,
            QuantityDTO second) {

        QuantityDTO target = new QuantityDTO();

        target.setUnit(first.getUnit());
        target.setMeasurementType(first.getMeasurementType());

        return subtract(
                first,
                second,
                target
        );
    }
    @Override
    public QuantityMeasurementDTO add(
            QuantityDTO first,
            QuantityDTO second) {

        QuantityDTO target = new QuantityDTO();

        target.setUnit(first.getUnit());
        target.setMeasurementType(first.getMeasurementType());

        return add(
                first,
                second,
                target
        );
    }

    @Override
    public QuantityMeasurementDTO divide(
            QuantityDTO first,
            QuantityDTO second) {

        try {

            double result;

            switch (first.getMeasurementType()) {

                case "Length":
                    result = getLengthQuantity(first)
                            .divide(getLengthQuantity(second));
                    break;

                case "Weight":
                    result = getWeightQuantity(first)
                            .divide(getWeightQuantity(second));
                    break;

                case "Volume":
                    result = getVolumeQuantity(first)
                            .divide(getVolumeQuantity(second));
                    break;

                default:
                    throw new QuantityMeasurementException(
                            "Division not supported."
                    );
            }

            QuantityMeasurementEntity entity =
                    new QuantityMeasurementEntity();

            entity.setThisValue(first.getValue());
            entity.setThisUnit(first.getUnit());
            entity.setThisMeasurementType(first.getMeasurementType());

            entity.setThatValue(second.getValue());
            entity.setThatUnit(second.getUnit());
            entity.setThatMeasurementType(second.getMeasurementType());

            entity.setOperation(OperationType.DIVIDE.name());

            entity.setResultString(String.valueOf(result));

            entity.setError(false);

            return saveSuccess(entity);

        } catch (Exception e) {

            return saveError(
                    OperationType.DIVIDE.name(),
                    e.getMessage()
            );
        }
    }
    @Override
    public List<QuantityMeasurementDTO> getOperationHistory(
            String operation) {

        return QuantityMeasurementDTO.fromList(
                repository.findByOperation(operation)
        );
    }

    @Override
    public List<QuantityMeasurementDTO> getMeasurementsByType(
            String type) {

        return QuantityMeasurementDTO.fromList(
                repository.findByThisMeasurementType(type)
        );
    }

    @Override
    public long getOperationCount(
            String operation) {

        return repository.countByOperationAndIsErrorFalse(
                operation
        );
    }

    @Override
    public List<QuantityMeasurementDTO> getErrorHistory() {

        return QuantityMeasurementDTO.fromList(
                repository.findByIsErrorTrue()
        );
    }

}