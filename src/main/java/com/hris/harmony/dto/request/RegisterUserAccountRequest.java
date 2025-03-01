package com.hris.harmony.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserAccountRequest {
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
}
