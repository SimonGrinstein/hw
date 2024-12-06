package app.repository;

import app.model.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.util.List;

public class CarRepositoryHibernate implements CarRepository {

    private EntityManager entityManager;

    public CarRepositoryHibernate() {
        entityManager = new Configuration().configure("hibernate/postgres.cfg.xml")
                .buildSessionFactory().createEntityManager();
    }



    @Override
    public Car save(Car car) {
        return null;
    }



    @Override
    public List<Car> getAll() {
        return entityManager.createQuery("from Car",Car.class).getResultList();
    }



    @Override
    public Car findById(Long id) {
        return entityManager.find(Car.class, id);
    }

    @Override
    public Car update(Car car) {
        return entityManager.merge(car);
    }

    @Override
    public Car updatePriceCar(Long id, BigDecimal price) {
        return entityManager.merge(findById(id));
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }
}
