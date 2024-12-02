package client;

import app.controller.CatController;
import app.model.Cat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


public class Client {
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext("app");

        CatController controller = (CatController) context.getBean(CatController.class);

        Cat cat = controller.getById(1);
        System.out.println(cat);
        System.out.println("Article: " + cat.getArticle());

    }
}
