package com.fb.webstore.api.order.mapper;

import com.fb.openapi.model.OrderLineDto;
import com.fb.webstore.api.item.Item;
import com.fb.webstore.api.order.entity.OrderLine;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class OrderLineMapper {

    private final ModelMapper mapper = new ModelMapper();

    public OrderLine dtoToEntity(final OrderLineDto orderLineDto, final Item item) {
        return OrderLine.builder()
            .id(orderLineDto.getId())
            .quantity(orderLineDto.getQuantity())
            .item(item)
            .build();
    }

    public OrderLineDto orderLineEntityToDto(final OrderLine orderLine) {
        return mapper.map(orderLine, OrderLineDto.class)
            .itemId(orderLine.getItem().getId())
            .unitPrice(orderLine.getItem().getPrice().getCurrentPrice().toString());
    }
}
