package com.example.shop.service;
import com.example.shop.domain.*; import com.example.shop.dto.OrderRequest; import com.example.shop.repo.*;
import lombok.RequiredArgsConstructor; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal; import java.time.Instant; import java.util.UUID;
@Service @RequiredArgsConstructor
public class CheckoutService {
 private final UserRepository users; private final ProductRepository products; private final OrderRepository orders;
 private final PaymentRepository payments; private final DeliveryRepository deliveries; private final ReceiptRepository receipts;
 private final PaymentStrategy payment;
 @Transactional
 public Order checkout(String email, OrderRequest req){
   User u=users.findByEmail(email).orElseThrow(); Product p=products.findById(req.productId()).orElseThrow();
   if(p.getStock()<req.quantity()) throw new IllegalStateException("Insufficient stock");
   p.setStock(p.getStock()-req.quantity()); products.save(p);
   Order o=new Order(); o.setCustomer(u); o.setProduct(p); o.setQuantity(req.quantity());
   o.setTotal(p.getPrice().multiply(BigDecimal.valueOf(req.quantity()))); o.setStatus(OrderStatus.CREATED); orders.save(o);
   String ref=payment.pay(o);
   Payment pay=new Payment(); pay.setOrder(o); pay.setAmount(o.getTotal()); pay.setReference(ref); pay.setStatus(PaymentStatus.SUCCESS); payments.save(pay);
   o.setStatus(OrderStatus.PAID); orders.save(o);
   Delivery d=new Delivery(); d.setOrder(o); d.setAddress(req.address()); d.setTrackingNumber("TRK-"+UUID.randomUUID()); d.setStatus("READY_FOR_SHIPMENT"); d.setEstimatedDate(Instant.now().plusSeconds(3*86400)); deliveries.save(d);
   Receipt receipt=new Receipt(); receipt.setPayment(pay); receipt.setAmount(pay.getAmount()); receipt.setReceiptNumber("RCP-"+UUID.randomUUID()); receipts.save(receipt);
   return o;
 }
}
