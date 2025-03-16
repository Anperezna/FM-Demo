package FootballManager;

import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Jugador extends Persona {

    // Només jugadors:
    protected int dorsal;
    protected String posicio; // NO LO SE SI ES SOLO PARA LOS JUGADORES O TAMBIEN PARA LOS ENTRENADORES
    protected int qualitat; // 30-100 indica la qualitat "teorica"


    // Constructor

    public Jugador(String nom, String cognom, int fecha, int motivacio, double sou_anual, int dorsal, String posicio, int qualitat) {
        super(nom, cognom, fecha, motivacio, sou_anual);
        this.dorsal = dorsal;
        this.qualitat = qualitat;
        this.posicio = posicio;
    }


    // Metodes

    public static void canviPosicio() {

    }

    static void dadesJugador(List<Persona> personas) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String nom = null;
        String cognom = null;
        int fecha = 0;
        int motivacio = 5;
        double sou_anual = 0;
        int dorsal = 0;
        String posicio = null;
        boolean continuar = false;
        boolean continuar2 = false;
        final int min = 30;
        final int max = 100;
        do {
            System.out.println("Nom: ");
            nom = scanner.nextLine();
            System.out.println("Cognom: ");
            cognom = scanner.nextLine();

            do {

                // Fecha
                System.out.println("Data de Naixement: ");
                fecha = scanner.nextInt();
                if (fecha < 1900 || fecha > 2025) {
                    System.out.println("Fecha invalida");
                } else {
                    continuar2 = true;
                }

                // Motivacio
                System.out.println("Nivell de motivació (1-10): ");
                motivacio = scanner.nextInt();
                if (motivacio < 1 || motivacio > 10) {
                    System.out.println("Motivacio no valida. Torna a intentar");
                } else {
                    continuar2 = true;
                }

                // Dorsal
                System.out.println("Dorsal del Jugador: ");
                dorsal = scanner.nextInt();
                if (dorsal > 99 || dorsal <= 0) {
                    System.out.println("No puede ser más grande de 99, más pequeño o igual a 0");
                } else {
                    continuar2 = true;
                }

                // Posicio
                System.out.println(" - Porter \n - Defensa \n - Migcampista \n - Davanter");

                System.out.println("Posicio del jugador: ");
                posicio = scanner.nextLine().toLowerCase();

                if (posicio.equals("porter") || posicio.equals("defensa") || posicio.equals("migcampista") || posicio.equals("davanter")) {
                    continuar = true;
                } else {
                    System.out.println("Introduce otra posicion");
                }
            } while (!continuar2);
            // Sou Anual

            System.out.println("Sou Anual: ");
            sou_anual = scanner.nextDouble();

            int qualitat = random.nextInt((max - min) + 1) + min;

            Jugador jugador = new Jugador(nom, cognom, fecha, motivacio, sou_anual, dorsal, posicio, qualitat);
            personas.add(jugador);

        } while (!continuar);

    }

    @Override
    public String toString() {
        return "Jugador * " +
                "nom='" + nom + '\'' +
                ", cognom='" + cognom + '\'' +
                ", fecha=" + fecha +
                ", motivacio=" + motivacio +
                ", sou_anual=" + sou_anual +
                ", dorsal=" + dorsal +
                ", posicio='" + posicio + '\'' +
                ", qualitat=" + qualitat +
                '*';
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
