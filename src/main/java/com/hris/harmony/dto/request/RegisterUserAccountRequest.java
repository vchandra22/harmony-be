package com.hris.harmony.dto.request;

import com.hris.harmony.entity.UserAccount;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserAccountRequest {
    private String username;
    private String password;
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
