package org.live.humanresourcemangandpayrollsys.helpers;

import org.live.humanresourcemangandpayrollsys.model.Employee;

public class PayrollCalculatorHelper
{
    public static double calculateGrossSalary(Employee employee) {
        return employee.getBaseSalary();    // TODO: extend with bonus/overtime logic.
    }

    public static double calculateDeductions(double grossSalary) {
        return grossSalary * 0.2;   // assuming taxes is 20%.
    }

    public static double calculateNetSalary(double grossSalary, double deductions) {
        return grossSalary - deductions;
    }
}
