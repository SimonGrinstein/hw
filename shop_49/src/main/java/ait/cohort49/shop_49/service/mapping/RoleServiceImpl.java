package ait.cohort49.shop_49.service.mapping;


import ait.cohort49.shop_49.model.entity.Role;
import ait.cohort49.shop_49.repository.RoleRepository;
import ait.cohort49.shop_49.service.interfaces.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role getRoleUser() {
        return repository.findByTitle("ROLE_USER").orElseThrow(
                () -> new RuntimeException("Database doesn't contain ROLE_USER")
        );
    }
}