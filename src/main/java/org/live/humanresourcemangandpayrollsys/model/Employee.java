package org.live.humanresourcemangandpayrollsys.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Employee implements Serializable {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String role;
    private Department department;
    private double baseSalary;
    private List<String> attendanceRecords;

    public Employee(String employeeId, String firstName, String lastName, String role, double baseSalary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.baseSalary = baseSalary;
        this.attendanceRecords = new ArrayList<>();
    }

    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    public double getBaseSalary() {
        return baseSalary;
    }
    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public List<String> getAttendanceRecords() {
        return new ArrayList<>(attendanceRecords);
    }
    public void addAttendance(String date) {
        attendanceRecords.add(date);
    }
    public void clearAttendance() {
        attendanceRecords.clear();
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId +
                ", firstName=" + firstName + ", lastName=" + lastName +
                ", role=" + role + ", department=" + department +
                ", baseSalary=" + baseSalary + ", attendanceRecords=" + attendanceRecords + "]";
    }
}
