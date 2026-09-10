package com.example.shop.config;
import com.example.shop.domain.Product; import com.example.shop.repo.ProductRepository; import lombok.RequiredArgsConstructor; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration; import java.math.BigDecimal;
@Configuration @RequiredArgsConstructor
public class DataInitializer {
 @Bean CommandLineRunner seed(ProductRepository r){ return args -> { if(r.count()==0){
   Product p=new Product(); p.setName("Premium Laptop"); p.setDescription("Business laptop"); p.setPrice(new BigDecimal("85000")); p.setStock(20); r.save(p);
   Product q=new Product(); q.setName("Smartphone"); q.setDescription("5G smartphone"); q.setPrice(new BigDecimal("45000")); q.setStock(30); r.save(q);
 }};}
}
