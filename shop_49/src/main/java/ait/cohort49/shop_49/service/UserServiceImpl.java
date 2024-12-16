package ait.cohort49.shop_49.service;

import ait.cohort49.shop_49.model.entity.User;
import ait.cohort49.shop_49.repository.UserRepository;
import ait.cohort49.shop_49.service.interfaces.RoleService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ait.cohort49.shop_49.service.interfaces.UserService;

import java.util.List;

@Service
public class UserServiceImpl{

    private final UserRepository repository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository repository, RoleService roleService) {
        this.repository = repository;
        this.roleService = roleService;
    }



    public void register(User user) {
        repository.save(user);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}