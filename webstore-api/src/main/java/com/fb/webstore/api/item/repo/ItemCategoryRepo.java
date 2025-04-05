package com.fb.webstore.api.item.repo;

import com.fb.webstore.api.item.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCategoryRepo extends JpaRepository<ItemCategory, Integer> {
}
