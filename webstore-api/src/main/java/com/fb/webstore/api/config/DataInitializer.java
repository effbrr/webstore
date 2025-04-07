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
        final ItemCategory equipment = ItemCategory.builder()
                .title("Equipment")
                .description("Gym equipemnt").build();

        itemCategoryRepository.saveAll(Arrays.asList(supplements, food, drinks, equipment));
    }


    private void setUpItems() {
        ItemCategory supplements = itemCategoryRepository.findById(1).orElseThrow();
        ItemCategory food = itemCategoryRepository.findById(2).orElseThrow();
        ItemCategory drinks = itemCategoryRepository.findById(3).orElseThrow();
        ItemCategory equipment = itemCategoryRepository.findById(4).orElseThrow();

        // Supplements
        Item item1 = createItem("Whey Protein 1kg", "High quality whey protein 1kg", "Whey protein description 1kg", 100, new BigDecimal("299.99"), supplements, "image1.jpg");
        Item item2 = createItem("Whey Protein 3kg", "High quality whey protein 3kg", "Whey protein description 3kg", 50, new BigDecimal("799.99"), supplements, "image2.jpg");
        Item item3 = createItem("Creatine", "Pure creatine monohydrate", "Creatine description", 200, new BigDecimal("199.99"), supplements, "image3.jpg");
        Item item4 = createItem("Multivitamins", "Daily multivitamin supplement", "Multivitamin description", 150, new BigDecimal("99.99"), supplements, "image4.jpg");
        Item item5 = createItem("BCAA", "Branched-chain amino acids", "BCAA description", 120, new BigDecimal("249.99"), supplements, "image5.jpg");

        // Food
        Item item6 = createItem("Almonds", "Organic almonds", "Almonds description", 50, new BigDecimal("129.99"), food, "image6.jpg");
        Item item7 = createItem("Oatmeal", "Instant oatmeal", "Oatmeal description", 75, new BigDecimal("49.99"), food, "image7.jpg");
        Item item8 = createItem("Chicken Breast", "Grilled chicken breast", "Chicken breast description", 30, new BigDecimal("79.99"), food, "image8.jpg");
        Item item9 = createItem("Peanut Butter", "Natural peanut butter", "Peanut butter description", 60, new BigDecimal("99.99"), food, "image9.jpg");
        Item item10 = createItem("Protein Bar", "High protein snack bar", "Protein bar description", 100, new BigDecimal("29.99"), food, "image10.jpg");

        // Drinks
        Item item11 = createItem("Orange Juice", "Freshly squeezed orange juice", "Orange juice description", 60, new BigDecimal("39.99"), drinks, "image11.jpg");
        Item item12 = createItem("Green Tea", "Organic green tea", "Green tea description", 80, new BigDecimal("59.99"), drinks, "image12.jpg");
        Item item13 = createItem("Protein Shake", "Ready-to-drink protein shake", "Protein shake description", 40, new BigDecimal("29.99"), drinks, "image13.jpg");
        Item item14 = createItem("Energy Drink", "Sugar-free energy drink", "Energy drink description", 90, new BigDecimal("19.99"), drinks, "image14.jpg");
        Item item15 = createItem("Coconut Water", "Natural coconut water", "Coconut water description", 100, new BigDecimal("49.99"), drinks, "image15.jpg");

        // Equipment
        Item item16 = createItem("Treadmill", "High-end treadmill for cardio workouts", "Treadmill description", 10, new BigDecimal("7999.99"), equipment, "image16.jpg");
        Item item17 = createItem("Dumbbells Set", "Adjustable dumbbells set", "Dumbbells set description", 25, new BigDecimal("1999.99"), equipment, "image17.jpg");
        Item item18 = createItem("Yoga Mat", "Non-slip yoga mat", "Yoga mat description", 100, new BigDecimal("299.99"), equipment, "image18.jpg");
        Item item19 = createItem("Kettlebell", "20kg kettlebell", "Kettlebell description", 50, new BigDecimal("499.99"), equipment, "image19.jpg");
        Item item20 = createItem("Rowing Machine", "High resistance rowing machine", "Rowing machine description", 15, new BigDecimal("5999.99"), equipment, "image20.jpg");

        itemRepository.saveAll(Arrays.asList(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12, item13, item14, item15, item16, item17, item18, item19, item20));
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