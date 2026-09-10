package com.example.shop.domain;
import jakarta.persistence.*; import lombok.*; import java.time.Instant;
@Entity @Getter @Setter @NoArgsConstructor
public class Delivery {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @OneToOne(optional=false) private Order order;
 private String address;
 private String trackingNumber;
 private String status="PENDING";
 private Instant estimatedDate;
}
