package FootballManager;

public class Equip {
    protected String nom;
    protected int any_fundacio;
    protected String ciutat;

    public Equip(String nom, int any_fundacio, String ciutat) {
        this.nom = nom;
        this.any_fundacio = any_fundacio;
        this.ciutat = ciutat;
    }

    public Equip() {
    }

    // toString

    @Override
    public String toString() {
        return "Equip: {" +
                "nom='" + nom + '\'' +
                ", any_fundacio=" + any_fundacio +
                ", ciutat='" + ciutat + '\'' +
                '}';
    }


    // Getters y Setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public int getAny_fundacio() {
        return any_fundacio;
    }

    public void setAny_fundacio(int any_fundacio) {
        this.any_fundacio = any_fundacio;
    }
}