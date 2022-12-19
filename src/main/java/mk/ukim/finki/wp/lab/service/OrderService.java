package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    List<Order> listAll();
    Long placeOrder(Balloon balloon, User user, LocalDateTime datetime_local);
}
