package com.example.shop.controller;
import com.example.shop.domain.Order; import com.example.shop.repo.OrderRepository; import lombok.RequiredArgsConstructor; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/orders") @RequiredArgsConstructor
public class OrderController {
 private final OrderRepository orders;
 @GetMapping public List<Order> mine(Authentication a){return orders.findByCustomerEmail(a.getName());}
 @GetMapping("/{id}") public Order one(@PathVariable Long id,Authentication a){
   Order o=orders.findById(id).orElseThrow(); if(!o.getCustomer().getEmail().equals(a.getName())) throw new org.springframework.security.access.AccessDeniedException("Forbidden"); return o;
 }
}
