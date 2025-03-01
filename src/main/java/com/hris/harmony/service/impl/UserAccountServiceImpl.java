package com.hris.harmony.service.impl;

import com.hris.harmony.entity.UserAccount;
import com.hris.harmony.repository.UserAccountRepository;
import com.hris.harmony.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;
    
    @Override
    public UserAccount getByUserId(String userId) {
        return userAccountRepository.findById(userId).orElse(null);
    }

    @Override
    public UserAccount getByUsername(String username) {
        return userAccountRepository.findByUsername(username).orElse(null);
    }

    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        return userAccountRepository.saveAndFlush(userAccount);
    }

    @Override
    public UserAccount getByContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount userAccount = (UserAccount) authentication.getPrincipal();
        
        return userAccount;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> user = userAccountRepository.findByUsername(username);
        System.out.println("loadUserByUsername " + username);
        
        return userAccountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
