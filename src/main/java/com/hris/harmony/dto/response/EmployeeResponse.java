package com.hris.harmony.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse {
    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String birth_place;
    private Date birth_date;
    private String address;
    private Date hire_date;
    private BigDecimal salary;
}
