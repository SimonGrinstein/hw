package ait.cohort49.shop_49.controller;

import ait.cohort49.shop_49.model.entity.Customer;
import ait.cohort49.shop_49.service.CustomerServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerServiceImpl customerService = new CustomerServiceImpl();

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @GetMapping
    public List<Customer> getAll() {
        return customerService.findAllCustomer();
    }

    /*
    @GetMapping
    public int getCustomerCount() {
        return customerService.getCustomerCount();
    }
*/

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }
/*
    @PutMapping("/{id}")
    public Customer restoreCustomer(@PathVariable Long id) {
        return customerService.restoreCustomer(id);
    }
*/
    @DeleteMapping("/{id}")
    public Customer removeCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

    @DeleteMapping("/{name}")
    public Customer removeCustomer(@PathVariable String name) {
        return customerService.deleteCustomer(name);
    }

}
