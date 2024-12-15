package ait.cohort49.shop_49.controller;

import ait.cohort49.shop_49.model.dto.CustomerDTO;
import ait.cohort49.shop_49.model.dto.ProductDTO;
import ait.cohort49.shop_49.service.interfaces.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get customer by id",  tags = {"Customer"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDTO.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = CustomerDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })

    @GetMapping("/{id}")
    public CustomerDTO getById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @GetMapping
    public List<CustomerDTO> getAll() {
        //System.out.println("getAll size ------------------ " + customerService.findAllCustomer().size());
        return customerService.findAllCustomer();
    }


    @GetMapping("/count")
    public int getCustomerCount() {
        return customerService.getCustomerCount();
    }


    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
//        System.out.println("ID: "+ id);
//        System.out.println("customerDTO: "+ customerDTO.toString());
//        CustomerDTO customerDTO2 = customerService.updateCustomer(id, customerDTO);
//        System.out.println("customerDTO2: "+ customerDTO2.toString());
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
