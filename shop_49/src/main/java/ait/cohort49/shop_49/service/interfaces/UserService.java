package ait.cohort49.shop_49.service.interfaces;

import ait.cohort49.shop_49.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void register(User user);
    List<User> findAll();
}