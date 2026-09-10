package com.example.shop.dto; import jakarta.validation.constraints.*; public record RegisterRequest(@Email @NotBlank String email,@Size(min=8) String password){}
