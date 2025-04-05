package com.fb.webstore.api.config;

import com.fb.webstore.api.user.Role;
import com.fb.webstore.api.user.User;
import com.fb.webstore.api.user.UserService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
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
}
