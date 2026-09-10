package com.example.shop.domain;
import jakarta.persistence.*; import lombok.*; import java.math.BigDecimal; import java.time.Instant;
@Entity @Table(name="orders") @Getter @Setter @NoArgsConstructor
public class Order {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) private User customer;
 @ManyToOne(optional=false) private Product product;
 private int quantity;
 @Column(precision=19,scale=2) private BigDecimal total;
 @Enumerated(EnumType.STRING) private OrderStatus status=OrderStatus.CREATED;
 private Instant createdAt=Instant.now();
}
