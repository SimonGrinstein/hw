package ait.cohort49.shop_49.service;

import ait.cohort49.shop_49.exceptionHandling.exception.FirstTestException;
import ait.cohort49.shop_49.exceptionHandling.exception.ThirdTestException;
import ait.cohort49.shop_49.model.dto.ProductDTO;
import ait.cohort49.shop_49.model.entity.Product;
import ait.cohort49.shop_49.repository.ProductRepository;
import ait.cohort49.shop_49.service.interfaces.ProductService;
import ait.cohort49.shop_49.service.mapping.ProductMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMappingService mappingService;

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

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
        return repository.findByActiveTrue().stream()
                .map(mappingService::mapEntityToDto)
                .toList();

//        return repository.findAll().stream()
//                .filter(Product::isActive)
//                .map(mappingService::mapEntityToDto)
//                .toList();
    }

    @Override
    public ProductDTO findById(long id) {
        //!---------------- LOGGER
//        logger.info("Method findById called with param: {}", id);
//        logger.warn("Method findById called with param: {}", id);
//        logger.error("Method findById called with param: {}", id);


        Product product = repository.findById(id).orElse(null);
        if(product == null || !product.isActive()) {
            throw new ThirdTestException("This is 3 Test Exception");
            //throw new FirstTestException(" This is the FirstTestException");
            //return null;
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
