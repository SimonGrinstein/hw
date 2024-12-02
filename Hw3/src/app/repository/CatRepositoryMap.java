package app.repository;

import app.model.Cat;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


@Repository
public class CatRepositoryMap implements CatRepository {

    private Map<Long, Cat> catsList = new HashMap<>();

    public CatRepositoryMap() throws IOException {
        catsList = getCatsList("/java/pro/Hw3/resources/cats.txt");
    }

    @Override
    public Cat getById(long id) {
        return catsList.get(id);
    }

    private static Map<Long, Cat> getCatsList(String path) throws IOException {

        Map<Long, Cat> catsList = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));

        String line;
        int  catId = 1;
        while ((line = reader.readLine()) != null) {
            catsList.put(Long.valueOf(catId) ,createRecord(line));
            catId++;
        }

        reader.close();

        return catsList;
    }

    private static Cat createRecord(String line) {

        String[] data = line.split(",");

        String id = data[0].trim();
        String name = data[1].trim();
        String breed = data[2].trim();

        return new Cat(Long.parseLong(id), name, breed);
    }

}
