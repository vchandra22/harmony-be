package com.hris.harmony.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SearchAttendanceRequest extends PagingRequest {
    private LocalDate startDate;
    private LocalDate endDate;
}
