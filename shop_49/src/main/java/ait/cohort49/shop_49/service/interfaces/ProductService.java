package ait.cohort49.shop_49.service.interfaces;

import ait.cohort49.shop_49.model.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface ProductService {

    Product save(Product product);

    List<Product> findAllActiveProducts();

    Product findById(long id);

    Product updateProduct(Long id, Product product);

    Long getProductCount();

    BigDecimal getTotalPrice();

    BigDecimal getAveragePrice();

    Product restoreProductById(long id);

    Product deleteById(Long id);

    Product deleteByName(String name);
}
