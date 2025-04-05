package com.fb.webstore.api.order.repo;

import com.fb.webstore.api.order.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepo extends JpaRepository<OrderLine, Integer> {
}
