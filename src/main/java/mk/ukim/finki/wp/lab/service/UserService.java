package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.User;

import java.util.List;

public interface UserService {
    List<User> listAll();

    User findById(Long id);
}
