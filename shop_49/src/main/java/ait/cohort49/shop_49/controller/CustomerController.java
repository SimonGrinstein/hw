package ait.cohort49.shop_49.controller;

import ait.cohort49.shop_49.model.entity.Customer;
import ait.cohort49.shop_49.service.interfaces.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


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


    @GetMapping("/count")
    public int getCustomerCount() {
        return customerService.getCustomerCount();
    }


    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    @PutMapping("/restore/{id}")
    public Customer restoreCustomer(@PathVariable Long id) {
        return customerService.restoreCustomer(id);
    }

    @DeleteMapping("/{id}")
    public Customer removeCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

    @DeleteMapping("/by_name/{name}")
    public Customer removeCustomer(@PathVariable String name) {
        return customerService.deleteCustomer(name);
    }

}
