package com.example.shop.controller;
import com.example.shop.dto.*; import com.example.shop.service.AuthService; import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth") @RequiredArgsConstructor
public class AuthController {
 private final AuthService auth;
 @PostMapping("/register") public TokenResponse register(@Valid @RequestBody RegisterRequest r){return auth.register(r);}
 @PostMapping("/login") public TokenResponse login(@Valid @RequestBody LoginRequest r){return auth.login(r);}
}
