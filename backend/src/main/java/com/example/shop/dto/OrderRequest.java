package com.example.shop.dto; import jakarta.validation.constraints.*; public record OrderRequest(@NotNull Long productId,@Min(1) int quantity,String address,String idempotencyKey){}
