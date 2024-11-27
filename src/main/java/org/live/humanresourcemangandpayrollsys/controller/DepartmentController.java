package org.live.humanresourcemangandpayrollsys.controller;

import org.live.humanresourcemangandpayrollsys.model.Department;
import org.live.humanresourcemangandpayrollsys.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class DepartmentController
{
    private final List<Department> departments;

    public DepartmentController() {
        this.departments = new ArrayList<>();
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void removeDepartment(String departmentId) {
        departments.removeIf(dep -> dep.getDepartmentId().equals(departmentId));
    }

    public void assignEmployeeToDepartment(String departmentId, Employee employee) {
        for (Department department : departments) {
            if (department.getDepartmentId().equals(departmentId)) {
                department.addEmployee(employee);
                employee.setDepartment(department);
                return;
            }
        }
    }

    public List<Department> getDepartments() {
        return departments;
    }

}
