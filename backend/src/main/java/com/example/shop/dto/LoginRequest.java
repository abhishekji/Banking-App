package com.example.shop.dto; import jakarta.validation.constraints.*; public record LoginRequest(@Email String email,@NotBlank String password){}
