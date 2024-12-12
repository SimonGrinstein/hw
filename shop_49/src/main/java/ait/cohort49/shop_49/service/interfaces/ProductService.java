package ait.cohort49.shop_49.service.interfaces;

import ait.cohort49.shop_49.model.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    ProductDTO save(ProductDTO productDTO);

    List<ProductDTO> findAllActiveProducts();

    ProductDTO findById(long id);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);

    Long getProductCount();

    BigDecimal getTotalPrice();

    BigDecimal getAveragePrice();

    ProductDTO restoreProductById(long id);

    ProductDTO deleteById(Long id);

    ProductDTO deleteByName(String name);
}
