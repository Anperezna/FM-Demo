package FootballManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Equip {
    protected String nom;
    protected int any_fundacio;
    protected String ciutat;

    public Equip(String nom, int any_fundacio, String ciutat) {
        this.nom = nom;
        this.any_fundacio = any_fundacio;
        this.ciutat = ciutat;
    }

    public Equip(String nom) {
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

    public static void darAltaEquips(String nom, List<Equip> equips) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = false;
        int any_fundacio;

        do {
            System.out.println("Quants equips vols crear? \n 0.- Para salir: ");
            int numeroEquipos = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            if (numeroEquipos < 0) {
                System.out.println("El numero no puede ser negativo...");
            } else if (numeroEquipos == 0) {
                continuar = true;
            } else {
                for (int i = 0; i < numeroEquipos; i++) {
                    System.out.print("Nom del equip " + (i + 1) + ": ");
                    nom = scanner.nextLine().toLowerCase();

                    boolean existe = false;
                    for (Equip equip : equips) {
                        if (equip.getNom().equalsIgnoreCase(nom)) { // Comparación sin importar mayúsculas
                            existe = true;
                            break;
                        }
                    }

                    if (existe) {
                        System.out.println("El nombre de este equipo ya existe. No se ha creado el equipo.");
                        continue; // Saltar al siguiente ciclo del for
                    }

                    do {
                        System.out.println("Introduce el año de fundación: ");
                        any_fundacio = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer
                        if (any_fundacio < 1857 || any_fundacio > 2025) {
                            System.out.println("El equipo no puede ser tan antiguo...");
                        }
                    } while (any_fundacio < 1857 || any_fundacio > 2025);

                    System.out.println("Introduce la ciudad del equipo: ");
                    String ciudad = scanner.nextLine().toLowerCase();

                    // Agregar el nuevo equipo a la lista de persona
                    Equip equip = new Equip(nom, any_fundacio, ciudad);
                    equips.add(equip);
                }
                continuar = true;
            }
        } while (!continuar);
    }

    public static void consultarDadesEquip(String nom, List<Equip> equips) {
        if (equips.isEmpty()) {
            System.out.println("No hay equipos creados.");
        } else {
            for (Equip equip : equips) {
                System.out.println(equip);
            }
        }
    }

    public static void consultarJugadorEquip(String nom, List<Equip> equips, List<Persona> personas) {
        Scanner scanner = new Scanner(System.in);
        boolean encontradoEquipo = false;
        boolean encontradoJugador = false;

        do {
            System.out.println("Introdueix el nom del equip a buscar: ");
            String nombreEquipo = scanner.nextLine().toLowerCase();

            for (Equip equip : equips) {
                if (nombreEquipo.equals(equip.getNom())) {
                    System.out.println("El equipo ha sido encontrado.");
                    encontradoEquipo = true;

                    do {
                        System.out.println("Introduce el nombre del jugador: ");
                        String nombreJugador = scanner.nextLine();

                        for (Persona persona : personas) {
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
                            System.out.println("EL JUGADOR NO EXISTE EN ESTE EQUIPO. Intenta de nuevo.");
                        }
                    } while (!encontradoJugador);

                    break; // Salir del bucle si se encuentra el equipo
                }
            }

            if (!encontradoEquipo) {
                System.out.println("EL EQUIPO NO HA SIDO ENCONTRADO. Intenta de nuevo.");
            }
        } while (!encontradoEquipo);
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