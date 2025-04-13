package com.hris.harmony.service.impl;

import com.hris.harmony.dto.request.AttendanceRequest;
import com.hris.harmony.dto.request.SearchAttendanceRequest;
import com.hris.harmony.dto.response.AttendanceResponse;
import com.hris.harmony.dto.response.EmployeeDetailResponse;
import com.hris.harmony.entity.Attendance;
import com.hris.harmony.entity.Employee;
import com.hris.harmony.repository.AttendanceRepository;
import com.hris.harmony.repository.EmployeeRepository;
import com.hris.harmony.service.AttendanceService;
import com.hris.harmony.specification.AttendanceSpecification;
import com.hris.harmony.util.SortUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public AttendanceResponse createAttendance(AttendanceRequest attendanceRequest) {
        Employee employee = employeeRepository.findById(attendanceRequest.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        
        Attendance attendance = Attendance.builder()
                .attendanceDate(attendanceRequest.getAttendanceDate())
                .checkIn(attendanceRequest.getCheckIn())
                .checkOut(attendanceRequest.getCheckOut())
                .employeeId(employee)
                .build();
        
        attendanceRepository.saveAndFlush(attendance);
        
        return toAttendanceResponse(attendance);
    }

    @Override
    public AttendanceResponse getAttendanceById(String id) {
        Attendance attendance = getOne(id);
        
        return toAttendanceResponse(attendance);
    }

    @Override
    public Page<AttendanceResponse> getAllAttendance(SearchAttendanceRequest searchAttendanceRequest) {
        Pageable attendancePageable = PageRequest.of(
                searchAttendanceRequest.getPage() -1,
                searchAttendanceRequest.getSize(),
                SortUtil.parseSortFromQueryParam(searchAttendanceRequest.getSort())
        );

        Specification<Attendance> attendanceSpecification = AttendanceSpecification.getSpecification(searchAttendanceRequest);
        Page<Attendance> attendancePage = attendanceRepository.findAll(attendanceSpecification, attendancePageable);
        
        return attendancePage.map(this::toAttendanceResponse);
    }

    @Override
    public AttendanceResponse updateAttendance(String id, AttendanceRequest attendanceRequest) {
        Attendance currentAttendance = getOne(id);

        if (attendanceRequest.getAttendanceDate() == null) {
            throw new IllegalArgumentException("Attendance date must not be null");
        }

        currentAttendance.setAttendanceDate(attendanceRequest.getAttendanceDate());
        currentAttendance.setCheckIn(attendanceRequest.getCheckIn());
        currentAttendance.setCheckOut(attendanceRequest.getCheckOut());

        Employee employee = employeeRepository.findById(attendanceRequest.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        currentAttendance.setEmployeeId(employee);

        attendanceRepository.save(currentAttendance);

        return toAttendanceResponse(currentAttendance);
    }

    @Override
    public Attendance getOne(String id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No attendance found with id: " + id));
    }

    @Override
    public void deleteAttendance(String id) {
        attendanceRepository.deleteById(id);
    }

    private AttendanceResponse toAttendanceResponse(Attendance attendance) {
        Employee employee = attendance.getEmployeeId();

        return AttendanceResponse.builder()
                .id(attendance.getId())
                .attendanceDate(attendance.getAttendanceDate())
                .checkIn(attendance.getCheckIn())
                .checkOut(attendance.getCheckOut())
                .employee(EmployeeDetailResponse.builder()
                        .id(employee.getId())
                        .build())
                .build();
    }
}
