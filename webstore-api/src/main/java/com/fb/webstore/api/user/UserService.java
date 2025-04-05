package com.fb.webstore.api.user;

import com.fb.webstore.api.customer.Customer;
import com.fb.webstore.api.customer.address.Address;
import com.fb.webstore.api.customer.address.Country;
import com.fb.webstore.api.customer.address.County;
import com.fb.webstore.api.customer.CustomerService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private CustomerService customerService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, CustomerService customerService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        User savedUser = userRepository.save(user);

        // Create a Customer entity for the new user
        Customer customer = new Customer();
        customer.setUser(savedUser);
        customerService.createCustomer(savedUser, customer);

        return savedUser;
    }

    public User createAdminUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }
}


//@PostConstruct
//private void setUpUser() {
//    final User user = User.builder()
//            .firstName("Fredrik")
//            .lastName("Bjerkø")
//            .email("f.bjerko@gmail.com")
//            // .role(RoleBackupEnum.USER)
//            .password("secret")
//            .build();
//    saveUser(user);
//    final Integer customerId = customerService.setUpAndSaveCustomer(user).getId();
//    final Address address = Address.builder()
//            .street("Skjoldveien 6D")
//            .postalCode("3175")
//            .county(County.VESTFOLD)
//            .country(Country.NORWAY)
//            .build();
//    customerService.saveCustomerAddress(customerId, address);
//}