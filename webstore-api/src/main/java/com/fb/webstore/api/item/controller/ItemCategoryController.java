package com.fb.webstore.api.item.controller;

import com.fb.webstore.api.item.entity.ItemCategory;
import com.fb.webstore.api.item.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("items/categories")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    @GetMapping
    public List<ItemCategory> getAllItemCategories() {
        return itemCategoryService.getAllItemCategories();
    }
}