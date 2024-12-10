package ait.cohort49.shop_49.controller;

import ait.cohort49.shop_49.model.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/products
//    //api/products

@RestController
@RequestMapping("/products")
public class ProductController {

    //POST -> /products
    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        //Todo  -> Service
        return product;
    }

    //Product - id
    //GET /products/3
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        //Todo -> service
        return null;
    }

    //GET /products
    @GetMapping
    public List<Product> getAll() {
        //GET
        // TODO -> Sevice
        return List.of();
    }

    /*
    // get:  GET /products
    @GetMapping
    public List<Product> getAll(@RequestParam(required = false) Integer limit) {
        // Todo обращаемся к сервису, получаем все продукты
        if (limit == null) {
            // параметр не пришел
        }
        return List.of();
    }
    */

    //Update PUT -> /product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        //Todo -> Service
        return product;
    }

    //delete DELETE -> /product/id
    @DeleteMapping("/{id}")
    public Product remove(@PathVariable Long id) {
        //Todo -> Service
        return null;
    }

}
