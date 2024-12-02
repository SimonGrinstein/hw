package app.repository;

import app.model.Cat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


@Repository
public class CatRepositoryMap implements CatRepository {

    private Map<Long, Cat> cats = new HashMap<>();

    //List<Cat> catsList = getCatsList("../../resources/cats.txt");


    public CatRepositoryMap() throws IOException {
        initData();

    }

    private void initData() {
        cats.put(1L,new Cat(1L,"Murzik", "Mein-Kun"));
        cats.put(2L,new Cat(2L,"Chacha","Pers"));
        cats.put(3L,new Cat(3L,"Mumu", "Seam"));
        cats.put(4L,new Cat(4L,"Tratata", "Sphinks"));
    }

    @Override
    public Cat getById(long id) {
        return cats.get(id);
    }
/*
    private static List<Cat> getCatsList(String path) throws IOException {
        List<Cat> catsList = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(path));

        String line;

        while ((line = reader.readLine()) != null) {
            catsList.add(createRecord(line));
        }

        reader.close();
        System.out.println("catsList: " + catsList.size());
        return catsList;
    }

    private static Cat createRecord(String line) {

        String[] data = line.split(",");

        String id = data[0].trim();
        String name = data[1].trim();
        String breed = data[2].trim();

        return new Cat(Long.parseLong(id), name, breed);
    }
*/

}
