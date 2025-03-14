package org.example;

import java.util.List;

public class App {
    public static void main(String[] args) {

        List<Employee> employees = CRUD.getEmployee();
        System.out.println(employees);



    }

}
