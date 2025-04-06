package com.fb.webstore.api.item.service;

import com.fb.openapi.model.*;
import com.fb.webstore.api.item.entity.Item;
import com.fb.webstore.api.item.entity.ItemCategory;
import com.fb.webstore.api.item.entity.ItemImage;
import com.fb.webstore.api.item.entity.ItemPrice;
import com.fb.webstore.api.item.repo.ItemCategoryRepository;
import com.fb.webstore.api.item.repo.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    public List<LimitedItemDto> getAllItems() {
        return itemRepository.findAll().stream().map(this::toLimitedItemDto).collect(Collectors.toList());
    }

    public Optional<ItemDto> getItemById(Integer id) {
        return itemRepository.findById(id).map(this::toItemDto);
    }

    @Transactional
    public ItemDto createItem(CreateItemDto createItemDto) {
        Item item = toItem(createItemDto);
        item.getImages().forEach(image -> image.setItem(item));
        return toItemDto(itemRepository.save(item));
    }

    @Transactional
    public Optional<ItemDto> updateItem(Integer id, ItemDto itemDto) {
        return itemRepository.findById(id).map(item -> {
            item.setTitle(itemDto.getTitle());
            item.setShortDescription(itemDto.getShortDescription());
            item.setDescription(itemDto.getDescription());
            item.setSupply(itemDto.getSupply());
            item.setPrice(toItemPrice(itemDto.getPrice()));

            itemCategoryRepository.findById(itemDto.getCategoryId()).ifPresent(item::setCategory);

            item.getImages().clear();
            item.getImages().addAll(itemDto.getImages().stream().map(this::toItemImage).toList());
            item.getImages().forEach(image -> image.setItem(item));
            return toItemDto(itemRepository.save(item));
        });
    }

    private LimitedItemDto toLimitedItemDto(Item item) {
        LimitedItemDto limitedItemDto = new LimitedItemDto();
        limitedItemDto.setId(item.getId());
        limitedItemDto.setCategoryId(item.getCategory().getId());
        limitedItemDto.setShortDescription(item.getShortDescription());
        limitedItemDto.setPrice(toCreateItemPriceDto(item.getPrice()));
        limitedItemDto.setImageUrl(item.getImages().isEmpty() ? null : item.getImages().get(0).getPath());
        return limitedItemDto;
    }

    private ItemDto toItemDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setTitle(item.getTitle());
        itemDto.setShortDescription(item.getShortDescription());
        itemDto.setDescription(item.getDescription());
        itemDto.setSupply(item.getSupply());
        itemDto.setPrice(toItemPriceDto(item.getPrice()));
        itemDto.setCategoryId(item.getCategory().getId());
        itemDto.setImages(item.getImages().stream().map(this::toItemImageDto).collect(Collectors.toList()));
        return itemDto;
    }

    private Item toItem(CreateItemDto createItemDto) {
        ItemCategory category = itemCategoryRepository.findById(createItemDto.getCategoryId()).orElse(null);
        Item item = new Item();
        item.setTitle(createItemDto.getTitle());
        item.setShortDescription(createItemDto.getShortDescription());
        item.setDescription(createItemDto.getDescription());
        item.setSupply(createItemDto.getSupply());
        item.setPrice(toItemPrice(createItemDto.getPrice()));
        item.setCategory(category);
        item.setImages(createItemDto.getImages().stream().map(this::toItemImage).collect(Collectors.toList()));
        return item;
    }

    private ItemPrice toItemPrice(CreateItemPriceDto createItemPriceDto) {
        ItemPrice itemPrice = new ItemPrice();
        itemPrice.setDefaultPrice(createItemPriceDto.getDefaultPrice());
        itemPrice.setCurrentPrice(createItemPriceDto.getCurrentPrice());
        itemPrice.setValidFrom(createItemPriceDto.getValidFrom());
        itemPrice.setValidTo(createItemPriceDto.getValidTo());
        return itemPrice;
    }

    private ItemPrice toItemPrice(ItemPriceDto itemPriceDto) {
        ItemPrice itemPrice = new ItemPrice();
        itemPrice.setId(itemPriceDto.getId());
        itemPrice.setDefaultPrice(itemPriceDto.getDefaultPrice());
        itemPrice.setCurrentPrice(itemPriceDto.getCurrentPrice());
        itemPrice.setValidFrom(itemPriceDto.getValidFrom());
        itemPrice.setValidTo(itemPriceDto.getValidTo());
        return itemPrice;
    }

    private ItemImage toItemImage(CreateItemImageDto createItemImageDto) {
        ItemImage itemImage = new ItemImage();
        itemImage.setPath(createItemImageDto.getPath());
        return itemImage;
    }

    private ItemImage toItemImage(ItemImageDto itemImageDto) {
        ItemImage itemImage = new ItemImage();
        itemImage.setId(itemImageDto.getId());
        itemImage.setPath(itemImageDto.getPath());
        return itemImage;
    }

    private CreateItemPriceDto toCreateItemPriceDto(ItemPrice itemPrice) {
        CreateItemPriceDto createItemPriceDto = new CreateItemPriceDto();
        createItemPriceDto.setDefaultPrice(itemPrice.getDefaultPrice());
        createItemPriceDto.setCurrentPrice(itemPrice.getCurrentPrice());
        createItemPriceDto.setValidFrom(itemPrice.getValidFrom());
        createItemPriceDto.setValidTo(itemPrice.getValidTo());
        return createItemPriceDto;
    }

    private ItemPriceDto toItemPriceDto(ItemPrice itemPrice) {
        ItemPriceDto itemPriceDto = new ItemPriceDto();
        itemPriceDto.setId(itemPrice.getId());
        itemPriceDto.setDefaultPrice(itemPrice.getDefaultPrice());
        itemPriceDto.setCurrentPrice(itemPrice.getCurrentPrice());
        itemPriceDto.setValidFrom(itemPrice.getValidFrom());
        itemPriceDto.setValidTo(itemPrice.getValidTo());
        return itemPriceDto;
    }

    private ItemImageDto toItemImageDto(ItemImage itemImage) {
        ItemImageDto itemImageDto = new ItemImageDto();
        itemImageDto.setId(itemImage.getId());
        itemImageDto.setPath(itemImage.getPath());
        return itemImageDto;
    }
}