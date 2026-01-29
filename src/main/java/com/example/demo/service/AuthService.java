package com.example.demo.service;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.RegisterDTO;

public interface AuthService {

    AuthResponse login(LoginDTO request);

    AuthResponse register(RegisterDTO request);
}
