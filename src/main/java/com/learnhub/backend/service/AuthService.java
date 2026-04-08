package com.learnhub.backend.service;

public interface AuthService {
    void sendOtp(String email, String name);
    boolean verifyOtp(String email, String otp);
}
