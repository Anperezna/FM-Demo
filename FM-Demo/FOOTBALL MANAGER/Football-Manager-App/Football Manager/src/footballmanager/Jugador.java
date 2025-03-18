package footballmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Jugador extends Persona {

    // Només jugadors:
    protected int dorsal;
    protected String posicio; // NO LO SE SI ES SOLO PARA LOS JUGADORES O TAMBIEN PARA LOS ENTRENADORES
    protected int qualitat; // 30-100 indica la qualitat "teorica"
    protected LocalDate fecha;

    // Constructor

    public Jugador(String nom, String cognom, LocalDate fecha, int motivacio, double sou_anual, int dorsal, String posicio, int qualitat) {
        super(nom, cognom, fecha, motivacio, sou_anual);
        this.dorsal = dorsal;
        this.qualitat = qualitat;
        this.posicio = posicio;
        this.fecha = fecha;
    }


    // Metodes

    public static void canviPosicio() {

    }

    public static void dadesJugador(List<Persona> personas) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String nom = null;
        String cognom = null;
        int motivacio = 5;
        double sou_anual = 0;
        int dorsal = 0;
        LocalDate fecha = null;
        String posicio = null;
        boolean continuar2 = false;
        boolean continuar3 = false;
        boolean continuar4 = false;
        boolean continuar5 = false;
        boolean fechaCorrecta = false;

        final int min = 30;
        final int max = 100;

        System.out.println("Nom: ");
        nom = scanner.nextLine().trim();
        System.out.println("Cognom: ");
        cognom = scanner.nextLine().trim();

        do {
            // Fecha
            System.out.println("Data de Naixement (DD/MM/YYYY): ");
            String fechaStr = scanner.nextLine();
            try {
                fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                continuar2 = true;
                LocalDate hoy = LocalDate.now();
                if (fecha.isAfter(hoy)){
                    System.out.println("Error. NO ESTAMOS EN EL FUTURO...");
                } else {
                    fechaCorrecta = true;
                }
            } catch (Exception e) {
                System.out.println("Fecha invalida, intenta de nuevo.");
            }
        } while (!fechaCorrecta);

        do {
            // Motivacio
            System.out.println("Nivell de motivació (1-10): ");
            motivacio = scanner.nextInt();
            if (motivacio < 1 || motivacio > 10) {
                System.out.println("Motivacio no valida. Torna a intentar");
            } else {
                continuar3 = true;
            }
        } while (!continuar3);

        do {
            // Dorsal
            System.out.println("Dorsal del Jugador: ");
            dorsal = scanner.nextInt();
            if (dorsal > 99 || dorsal <= 0) {
                System.out.println("No puede ser más grande de 99, más pequeño o igual a 0");
            } else {
                continuar4 = true;
            }
        } while (!continuar4);

        scanner.nextLine();
        do {
            // Posicio
            System.out.println(" - POR \n - DEF \n - MIG \n - DAV");
            System.out.println("Posicio del jugador: ");
            posicio = scanner.nextLine().toUpperCase().trim();


            if (posicio.equals("POR") || posicio.equals("DEF") || posicio.equals("MIG") || posicio.equals("DAV")) {
                continuar5 = true;
            } else {
                System.out.println("Introduce otra posicion");
            }
        } while (!continuar5);

        do {
            // Sou Anual
            System.out.println("Sou Anual: ");
            sou_anual = scanner.nextDouble();
        } while (sou_anual < 0);


        int qualitat = random.nextInt((max - min) + 1) + min;

        Jugador jugador = new Jugador(nom, cognom, fecha, motivacio, sou_anual, dorsal, posicio, qualitat);
        personas.add(jugador);
        System.out.println("JUGADOR GUARDADO!");

    }

    public static void consultarJugadorEnEquipo(ArrayList<Equip> equips, String nombreEquipo, String nombreJugador) {
        for (Equip equip : equips) {
            if (nombreEquipo.equalsIgnoreCase(equip.getNom())) { // Comparación ignorando mayúsculas
                System.out.println("El equipo ha sido encontrado.");

                // Asumimos que Equip tiene un método para obtener la lista de jugadores
                for (Persona persona : equip.getJugadores()) {
                    if (persona instanceof Jugador && nombreJugador.equalsIgnoreCase(persona.getNom())) {
                        Jugador jugador = (Jugador) persona;
                        System.out.println("Dades del jugador:");
                        System.out.println("Nom: " + jugador.getNom());
                        System.out.println("Cognom: " + jugador.getCognom());
                        System.out.println("Data de Naixement: " + jugador.getFecha());
                        System.out.println("Motivació: " + jugador.getMotivacio());
                        System.out.println("Sou anual: " + jugador.getSou_anual());
                        System.out.println("Dorsal: " + jugador.getDorsal());
                        System.out.println("Posició: " + jugador.getPosicio());
                        System.out.println("Qualitat: " + jugador.getQualitat());
                        return; // Salir si el jugador ha sido encontrado
                    }
                }

                System.out.println("EL JUGADOR NO EXISTE EN ESTE EQUIPO.");
                return; // Salir si el jugador no fue encontrado
            }
        }

        System.out.println("EL EQUIPO NO HA SIDO ENCONTRADO.");
    }

    public static void fitxarJugador(String nomJugador, List<Persona> personas, Equip equip) {
        for (int i = 0; i < personas.size(); i++) {
            Persona persona = personas.get(i);
            if (persona instanceof Jugador && persona.getNom().equalsIgnoreCase(nomJugador)) {
                equip.addJugador((Jugador) persona); // Añadir al equipo
                personas.remove(i); // Eliminar del mercado
                System.out.println("Jugador " + nomJugador + " fitxat amb èxit!");
                return;
            }
        }
        System.out.println("No s'ha trobat el jugador amb nom: " + nomJugador);
    }

    public void entrenament() {
        this.qualitat += 5;
        if (this.qualitat > 100) this.qualitat = 100;

        this.motivacio += 1;
        if (this.motivacio > 10) this.motivacio = 10;
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
