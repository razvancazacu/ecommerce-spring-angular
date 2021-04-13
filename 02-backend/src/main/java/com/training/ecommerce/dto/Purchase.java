package com.training.ecommerce.dto;

import com.training.ecommerce.model.Address;
import com.training.ecommerce.model.Customer;
import com.training.ecommerce.model.Order;
import com.training.ecommerce.model.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
