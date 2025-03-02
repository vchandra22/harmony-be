package com.hris.harmony.dto.response;

import com.hris.harmony.constant.UserRole;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String username;
    private UserRole role;
    private String token;

    public String getRole() {
        return role != null ? role.getValue() : null;
    }
}
