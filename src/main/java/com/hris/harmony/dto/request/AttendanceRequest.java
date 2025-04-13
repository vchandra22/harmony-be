package com.hris.harmony.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceRequest {
    private LocalDateTime attendanceDate;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String employeeId;
}
