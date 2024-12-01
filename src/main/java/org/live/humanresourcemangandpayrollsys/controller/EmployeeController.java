package org.live.humanresourcemangandpayrollsys.controller;

import org.live.humanresourcemangandpayrollsys.helpers.FileHelper;
import org.live.humanresourcemangandpayrollsys.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeController {
    private final List<Employee> employees;

    public EmployeeController() {
        List<Employee> loadedEmployees = FileHelper.loadFromFile("employees.dat");
        this.employees = (loadedEmployees != null) ? loadedEmployees : new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        saveEmployees();
    }

    public void removeEmployee(String employeeId) {
        employees.removeIf(emp -> emp.getEmployeeId().equals(employeeId));
        saveEmployees();
    }

    public void updateEmployee(Employee updatedEmployee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeId().equals(updatedEmployee.getEmployeeId())) {
                employees.set(i, updatedEmployee);
                saveEmployees();
                return;
            }
        }
        System.out.println("Employee with ID " + updatedEmployee.getEmployeeId() + " not found.");
    }

    public Employee getEmployeeById(String employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equals(employeeId)) {
                return employee;
            }
        }
        return null;
    }

    public List<Employee> searchEmployees(String keyword) {
        List<Employee> results = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getFirstName().toLowerCase().contains(keyword.toLowerCase()) ||
                employee.getLastName().toLowerCase().contains(keyword.toLowerCase()) ||
                employee.getEmployeeId().contains(keyword)) {
                results.add(employee);
            }
        }
        return results;
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    private void saveEmployees() {
        FileHelper.saveToFile("employees.dat", employees);
    }
}
