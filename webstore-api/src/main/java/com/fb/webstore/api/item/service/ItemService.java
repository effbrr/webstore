package com.fb.webstore.api.item.service;

import com.fb.webstore.api.item.Item;
import com.fb.webstore.api.item.ItemCategory;
import com.fb.webstore.api.item.ItemPrice;
import com.fb.webstore.api.item.repo.ItemPriceRepo;
import com.fb.webstore.api.item.repo.ItemRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemPriceRepo itemPriceRepo;
    private final EntityManager em;

    public void saveItem(final Item item) {
        itemRepository.save(item);
    }

    public Item getItem(final Integer id) {
        return itemRepository.findById(id).orElseThrow();
    }

    @PostConstruct
    @Transactional
    public void setUpItems() {
        final ItemCategory protein = em.find(ItemCategory.class, 1);
        final ItemCategory food = em.find(ItemCategory.class, 2);
        final ItemCategory drinks = em.find(ItemCategory.class, 3);
        final ItemPrice itemPrice1 = new ItemPrice(new BigDecimal("99.99"));
        final ItemPrice itemPrice2 = new ItemPrice(new BigDecimal("49.99"));
        final ItemPrice itemPrice3 = new ItemPrice(new BigDecimal("29.99"));
        final Item item1 = Item.builder()
            .title("Protein Powder")
            .description("Markedets beste proteinpulver")
            .category(protein)
            .price(itemPrice1)
            .supply(999)
            .build();

        final Item item2 = Item.builder()
            .title("Energy Bar")
            .description("Markedets beste energibar")
            .category(food)
            .price(itemPrice2)
            .supply(999)
            .build();

        final Item item3 = Item.builder()
            .title("Monster Energy Ultra Fiesta")
            .description("Markedets beste proteinpulver")
            .category(drinks)
            .price(itemPrice3)
            .supply(999)
            .build();
        saveItem(item1);
        saveItem(item2);
        saveItem(item3);
    }

}
