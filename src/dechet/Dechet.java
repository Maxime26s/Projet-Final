package dechet;

public abstract class Dechet implements Comparable<Dechet>{
    protected String type;
    protected float masseVolumique;
    protected float pourcentage;

    public int compareTo(Dechet o) {
        return (int)(masseVolumique-o.masseVolumique);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getMasseVolumique() {
        return masseVolumique;
    }

    public void setMasseVolumique(float masseVolumique) {
        this.masseVolumique = masseVolumique;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }
}
