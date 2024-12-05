package app.constants;

public interface Constants {

    String DB_DRIVER_PATH = "org.postgresql.Driver";

    // http://localhost:8080/car?id=2
    //jdbc:postgres://10.2.3.4:5432/g_49_cars?user=my_user&password=pos1234

    String DB_ADDRESS = "jdbc:postgresql://localhost:5433/";
    String DB_NAME = "g_49_cars";
    String DB_USERNAME = "my_user";
    String DB_PASSWORD = "pos1234";

    //CONSOLE
    //docker run --name postgres49 -p 5432:5432 -e POSTGRES_USER=my_user -e POSTGRES_PASSWORD=pos1234 -e POSTGRES_DB=g_49_cars -d postgres
}
