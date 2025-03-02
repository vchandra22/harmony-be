package com.hris.harmony.service.impl;

import com.hris.harmony.dto.request.EmployeeRequest;
import com.hris.harmony.dto.response.EmployeeResponse;
import com.hris.harmony.entity.Employee;
import com.hris.harmony.repository.EmployeeRepository;
import com.hris.harmony.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeRequest createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = Employee.builder()
                .phone(employeeRequest.getPhone())
                .birth_place(employeeRequest.getBirth_place())
                .birth_date(employeeRequest.getBirth_date())
                .address(employeeRequest.getAddress())
                .hire_date(employeeRequest.getHire_date())
                .salary(employeeRequest.getSalary())
                .userAccount(employeeRequest.getUserAccount())
                .build();

        employeeRepository.saveAndFlush(employee);
        
        return null;
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return List.of();
    }

    @Override
    public EmployeeResponse getEmployeeById(String employeeId) {
        return null;
    }

    @Override
    public Employee getOne(String id) {
        return null;
    }

    @Override
    public EmployeeResponse updateEmployee(String employeeId, EmployeeRequest employeeRequest) {
        return null;
    }

    @Override
    public void deleteEmployee(String employeeId) {

    }
    
    private EmployeeResponse toEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .phone(employee.getPhone())
                .birth_place(employee.getBirth_place())
                .birth_date(employee.getBirth_date())
                .address(employee.getAddress())
                .salary(employee.getSalary())
                .build();
    }
}
