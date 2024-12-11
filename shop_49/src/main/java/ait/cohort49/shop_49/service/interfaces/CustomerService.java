package ait.cohort49.shop_49.service.interfaces;

import ait.cohort49.shop_49.model.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
