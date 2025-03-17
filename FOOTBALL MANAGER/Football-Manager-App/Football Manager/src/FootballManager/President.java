package FootballManager;

public class President {
    private String nom;

    public President(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "President{" +
                "nom='" + nom + '\'' +
                '}';
    }
}
