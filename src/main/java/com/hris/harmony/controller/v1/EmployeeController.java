package com.hris.harmony.controller.v1;

import com.hris.harmony.constant.Constant;
import com.hris.harmony.dto.request.EmployeeRequest;
import com.hris.harmony.dto.response.EmployeeResponse;
import com.hris.harmony.service.EmployeeService;
import com.hris.harmony.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.EMPLOYEE_API)
public class EmployeeController {
    private final EmployeeService employeeService;
    
    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        List<EmployeeResponse> employeeResponseList = employeeService.getAllEmployees();
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_GET_ALL_EMPLOYEE, employeeResponseList);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String id) {
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_GET_EMPLOYEE_BY_ID, employeeResponse);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable String id, @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.updateEmployee(id, employeeRequest);
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_UPDATE_EMPLOYEE, employeeResponse);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_DELETE_EMPLOYEE, null);
    }
}
