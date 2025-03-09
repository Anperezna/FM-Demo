package FootballManager;

import java.util.Date;

public class Jugador {
    protected String nom;
    protected String cognom;
    protected String fecha;
    protected int motivacio; // 1-10 nivell de motivacio
    protected double sou_anual;

    // Només jugadors:
    protected int dorsal;
    protected String posicio; // NO LO SE SI ES SOLO PARA LOS JUGADORES O TAMBIEN PARA LOS ENTRENADORES
    protected int qualitat; // 30-100 indica la qualitat "teorica"


    // Constructor

    public Jugador(String nom, String cognom,
                   String fecha, int motivacio,
                   double sou_anual, int dorsal,
                   String posicio, int qualitat) {
        this.nom = nom;
        this.cognom = cognom;
        this.fecha = fecha;
        this.motivacio = motivacio;
        this.sou_anual = sou_anual;
        this.dorsal = dorsal;
        this.posicio = posicio;
        this.qualitat = qualitat;
    }


    // Metodes

    public static void canviPosicio() {

    }

    // Getters and Setters


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getMotivacio() {
        return motivacio;
    }

    public void setMotivacio(int motivacio) {
        this.motivacio = motivacio;
    }

    public double getSou_anual() {
        return sou_anual;
    }

    public void setSou_anual(double sou_anual) {
        this.sou_anual = sou_anual;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getPosicio() {
        return posicio;
    }

    public void setPosicio(String posicio) {
        this.posicio = posicio;
    }

    public int getQualitat() {
        return qualitat;
    }

    public void setQualitat(int qualitat) {
        this.qualitat = qualitat;
    }
}
