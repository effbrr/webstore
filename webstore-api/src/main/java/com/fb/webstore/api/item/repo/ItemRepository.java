package com.fb.webstore.api.item.repo;

import com.fb.webstore.api.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
