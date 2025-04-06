package com.fb.webstore.api.order.rest;

import com.fb.openapi.api.OrderApi;
import com.fb.openapi.model.OrderDto;
import com.fb.webstore.api.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class OrderRest implements OrderApi {

    private final OrderService orderService;

//    @Override
//    public ResponseEntity<OrderDto> createOrder(final OrderDto orderDto) {
//        final OrderDto createdOrder = orderService.createOrder(orderDto);
//        return ResponseEntity.ok(createdOrder);
//    }

    @Override
    public ResponseEntity<List<OrderDto>> getCustomerOrders(Integer customerId) {
        return OrderApi.super.getCustomerOrders(customerId);
    }

    @Override
    public ResponseEntity<List<OrderDto>> getOrders() {
        return OrderApi.super.getOrders();
    }
}
