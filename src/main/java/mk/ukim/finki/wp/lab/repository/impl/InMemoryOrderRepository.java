package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryOrderRepository {
    public List<Order> listAll() {
        return DataHolder.orders;
    }

    public Long placeOrder(Balloon balloon, String clientName, String address) {
//        Order order = new Order(balloon);
//        DataHolder.orders.add(order);
//
//        return order.getId();
        return null;
    }
}
