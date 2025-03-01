package com.hris.harmony.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String roles;
    private String token;
}
