package com.fb.webstore.api.item.repo;

import com.fb.webstore.api.item.ItemPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPriceRepo extends JpaRepository<ItemPrice, Integer> {
}
