package org.live.humanresourcemangandpayrollsys.controller;

import org.live.humanresourcemangandpayrollsys.helpers.PayrollCalculatorHelper;
import org.live.humanresourcemangandpayrollsys.model.Department;
import org.live.humanresourcemangandpayrollsys.model.Employee;
import org.live.humanresourcemangandpayrollsys.model.Payroll;

import java.util.ArrayList;
import java.util.List;

public class PayrollController {
    private final List<Payroll> payrolls;

    public PayrollController() {
        payrolls = new ArrayList<>();
    }

    public void processPayrollForEmployee(Employee employee, double hoursWorked, double overtimeHours, double bonuses) {
        double grossSalary = PayrollCalculatorHelper.calculateGrossSalary(employee, hoursWorked, overtimeHours, bonuses);
        double deductions = PayrollCalculatorHelper.calculateDeductions(grossSalary);
        double netSalary = PayrollCalculatorHelper.calculateNetSalary(grossSalary, deductions);

        Payroll payroll = new Payroll(employee.getEmployeeId(), grossSalary, deductions, netSalary);
        payrolls.add(payroll);
    }

    public List<Payroll> getPayrollsForDepartment(Department department) {
        List<Payroll> departmentPayrolls = new ArrayList<>();
        for (Payroll payroll : payrolls) {
            if (payroll.getEmployeeID().startsWith(department.getDepartmentId())) {
                departmentPayrolls.add(payroll);
            }
        }
        return departmentPayrolls;
    }

    public Payroll getPayrollForEmployee(String employeeId) {
        for (Payroll payroll : payrolls) {
            if (payroll.getEmployeeID().equals(employeeId)) {
                return payroll;
            }
        }
        return null;
    }

    public void generatePayrollReportForIndividual(String employeeId) {
        Payroll payroll = getPayrollForEmployee(employeeId);
        if (payroll != null) {
            System.out.println(payroll.toString());
        } else {
            System.out.println("No payroll found for employee ID: " + employeeId);
        }
    }

    public void generatePayrollReportForDepartment(Department department) {
        List<Payroll> departmentPayrolls = getPayrollsForDepartment(department);
        if (departmentPayrolls.isEmpty()) {
            System.out.println("No payrolls found for department: " + department.getName());
            return;
        }

        System.out.println("Payroll Report for Department: " + department.getName());
        for (Payroll payroll : departmentPayrolls) {
            System.out.println(payroll.toString());
        }
    }
}
