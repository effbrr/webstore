package com.fb.webstore.api.customer;

import com.fb.webstore.api.customer.address.Address;
import com.fb.webstore.api.user.User;
import com.fb.webstore.api.user.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    public Customer createCustomer(User user, Customer customer) {
        customer.setUser(user);
        return customerRepository.save(customer);
    }

    public Customer updateCustomerAddress(User user, Address address) {
        Customer customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        customer.setAddress(address);
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerByUser(User user) {
        return customerRepository.findByUser(user);
    }

    public Optional<Customer> getCustomerByUserId(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return customerRepository.findByUser(user.get());
        } else {
            return Optional.empty();
        }
    }
}
