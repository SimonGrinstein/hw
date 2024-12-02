package app.repository;

import app.model.Product;

public interface ProductRepository {
    Product getById(long id);
}
