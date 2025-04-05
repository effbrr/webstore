package com.fb.webstore.api.order.rest;

import com.fb.openapi.api.CartApi;
import com.fb.openapi.model.OrderDto;
import com.fb.webstore.api.order.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
public class CartRest implements CartApi {

    private final CartService cartService;

    @Override
    public ResponseEntity<OrderDto> createCart(final OrderDto orderDto) {
        final OrderDto createdCart = cartService.createCart(orderDto);
        return ResponseEntity.ok(createdCart);
    }

    @Override
    public ResponseEntity<OrderDto> updateCart(final OrderDto orderDto) {
        final OrderDto updatedCart = cartService.updateCart(orderDto);
        return ResponseEntity.ok(updatedCart);
    }
}
