package app.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Cat {

    private long id;
    private String name;
    private String breed;
    private String article;

    public Cat(long id, String name, String breed) {
        this.id = id;
        this.name = name;
        this.breed = breed;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Cat cat)) return false;

        return id == cat.id && Objects.equals(name, cat.name) && Objects.equals(breed, cat.breed);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(breed);
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                '}';
    }
}
