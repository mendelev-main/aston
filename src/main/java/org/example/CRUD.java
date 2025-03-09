package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUD {


    public static List<Employee> getEmployee(String query) {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");

                employees.add(new Employee(id, name, surname));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;


    }


    public static Employee createEmployee (Employee employee) {

        String INSERT_EMPLOYEE = "INSERT INTO employees(name, surname) VALUES (?, ?)";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;

    }

    public static Employee updateEmployee(int idEmployee, String newSurname) {
        String UPDATE_EMPLOYEE = "UPDATE employees SET surname = ? WHERE id = ?";
        String SELECT_UPDATED_EMPLOYEE = "SELECT * FROM employees WHERE id = ?";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(UPDATE_EMPLOYEE);
             PreparedStatement selectStatement = connection.prepareStatement(SELECT_UPDATED_EMPLOYEE)) {


            updateStatement.setString(1, newSurname);
            updateStatement.setInt(2, idEmployee);
            int rowsUpdated = updateStatement.executeUpdate();


            if (rowsUpdated == 0) {
                throw new RuntimeException("Employee with id " + idEmployee + " not found.");
            }


            selectStatement.setInt(1, idEmployee);
            ResultSet resultSet = selectStatement.executeQuery();


            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");

                return new Employee(id, name, surname);
            } else {
                throw new RuntimeException("Failed to retrieve updated employee.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteEmployee(int idEmployee) {

        String UPDATE_EMPLOYEE = "DELETE FROM employees WHERE id = ?";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE)) {
            preparedStatement.setInt(1, idEmployee);
            preparedStatement.executeUpdate();

            System.out.println(getEmployee("SELECT * FROM employees"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        getEmployee("SELECT * FROM employees");
    }

}
