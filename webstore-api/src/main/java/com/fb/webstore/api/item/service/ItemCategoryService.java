package com.fb.webstore.api.item.service;

import com.fb.webstore.api.item.entity.ItemCategory;
import com.fb.webstore.api.item.repo.ItemCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCategoryService {

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    public List<ItemCategory> getAllItemCategories() {
        return itemCategoryRepository.findAll();
    }
}
