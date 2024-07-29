package com.example.payment_service.controller;

import com.example.payment_service.model.Payment;
import com.example.payment_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment paymentDetails) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            Payment updatedPayment = payment.get();
            updatedPayment.setUserId(paymentDetails.getUserId());
            updatedPayment.setOrderId(paymentDetails.getOrderId());
            updatedPayment.setAmount(paymentDetails.getAmount());
            updatedPayment.setStatus(paymentDetails.getStatus());
            return ResponseEntity.ok(paymentRepository.save(updatedPayment));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            paymentRepository.delete(payment.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
