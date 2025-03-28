package com.hris.harmony.service.impl;

import com.hris.harmony.dto.request.DepartmentRequest;
import com.hris.harmony.dto.request.SearchDepartmentRequest;
import com.hris.harmony.dto.response.DepartmentResponse;
import com.hris.harmony.entity.Department;
import com.hris.harmony.repository.DepartmentRepository;
import com.hris.harmony.service.DepartmentService;
import com.hris.harmony.specification.DepartmentSpecification;
import com.hris.harmony.util.SortUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    
    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        Department department = Department.builder()
                .name(departmentRequest.getName())
                .build();
        
        departmentRepository.saveAndFlush(department);
        
        return toDepartmentResponse(department);
    }

    @Override
    public Page<DepartmentResponse> getAllDepartments(SearchDepartmentRequest searchDepartmentRequest) {
        Pageable departmentPageable = PageRequest.of(
                (searchDepartmentRequest.getPage() - 1),
                searchDepartmentRequest.getSize(),
                SortUtil.parseSortFromQueryParam(searchDepartmentRequest.getSort())
        );

        Specification<Department> departmentSpecification = DepartmentSpecification.getSpecification(searchDepartmentRequest);
        Page<Department> departmentPage = departmentRepository.findAll(departmentSpecification, departmentPageable);
        
        return departmentPage.map(this::toDepartmentResponse);
    }

    @Override
    public DepartmentResponse getDepartmentById(String id) {
        Department department = getOne(id);
        
        return toDepartmentResponse(department);
    }

    @Override
    public DepartmentResponse updateDepartment(String id, DepartmentRequest departmentRequest) {
        Department currentDepartment = getOne(id);

        currentDepartment.setId(id);
        currentDepartment.setName(departmentRequest.getName());
        
        departmentRepository.save(currentDepartment);
        
        return toDepartmentResponse(currentDepartment);
    }

    @Override
    public Department getOne(String id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department with id " + id + " not found"));
    }

    @Override
    public void deleteDepartment(String id) {
        departmentRepository.deleteById(id);
    }
    
    private DepartmentResponse toDepartmentResponse(Department department) {
        DepartmentResponse departmentResponse = new DepartmentResponse();
        
        departmentResponse.setId(department.getId());
        departmentResponse.setName(department.getName());
        
        return departmentResponse;
    }
}
