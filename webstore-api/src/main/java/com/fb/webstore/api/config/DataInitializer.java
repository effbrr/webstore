package com.fb.webstore.api.config;

import com.fb.webstore.api.item.entity.Item;
import com.fb.webstore.api.item.entity.ItemCategory;
import com.fb.webstore.api.item.entity.ItemImage;
import com.fb.webstore.api.item.entity.ItemPrice;
import com.fb.webstore.api.item.repo.ItemCategoryRepository;
import com.fb.webstore.api.item.repo.ItemRepository;
import com.fb.webstore.api.user.Role;
import com.fb.webstore.api.user.User;
import com.fb.webstore.api.user.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

@Component
@Transactional
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @PostConstruct
    public void init() {
        createAdminUser();
        setUpItemCategories();
        setUpItems();
    }

    void createAdminUser() {
        User adminUser = new User();
        adminUser.setFirstName("Admin");
        adminUser.setLastName("User");
        adminUser.setEmail("admin@example.com");
        adminUser.setPassword("admin");
        adminUser.setRole(Role.ADMIN);

        try {
            User createdAdmin = userService.createAdminUser(adminUser);
            logger.info("Admin user created successfully: {}", createdAdmin);
        } catch (IllegalArgumentException e) {
            logger.info("Admin user already exists.");
        }
    }

    private void setUpItemCategories() {
        final ItemCategory supplements = ItemCategory.builder()
                .title("Supplements")
                .description("Supplements").build();
        final ItemCategory food = ItemCategory.builder()
                .title("Food")
                .description("Various food items").build();
        final ItemCategory drinks = ItemCategory.builder()
                .title("Drinks")
                .description("Various drinks").build();

        itemCategoryRepository.saveAll(Arrays.asList(supplements, food, drinks));
    }

    private void setUpItems() {
        ItemCategory supplements = itemCategoryRepository.findById(1).orElseThrow();
        ItemCategory food = itemCategoryRepository.findById(2).orElseThrow();
        ItemCategory drinks = itemCategoryRepository.findById(3).orElseThrow();

        Item item1 = createItem("Whey Protein", "High quality whey protein", "Whey protein description", 100, new BigDecimal("29.99"), supplements, "image1.jpg");
        Item item2 = createItem("Creatine", "Pure creatine monohydrate", "Creatine description", 200, new BigDecimal("19.99"), supplements, "image2.jpg");
        Item item3 = createItem("Multivitamins", "Daily multivitamin supplement", "Multivitamin description", 150, new BigDecimal("9.99"), supplements, "image3.jpg");
        Item item4 = createItem("Almonds", "Organic almonds", "Almonds description", 50, new BigDecimal("12.99"), food, "image4.jpg");
        Item item5 = createItem("Oatmeal", "Instant oatmeal", "Oatmeal description", 75, new BigDecimal("4.99"), food, "image5.jpg");
        Item item6 = createItem("Chicken Breast", "Grilled chicken breast", "Chicken breast description", 30, new BigDecimal("7.99"), food, "image6.jpg");
        Item item7 = createItem("Orange Juice", "Freshly squeezed orange juice", "Orange juice description", 60, new BigDecimal("3.99"), drinks, "image7.jpg");
        Item item8 = createItem("Green Tea", "Organic green tea", "Green tea description", 80, new BigDecimal("5.99"), drinks, "image8.jpg");
        Item item9 = createItem("Protein Shake", "Ready-to-drink protein shake", "Protein shake description", 40, new BigDecimal("2.99"), drinks, "image9.jpg");
        Item item10 = createItem("Energy Drink", "Sugar-free energy drink", "Energy drink description", 90, new BigDecimal("1.99"), drinks, "image10.jpg");

        itemRepository.saveAll(Arrays.asList(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10));
    }

    private Item createItem(String title, String shortDescription, String description, int supply, BigDecimal price, ItemCategory category, String imagePath) {
        ItemPrice itemPrice = new ItemPrice(price, price);

        Item item = Item.builder()
                .title(title)
                .shortDescription(shortDescription)
                .description(description)
                .supply(supply)
                .price(itemPrice)
                .category(category)
                .build();

        addImageToItem(item, imagePath);

        return item;
    }

    private void addImageToItem(Item item, String imagePath) {
        ItemImage itemImage = new ItemImage(imagePath, item);
        item.setImages(Collections.singletonList(itemImage));
    }
}