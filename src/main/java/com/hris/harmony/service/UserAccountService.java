package com.hris.harmony.service;

import com.hris.harmony.entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserAccountService {
    UserAccount getByUserId(String userId);
    UserAccount getByUsername(String username);
    UserAccount createUserAccount(UserAccount userAccount);
    UserAccount getByContext();
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
