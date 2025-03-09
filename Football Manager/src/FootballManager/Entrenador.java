package FootballManager;

import java.util.Date;

public class Entrenador {
    protected String nom;
    protected String cognom;
    protected String fecha;
    protected int motivacio; // 1-10 nivell de motivacio
    protected double sou_anual;

    //Només entrenador:

    protected int tornejos_guanyats;
    protected boolean seleccionador;


    // Constructor

    public Entrenador(String nom, String cognom, String fecha,
                      int motivacio, boolean seleccionador,
                      int tornejos_guanyats, double sou_anual) {
        this.nom = nom;
        this.cognom = cognom;
        this.fecha = fecha;
        this.motivacio = motivacio;
        this.seleccionador = seleccionador;
        this.tornejos_guanyats = tornejos_guanyats;
        this.sou_anual = sou_anual;
    }


    //Getter and Setter


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isSeleccionador() {
        return seleccionador;
    }

    public void setSeleccionador(boolean seleccionador) {
        this.seleccionador = seleccionador;
    }

    public int getTornejos_guanyats() {
        return tornejos_guanyats;
    }

    public void setTornejos_guanyats(int tornejos_guanyats) {
        this.tornejos_guanyats = tornejos_guanyats;
    }

    public double getSou_anual() {
        return sou_anual;
    }

    public void setSou_anual(double sou_anual) {
        this.sou_anual = sou_anual;
    }

    public int getMotivacio() {
        return motivacio;
    }

    public void setMotivacio(int motivacio) {
        this.motivacio = motivacio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }
}
