package familyApp.family.parents;

import familyApp.family.children.Daughter;
import familyApp.family.children.Son;
import org.springframework.beans.factory.annotation.Autowired;

public class Mother {

    @Autowired
    private Daughter daughter;
    @Autowired
    private Son son;

    public void feedTheChildren(){
        daughter.eat();
        son.eat();
    }

    public void goWork(){
        System.out.println("Mother: I working");
    }

    public Daughter getDaughter() {
        return daughter;
    }

    public void setDaughter(Daughter daughter) {
        this.daughter = daughter;
    }

    public Son getSon() {
        return son;
    }

    public void setSon(Son son) {
        this.son = son;
    }
}