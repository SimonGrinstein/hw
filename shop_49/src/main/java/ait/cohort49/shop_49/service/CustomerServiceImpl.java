package ait.cohort49.shop_49.service;

import ait.cohort49.shop_49.model.dto.CustomerDTO;
import ait.cohort49.shop_49.model.entity.Cart;
import ait.cohort49.shop_49.model.entity.Customer;
import ait.cohort49.shop_49.repository.CustomerRepository;
import ait.cohort49.shop_49.service.interfaces.CustomerService;
import ait.cohort49.shop_49.service.mapping.CustomerMappingService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMappingService mappingService;

    public CustomerServiceImpl(CustomerRepository repository, CustomerMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    @Transactional
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = mappingService.mapDtoToEntity(customerDTO);
        customer.setActive(true);

        Cart cart = new Cart();
        cart.setCustomer(customer);
        customer.setCart(cart);

        return  mappingService.mapEntityToDto(repository.save(customer));
    }

    @Override
    public List<CustomerDTO> findAllCustomer() {
        return repository.findAll().stream()
                .filter(Customer::isActive)
                .map(mappingService::mapEntityToDto)
                .toList();
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        Customer customer = repository.findById(id).orElse(null);
        if (customer == null  || !customer.isActive()) {
            return null;
        }
        return mappingService.mapEntityToDto(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        //return repository.updateCustomer(id, customer);
        return null;
    }

    @Override
    public CustomerDTO deleteCustomer(Long id) {

        //return repository.deleteCustomer(id);
        return null;
    }

    @Override
    public CustomerDTO deleteCustomer(String name) {
        //return repository.deleteCustomer(name);
        return null;
    }

    @Override
    public CustomerDTO restoreCustomer(Long id) {
        //return repository.restoreCustomer(id);
        return null;
    }

    @Override
    public int getCustomerCount() {
        return findAllCustomer().size();
    }
}
