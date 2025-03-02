package com.hris.harmony.service.impl;

import com.hris.harmony.constant.Constant;
import com.hris.harmony.constant.UserRole;
import com.hris.harmony.dto.request.AuthRequest;
import com.hris.harmony.dto.request.EmployeeRequest;
import com.hris.harmony.dto.request.RegisterUserAccountRequest;
import com.hris.harmony.dto.response.AuthResponse;
import com.hris.harmony.dto.response.RegisterUserAccountResponse;
import com.hris.harmony.entity.UserAccount;
import com.hris.harmony.service.AuthService;
import com.hris.harmony.service.EmployeeService;
import com.hris.harmony.service.JwtService;
import com.hris.harmony.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserAccountService userAccountService;
    private final EmployeeService employeeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RegisterUserAccountResponse registerUserAccount(RegisterUserAccountRequest request) {
        validateUsernameNotExists(request.getUsername());
        
        UserAccount userAccount = toUserAccount(
                request.getUsername(),
                request.getPassword(),
                UserRole.ROLE_KARYAWAN
        );
        
        UserAccount savedUserAccount = userAccountService.createUserAccount(userAccount);
        
        employeeService.createEmployee(EmployeeRequest.builder()
                .first_name(request.getFirst_name())
                .last_name(request.getLast_name())
                .email(request.getEmail())
                .phone(request.getPhone())
                .birth_place(request.getBirth_place())
                .birth_date(request.getBirth_date())
                .address(request.getAddress())
                .hire_date(request.getHire_date())
                .salary(request.getSalary())
                .userAccount(savedUserAccount)
                .build()
        );
        
        return toRegisterUserAccountResponse(userAccount);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(
                usernamePasswordAuthenticationToken
        );
        
        UserAccount userAccount = (UserAccount) authentication.getPrincipal();
        if (!authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, Constant.ERROR_INVALID_CREDENTIALS);
        }
        
        String generatedToken = jwtService.generateToken(userAccount);

        return AuthResponse.builder()
                .username(userAccount.getUsername())
                .token(generatedToken)
                .role(userAccount.getRole())
                .build();
    }
    
    private UserAccount toUserAccount(String username, String password, UserRole role) {
        return UserAccount.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(role)
                .build();
    }
    
    private RegisterUserAccountResponse toRegisterUserAccountResponse(UserAccount userAccount) {
        return RegisterUserAccountResponse.builder()
                .username(userAccount.getUsername())
                .role(userAccount.getRole())
                .build();
    }
    
    private void validateUsernameNotExists(String username) {
        if (userAccountService.getByUsername(username) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, Constant.ERROR_USERNAME_EXISTS);
        }
    }
}
