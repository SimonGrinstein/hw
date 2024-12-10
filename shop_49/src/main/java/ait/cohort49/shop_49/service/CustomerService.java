package ait.cohort49.shop_49.service;

import ait.cohort49.shop_49.model.entity.Customer;
import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    List<Customer> findAllCustomer();

    Customer findCustomerById(Long id);

    Customer updateCustomer(Long id, Customer customer);

    Customer deleteCustomer(Long id);

    Customer deleteCustomer(String name);

    Customer restoreCustomer(Long id);

    int getCustomerCount();

}
