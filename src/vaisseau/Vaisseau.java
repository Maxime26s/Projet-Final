package vaisseau;

import dechet.Dechet;

import java.util.ArrayList;
import java.util.List;

public abstract class Vaisseau {
    protected List<Dechet> listeMatiere = new ArrayList<Dechet>();
    protected int matiereMax;
    protected int centreActuel =-1;

    public int getMatiereMax() {
        return matiereMax;
    }

    public void setMatiereMax(int matiereMax) {
        this.matiereMax = matiereMax;
    }

    public List<Dechet> getListeMatiere() {
        return listeMatiere;
    }

    public void setListeMatiere(List<Dechet> listeMatiere) {
        this.listeMatiere = listeMatiere;
    }

    public int getCentreActuel() {
        return centreActuel;
    }

    public void setCentreActuel(int centreActuel) {
        this.centreActuel = centreActuel;
    }
}
