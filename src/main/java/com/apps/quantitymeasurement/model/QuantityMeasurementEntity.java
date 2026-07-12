package com.apps.quantitymeasurement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "quantity_measurement_entity",
        indexes = {
                @Index(name = "idx_operation", columnList = "operation"),
                @Index(name = "idx_measurement_type", columnList = "this_measurement_type"),
                @Index(name = "idx_created_at", columnList = "created_at")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "this_value", nullable = false)
        private double thisValue;

        @Column(name = "this_unit", nullable = false)
        private String thisUnit;

        @Column(name = "this_measurement_type", nullable = false)
        private String thisMeasurementType;

        @Column(name = "that_value")
        private Double thatValue;

        @Column(name = "that_unit")
        private String thatUnit;

        @Column(name = "that_measurement_type")
        private String thatMeasurementType;

        @Column(name = "operation", nullable = false)
        private String operation;

        @Column(name = "result_value")
        private Double resultValue;

        @Column(name = "result_unit")
        private String resultUnit;

        @Column(name = "result_measurement_type")
        private String resultMeasurementType;

        @Column(name = "result_string")
        private String resultString;

        @Column(name = "is_error")
        private boolean isError;

        @Column(name = "error_message")
        private String errorMessage;

        @Column(name = "created_at", nullable = false, updatable = false)
        private LocalDateTime createdAt;

        @Column(name = "updated_at", nullable = false)
        private LocalDateTime updatedAt;

        @PrePersist
        protected void onCreate() {
                createdAt = LocalDateTime.now();
                updatedAt = LocalDateTime.now();
        }

        @PreUpdate
        protected void onUpdate() {
                updatedAt = LocalDateTime.now();
        }
}