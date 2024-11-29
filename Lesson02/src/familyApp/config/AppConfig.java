package familyApp.config;

import familyApp.family.parents.Father;
import familyApp.family.parents.Grandfather;
import familyApp.family.parents.Mother;
import familyApp.family.children.Son;
import familyApp.family.children.Daughter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class AppConfig {
    //Bean

    @Bean
    public Grandfather grandfather(){
        return new Grandfather();
    }

    @Bean
    public Father getFather(){
        return new Father();
    }

    @Bean
    public Mother getMother(){
        return new Mother();
    }

    @Bean
    public Son getSon(){
        return new Son();
    }

    @Bean
    public Daughter getDaughter(){
        return new Daughter();
    }

}
