package com.hris.harmony.service;

import com.hris.harmony.dto.request.AttendanceRequest;
import com.hris.harmony.dto.request.SearchAnnouncementRequest;
import com.hris.harmony.dto.request.SearchAttendanceRequest;
import com.hris.harmony.dto.response.AnnouncementResponse;
import com.hris.harmony.dto.response.AttendanceResponse;
import com.hris.harmony.entity.Attendance;
import org.springframework.data.domain.Page;

public interface AttendanceService {
    AttendanceResponse createAttendance(AttendanceRequest attendanceRequest);
    AttendanceResponse getAttendanceById(String id);
    Page<AttendanceResponse> getAllAttendance(SearchAttendanceRequest searchAttendanceRequest);
    AttendanceResponse updateAttendance(String id, AttendanceRequest attendanceRequest);
    Attendance getOne(String id);
    void deleteAttendance(String id);
}
