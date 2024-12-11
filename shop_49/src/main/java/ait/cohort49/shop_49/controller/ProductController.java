package ait.cohort49.shop_49.controller;

import ait.cohort49.shop_49.model.entity.Product;
import ait.cohort49.shop_49.service.interfaces.ProductService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

//http://localhost:8080/products
//    //api/products

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //POST -> /products
    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    //Product - id
    //GET /products/3
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.findById(id);
    }

    //GET /products
    @GetMapping
    public List<Product> getAll() {
        return productService.findAllActiveProducts();
    }

    @GetMapping("/count")
    public Long getProductCount() {
        return productService.getProductCount();
    }

    @GetMapping("/total_price")
    public BigDecimal getTotalPrice() {
        return productService.getTotalPrice();
    }

    @GetMapping("/average_price")
    public BigDecimal getAveragePrice() {
        return productService.getAveragePrice();
    }

    @PutMapping ("/restore{id}")
    public Product restoreProduct(@PathVariable Long id) {
        return productService.restoreProductById(id);
    }

    //delete DELETE -> /product/id
    @DeleteMapping("/{id}")
    public Product remove(@PathVariable Long id) {
        return productService.deleteById(id);
    }

    //delete DELETE -> /product/id
    @DeleteMapping("/by_title/{name}")
    public Product remove(@PathVariable String name) {
        return productService.deleteByName(name);
    }



}
