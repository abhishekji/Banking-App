package com.example.shop.service;
import com.example.shop.domain.Order;
public interface PaymentStrategy { String pay(Order order); }
