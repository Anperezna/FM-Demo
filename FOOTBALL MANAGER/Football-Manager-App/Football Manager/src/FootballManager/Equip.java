package FootballManager;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Equip {
    protected String nom;
    protected int any_fundacio;
    protected String ciutat;
    protected int punts; // Puntos del equipo
    protected int partitsDisputats; // Partidos disputados
    protected int golsAFavor; // Goles a favor
    protected int golsEnContra; // Goles en contra
    private ArrayList<Persona> jugadores;
    private Entrenador entrenador;
    private ArrayList<Jugador> jugadors;
    private ArrayList<Equip> equips;
    protected President president;

    public Equip(String nom, int any_fundacio, String ciutat) {
        this.nom = nom;
        this.any_fundacio = any_fundacio;
        this.ciutat = ciutat;
        this.punts = 0;
        this.partitsDisputats = 0;
        this.golsAFavor = 0;
        this.golsEnContra = 0;
        this.jugadores = new ArrayList<>();
        this.jugadors = new ArrayList<>();
        this.president = null;
    }


    public ArrayList<Persona> getJugadores() {
        return jugadores;
    }

    public void afegirJugador(Persona jugador) {
        jugadores.add(jugador);
    }

    public static void darAltaEquipos(ArrayList<Equip> equips) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = false;
        boolean continuar2;
        int any_fundacio;
        int numeroEquipos;

        do {
            System.out.println("¿Cuántos equipos quieres crear? \n 0.- Para salir: ");
            numeroEquipos = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            if (numeroEquipos < 0) {
                System.out.println("El número no puede ser negativo...");
            } else if (numeroEquipos == 0) {
                continuar = true;
            } else {
                for (int i = 0; i < numeroEquipos; i++) {
                    System.out.print("Nombre del equipo " + (i + 1) + ": ");
                    String nom = scanner.nextLine().toLowerCase();

                    // Verificar si el equipo ya existe
                    boolean existe = false;
                    for (Equip equip : equips) {
                        if (equip.getNom().equals(nom)) {
                            existe = true;
                            break;
                        }
                    }

                    if (existe) {
                        System.out.println("El nombre de este equipo ya existe. No se ha creado el equipo.");
                        continue; // Saltar al siguiente ciclo del for
                    }

                    continuar2 = false;
                    do {
                        System.out.println("Introduce el año de fundación: ");
                        any_fundacio = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer
                        if (any_fundacio < 1857 || any_fundacio > 2025) {
                            System.out.println("El equipo no puede ser tan antiguo, o al menos no tanto como el equipo más antiguo del mundo...");
                            continuar2 = false;
                        } else {
                            continuar2 = true;
                        }
                    } while (!continuar2);

                    System.out.println("Introduce la ciudad del equipo: ");
                    String ciudad = scanner.nextLine().toLowerCase();

                    // Agregar el nuevo equipo a la lista
                    equips.add(new Equip(nom, any_fundacio, ciudad));
                    System.out.println("¡Equipo creado con éxito!");
                }
                continuar = true;
            }
        } while (!continuar);
    }

    public void consultarJugador(String nombreJugador) {
        boolean encontradoJugador = false;

        for (Persona persona : jugadores) {
            if (persona instanceof Jugador && nombreJugador.equals(persona.getNom())) {
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
                encontradoJugador = true;
            }
        }

        if (!encontradoJugador) {
            System.out.println("EL JUGADOR NO EXISTE EN ESTE EQUIPO.");
        }
    }

    public static void consultarDadesEquip(ArrayList<Equip> listaEquips) {
        if (listaEquips.isEmpty()) {
            System.out.println("No hay equipos creados.");
        } else {
            for (Equip equip : listaEquips) {
                System.out.println(equip);
            }
        }
    }

    // Método para actualizar estadísticas después de un partido
    public void actualitzarEstadistiques(int golsAFavor, int golsEnContra) {
        this.golsAFavor += golsAFavor;
        this.golsEnContra += golsEnContra;
        this.partitsDisputats++;

        // Actualizar puntos
        if (golsAFavor > golsEnContra) {
            this.punts += 3; // 3 puntos por victoria
        } else if (golsAFavor == golsEnContra) {
            this.punts += 1; // 1 punto por empate
        }
    }

    public void transferirJugador(Jugador jugador, Equip nouEquip) {
        if (jugadors.contains(jugador)) {
            nouEquip.addJugador(jugador);
            jugadors.remove(jugador);
            System.out.println("Jugador " + jugador.getNom() + " transferit a " + nouEquip.getNom() + " correctament.");
        } else {
            System.out.println("El jugador " + jugador.getNom() + " no pertany a " + this.nom + ".");
        }
    }

    public void transferirEntrenador(Entrenador entrenador, Equip nouEquip) {
        if (this.entrenador != null && this.entrenador.equals(entrenador)) {
            nouEquip.setEntrenador(entrenador);
            this.entrenador = null;
            System.out.println("Entrenador " + entrenador.getNom() + " transferit a " + nouEquip.getNom() + " correctament.");
        } else {
            System.out.println("El entrenador " + entrenador.getNom() + " no pertany a " + this.nom + ".");
        }
    }

    public void gestionarTransferencia(List<Persona> personas, List<Equip> equips) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el nom del jugador o entrenador que vols transferir: ");
        String nomTransferir = sc.nextLine();

        Persona personaATransferir = null;
        for (Persona persona : personas) {
            if (persona.getNom().equalsIgnoreCase(nomTransferir)) {
                personaATransferir = persona;
                break;
            }
        }

        if (personaATransferir == null) {
            System.out.println("No s'ha trobat cap jugador o entrenador amb aquest nom");
            return;
        }

        System.out.println("Equipos disponibles per a la transferència: ");
        for (Equip equip : equips) {
            System.out.println(equip.getNom());
        }

        System.out.println("Introdueix el nom del equip al que vols transferir: ");
        String nomNouEquip = sc.nextLine();

        Equip nouEquip = null;
        for (Equip equip : equips) {
            if (equip.getNom().equalsIgnoreCase(nomNouEquip)) {
                nouEquip = equip;
                break;
            }
        }

        if (nouEquip == null) {
            System.out.println("No s'ha trobat el nou equip");
            return;
        }

        if (personaATransferir instanceof Jugador) {
            Jugador jugador = (Jugador) personaATransferir;
            this.transferirJugador(jugador, nouEquip);
        } else if (personaATransferir instanceof Entrenador) {
            Entrenador entrenador = (Entrenador) personaATransferir;
            this.transferirEntrenador(entrenador, nouEquip);
        }
    }

    public void addJugador(Jugador jugador) {
        jugadors.add(jugador);
        System.out.println("Jugador " + jugador.getNom() + " afegit a l'equip " + nom);
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
        System.out.println("Entrenador " + entrenador.getNom() + " assignat a l'equip " + nom);
    }

    public Equip() {
        this.president = null;
    }

    public void modificaPresident(String nouPresident) {
        if (this.president != null && this.president.getNom().equalsIgnoreCase(nouPresident)) {
            System.out.println("El president ya es president d'aquest equip. No es relitzara cap canvi");
        } else {
            if (this.president == null) {
                System.out.println("En aquest equip no hi havia president. S'ha assignat el nou president");
            }
            this.president = new President(nouPresident);
            System.out.println("El president s'ha actualitzat correctament");
        }
    }

    public static void eliminarEquip(ArrayList<Equip> equips) {
        Scanner sc = new Scanner(System.in);

        if(equips.isEmpty()) {
            System.out.println("No hi ha equips per eliminar");
            return;
        }
        System.out.println("\nLLisata de equips actual: ");
        mostrarEquips(equips);

        System.out.println("\n Introdueix el nom del equip que vols eliminar: ");
        String nomEliminar = sc.nextLine();

        Equip equipoEliminar = null;
        for (Equip equip : equips) {
            if (equip.getNom().equalsIgnoreCase(nomEliminar)) {
                equipoEliminar = equip;
                break;
            }
        }

        if(equipoEliminar != null) {
            equips.remove(equipoEliminar);
            System.out.println("\nEquip " + nomEliminar + " eliminat amb exit" );
        } else {
            System.out.println("\nEquip " + nomEliminar + " no existeix el equip");
        }
    }

    public static void mostrarEquips(ArrayList<Equip> equips) {
        if(equips.isEmpty()) {
            System.out.println("La lista esta vacia");
        } else {
            System.out.println("\nEquips: ");
            for (Equip equip : equips) {
                System.out.println("- " + equip);
            }
        }
    }

    // Métodos para obtener estadísticas
    public int getPunts() {
        return punts;
    }

    public President getPresident() {
        return president;
    }

    public void setPresident(President president) {
        this.president = president;
    }

    public int getGolsAFavor() {
        return golsAFavor;
    }

    public int getGolsEnContra() {
        return golsEnContra;
    }

    @Override
    public String toString() {
        return "Equip: {" +
                "nom='" + nom + '\'' +
                ", any_fundacio=" + any_fundacio +
                ", ciutat='" + ciutat + '\'' +
                ", punts=" + punts +
                ", partitsDisputats=" + partitsDisputats +
                ", golsAFavor=" + golsAFavor +
                ", golsEnContra=" + golsEnContra +
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
