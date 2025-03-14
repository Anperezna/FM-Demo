package FootballManager;

import jdk.jshell.spi.ExecutionControl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static List<String> fichajes;
    private static final String fileName = "src/FootballManager/mercat_fitxages.txt";
    private static final Path filePath = Paths.get(fileName);
    private static List<Persona> personas = new ArrayList<>();
    private static ArrayList<Equip> equips = new ArrayList<Equip>();

    public static void main(String[] args) throws IOException {
        DarAltaEquipos gestorEquips = new DarAltaEquipos();
        BaixaEquips eliminador = new BaixaEquips(gestorEquips.getEquips());

        President president = new President();
        Lliga lliga = new Lliga();

        String nom = null;


        fichajes = cargarFichajes();
        menuPrincipal(gestorEquips, eliminador, nom);
    }


    public static void menuPrincipal(DarAltaEquipos gestorEquips, BaixaEquips eliminador, String nom) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = false;

        do {
            System.out.println("<---------------MENU--------------->");
            System.out.println("Welcome to Politecnics Football Manager");
            System.out.println("1- Veure classificacio lliga actual 🏆");
            System.out.println("2- Gestionar equip ⚽");
            System.out.println("3- Donar d'alta equip");
            System.out.println("4- Donar d'alta jugador/a o entrenador/a");
            System.out.println("5- Consultar dades equip");
            System.out.println("6- Consultar dades jugador/a equip");
            System.out.println("7- Disputar nova lliga");
            System.out.println("8- Realitzar sessió entrenament (mercat/fitxages)");
            System.out.println("9- Transferir jugador/a");
            System.out.println("10- Desar dades equips");
            System.out.println("0- Sortir");
            System.out.println("<---------------MENU---------------> \n");
            System.out.println("Introdueix la teva opcio: ");

            try {
                int opcio = sc.nextInt(); // Intentar leer un entero

                if (opcio < 0 || opcio > 10) {
                    System.out.println("Opcio no valida. Torna a intentar");
                } else {
                    switch (opcio) {
                        case 1:
                            break;
                        case 2:
                            mostrarmenusecundario(eliminador);
                            break;
                        case 3:
                            darAltaEquipos(nom);
                            break;
                        case 4:
                            menuPersona();
                            break;
                        case 5:
                            consultarDadesEquip(nom);
                            break;
                        case 6:
                            consultarJugadorEquip(nom);
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 0:
                            continuar = true;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error. Debes ingresar un número entero...");
                sc.next();
            }
        } while (!continuar);
    }

    // 3.- DONAR ALTA EQUIP

    public static void darAltaEquipos(String nom) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = false;
        boolean continuar2 = false;
        int any_fundacio = 0;
        int numeroEquipos = 0;

        do {
            System.out.println("Quants equips vols crear? \n 0.- Para salir: ");
            numeroEquipos = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            if (numeroEquipos < 0) {
                System.out.println("El numero no puede ser negativo...");
            } else if (numeroEquipos == 0) {
                continuar = true;
            } else {
                for (int i = 0; i < numeroEquipos; i++) {
                    System.out.print("Nom del equip " + (i + 1) + ": ");
                    nom = scanner.nextLine().toLowerCase();

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
                    System.out.println("Equip creat amb èxit!");
                }
                continuar = true;
            }
        } while (!continuar);
    }

    // 4.- DONAR ALTA JUGADOR O ENTRENADOR - ÁNGEL PÉREZ MORENO

    private static void menuPersona() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = false;
        String persona = null;

        do {
            System.out.println("Quieres entrenador o jugador?: ");
            persona = scanner.nextLine().toLowerCase();
            if (persona.equals("entrenador")) {
                Entrenador.dadesEntrenador(personas);
                System.out.println("Entrenador creado con éxito!");
                continuar = true;
            } else if (persona.equals("jugador")) {

                Jugador.dadesJugador(personas);
                System.out.println("Jugador creado con éxito!");
                continuar = true;
            } else {
                System.out.println("Opcio no valida. Torna a intentar...");
            }
        } while (!continuar);
    }

    // 5.- CONSULTAR DADES EQUIP

    private static void consultarDadesEquip(String nom) {
        if (equips.isEmpty()) {
            System.out.println("No hay equipos creados.");
        } else {
            for (Equip equip : equips) {
                System.out.println(equip);
            }
        }
    }

    // 6.- CONSULTAR DADES JUGADOR EQUIP

    private static void consultarJugadorEquip(String nom) {
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

    // DAVID

    private static void mostrarmenusecundario(BaixaEquips eliminador) {
        boolean continuar2 = false;
        int opcio2 = 0;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1- Donar de Baixa equip");
            System.out.println("2- Modificar President/a");
            System.out.println("3- Destruir Entrenador");
            System.out.println("4- Fitxar jugador/a o entrenador/a");
            System.out.println("0- Sortir \n");
            System.out.println("Introdueix la teva opcio: ");
            opcio2 = scanner.nextInt();
            if (opcio2 > 4 || opcio2 < 0) {
                System.out.println("Opcio no valida. Torna a intentar");
                continuar2 = false;
            } else {
                switch (opcio2) {
                    case 1:
                        eliminador.eliminarEquip();
                        eliminador.mostrarEquips();
                        break;
                    case 2:

                    case 3:
                    case 4:
                    case 0:
                        continuar2 = true;
                }
            }
        } while (!continuar2);

    }

    private static List<String> cargarFichajes() throws IOException {
        if (Files.exists(filePath)) {
            return (Files.readAllLines(filePath));
        } else {
            return (new ArrayList<>());
        }
    }
}