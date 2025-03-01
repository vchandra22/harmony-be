package com.hris.harmony.dto.response;

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
    private String role;
}
