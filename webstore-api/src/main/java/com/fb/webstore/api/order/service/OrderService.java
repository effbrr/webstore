package com.fb.webstore.api.order.service;

import com.fb.openapi.model.OrderDto;
import com.fb.openapi.model.PaymentStatus;
import com.fb.webstore.api.customer.Customer;
import com.fb.webstore.api.customer.CustomerService;
import com.fb.webstore.api.order.entity.Order;
import com.fb.webstore.api.order.entity.OrderLine;
import com.fb.webstore.api.order.mapper.OrderMapper;
import com.fb.webstore.api.order.repo.OrderRepository;
import com.fb.webstore.api.order.validation.OrderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerService customerService;
    private final OrderLineService orderLineService;
    private final OrderValidator orderValidator;

//    public OrderDto createCart(final OrderDto orderDto) {
//        final Order cart = mapOrderDtoToEntity(orderDto);
//        cart.setLastModified(OffsetDateTime.now());
//
//        orderValidator.validateNewCart(cart);
//
//
//        final Order createdCart = orderRepository.save(cart);
//        return orderMapper.orderEntityToDto(createdCart);
//    }
//
//    public OrderDto createOrder(final OrderDto orderDto) {
//
//        final Order order = mapOrderDtoToEntity(orderDto);
//        order.setLastModified(OffsetDateTime.now());
//        order.setPaymentStatus(PaymentStatus.UNPAID);
//
//        orderValidator.validateNewOrder(order);
//
//        final Order createdOrder = orderRepository.save(order);
//        return orderMapper.orderEntityToDto(createdOrder);
//    }
//

//    private Order mapOrderDtoToEntity(final OrderDto orderDto) {
//        final Customer customer = customerService.getCustomerByUserId(orderDto.getCustomerId())
//                .orElseThrow(() -> new IllegalArgumentException("Customer not found for userId: " + orderDto.getCustomerId()));
//        final List<OrderLine> orderLines = orderLineService.mapDtoToEntityList(orderDto.getOrderLines());
//        return orderMapper.orderDtoToEntity(orderDto, orderLines, customer);
//    }


}










