package org.example;

import java.util.List;

public class App {
    public static void main(String[] args) {

        CRUD.createEmployee(new Employee(0, "Иванова", "Дарья"));

        List<Employee> employees = CRUD.getEmployee();
        System.out.println(employees);



    }

}
