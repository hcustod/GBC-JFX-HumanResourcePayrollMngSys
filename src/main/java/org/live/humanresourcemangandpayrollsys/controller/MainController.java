package org.live.humanresourcemangandpayrollsys.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.live.humanresourcemangandpayrollsys.helpers.FileHelper;
import org.live.humanresourcemangandpayrollsys.helpers.PayrollCalculatorHelper;
import org.live.humanresourcemangandpayrollsys.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class MainController
{

    // Employee Management
    @FXML private TextField employeeIDTextField, firstNameTextField, lastNameTextField, roleTextField, baseSalaryTextField;
    @FXML private Button clearEmployeeButton, saveEmployeeButton;
    @FXML private TextArea outputBox;

    // Payroll Processing
    @FXML private TextField payrollEmployeeIDTextField, hoursWorkedTxtField, overtimeHoursTxtField, payrollBonusesTxtField;
    @FXML private Button payrollClearBttn, payrollSaveBttn;
    @FXML private TextArea grossSalaryOutput, deductionsOutput, netSalaryOutput;

    // Reports View
    @FXML private TextField reportsEmployeeID;
    @FXML private TextArea reportsViewOutput;
    @FXML private Button reportsSubmitEmployee, reportsSubmitDepartment;

    private final List<Employee> employees = new ArrayList<>();
    private static final String EMPLOYEE_FILE = "employees.ser";

    // Initialize method to bind button actions
    @FXML
    public void initialize()
    {
        System.out.println("Controller initialized");
        clearEmployeeButton.setOnAction(event -> onClearEmployee());
        saveEmployeeButton.setOnAction(event -> onSaveEmployee());
        payrollClearBttn.setOnAction(event -> onPayrollClear());
        payrollSaveBttn.setOnAction(event -> onPayrollSave());
        reportsSubmitEmployee.setOnAction(event -> onReportsSubmitEmployee());
        reportsSubmitDepartment.setOnAction(event -> onReportsSubmitDepartment());

    }

    // Employee Management Handlers
    @FXML
    private void onClearEmployee()
    {
        employeeIDTextField.clear();
        firstNameTextField.clear();
        lastNameTextField.clear();
        roleTextField.clear();
        baseSalaryTextField.clear();
    }

    @FXML
    private void onSaveEmployee()
    {
        try
        {
            String id = employeeIDTextField.getText();
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String role = roleTextField.getText();
            double baseSalary = Double.parseDouble(baseSalaryTextField.getText());

            Employee employee = new Employee(id, firstName, lastName, role, baseSalary);
            employees.add(employee);
            FileHelper.saveToFile(EMPLOYEE_FILE, employees);
            outputBox.appendText("Employee saved: " + employee + "\n");
        } catch (Exception e)
        {
            outputBox.appendText("Error saving employee: " + e.getMessage() + "\n");
        }
    }

    // Payroll Processing Handlers
    @FXML
    private void onPayrollClear()
    {
        payrollEmployeeIDTextField.clear();
        hoursWorkedTxtField.clear();
        overtimeHoursTxtField.clear();
        payrollBonusesTxtField.clear();
    }

    @FXML
    private void onPayrollSave()
    {
        try
        {
            String id = payrollEmployeeIDTextField.getText();
            double hoursWorked = Double.parseDouble(hoursWorkedTxtField.getText());
            double overtimeHours = Double.parseDouble(overtimeHoursTxtField.getText());
            double bonuses = Double.parseDouble(payrollBonusesTxtField.getText());

            Employee employee = employees.stream()
                    .filter(e -> e.getEmployeeId().equals(id))
                    .findFirst()
                    .orElse(null);

            if (employee == null)
            {
                outputBox.appendText("Employee not found.\n");
                return;
            }

            double grossSalary = PayrollCalculatorHelper.calculateGrossSalary(employee, hoursWorked, overtimeHours, bonuses);
            double deductions = PayrollCalculatorHelper.calculateDeductions(grossSalary);
            double netSalary = PayrollCalculatorHelper.calculateNetSalary(grossSalary, deductions);

            grossSalaryOutput.setText(String.format("%.2f", grossSalary));
            deductionsOutput.setText(String.format("%.2f", deductions));
            netSalaryOutput.setText(String.format("%.2f", netSalary));

            outputBox.appendText(String.format(
                    "Payroll processed for %s: Gross: %.2f, Deductions: %.2f, Net: %.2f\n",
                    employee.getFullName(), grossSalary, deductions, netSalary
            ));
        }
        catch (Exception e)
        {
            outputBox.appendText("Error processing payroll: " + e.getMessage() + "\n");
        }
    }

    // Reports View Handlers
    @FXML
    private void onReportsSubmitEmployee()
    {
        try
        {
            String id = reportsEmployeeID.getText();
            Employee employee = employees.stream()
                    .filter(e -> e.getEmployeeId().equals(id))
                    .findFirst()
                    .orElse(null);

            if (employee != null)
            {
                reportsViewOutput.setText(employee.toString());
            } else
            {
                reportsViewOutput.setText("Employee not found.\n");
            }
        } catch (Exception e)
        {
            reportsViewOutput.setText("Error fetching employee data: " + e.getMessage() + "\n");
        }
    }

    @FXML
    private void onReportsSubmitDepartment()
    {
        // Add logic for department-based reports here
        reportsViewOutput.appendText("Department reports functionality not implemented yet.\n");
    }
}
