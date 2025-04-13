package com.hris.harmony.controller.v1;

import com.hris.harmony.constant.Constant;
import com.hris.harmony.dto.request.AttendanceRequest;
import com.hris.harmony.dto.request.SearchAttendanceRequest;
import com.hris.harmony.dto.response.AttendanceResponse;
import com.hris.harmony.service.AttendanceService;
import com.hris.harmony.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.ATTENDANCE_API)
@CrossOrigin(origins = "*")
public class AttendanceController {
    private final AttendanceService attendanceService;
    
    @GetMapping
    public ResponseEntity<?> findAllAttendance(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "id") String sort
    ) {
        SearchAttendanceRequest searchAttendanceRequest = SearchAttendanceRequest.builder()
                .startDate(startDate)
                .endDate(endDate)
                .page(page)
                .size(size)
                .sort(sort)
                .build();
        Page<AttendanceResponse> attendanceResponsePage = attendanceService.getAllAttendance(searchAttendanceRequest);
        
        return ResponseUtil.buildPageResponse(HttpStatus.OK, Constant.SUCCESS_GET_ALL_ATTENDANCE, attendanceResponsePage);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getAttendanceById(@PathVariable("id") String id) {
        AttendanceResponse attendanceResponse = attendanceService.getAttendanceById(id);
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_GET_ATTENDANCE_BY_ID, attendanceResponse);
    }
    
    @PostMapping
    public ResponseEntity<?> createAttendance(@RequestBody @Valid AttendanceRequest attendanceRequest) {
        AttendanceResponse attendanceResponse = attendanceService.createAttendance(attendanceRequest);
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_CREATE_ATTENDANCE, attendanceResponse);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAttendance(@PathVariable String id, @RequestBody @Valid AttendanceRequest attendanceRequest) {
        AttendanceResponse attendanceResponse = attendanceService.updateAttendance(id, attendanceRequest);
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_UPDATE_ATTENDANCE, attendanceResponse);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendance(@PathVariable String id) {
        attendanceService.deleteAttendance(id);
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_DELETE_ATTENDANCE, null);
    }
}
