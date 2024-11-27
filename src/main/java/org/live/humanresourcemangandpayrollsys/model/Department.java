package org.live.humanresourcemangandpayrollsys.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Department implements Serializable
{
    private String departmentId;
    private String name;
    private List<Employee> employees;

    public Department(String departmentId, String name)
    {
        this.departmentId = departmentId;
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    // Setters

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public double getTotalPayrollCosts() {
        return employees.stream().mapToDouble(Employee::getBaseSalary).sum();
    }

    public double getAverageSalary() {
        return employees.isEmpty() ? 0 : getTotalPayrollCosts() / employees.size();
    }

    @Override
    public String toString() {
        return "Department [departmentId=" + departmentId + ", name=" + name + ", Total Employees in Dept: " + employees.size() + "]";
    }



}
