package com.fb.webstore.api.customer;

import com.fb.webstore.api.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByUser(User user);
}
