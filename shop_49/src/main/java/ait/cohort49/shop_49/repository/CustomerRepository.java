package ait.cohort49.shop_49.repository;

import ait.cohort49.shop_49.model.entity.Customer;

import java.util.List;

public interface CustomerRepository {

    Customer saveCustomer(Customer customer);

    List<Customer> findAllCustomer();

    Customer findCustomerById(Long id);

    Customer findCustomerByName(String name);

    Customer updateCustomer(Long id, Customer customer);

    Customer deleteCustomer(Long id);

    Customer deleteCustomer(String name);

    Customer restoreCustomer(Long id);

    int getCustomerCount();

}