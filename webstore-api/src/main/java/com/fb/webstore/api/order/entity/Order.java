package com.fb.webstore.api.order.entity;

import com.fb.openapi.model.OrderStatus;
import com.fb.openapi.model.PaymentStatus;
import com.fb.webstore.api.customer.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(
        cascade = CascadeType.PERSIST,
        orphanRemoval = true
    )
    @JoinColumn(name = "_order_id", nullable = false)
    private List<OrderLine> orderLines;

    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.SHOPPING_CART;

    private OffsetDateTime lastModified;

    private OffsetDateTime finalizedOrderDateTIme;
}
