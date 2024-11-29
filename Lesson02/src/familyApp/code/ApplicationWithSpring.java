package familyApp.code;

import familyApp.family.parents.Grandfather;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class ApplicationWithSpring {

    public static void main(String[] args) {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext("app.config");
        Grandfather grandfather = context.getBean(Grandfather.class);
        grandfather.inflateResponsibilities();
    }
}
