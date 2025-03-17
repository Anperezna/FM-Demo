package FootballManager;

import java.time.LocalDate;

abstract class Persona {
    protected String nom;
    protected String cognom;
    protected LocalDate fecha;
    protected int motivacio; // 1-10 nivell de motivacio
    protected double sou_anual;

    public Persona(String nom, String cognom, LocalDate fecha, int motivacio, double sou_anual) {
        this.nom = nom;
        this.cognom = cognom;
        this.fecha = fecha;
        this.motivacio = motivacio;
        this.sou_anual = sou_anual;
    }

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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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
}
