package FootballManager;

import java.util.Date;

public class Entrenador extends Persona {

    //Només entrenador:

    protected int tornejos_guanyats;
    protected boolean seleccionador;


    // Constructor


    public Entrenador(String nom, String cognom, String fecha, int motivacio, double sou_anual, int tornejos_guanyats, boolean seleccionador) {
        super(nom, cognom, fecha, motivacio, sou_anual);
        this.tornejos_guanyats = tornejos_guanyats;
        this.seleccionador = seleccionador;
    }



    // Getters y Setters


    public int getTornejos_guanyats() {
        return tornejos_guanyats;
    }

    public void setTornejos_guanyats(int tornejos_guanyats) {
        this.tornejos_guanyats = tornejos_guanyats;
    }

    public boolean isSeleccionador() {
        return seleccionador;
    }

    public void setSeleccionador(boolean seleccionador) {
        this.seleccionador = seleccionador;
    }
}
