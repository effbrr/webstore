package com.fb.webstore.api.item.service;

import com.fb.webstore.api.item.ItemCategory;
import com.fb.webstore.api.item.repo.ItemCategoryRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemCategoryService {

    private final ItemCategoryRepo itemCategoryRepo;

    public void saveItemCategory(final ItemCategory category) {
        itemCategoryRepo.save(category);
    }

    @PostConstruct
    private void setUpItems() {
        final ItemCategory protein = ItemCategory.builder()
            .title("Protein")
            .description("Proteintilskudd").build();
        final ItemCategory matvarer = ItemCategory.builder()
            .title("Food")
            .description("Masse matvarer").build();
        final ItemCategory drikke = ItemCategory.builder()
            .title("Drinks")
            .description("Masse Drikke").build();
        saveItemCategory(protein);
        saveItemCategory(matvarer);
        saveItemCategory(drikke);
    }
}
