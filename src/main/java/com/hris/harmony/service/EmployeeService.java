package com.hris.harmony.service;

import com.hris.harmony.dto.request.EmployeeRequest;
import com.hris.harmony.dto.response.EmployeeResponse;
import com.hris.harmony.entity.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);
    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse getEmployeeById(String employeeId);
    Employee getOne(String id);
    EmployeeResponse updateEmployee(String employeeId, EmployeeRequest employeeRequest);
    void deleteEmployee(String employeeId);
}
