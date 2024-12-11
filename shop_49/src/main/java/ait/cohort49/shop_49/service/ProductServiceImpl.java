package ait.cohort49.shop_49.service;

import ait.cohort49.shop_49.model.entity.Product;
import ait.cohort49.shop_49.repository.ProductRepository;
import ait.cohort49.shop_49.service.interfaces.ProductService;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        product.setActive(true);
        return repository.save(product);
    }

    @Override
    public List<Product> findAllActiveProducts() {
        return repository.findAll().stream()
                .filter(Product::isActive)
                .toList();
                //.collect(Collectors.toList());
    }

    @Override
    public Product findById(long id) {
        Product product = repository.findById(id).orElse(null);
        if(product == null || product.isActive()) {
            return null;
        }
        return product;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Long getProductCount() {
        return 0L;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return null;
    }

    @Override
    public BigDecimal getAveragePrice() {
        return null;
    }

    @Override
    public Product restoreProductById(long id) {
        return null;
    }

    @Override
    public Product deleteById(Long id) {
        return null;
    }

    @Override
    public Product deleteByName(String name) {
        return null;
    }
}
