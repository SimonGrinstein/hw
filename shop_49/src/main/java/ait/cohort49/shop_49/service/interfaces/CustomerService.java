package ait.cohort49.shop_49.service.interfaces;

import ait.cohort49.shop_49.model.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> findAllCustomer();

    CustomerDTO findCustomerById(Long id);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    CustomerDTO deleteCustomer(Long id);

    CustomerDTO deleteCustomer(String name);

    CustomerDTO restoreCustomer(Long id);

    int getCustomerCount();

}
