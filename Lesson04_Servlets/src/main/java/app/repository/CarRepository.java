package app.repository;

import app.model.Car;

import java.math.BigDecimal;
import java.util.List;

public interface CarRepository {
    // Получение всех машин
    List<Car> getAll();

    // Сохранение машины в Хранилище Данных
    Car save(Car car);

    Car findById(Long id);

    Car updateCar(Long id, String brand, BigDecimal price, int year);

    Car updatePriceCar(Long id, BigDecimal price);

    Car deleteCar(Long id);

}
