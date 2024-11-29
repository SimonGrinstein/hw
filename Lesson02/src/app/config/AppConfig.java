package app.config;

import app.staff.administration.Director;
import app.staff.administration.ProductionChef;
import app.staff.administration.SalesChef;
import app.staff.specialist.Secretary;
import app.staff.specialist.production.MachineOperator;
import app.staff.specialist.production.StoreKeeper;
import app.staff.specialist.sales.Merchandiser;
import app.staff.specialist.sales.SalesManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class AppConfig {
    //Bean

    @Bean
    public Director director(){
        return new Director();
    }

    @Bean
    public ProductionChef getProductionChef(){
        return new ProductionChef();
    }

    @Bean
    public SalesChef getSalesChef(){
        return new SalesChef();
    }

    @Bean
    public MachineOperator getMachineOperator(){
        return new MachineOperator();
    }

    @Bean
    public StoreKeeper  getStoreKeeper(){
        return new StoreKeeper();
    }

    @Bean
    public Merchandiser getMerchandiser(){
        return new Merchandiser();
    }

    @Bean
    public SalesManager getSalesManager(){
        return new SalesManager();
    }
    @Bean
    public Secretary getSecretary(){
        return new Secretary();
    }
}
