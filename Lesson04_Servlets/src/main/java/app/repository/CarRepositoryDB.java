package app.repository;

import app.model.Car;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static app.constants.Constants.*;


public class CarRepositoryDB implements CarRepository {

    private Connection getConnection() {
        try {
            Class.forName(DB_DRIVER_PATH);

            //jdbc:postgres://10.2.3.4:5432/g_49_cars?user=my_user&password=pos1234
            String dbUrl = String.format("%s%s?user=%s&password=%s",
                    DB_ADDRESS, DB_NAME, DB_USERNAME, DB_PASSWORD);

            return DriverManager.getConnection(dbUrl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //CONSOLE
        //docker run --name postgres49 -p 5433:5432 -e POSTGRES_USER=my_user -e POSTGRES_PASSWORD=pos1234 -e POSTGRES_DB=g_49_cars -d postgres
    }

    @Override
    public Car save(Car car) {
        try (Connection connection = getConnection()) {

            //INSERT INTO cars (brand, price, year) VALUES ('Toyota', 35000, 2022);
            String query = String.format(
                    "INSERT INTO cars (brand, price, year) VALUES ('%s', %s, %d);",
                    car.getBrand(), car.getPrice(), car.getYear());

            Statement statement = connection.createStatement();
            //statement.execute(query);

            statement.execute(query, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();

            resultSet.next();
            //Long id = resultSet.getLong(1);
            Long id = resultSet.getLong("id");
            car.setId(id);

            return car;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();

        try (Connection connection = getConnection()) {

            //SELECT * from cars;
            String query = String.format(
                    "SELECT * from cars;", cars);

            Statement statement = connection.createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getLong("id"));
                car.setBrand(resultSet.getString("brand"));
                car.setPrice(resultSet.getBigDecimal("price"));
                car.setYear(resultSet.getInt("year"));
                cars.add(car);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cars;
    }


    @Override
    public Car findById(Long id) {
        try (Connection connection = getConnection()) {
            //SELECT * from cars WHERE id=2;
            String query = String.format(
                    "SELECT * from cars WHERE id=%d;",
                    id);

            Statement statement = connection.createStatement();
            statement.execute(query);

            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                String brand = resultSet.getString("brand");
                BigDecimal price = resultSet.getBigDecimal("price");
                int year = resultSet.getInt("year");
                return new Car(id, brand, price, year);
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Car update(Car car) {

        try (Connection connection = getConnection()) {
            //UPDATE cars SET price = 25000 WHERE id=1;
            String query = String.format(
                    "UPDATE cars SET price = %s WHERE id=%d;",
                    car.getPrice(), car.getId());
            Statement statement = connection.createStatement();
            statement.execute(query);

            //ResultSet resultSet = statement.executeQuery(query);
            //resultSet.next();

            //String price = resultSet.getString("price");


            return car;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car updatePriceCar(Long id, BigDecimal price) {
        try (Connection connection = getConnection()) {
            //UPDATE cars SET price = 25000 WHERE id=1;
            String query = String.format(
                    "UPDATE cars SET price = %s WHERE id=%d;",
                    price, id);

            Statement statement = connection.createStatement();
            statement.execute(query);

            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();

            String brand = resultSet.getString("brand");
            int year = resultSet.getInt("year");

            return new Car(id, brand, price, year);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = getConnection()) {

            String query = String.format(
                    "DELETE * from cars WHERE id=%d;",
                    id);
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
