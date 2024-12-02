package app.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private long id;
    private String title;
    private BigDecimal price;
    private String article;

    public Product(long id, String title, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", article='" + article + '\'' +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;

        return id == product.id && Objects.equals(title, product.title) && Objects.equals(price, product.price) && Objects.equals(article, product.article);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(price);
        result = 31 * result + Objects.hashCode(article);
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }


}
