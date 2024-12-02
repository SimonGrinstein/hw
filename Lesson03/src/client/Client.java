package client;

import app.controller.ProductController;
import app.model.Product;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Client {
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext("app");

        ProductController controller = (ProductController) context.getBean(ProductController.class);

        Product product = controller.getById(1);
        System.out.println(product);
        System.out.println("Article: " + product.getArticle());

    }
}
