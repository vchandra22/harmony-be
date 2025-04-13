package com.hris.harmony.service.impl;

import com.hris.harmony.dto.request.EmployeeRequest;
import com.hris.harmony.dto.response.EmployeeResponse;
import com.hris.harmony.entity.Employee;
import com.hris.harmony.entity.Position;
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
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = Employee.builder()
                .first_name(employeeRequest.getFirst_name())
                .last_name(employeeRequest.getLast_name())
                .email(employeeRequest.getEmail())
                .phone(employeeRequest.getPhone())
                .birth_place(employeeRequest.getBirth_place())
                .birth_date(employeeRequest.getBirth_date())
                .address(employeeRequest.getAddress())
                .hire_date(employeeRequest.getHire_date())
                .salary(employeeRequest.getSalary())
                .userAccount(employeeRequest.getUserAccount())
                .build();

        employee = employeeRepository.saveAndFlush(employee);

        return toEmployeeResponse(employee);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::toEmployeeResponse)
                .toList();
    }

    @Override
    public EmployeeResponse getEmployeeById(String employeeId) {
        Employee employee = getOne(employeeId);
        
        return toEmployeeResponse(employee);
    }

    @Override
    public Employee getOne(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with id " + id + " not found"));
    }

    @Override
    public EmployeeResponse updateEmployee(String employeeId, EmployeeRequest employeeRequest) {
        Employee employee = getOne(employeeId);
        
        employee.setFirst_name(employeeRequest.getFirst_name());
        employee.setLast_name(employeeRequest.getLast_name());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPhone(employeeRequest.getPhone());
        employee.setBirth_place(employeeRequest.getBirth_place());
        employee.setBirth_date(employeeRequest.getBirth_date());
        employee.setAddress(employeeRequest.getAddress());
        employee.setHire_date(employeeRequest.getHire_date());
        employee.setSalary(employeeRequest.getSalary());
        
        employeeRepository.save(employee);
        
        return toEmployeeResponse(employee);
    }

    @Override
    public void deleteEmployee(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }
    
    private EmployeeResponse toEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .first_name(employee.getFirst_name())
                .last_name(employee.getLast_name())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .birth_place(employee.getBirth_place())
                .birth_date(employee.getBirth_date())
                .address(employee.getAddress())
                .hire_date(employee.getHire_date())
                .salary(employee.getSalary())
                .build();
    }
}
