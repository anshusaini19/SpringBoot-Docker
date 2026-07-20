package com.apps.quantitymeasurement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuantityMeasurementApplication {

    public static void main(String[] args) {
        System.out.println("CLIENT ID = " + System.getenv("GOOGLE_CLIENT_ID"));
        System.out.println("CLIENT SECRET = " + System.getenv("GOOGLE_CLIENT_SECRET"));


        SpringApplication.run(
                QuantityMeasurementApplication.class,
                args
        );

    }

}