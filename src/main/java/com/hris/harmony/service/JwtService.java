package com.hris.harmony.service;

import com.hris.harmony.entity.UserAccount;

public interface JwtService {
    String generateToken(UserAccount userAccount);
    boolean verifyToken(String bearerToken);
    String getUserIdByToken(String bearerTokenFromAuthHeader);
}
