package com.fb.webstore.api.order.service;

import com.fb.openapi.model.OrderLineDto;
import com.fb.webstore.api.item.service.ItemService;
import com.fb.webstore.api.order.mapper.OrderLineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final ItemService itemService;
    private final OrderLineMapper orderLineMapper;

//    public List<OrderLine> mapDtoToEntityList(List<OrderLineDto> orderLineDtoList) {
//        return orderLineDtoList.stream().map(o -> orderLineMapper.dtoToEntity(o, itemService.getItemById(o.getItemId()))).collect(Collectors.toList());
//    }
}










