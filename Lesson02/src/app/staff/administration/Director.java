package app.staff.administration;

import app.staff.specialist.Secretary;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Sergey Bugaenko
 * {@code @date} 29.11.2024
 */

public class Director {
    // Аннотация говорит Spring что нужно достать из SpringContext
    // объект ProductionChef и поместить его в это поле
    @Autowired
    private ProductionChef productionChef;
    @Autowired
    private SalesChef salesChef;
    @Autowired
    private Secretary secretary;

    public void manageCompany() {
        secretary.work();
        productionChef.giveOrders();
        salesChef.giveOrders();
    }


    public ProductionChef getProductionChef() {
        return productionChef;
    }

    public void setProductionChef(ProductionChef productionChef) {
        this.productionChef = productionChef;
    }

    public SalesChef getSalesChef() {
        return salesChef;
    }

    public void setSalesChef(SalesChef salesChef) {
        this.salesChef = salesChef;
    }

    public Secretary getSecretary() {
        return secretary;
    }

    public void setSecretary(Secretary secretary) {
        this.secretary = secretary;
    }
}