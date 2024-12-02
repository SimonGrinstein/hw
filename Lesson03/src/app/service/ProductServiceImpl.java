package app.service;

import app.model.Product;
import app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
//! File s nastrojkami
@PropertySource("classpath:application.properties")
public class ProductServiceImpl implements ProductService {


    private ProductRepository repository;
    private String articlePrefix;

    public ProductServiceImpl(ProductRepository repository,@Value("${article.prefix}") String articlePrefix) {
        this.repository = repository;
        this.articlePrefix = articlePrefix;
    }

    @Override
    public Product getProductById(long id) {
        Product product = repository.getById(id);
        setArticle(product);
        return product;
    }
    private void setArticle (Product product) {
        // Apple, id=1 -> Art-A-1
        String article = String.format("%s-%s-%d", articlePrefix, product.getTitle().charAt(0), product.getId());
        product.setArticle(article);
    }
}
