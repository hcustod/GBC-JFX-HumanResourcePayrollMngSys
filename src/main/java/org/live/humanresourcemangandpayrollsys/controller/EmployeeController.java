package org.live.humanresourcemangandpayrollsys.controller;

import org.live.humanresourcemangandpayrollsys.helpers.FileHelper;
import org.live.humanresourcemangandpayrollsys.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeController
{
    private final List<Employee> employees;

    public EmployeeController() {
        List<Employee> loadedEmployees = FileHelper.loadFromFile("employees.dat");
        this.employees = (loadedEmployees != null) ? loadedEmployees : new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        saveEmployees();
    }

    public void removeEmployee(Employee employee) {
        employees.removeIf(emp -> emp.getEmployeeId() == employee.getEmployeeId());
        saveEmployees();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    private void saveEmployees() {
        FileHelper.saveToFile("employees.dat", employees);
    }
}
