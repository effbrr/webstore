package com.fb.webstore.api.order.mapper;

import com.fb.openapi.model.OrderDto;
import com.fb.webstore.api.customer.Customer;
import com.fb.webstore.api.order.entity.Order;
import com.fb.webstore.api.order.entity.OrderLine;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderMapper {

    private final ModelMapper mapper = new ModelMapper();
    private final OrderLineMapper orderLineMapper;

    public Order orderDtoToEntity(final OrderDto orderDto, final List<OrderLine> orderLines, final Customer customer) {
        return Order.builder()
            .id(orderDto.getId())
            .customer(customer)
            .orderLines(orderLines)
            .orderStatus(orderDto.getOrderStatus())
            .totalPrice(new BigDecimal(orderDto.getTotalPrice()))
            .paymentStatus(orderDto.getPaymentStatus())
            .lastModified(orderDto.getLastModified())
            .finalizedOrderDateTIme(orderDto.getFinalizedOrderDateTIme())
            .build();
    }
    public OrderDto orderEntityToDto(final Order createdOrder) {
        return mapper.map(createdOrder, OrderDto.class)
            .customerId(createdOrder.getCustomer().getId())
            .orderLines(createdOrder.getOrderLines().stream()
                .map(orderLineMapper::orderLineEntityToDto).collect(Collectors.toList())
            );
    }

}
