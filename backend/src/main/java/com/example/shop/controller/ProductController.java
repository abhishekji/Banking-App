package com.example.shop.controller;
import com.example.shop.domain.Product; import com.example.shop.service.ProductService; import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/products") @RequiredArgsConstructor
public class ProductController {
 private final ProductService service;
 @GetMapping public List<Product> all(){return service.all();}
 @GetMapping("/{id}") public Product get(@PathVariable Long id){return service.get(id);}
}
