package familyApp.family.parents;

import familyApp.family.children.Daughter;
import familyApp.family.children.Son;
import org.springframework.beans.factory.annotation.Autowired;


public class Father {

    @Autowired
    private Daughter daughter;
    @Autowired
    private Son son;

    public void takeCareOfTheChildren(){
        daughter.play();
        son.makeHomeworks();
    }

    public void makeToEat(){
        System.out.println("Father: I make to eat");
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