package ait.cohort49.shop_49.service;

import ait.cohort49.shop_49.model.dto.ProductDTO;
import ait.cohort49.shop_49.model.entity.Product;
import ait.cohort49.shop_49.repository.ProductRepository;
import ait.cohort49.shop_49.service.interfaces.ProductService;
import ait.cohort49.shop_49.service.mapping.ProductMappingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMappingService mappingService;

    public ProductServiceImpl(ProductRepository repository, ProductMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = mappingService.mapDtoToEntity(productDTO);
        product.setActive(true);
        return mappingService.mapEntityToDto(repository.save(product));
    }

    @Override
    public List<ProductDTO> findAllActiveProducts() {
        return repository.findAll().stream()
                .filter(Product::isActive)
                .map(mappingService::mapEntityToDto)
                .toList();
    }

    @Override
    public ProductDTO findById(long id) {
        Product product = repository.findById(id).orElse(null);
        if(product == null || product.isActive()) {
            return null;
        }
        return mappingService.mapEntityToDto(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO product) {
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
    public ProductDTO restoreProductById(long id) {
        return null;
    }

    @Override
    public ProductDTO deleteById(Long id) {
        return null;
    }

    @Override
    public ProductDTO deleteByName(String name) {
        return null;
    }
}
