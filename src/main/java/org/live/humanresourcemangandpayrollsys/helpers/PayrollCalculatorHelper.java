package org.live.humanresourcemangandpayrollsys.helpers;

import org.live.humanresourcemangandpayrollsys.model.Employee;

public class PayrollCalculatorHelper {
    public static double calculateGrossSalary(Employee employee, double hoursWorked, double overtimeHours, double bonuses) {
        double hourlyRate = employee.getBaseSalary() / 160; // 160 working hours per month
        double overtimeRate = hourlyRate * 1.5;
        return (hoursWorked * hourlyRate) + (overtimeHours * overtimeRate) + bonuses;
    }

    public static double calculateDeductions(double grossSalary) {
        return grossSalary * 0.2; // assuming taxes is 20%.
    }

    public static double calculateNetSalary(double grossSalary, double deductions) {
        return grossSalary - deductions;
    }
}
