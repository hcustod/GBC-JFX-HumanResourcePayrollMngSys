package org.live.humanresourcemangandpayrollsys.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.live.humanresourcemangandpayrollsys.model.Department;
import org.live.humanresourcemangandpayrollsys.model.Employee;
import org.live.humanresourcemangandpayrollsys.model.Payroll;

public class MainController {
    private DepartmentController departmentController;
    private EmployeeController employeeController;
    public PayrollController payrollController;

    @FXML
    private TextField employeeIDTextField, firstNameTextField, lastNameTextField, roleTextField, baseSalaryTextField;
    @FXML
    private TextField payrollEmployeeIDTextField, hoursWorkedTextField, overtimeHoursTxtField, payrollBonusesTxtField;
    @FXML
    private TextArea outputBox, grossSalaryOutput, deductionsOutput, netSalaryOutput, reportsViewOutput;
    @FXML
    private ComboBox<String> deptSelection;
    @FXML
    private TextField reportsEmployeeID;

    @FXML
    private void initialize() {
        departmentController = new DepartmentController();
        employeeController = new EmployeeController();
        payrollController = new PayrollController();

        loadDepartmentSelection();
    }


    private void loadDepartmentSelection() {
        deptSelection.getItems().clear();
        for (Department department : departmentController.getAllDepartments()) {
            deptSelection.getItems().add(department.getName() + " (" + department.getDepartmentId() + ")");

        }
    }

    @FXML
    private void handleAddEmployee() {
        try {
            String employeeID = employeeIDTextField.getText();
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String role = roleTextField.getText();
            double baseSalary = Double.parseDouble(baseSalaryTextField.getText());
            String departmentID = extractDepartmentID(deptSelection.getValue());

            Employee newEmployee = new Employee(employeeID, firstName, lastName, role, baseSalary);

            Department department = departmentController.getDepartmentById(departmentID);
            if (department == null) {
                showError("Department not found for ID: " + departmentID);
                return;
            }

            departmentController.assignEmployeeToDepartment(departmentID, newEmployee);
            employeeController.addEmployee(newEmployee);

            showSuccess("Employee added successfully!");
            outputBox.setText("Added Employee: " + newEmployee.toString());
        } catch (NumberFormatException e) {
            showError("Base salary must be a valid number");
        } catch (Exception e) {
            showError("Error adding employee: " + e.getMessage());
        }
    }



    @FXML
    private void handleProcessPayroll() {
        try
        {
            String employeeID = payrollEmployeeIDTextField.getText();
            double hoursWorked = Double.parseDouble(hoursWorkedTextField.getText());
            double overtimeHours = Double.parseDouble(overtimeHoursTxtField.getText());
            double bonuses = Double.parseDouble(payrollBonusesTxtField.getText());

            Employee employee = employeeController.getEmployeeById(employeeID);
            if (employee == null) {
                showError("Employee not found for ID: " + employeeID);
                return;
            }

            payrollController.processPayrollForEmployee(employee, hoursWorked, overtimeHours, bonuses);

            // Display payroll data
            grossSalaryOutput.setText(String.valueOf(payrollController.getPayrollForEmployee(employeeID).getGrossSalary()));
            deductionsOutput.setText(String.valueOf(payrollController.getPayrollForEmployee(employeeID).getDeductions()));
            netSalaryOutput.setText(String.valueOf(payrollController.getPayrollForEmployee(employeeID).getNetSalary()));

            showSuccess("Payroll processed successfully!");
            }
            catch (NumberFormatException e)
            {
                showError("Hours worked, overtime hours, and bonuses must be valid numbers.");
            }
            catch (Exception e) {
                showError("Error processing payroll: " + e.getMessage());
            }
    }

    @FXML
    private void handleGenerateReport() {
        try
        {
            String selectEmployeeID = reportsEmployeeID.getText();
            String departmentInfo = deptSelection.getValue();

            if (!selectEmployeeID.isEmpty()) {
                reportsViewOutput.setText(payrollController.getPayrollForEmployee(selectEmployeeID).toString());
            }
            else if (departmentInfo != null) {
                String departmentID = extractDepartmentID(departmentInfo);
                Department department = departmentController.getDepartmentById(departmentID);
                if (department != null) {
                    reportsViewOutput.setText("Payroll report for " + department.getName() + ":\n" +
                            payrollController.getPayrollsForDepartment(department).toString());
                } else {
                    showError("Department not found for ID: " + departmentID);
                }
            } else {
                showError("No selection made.");
            }
        } catch (Exception e) {
            showError("Error generating report: " + e.getMessage());
        }
    }


    private String extractDepartmentID(String deptSelection) {
        if (deptSelection == null || !deptSelection.contains("(")) {
            showError("Invalid department selection: " + deptSelection);
            return null;
        }
        return deptSelection.substring(deptSelection.indexOf('(') + 1, deptSelection.indexOf(')'));
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText(message);
        alert.showAndWait();
    }

}

