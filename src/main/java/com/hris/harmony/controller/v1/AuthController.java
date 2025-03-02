package com.hris.harmony.controller.v1;

import com.hris.harmony.constant.Constant;
import com.hris.harmony.dto.request.AuthRequest;
import com.hris.harmony.dto.request.RegisterUserAccountRequest;
import com.hris.harmony.dto.response.AuthResponse;
import com.hris.harmony.dto.response.RegisterUserAccountResponse;
import com.hris.harmony.service.AuthService;
import com.hris.harmony.util.ResponseUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.AUTH_API)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserAccountRequest request) {
        RegisterUserAccountResponse response = authService.registerUserAccount(request);

        return ResponseUtil.buildResponse(HttpStatus.CREATED, Constant.SUCCESS_REGISTER, response);
    }
    
    @SecurityRequirements
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        AuthResponse response = authService.login(authRequest);
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_LOGIN, response);
    }
}
