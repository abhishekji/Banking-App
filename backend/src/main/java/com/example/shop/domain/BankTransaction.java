package com.example.shop.domain;
import jakarta.persistence.*; import lombok.*; import java.math.BigDecimal; import java.time.Instant;
@Entity @Table(name="bank_transactions") @Getter @Setter @NoArgsConstructor
public class BankTransaction {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) private Account account;
 @Enumerated(EnumType.STRING) private TransactionType type;
 @Column(nullable=false,precision=19,scale=2) private BigDecimal amount;
 private String reference;
 private Instant createdAt=Instant.now();
}
