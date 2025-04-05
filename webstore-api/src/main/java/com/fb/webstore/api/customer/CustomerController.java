package com.fb.webstore.api.customer;

import com.fb.webstore.api.customer.address.Address;
import com.fb.webstore.api.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add-address")
    public Map<String, String> addAddress(@RequestBody Address address, Authentication authentication) {
        Map<String, String> response = new HashMap<>();
        User user = (User) authentication.getPrincipal();
        try {
            Customer customer = customerService.updateCustomerAddress(user, address);
            response.put("message", "Address added successfully");
            response.put("customerId", customer.getId().toString());
        } catch (IllegalArgumentException e) {
            response.put("message", e.getMessage());
        }
        return response;
    }

    @GetMapping("/info")
    public ResponseEntity<Customer> getCustomerInfo(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Optional<Customer> customerOpt = customerService.getCustomerByUser(user);
        return customerOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
