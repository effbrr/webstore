package com.fb.webstore.api.item;

import com.fb.openapi.api.ItemsApi;
import com.fb.openapi.model.CreateItemDto;
import com.fb.openapi.model.ItemDto;
import com.fb.openapi.model.LimitedItemDto;
import com.fb.webstore.api.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemController implements ItemsApi {

    @Autowired
    private ItemService itemService;

    @Override
    public ResponseEntity<List<LimitedItemDto>> getAllItems() {
        List<LimitedItemDto> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @Override
    public ResponseEntity<ItemDto> getItemById(Integer id) {
        Optional<ItemDto> item = itemService.getItemById(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<ItemDto> createItem(CreateItemDto createItemDto) {
        ItemDto createdItem = itemService.createItem(createItemDto);
        return ResponseEntity.status(201).body(createdItem);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<ItemDto> updateItemById(Integer id, ItemDto itemDto) {
        Optional<ItemDto> updatedItem = itemService.updateItem(id, itemDto);
        return updatedItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
