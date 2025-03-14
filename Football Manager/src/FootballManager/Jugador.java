package FootballManager;

import java.util.Date;

public class Jugador extends Persona{

    // Només jugadors:
    protected int dorsal;
    protected String posicio; // NO LO SE SI ES SOLO PARA LOS JUGADORES O TAMBIEN PARA LOS ENTRENADORES
    protected int qualitat; // 30-100 indica la qualitat "teorica"


    // Constructor

    public Jugador(String nom, String cognom, String fecha, int motivacio, double sou_anual, int dorsal, String posicio, int qualitat) {
        super(nom, cognom, fecha, motivacio, sou_anual);
        this.dorsal = dorsal;
        this.posicio = posicio;
        this.qualitat = qualitat;
    }


    // Metodes

    public static void canviPosicio() {

    }

    // Getters and Setters

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
