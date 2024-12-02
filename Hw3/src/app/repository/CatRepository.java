package app.repository;

import app.model.Cat;

public interface CatRepository {
    Cat getById(long id);
}
