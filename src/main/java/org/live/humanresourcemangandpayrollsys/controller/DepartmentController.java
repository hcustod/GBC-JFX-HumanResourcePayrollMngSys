package org.live.humanresourcemangandpayrollsys.controller;

import org.live.humanresourcemangandpayrollsys.helpers.FileHelper;
import org.live.humanresourcemangandpayrollsys.model.Department;
import org.live.humanresourcemangandpayrollsys.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class DepartmentController {
    private final List<Department> departments;

    public DepartmentController() {
        List<Department> loadedDepartments = FileHelper.loadFromFile("departments.dat");
        this.departments = (loadedDepartments != null) ? loadedDepartments : new ArrayList<>();
    }

    public void addDepartment(Department department) {
        departments.add(department);
        saveDepartments();
    }

    public void removeDepartment(String departmentId) {
        departments.removeIf(dep -> dep.getDepartmentId().equals(departmentId));
        saveDepartments();
    }

    public void assignEmployeeToDepartment(String departmentId, Employee employee) {
        for (Department department : departments) {
            if (department.getDepartmentId().equals(departmentId)) {
                department.addEmployee(employee);
                employee.setDepartment(department);
                saveDepartments();
                return;
            }
        }
    }

    public Department getDepartmentById(String departmentId) {
        for (Department department : departments) {
            if (department.getDepartmentId().equals(departmentId)) {
                return department;
            }
        }
        return null;
    }

    public List<Department> getAllDepartments() {
        return new ArrayList<>(departments);
    }

    private void saveDepartments() {
        FileHelper.saveToFile("departments.dat", departments);
    }
}
