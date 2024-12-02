package app.service;

import app.model.Cat;
import app.repository.CatRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service

@PropertySource("classpath:application.properties")
public class CatServiceImpl implements CatService {


    private CatRepository repository;
    private String articlePrefix;

    public CatServiceImpl(CatRepository repository, @Value("${article.prefix}") String articlePrefix) {
        this.repository = repository;
        this.articlePrefix = articlePrefix;
    }

    @Override
    public Cat getCatById(long id) {
        Cat cat = repository.getById(id);
        setArticle(cat);
        return cat;
    }
    private void setArticle (Cat cat) {
        // Apple, id=1 -> Art-A-1
        String article = String.format("%s-%s-%d", articlePrefix, cat.getName().charAt(0), cat.getId());
        cat.setArticle(article);
    }
}
