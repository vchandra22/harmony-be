package com.hris.harmony.dto.response;

import com.hris.harmony.constant.UserRole;
import com.hris.harmony.entity.UserAccount;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserAccountResponse {
    private String username;
    private UserRole role;
}
