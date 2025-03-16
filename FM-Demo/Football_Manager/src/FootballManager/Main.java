package FootballManager;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static List<String> fichajes;
    private static final String fileName = "src/FootballManager/mercat_fitxages.txt";
    private static final Path filePath = Paths.get(fileName);
    static List<Persona> personas = new ArrayList<>();
    protected static ArrayList<Equip> equips = new ArrayList<Equip>();
    protected static ArrayList<Lliga> lligues = new ArrayList<Lliga>();


    public static void main(String[] args) throws IOException {


        President president = new President();

        String nom = null;


        fichajes = cargarFichajes();


        menuPrincipal(nom);
    }

    public static void menuPrincipal(String nom) {
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

            try {
                System.out.println("Introdueix la teva opcio: ");
                int opcio = sc.nextInt();

                if (opcio < 0 || opcio > 10) {
                    System.out.println("Opcio no valida. Torna a intentar");
                } else {
                    switch (opcio) {
                        case 1:
                            break;
                        case 2:
                            mostrarmenusecundario();
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
                            // Crear la lliga
                            Lliga.crearLliga(lligues);
                            break;
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
        Equip.darAltaEquips(nom, equips);
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

    public static void consultarDadesEquip(String nom) {
        Equip.consultarJugadorEquip(nom, equips, personas);
    }

    // 6.- CONSULTAR DADES JUGADOR EQUIP

    private static void consultarJugadorEquip(String nom) {
        Equip.consultarDadesEquip(nom, equips);
    }

    // 7. Crear lliga

    // DAVID

    private static void mostrarmenusecundario() {
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