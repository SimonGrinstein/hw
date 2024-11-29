package app.staff.administration;

import app.staff.specialist.sales.Merchandiser;
import app.staff.specialist.sales.SalesManager;
import org.springframework.beans.factory.annotation.Autowired;

public class SalesChef {
    @Autowired
    private Merchandiser merchandiser;
    @Autowired
    private SalesManager salesManager;

    public void giveOrders(){
        merchandiser.work();
        salesManager.work();
    }

    public Merchandiser getMerchandiser() {
        return merchandiser;
    }

    public void setMerchandiser(Merchandiser merchandiser) {
        this.merchandiser = merchandiser;
    }

    public SalesManager getSalesManager() {
        return salesManager;
    }

    public void setSalesManager(SalesManager salesManager) {
        this.salesManager = salesManager;
    }
}
