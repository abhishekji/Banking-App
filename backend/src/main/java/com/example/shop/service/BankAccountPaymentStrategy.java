package com.example.shop.service;
import com.example.shop.domain.*; import com.example.shop.repo.*; import lombok.RequiredArgsConstructor; import org.springframework.stereotype.Component; import org.springframework.transaction.annotation.Transactional; import java.math.BigDecimal; import java.util.UUID;
@Component @RequiredArgsConstructor
public class BankAccountPaymentStrategy implements PaymentStrategy {
 private final AccountRepository accounts; private final BankTransactionRepository transactions;
 @Override @Transactional
 public String pay(Order order){
   Account a=accounts.findByUserEmail(order.getCustomer().getEmail()).orElseThrow();
   BigDecimal amount=order.getTotal();
   if(a.getBalance().compareTo(amount)<0) throw new IllegalStateException("Insufficient account balance");
   a.setBalance(a.getBalance().subtract(amount));
   BankTransaction t=new BankTransaction(); t.setAccount(a); t.setAmount(amount); t.setType(TransactionType.DEBIT);
   t.setReference("PAY-"+UUID.randomUUID()); transactions.save(t); accounts.save(a);
   return t.getReference();
 }
}
