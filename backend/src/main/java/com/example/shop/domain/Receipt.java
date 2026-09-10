package com.example.shop.domain;
import jakarta.persistence.*; import lombok.*; import java.math.BigDecimal; import java.time.Instant;
@Entity @Getter @Setter @NoArgsConstructor
public class Receipt {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @OneToOne(optional=false) private Payment payment;
 private String receiptNumber;
 @Column(precision=19,scale=2) private BigDecimal amount;
 private Instant issuedAt=Instant.now();
}
