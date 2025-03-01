package com.hris.harmony.dto.response;

import com.hris.harmony.constant.UserRole;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserAccountResponse {
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private UserRole role;
    
    public String getRole() {
        return role != null ? role.getValue() : null;
    }
}
