package com.example.shop.domain;
import jakarta.persistence.*; import lombok.*;
@Entity @Table(name="users") @Getter @Setter @NoArgsConstructor
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(unique=true,nullable=false) private String email;
 @Column(nullable=false) private String password;
 @Enumerated(EnumType.STRING) private Role role=Role.CUSTOMER;
}
