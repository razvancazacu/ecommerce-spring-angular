package com.training.ecommerce.service;

import com.training.ecommerce.dto.Purchase;
import com.training.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
