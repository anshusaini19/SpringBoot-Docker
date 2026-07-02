package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.entity.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.exception.DatabaseException;
import com.apps.quantitymeasurement.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {
    private static final String INSERT_QUERY =
            """
            INSERT INTO quantity_measurement_entity
            (
                this_value,
                this_unit,
                this_measurement_type,
                that_value,
                that_unit,
                that_measurement_type,
                operation,
                result_value,
                result_unit,
                result_measurement_type,
                is_error,
                error_message
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
    private static final String SELECT_ALL_QUERY =
            """
            SELECT *
            FROM quantity_measurement_entity
            ORDER BY id
            """;

    public QuantityMeasurementDatabaseRepository() {

    }
    private void setQuantity(
            PreparedStatement statement,
            int startIndex,
            QuantityDTO quantity
    ) throws SQLException {

        if (quantity == null) {

            statement.setNull(startIndex, java.sql.Types.DOUBLE);
            statement.setNull(startIndex + 1, java.sql.Types.VARCHAR);
            statement.setNull(startIndex + 2, java.sql.Types.VARCHAR);

            return;
        }

        statement.setDouble(
                startIndex,
                quantity.getValue()
        );

        statement.setString(
                startIndex + 1,
                quantity.getUnit()
        );

        statement.setString(
                startIndex + 2,
                quantity.getMeasurementType()
        );
    }
    private QuantityDTO createQuantity(
            double value,
            String unit,
            String measurementType
    ) {

        if (unit == null ||
                measurementType == null) {

            return null;
        }

        return new QuantityDTO(
                value,
                unit,
                measurementType
        );
    }
    private QuantityMeasurementEntity mapResultSet(
            ResultSet resultSet
    ) throws SQLException {

        QuantityDTO firstQuantity =
                createQuantity(
                        resultSet.getDouble("this_value"),
                        resultSet.getString("this_unit"),
                        resultSet.getString("this_measurement_type")
                );

        QuantityDTO secondQuantity =
                createQuantity(
                        resultSet.getDouble("that_value"),
                        resultSet.getString("that_unit"),
                        resultSet.getString("that_measurement_type")
                );

        QuantityDTO result =
                createQuantity(
                        resultSet.getDouble("result_value"),
                        resultSet.getString("result_unit"),
                        resultSet.getString("result_measurement_type")
                );

        if (resultSet.getBoolean("is_error")) {

            return new QuantityMeasurementEntity(
                    resultSet.getString("operation"),
                    resultSet.getString("error_message")
            );
        }

        return new QuantityMeasurementEntity(
                firstQuantity,
                secondQuantity,
                resultSet.getString("operation"),
                result
        );
    }

    @Override
    public void save(
            QuantityMeasurementEntity entity
    ) {

        try (
                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(
                                INSERT_QUERY
                        )
        ) {
            setQuantity(statement, 1, entity.getFirstQuantity());

            setQuantity(statement, 4, entity.getSecondQuantity());

            statement.setString(7, entity.getOperation());

            setQuantity(statement, 8, entity.getResult());

            statement.setBoolean(11, entity.isError());

            statement.setString(12, entity.getErrorMessage());

            statement.executeUpdate();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Unable to save measurement.",
                    e
            );
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {

        List<QuantityMeasurementEntity> measurements =
                new ArrayList<>();

        try (
                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(
                                SELECT_ALL_QUERY
                        );

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                measurements.add(
                        mapResultSet(resultSet)
                );
            }

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Unable to fetch measurements.",
                    e
            );
        }

        return measurements;
    }
    @Override
    public int getTotalCount() {

        String sql = "SELECT COUNT(*) FROM quantity_measurement_entity";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            return 0;

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Unable to count measurements.",
                    e
            );
        }
    }
    @Override
    public void deleteAll() {

        String sql = "DELETE FROM quantity_measurement_entity";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.executeUpdate();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Unable to delete measurements.",
                    e
            );
        }
    }

}