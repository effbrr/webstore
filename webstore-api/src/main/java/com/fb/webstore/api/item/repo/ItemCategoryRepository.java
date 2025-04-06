package com.fb.webstore.api.item.repo;

import com.fb.webstore.api.item.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Integer> {
}
