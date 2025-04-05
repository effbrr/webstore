package com.fb.webstore.api.order.service;

import com.fb.openapi.model.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    public OrderDto createCart(OrderDto orderDto) {
        return new OrderDto();
    }

    public OrderDto updateCart(OrderDto orderDto) {
        return new OrderDto();
    }

    public OrderDto getCart(Integer customerId) {
        return new OrderDto();
    }
}
