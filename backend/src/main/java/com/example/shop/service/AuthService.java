package com.example.shop.service;
import com.example.shop.domain.*; import com.example.shop.dto.*; import com.example.shop.repo.*; import com.example.shop.security.JwtService;
import lombok.RequiredArgsConstructor; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
@Service @RequiredArgsConstructor
public class AuthService {
 private final UserRepository users; private final AccountRepository accounts; private final PasswordEncoder encoder; private final JwtService jwt;
 @Transactional public TokenResponse register(RegisterRequest r){
   if(users.findByEmail(r.email()).isPresent()) throw new IllegalArgumentException("Email already registered");
   User u=new User(); u.setEmail(r.email()); u.setPassword(encoder.encode(r.password())); users.save(u);
   Account a=new Account(); a.setUser(u); accounts.save(a); return new TokenResponse(jwt.generate(u.getEmail()));
 }
 public TokenResponse login(LoginRequest r){
   User u=users.findByEmail(r.email()).orElseThrow(()->new IllegalArgumentException("Invalid credentials"));
   if(!encoder.matches(r.password(),u.getPassword())) throw new IllegalArgumentException("Invalid credentials");
   return new TokenResponse(jwt.generate(u.getEmail()));
 }
}
