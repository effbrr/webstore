package com.fb.webstore.api.item.repo;

import com.fb.webstore.api.item.entity.ItemPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPriceRepository extends JpaRepository<ItemPrice, Integer> {
}
