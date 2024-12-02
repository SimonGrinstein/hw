package app.controller;

import app.model.Product;
import app.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class ProductController {
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }
    public Product getById(long id){
        return service.getProductById(id);
    }
}
