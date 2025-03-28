package com.hris.harmony.service;

import com.hris.harmony.dto.request.DepartmentRequest;
import com.hris.harmony.dto.request.SearchDepartmentRequest;
import com.hris.harmony.dto.response.DepartmentResponse;
import com.hris.harmony.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DepartmentService {
    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);
    Page<DepartmentResponse> getAllDepartments(SearchDepartmentRequest searchDepartmentRequest);
    DepartmentResponse getDepartmentById(String id);
    DepartmentResponse updateDepartment(String id, DepartmentRequest departmentRequest);
    Department getOne(String id);
    void deleteDepartment(String id);
}
