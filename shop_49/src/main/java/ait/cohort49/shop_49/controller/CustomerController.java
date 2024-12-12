package ait.cohort49.shop_49.controller;

import ait.cohort49.shop_49.model.dto.CustomerDTO;
import ait.cohort49.shop_49.service.interfaces.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Tag(name = "Customer controller", description = "Controller for Customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Create customer", description = "Add new customer", tags ={"Customer"})
    @PostMapping
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }

    @GetMapping("/{id}")
    public CustomerDTO getById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @GetMapping
    public List<CustomerDTO> getAll() {
        return customerService.findAllCustomer();
    }


    @GetMapping("/count")
    public int getCustomerCount() {
        return customerService.getCustomerCount();
    }


    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }

    @PutMapping("/restore/{id}")
    public CustomerDTO restoreCustomer(@PathVariable Long id) {
        return customerService.restoreCustomer(id);
    }

    @DeleteMapping("/{id}")
    public CustomerDTO removeCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

    @DeleteMapping("/by_name/{name}")
    public CustomerDTO removeCustomer(@PathVariable String name) {
        return customerService.deleteCustomer(name);
    }

}
