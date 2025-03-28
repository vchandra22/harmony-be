package com.hris.harmony.controller.v1;

import com.hris.harmony.constant.Constant;
import com.hris.harmony.dto.request.DepartmentRequest;
import com.hris.harmony.dto.request.SearchDepartmentRequest;
import com.hris.harmony.dto.response.DepartmentResponse;
import com.hris.harmony.service.DepartmentService;
import com.hris.harmony.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.DEPARTMENT_API)
@CrossOrigin(origins = "*")
public class DepartmentController {
    private final DepartmentService departmentService;
    
    @GetMapping
    public ResponseEntity<?> findAllDepartments(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "name") String sort
    ) {
        SearchDepartmentRequest searchDepartmentRequest = SearchDepartmentRequest.builder()
                .name(name)
                .page(page)
                .size(size)
                .sort(sort)
                .build();

        Page<DepartmentResponse> departmentPage = departmentService.getAllDepartments(searchDepartmentRequest);
        
        return ResponseUtil.buildPageResponse(HttpStatus.OK, Constant.SUCCESS_GET_ALL_DEPARTMENT, departmentPage);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable String id) {
        DepartmentResponse departmentResponse = departmentService.getDepartmentById(id);
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_GET_DEPARTMENT_BY_ID, departmentResponse);
    }
    
    @PostMapping
    public ResponseEntity<?> createDepartment(@Valid @RequestBody DepartmentRequest departmentRequest) {
        DepartmentResponse departmentResponse = departmentService.createDepartment(departmentRequest);
        
        return ResponseUtil.buildResponse(HttpStatus.CREATED, Constant.SUCCESS_CREATE_DEPARTMENT, departmentResponse);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable String id, @Valid @RequestBody DepartmentRequest departmentRequest) {
        DepartmentResponse departmentResponse = departmentService.updateDepartment(id, departmentRequest);
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_UPDATE_DEPARTMENT, departmentResponse);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable String id) {
        departmentService.deleteDepartment(id);
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_DELETE_DEPARTMENT, null);
    }
}
