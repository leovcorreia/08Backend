package com.desafio08_01.services;

import com.desafio08_01.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private ShippingService shippingService;

    public double total(Order order) {

        double amountWithDiscount = order.getBasic() - (order.getDiscount() / 100.0 * order.getBasic());

        double shipment = shippingService.shipment(order);

        return amountWithDiscount + shipment;

    }

}
