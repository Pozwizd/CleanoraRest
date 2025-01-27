package com.example.cleanorarest.service;

import com.example.cleanorarest.entity.AdminUser;
import org.springframework.stereotype.Service;

@Service
public interface PasswordResetTokenService {
    void updatePassword(String token,String password);
    boolean validatePasswordResetToken(String token);
    String createAndSavePasswordResetToken(AdminUser user);
}
