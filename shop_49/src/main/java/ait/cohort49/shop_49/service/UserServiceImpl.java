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
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository repository, RoleService roleService) {
        this.repository = repository;
        this.roleService = roleService;
    }


    @Override
    public void register(User user) {
        repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}