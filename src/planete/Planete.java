package planete;

import dechet.*;
import vaisseau.Vaisseau;

public class Planete {
    protected float nbGadolinium;
    protected float nbNeptunium;
    protected float nbPlutonium;
    protected float nbTerbium;
    protected float nbThulium;

    public Planete(float nbGadolinium, float nbNeptunium, float nbPlutonium, float nbTerbium, float nbThulium) {
        this.nbGadolinium = nbGadolinium;
        this.nbNeptunium = nbNeptunium;
        this.nbPlutonium = nbPlutonium;
        this.nbTerbium = nbTerbium;
        this.nbThulium = nbThulium;
    }

    public void charge(Vaisseau vaisseau){
        for(int i=0;i<vaisseau.getMatiereMax();i++) {
            int random = (int) (Math.random() * (nbGadolinium + nbNeptunium + nbPlutonium + nbTerbium + nbThulium)) + 1;
            if (random <= nbGadolinium) {
                vaisseau.getListeMatiere().add(new Gadolinium());
            } else if (random <= nbGadolinium + nbNeptunium) {
                vaisseau.getListeMatiere().add(new Neptunium());
            } else if (random <= nbGadolinium + nbNeptunium + nbPlutonium) {
                vaisseau.getListeMatiere().add(new Plutonium());
            } else if (random <= nbGadolinium + nbNeptunium + nbPlutonium + nbTerbium) {
                vaisseau.getListeMatiere().add(new Terbium());
            } else {
                vaisseau.getListeMatiere().add(new Thulium());
            }
        }
    }
}
