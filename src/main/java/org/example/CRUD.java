package org.example;

import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public final class CRUD {

    private CRUD() {
        throw new IllegalStateException("Utility class");
    }


    public static List<Employee> getEmployee() {
        List<Employee> employees = new ArrayList<>();
        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {
            employees = session.createQuery("FROM Employee", Employee.class)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static Employee createEmployee(Employee employee) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    public static Employee updateEmployee(int id, String newSurname) {
        Employee employee = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            employee = session.get(Employee.class, id);

            if (employee != null) {
                employee.setSurname(newSurname);
                session.merge(employee);
            } else {
                throw new RuntimeException("Employee with id" + id + "not faund");
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update");
        }
        return employee;
    }

    public static void deleteEmployee(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, id);

            if (employee != null) {
                session.remove(employee);
            } else {
                throw new RuntimeException("Employee wiht id" + id + "not faund");
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete");
        }
    }
}
