package familyApp.family.parents;

import org.springframework.beans.factory.annotation.Autowired;

public class Grandfather {

    @Autowired
    private Mother mother;
    @Autowired
    private Father father;

    public void inflateResponsibilities() {
        father.makeToEat();
        mother.feedTheChildren();
        father.takeCareOfTheChildren();
        mother.goWork();
    }

    public Mother getMother() {
        return mother;
    }

    public void setMother(Mother mother) {
        this.mother = mother;
    }

    public Father getFather() {
        return father;
    }

    public void setFather(Father father) {
        this.father = father;
    }
}