package com.example.shop.service;
import com.example.shop.domain.Product; import com.example.shop.repo.ProductRepository; import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict; import org.springframework.cache.annotation.Cacheable; import org.springframework.stereotype.Service; import java.util.List;
@Service @RequiredArgsConstructor
public class ProductService {
 private final ProductRepository repo;
 @Cacheable("products") public List<Product> all(){ return repo.findAll(); }
 @Cacheable(value="product",key="#id") public Product get(Long id){ return repo.findById(id).orElseThrow(); }
 @CacheEvict(value={"products","product"},allEntries=true) public Product save(Product p){ return repo.save(p); }
}
