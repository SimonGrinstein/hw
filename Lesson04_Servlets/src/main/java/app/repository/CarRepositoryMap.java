package app.repository;

import app.model.Car;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarRepositoryMap implements CarRepository {
    private Map<Long, Car> database = new HashMap<>();
    private long currentId;

    public CarRepositoryMap() {
        initData();
    }

    private void initData() {
        save(new Car("VW", new BigDecimal(15000), 2015));
        save(new Car("Mazda", new BigDecimal(30000), 2023));
        save(new Car("Ford", new BigDecimal(40000), 2024));
    }

    @Override
    public List<Car> getAll() {
        return new ArrayList<>(database.values());
//        return database.values().stream().toList();
    }

    @Override
    public Car save(Car car) {
        car.setId(++currentId);
        database.put(car.getId(), car);
        return car;
    }

    @Override
    public Car findById(Long id) {
        return database.getOrDefault(id, null);
    }

    @Override
    public Car updatePriceCar(Long id, BigDecimal price) {
        Car car = findById(id);
        car.setPrice(price);
        return car;
    }

    @Override
    public Car update(Car car) {

        return car;
    }

    @Override
    public void deleteById(Long id) {

        Car delCar = findById(id);

        if (delCar != null) {
            database.remove(id);
        }
    }


}
