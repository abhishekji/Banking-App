package com.example.shop.controller;
import com.example.shop.domain.Order; import com.example.shop.dto.OrderRequest; import com.example.shop.service.CheckoutService;
import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/checkout") @RequiredArgsConstructor
public class CheckoutController {
 private final CheckoutService service;
 @PostMapping public Order checkout(Authentication auth,@Valid @RequestBody OrderRequest req){return service.checkout(auth.getName(),req);}
}
