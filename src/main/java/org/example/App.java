package org.example;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args){
        List<Employee> employees = CRUD.getEmployee("SELECT * FROM employees");
        System.out.println(employees);

//        Employee employee = new Employee();
//        employee.setName("Мария");
//        employee.setSurname("Иванова");
//        System.out.println(CRUD.createEmployee(employee));
//        System.out.println(CRUD.updateEmployee(2, "Sodova"));





















    }

}
