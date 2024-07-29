package com.example.order_service.controller;

import com.example.order_service.model.OrderEntity;
import com.example.order_service.model.Product;
import com.example.order_service.model.User;
import com.example.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable Long id) {
        Optional<OrderEntity> order = orderRepository.findById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderEntity order) {
        // Check if user exists
        ResponseEntity<User> userResponse = restTemplate.getForEntity("http://localhost:8083/api/users/" + order.getUserId(), User.class);
        if (!userResponse.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(userResponse.getStatusCode()).body("User not found");
        }

        // Check if product exists
        ResponseEntity<Product> productResponse = restTemplate.getForEntity("http://localhost:8081/api/products/" + order.getProductId(), Product.class);
        if (!productResponse.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(productResponse.getStatusCode()).body("Product not found");
        }

        // Save order
        orderRepository.save(order);
        return ResponseEntity.ok("Order created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderEntity> updateOrder(@PathVariable Long id, @RequestBody OrderEntity orderDetails) {
        Optional<OrderEntity> order = orderRepository.findById(id);
        if (order.isPresent()) {
            OrderEntity updatedOrder = order.get();
            updatedOrder.setUserId(orderDetails.getUserId());
            updatedOrder.setProductId(orderDetails.getProductId());
            updatedOrder.setQuantity(orderDetails.getQuantity());
            return ResponseEntity.ok(orderRepository.save(updatedOrder));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        Optional<OrderEntity> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.delete(order.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
