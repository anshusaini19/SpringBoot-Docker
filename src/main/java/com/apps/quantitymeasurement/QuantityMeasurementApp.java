package com.apps.quantitymeasurement;
import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.entity.QuantityDTO;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        IQuantityMeasurementRepository repository =
                QuantityMeasurementCacheRepository.getInstance();

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repository);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        // ==========================
        // LENGTH EXAMPLES
        // ==========================

        QuantityDTO feetDTO =
                new QuantityDTO(
                        1.0,
                        "FEET",
                        "Length"
                );

        QuantityDTO inchesDTO =
                new QuantityDTO(
                        12.0,
                        "INCHES",
                        "Length"
                );

        System.out.println(
                "Length Equality : "
                        + controller.compare(
                        feetDTO,
                        inchesDTO
                )
        );

        QuantityDTO convertedLength =
                controller.convert(
                        feetDTO,
                        "INCHES"
                );

        System.out.println(
                "Length Conversion : "
                        + convertedLength
        );

        QuantityDTO addedLength =
                controller.add(
                        feetDTO,
                        inchesDTO,
                        "FEET"
                );

        System.out.println(
                "Length Addition : "
                        + addedLength
        );
        QuantityDTO length1 =
                new QuantityDTO(
                        10.0,
                        "FEET",
                        "Length"
                );

        QuantityDTO length2 =
                new QuantityDTO(
                        6.0,
                        "INCHES",
                        "Length"
                );

        QuantityDTO subtractedLength =
                controller.subtract(
                        length1,
                        length2,
                        "FEET"
                );

        System.out.println(
                "Length Subtraction : "
                        + subtractedLength
        );

        QuantityDTO length3 =
                new QuantityDTO(
                        24.0,
                        "INCHES",
                        "Length"
                );

        QuantityDTO length4 =
                new QuantityDTO(
                        2.0,
                        "FEET",
                        "Length"
                );

        System.out.println(
                "Length Division : "
                        + controller.divide(
                        length3,
                        length4
                )
        );
        // ==========================
        // WEIGHT EXAMPLES
        // ==========================

        QuantityDTO kilogramDTO =
                new QuantityDTO(
                        1.0,
                        "KILOGRAM",
                        "Weight"
                );

        QuantityDTO gramDTO =
                new QuantityDTO(
                        1000.0,
                        "GRAM",
                        "Weight"
                );

        System.out.println(
                "Weight Equality : "
                        + controller.compare(
                        kilogramDTO,
                        gramDTO
                )
        );

        System.out.println(
                "Weight Conversion : "
                        + controller.convert(
                        kilogramDTO,
                        "GRAM"
                )
        );

        System.out.println(
                "Weight Addition : "
                        + controller.add(
                        kilogramDTO,
                        gramDTO,
                        "KILOGRAM"
                )
        );

        QuantityDTO weight1 =
                new QuantityDTO(
                        10.0,
                        "KILOGRAM",
                        "Weight"
                );

        QuantityDTO weight2 =
                new QuantityDTO(
                        5000.0,
                        "GRAM",
                        "Weight"
                );

        System.out.println(
                "Weight Subtraction : "
                        + controller.subtract(
                        weight1,
                        weight2,
                        "KILOGRAM"
                )
        );

        QuantityDTO weight3 =
                new QuantityDTO(
                        10.0,
                        "KILOGRAM",
                        "Weight"
                );

        QuantityDTO weight4 =
                new QuantityDTO(
                        5.0,
                        "KILOGRAM",
                        "Weight"
                );

        System.out.println(
                "Weight Division : "
                        + controller.divide(
                        weight3,
                        weight4
                )
        );

        // ==========================
        // VOLUME EXAMPLES
        // ==========================

        QuantityDTO litreDTO =
                new QuantityDTO(
                        1.0,
                        "LITRE",
                        "Volume"
                );

        QuantityDTO milliLitreDTO =
                new QuantityDTO(
                        1000.0,
                        "MILLILITRE",
                        "Volume"
                );

        System.out.println(
                "Volume Equality : "
                        + controller.compare(
                        litreDTO,
                        milliLitreDTO
                )
        );

        System.out.println(
                "Volume Conversion : "
                        + controller.convert(
                        litreDTO,
                        "MILLILITRE"
                )
        );

        System.out.println(
                "Volume Addition : "
                        + controller.add(
                        litreDTO,
                        milliLitreDTO,
                        "LITRE"
                )
        );

        QuantityDTO volume1 =
                new QuantityDTO(
                        5.0,
                        "LITRE",
                        "Volume"
                );

        QuantityDTO volume2 =
                new QuantityDTO(
                        500.0,
                        "MILLILITRE",
                        "Volume"
                );

        System.out.println(
                "Volume Subtraction : "
                        + controller.subtract(
                        volume1,
                        volume2,
                        "LITRE"
                )
        );

        QuantityDTO volume3 =
                new QuantityDTO(
                        1000.0,
                        "MILLILITRE",
                        "Volume"
                );

        QuantityDTO volume4 =
                new QuantityDTO(
                        1.0,
                        "LITRE",
                        "Volume"
                );

        System.out.println(
                "Volume Division : "
                        + controller.divide(
                        volume3,
                        volume4
                )
        );

        // ==========================
        // TEMPERATURE EXAMPLES
        // ==========================

        QuantityDTO celsiusDTO =
                new QuantityDTO(
                        100.0,
                        "CELSIUS",
                        "Temperature"
                );

        QuantityDTO fahrenheitDTO =
                new QuantityDTO(
                        212.0,
                        "FAHRENHEIT",
                        "Temperature"
                );

        QuantityDTO kelvinDTO =
                new QuantityDTO(
                        373.15,
                        "KELVIN",
                        "Temperature"
                );

        System.out.println(
                "Temperature Equality (Celsius vs Fahrenheit) : "
                        + controller.compare(
                        celsiusDTO,
                        fahrenheitDTO
                )
        );

        System.out.println(
                "Temperature Equality (Celsius vs Kelvin) : "
                        + controller.compare(
                        celsiusDTO,
                        kelvinDTO
                )
        );

        System.out.println(
                "Temperature Conversion (Celsius -> Fahrenheit) : "
                        + controller.convert(
                        celsiusDTO,
                        "FAHRENHEIT"
                )
        );

        System.out.println(
                "Temperature Conversion (Fahrenheit -> Celsius) : "
                        + controller.convert(
                        fahrenheitDTO,
                        "CELSIUS"
                )
        );

        System.out.println(
                "Temperature Conversion (Kelvin -> Celsius) : "
                        + controller.convert(
                        kelvinDTO,
                        "CELSIUS"
                )
        );

// ==========================================
// UC14 NEW
// Unsupported Arithmetic Demonstration
// ==========================================

        try {

            System.out.println(
                    controller.add(
                            celsiusDTO,
                            new QuantityDTO(
                                    50.0,
                                    "CELSIUS",
                                    "Temperature"
                            ),
                            "CELSIUS"
                    )
            );

        } catch (Exception e) {

            System.out.println(
                    "Temperature Addition : "
                            + e.getMessage()
            );
        }

        try {

            System.out.println(
                    controller.subtract(
                            celsiusDTO,
                            new QuantityDTO(
                                    50.0,
                                    "CELSIUS",
                                    "Temperature"
                            ),
                            "CELSIUS"
                    )
            );

        } catch (Exception e) {

            System.out.println(
                    "Temperature Subtraction : "
                            + e.getMessage()
            );
        }

        try {

            System.out.println(
                    controller.divide(
                            celsiusDTO,
                            new QuantityDTO(
                                    50.0,
                                    "CELSIUS",
                                    "Temperature"
                            )
                    )
            );

        } catch (Exception e) {

            System.out.println(
                    "Temperature Division : "
                            + e.getMessage()
            );
        }
    }
}