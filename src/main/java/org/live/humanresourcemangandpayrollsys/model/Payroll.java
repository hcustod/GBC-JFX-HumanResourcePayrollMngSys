package org.live.humanresourcemangandpayrollsys.model;

import java.io.Serializable;

public class Payroll implements Serializable
{
    private String employeeID;
    private double grossSalary;
    private double deductions;
    private double netSalary;

    public Payroll(String employeeID, Double grossSalary ,Double deductions, Double netSalary)
    {
        this.employeeID = employeeID;
        this.grossSalary = grossSalary;
        this.deductions = deductions;
        this.netSalary = netSalary;
    }

    // Getters and setters
    public String getEmployeeID() { return employeeID; }
    public void setEmployeeID(String employeeID) { this.employeeID = employeeID; }

    public double getGrossSalary() { return grossSalary; }
    public void setGrossSalary(double grossSalary) { this.grossSalary = grossSalary;}

    public double getDeductions() { return deductions; }
    public void setDeductions(double deductions) { this.deductions = deductions; }

    public double getNetSalary() { return netSalary; }
    public void setNetSalary(double netSalary) { this.netSalary = netSalary; }
    

    @Override
    public String toString() {
        return "Employee ID: " + employeeID +
                "- Gross Salary: " + grossSalary +
                "- Deductions: " + deductions +
                "- Net Salary: " + netSalary;
    }
}
