package app.staff.administration;

import app.staff.specialist.Secretary;
import org.springframework.beans.factory.annotation.Autowired;

public class Director {
    @Autowired
    private ProductionChef productionChef;
    @Autowired
    private SalesChef salesChef;
    @Autowired
    private Secretary secretary;


    public void manegeCompany() {
        secretary.work();
        salesChef.giveOrders();
        productionChef.giveOrders();
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
