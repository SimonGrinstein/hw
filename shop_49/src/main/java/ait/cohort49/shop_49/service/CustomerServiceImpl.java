package ait.cohort49.shop_49.service;

import ait.cohort49.shop_49.model.entity.Customer;
import ait.cohort49.shop_49.repository.CustomerRepositoryHibernate;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepositoryHibernate customerRepositoryHibernate = new CustomerRepositoryHibernate();

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepositoryHibernate.saveCustomer(customer);
        //return customer;
    }

    @Override
    public List<Customer> findAllCustomer() {
        return customerRepositoryHibernate.findAllCustomer();
        //return List.of();
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepositoryHibernate.findCustomerById(id);
        //return null;
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        return customerRepositoryHibernate.updateCustomer(id, customer);
        //return null;
    }

    @Override
    public Customer deleteCustomer(Long id) {
        return customerRepositoryHibernate.deleteCustomer(id);
        //return null;
    }

    @Override
    public Customer deleteCustomer(String name) {
        return customerRepositoryHibernate.deleteCustomer(name);
        //return null;
    }

    @Override
    public Customer restoreCustomer(Long id) {
        return customerRepositoryHibernate.restoreCustomer(id);
        //return null;
    }

    @Override
    public int getCustomerCount() {
        return findAllCustomer().size();
    }
}
