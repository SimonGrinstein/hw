package familyApp.code;
import familyApp.family.children.Daughter;
import familyApp.family.children.Son;
import familyApp.family.parents.Father;
import familyApp.family.parents.Grandfather;
import familyApp.family.parents.Mother;

public class Application {
    public static void main(String[] args) {

        Son son = new Son();
        Daughter daughter = new Daughter();

        Father father = new Father();
        father.setSon(son);
        father.setDaughter(daughter);

        Mother mother = new Mother();
        mother.setSon(son);
        mother.setDaughter(daughter);

        Grandfather grandfather = new Grandfather();
        grandfather.setFather(father);
        grandfather.setMother(mother);

        grandfather.inflateResponsibilities();
    }
}
