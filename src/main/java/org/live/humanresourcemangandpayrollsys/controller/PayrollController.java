package org.live.humanresourcemangandpayrollsys.controller;

import org.live.humanresourcemangandpayrollsys.helpers.PayrollCalculatorHelper;
import org.live.humanresourcemangandpayrollsys.model.Employee;
import org.live.humanresourcemangandpayrollsys.model.Payroll;

import java.util.ArrayList;
import java.util.List;

public class PayrollController
{
    private final List<Employee> employees;
    private final List<Payroll> payrolls;

    public PayrollController(List<Employee> employees) {
        this.employees = employees;
        this.payrolls = new ArrayList<>();
    }

    // Process payroll for all employees in the system, calculates gross salary, deductions, and net salary for each employee.
    public void processPayrolls()
    {
        payrolls.clear();
        for (Employee employee : employees)
        {
            double grossSalary = PayrollCalculatorHelper.calculateGrossSalary(employee);
            double deductions = PayrollCalculatorHelper.calculateDeductions(grossSalary);
            double netSalary = PayrollCalculatorHelper.calculateNetSalary(grossSalary, deductions);

            // Create and add a payroll entry for the current employee
            Payroll payroll = new Payroll(employee.getEmployeeId(), grossSalary, deductions, netSalary);
            payrolls.add(payroll);
        }
    }

    // Return list of procesed payrolls
    public List<Payroll> getPayrolls()
    {
        return payrolls;
    }

    // returns a payroll report as a string.
    public String generatePayrollReport() {
        StringBuilder report = new StringBuilder("Payroll Report\n");
        report.append("==================================");
        for (Payroll payroll : payrolls) {
            report.append("Employee ID: ").append(payroll.getEmployeeID()).append("\n");
            report.append("Gross Salary: $").append(String.format("%.2f", payroll.getGrossSalary())).append("\n");
            report.append("Deductions: $").append(String.format("%.2f", payroll.getDeductions())).append("\n");
            report.append("Net Salary: $").append(String.format("%.2f", payroll.getNetSalary())).append("\n");
            report.append("==================================");
        }
        return report.toString();
    }
}
