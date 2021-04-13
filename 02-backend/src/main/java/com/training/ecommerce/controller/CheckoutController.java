package com.training.ecommerce.controller;

import com.training.ecommerce.dto.Purchase;
import com.training.ecommerce.dto.PurchaseResponse;
import com.training.ecommerce.service.CheckoutService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        return checkoutService.placeOrder(purchase);
    }

//    @RequestMapping(value = "/buy", method = RequestMethod.POST)
//    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
//        return checkoutService.placeOrder(purchase);
//    }

}
