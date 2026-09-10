package com.example.shop.domain;
import jakarta.persistence.*; import lombok.*; import java.math.BigDecimal;
@Entity @Getter @Setter @NoArgsConstructor
public class Product {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String name;
 private String description;
 @Column(nullable=false,precision=19,scale=2) private BigDecimal price;
 @Column(nullable=false) private int stock;
}
