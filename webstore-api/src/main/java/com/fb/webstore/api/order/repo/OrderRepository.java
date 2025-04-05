package com.fb.webstore.api.order.repo;

import com.fb.openapi.model.OrderStatus;
import com.fb.webstore.api.customer.Customer;
import com.fb.webstore.api.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT COUNT(o) FROM orders o WHERE o.customer=:customer and o.orderStatus=:orderStatus")
    long countByUserIdandOrderStatus(@Param("customer") Customer customer, @Param("orderStatus") OrderStatus orderStatus);
}
