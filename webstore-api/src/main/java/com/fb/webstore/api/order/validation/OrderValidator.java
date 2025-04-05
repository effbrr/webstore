package com.fb.webstore.api.order.validation;

import com.fb.openapi.model.OrderStatus;
import com.fb.webstore.api.customer.Customer;
import com.fb.webstore.api.customer.CustomerService;
import com.fb.webstore.api.order.entity.Order;
import com.fb.webstore.api.order.entity.OrderLine;
import com.fb.webstore.api.order.repo.OrderRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@AllArgsConstructor
@Service
public class OrderValidator {

    private static final Logger log = LoggerFactory.getLogger(OrderValidator.class);
    final CustomerService customerService;

    private final OrderRepository orderRepository;

    public void validateNewCart(final Order cart) {
        if(cart.getId() != null) {
            log.error("Cart ID: {} not allowed on new cart entries. Cust ID: {}", cart.getId(), cart.getCustomer().getId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(cart.getOrderStatus() != OrderStatus.SHOPPING_CART) {
            log.error("Order status: {} is not allowed for new shopping carts. Cust ID: {}", cart.getOrderStatus(), cart.getCustomer().getId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (customerService.getCustomerByUserId(cart.getCustomer().getId()).isPresent() && hasOpenCart(customerService.getCustomerByUserId(cart.getCustomer().getId()).get())) {
            log.error("Customer has an already active cart. Cust ID: {}", cart.getCustomer().getId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        validateNewOrderlines(cart.getOrderLines());
    }

    private void validateNewOrderlines(final List<OrderLine> orderLines) {
        
    }
    public void validateNewOrder(Order order) {

    }

    public Order getOrder(final Integer orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }

    public Boolean orderExists(final Integer orderId) {
        return orderRepository.existsById(orderId);
    }

    public Boolean hasOpenCart(final Customer customer) {
        final Integer cartCount = Math.toIntExact(orderRepository.countByUserIdandOrderStatus(customer, OrderStatus.SHOPPING_CART));
        return cartCount == 1;
    }


}
