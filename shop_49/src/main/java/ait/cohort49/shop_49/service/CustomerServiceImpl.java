package ait.cohort49.shop_49.service;

import ait.cohort49.shop_49.model.entity.Customer;
import ait.cohort49.shop_49.repository.CustomerRepository;
import ait.cohort49.shop_49.service.interfaces.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        customer.setActive(true);
        return repository.save(customer);
    }

    @Override
    public List<Customer> findAllCustomer() {
        return repository.findAll().stream()
                .filter(Customer::isActive)
                .toList();
    }

    @Override
    public Customer findCustomerById(Long id) {
        Customer customer = repository.findById(id).orElse(null);
        if (customer == null  || customer.isActive()) {
            return null;
        }
        return customer;
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        //return repository.updateCustomer(id, customer);
        return null;
    }

    @Override
    public Customer deleteCustomer(Long id) {

        //return repository.deleteCustomer(id);
        return null;
    }

    @Override
    public Customer deleteCustomer(String name) {
        //return repository.deleteCustomer(name);
        return null;
    }

    @Override
    public Customer restoreCustomer(Long id) {
        //return repository.restoreCustomer(id);
        return null;
    }

    @Override
    public int getCustomerCount() {
        return findAllCustomer().size();
    }
}
