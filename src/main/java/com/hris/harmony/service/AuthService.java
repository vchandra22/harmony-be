package com.hris.harmony.service;

import com.hris.harmony.dto.request.AuthRequest;
import com.hris.harmony.dto.request.RegisterUserAccountRequest;
import com.hris.harmony.dto.response.AuthResponse;
import com.hris.harmony.dto.response.RegisterUserAccountResponse;

public interface AuthService {
    RegisterUserAccountResponse registerUserAccount(RegisterUserAccountRequest request);
    AuthResponse login(AuthRequest request);
}
