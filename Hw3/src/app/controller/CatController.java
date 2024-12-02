package app.controller;

import app.model.Cat;
import app.service.CatService;
import org.springframework.stereotype.Component;

@Component
public class CatController {
    private CatService service;

    public CatController(CatService service) {
        this.service = service;
    }
    public Cat getById(long id){
        return service.getCatById(id);
    }
}
