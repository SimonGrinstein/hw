package app.code;

import app.staff.administration.Director;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class ApplicationWithSpring {

    public static void main(String[] args) {

        // File/ProductStructure/Dependencies, vybiraem + 6xfiles .jar
        // Алгоритм работы Спринга:

// 1. Стартует приложение
// 2. Спринг сканирует наше приложение и находит класс, помеченный аннотацией @Configuration
// 3. Из этого класса Спринг запускает все методы, помеченные аннотацией @Bean
// 4. Все объекты, которые возвращают эти методы, Спринг делает бинами, то есть
//    - помещает их в Спринг-контекст (хранилище бинов).
// 5. Сканирует созданные бины на предмет наличия аннотации @Autowired на полях.
// 6. Для тех полей, которые помечены аннотацией @Autowired,
//    Спринг находит соответствующие бины в Спринг-контексте и сеттит эти бины в соответствующие поля,
//    например поле secretary в классе Director.
// 7. Таким образом мы на старте приложения имеем Спринг-контекст,
//    в котором уже лежат все созданные и готовые к работе бины, с заполненными полями.

        AbstractApplicationContext context = new AnnotationConfigApplicationContext("app.config");
        Director director = context.getBean(Director.class);
        director.manageCompany();
    }
}
