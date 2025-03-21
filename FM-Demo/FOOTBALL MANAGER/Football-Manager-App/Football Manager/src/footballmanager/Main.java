package footballmanager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<String> fichajes;
    private static final String fileName = "src/mercat_fitxages.txt";

    private static final Path filePath = Paths.get(fileName);
    private static List<Persona> personas = new ArrayList<>();
    private static ArrayList<Equip> equips = new ArrayList<Equip>();

    /**
    *@author Anperezna
    *@author Puliidoo
     */
    public static void main(String[] args) throws IOException {
        String nom = null;
        fichajes = cargarFichajes();
        menuPrincipal();
    }

    /**
    * Menu principal, se printea las opciones que tiene el usuario
    * @throws InputMismatchException Si el input ha sido incorrecto
    * @throws Exception Si ha habido algun problema y cierra el programa
    */
    public static void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = false;

        do {
            menu();
            try {
                int opcio = sc.nextInt(); // Intentar leer un entero

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
                            Equip.darAltaEquipos(equips);
                            break;
                        case 4:
                            menuPersona();
                            break;
                        case 5:
                            Equip.consultarDadesEquip(equips);
                            break;
                        case 6:
                            consultarJugadorEquip();
                            break;
                        case 7:
                            Lliga lliga = new Lliga("");
                            lliga.crearLliga(equips);
                            break;
                        case 8:
                            realitzarEntrenament();
                            break;
                        case 9:
                            transferirJugadorOEntrenador();
                        case 10:
                            guardarDatos();
                        case 0:
                            continuar = true;
                    }
                }
            } catch (InputMismatchException c) {
                System.out.println("Error. Debes ingresar un número entero...");
                sc.next();
            } catch (Exception e) {
                System.out.println("Error. Letra o caracter no válido" + e.getMessage());
            }
        } while (!continuar);
    }

    private static void menu() {
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
    }

    /**
     * Guarda los datos del usuario, tanto equipos como jugadores en un archivo
     */

    private static void guardarDatos() {
        String mercado = "src/mercat_fitxages.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(mercado, true))) {
            // Guardar Jugadores
            for (Persona persona : personas) {
                if (persona instanceof Jugador) {
                    Jugador jugador = (Jugador) persona;
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    // Formatear la fecha
                    String fechaFormateada = jugador.getFecha().format(formatter);
                    String linea = String.format("J"+";%s;%s;%s;%.2f;%d;%s;%d",
                            jugador.getNom(),
                            jugador.getCognom(),
                            fechaFormateada,
                            jugador.getSou_anual(),
                            jugador.getDorsal(),
                            jugador.getPosicio(),
                            jugador.getQualitat());
                    writer.write(linea);
                    writer.newLine();
                } else if (persona instanceof Entrenador) {
                    Entrenador entrenador = (Entrenador) persona;
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    // Formatear la fecha
                    String fechaFormateada = entrenador.getFecha().format(formatter);
                    String linea = String.format("E;%s;%s;%s;%.2f;%d;%b",
                            entrenador.getNom(),
                            entrenador.getCognom(),
                            fechaFormateada,
                            entrenador.getSou_anual(),
                            entrenador.getTornejos_guanyats(),
                            entrenador.isSeleccionador());
                    writer.write(linea);
                    writer.newLine();
                }
            }
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Ha habido un problema" + e.getMessage());
        }
    }

    /**
     * Carga los fichajes desde un archivo
     *
     */
    private static void menuPersona() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = false;
        String persona = null;
        char dato;

        do {
            System.out.println("Quieres entrenador o jugador?: ");
            persona = scanner.nextLine().toLowerCase();
            if (persona.equals("entrenador")) {
                Entrenador.dadesEntrenador(personas);
                System.out.println("Entrenador creado con éxito!");
                dato = 'E';
                continuar = true;
            } else if (persona.equals("jugador")) {
                Jugador.dadesJugador(personas);
                System.out.println("Jugador creado con éxito!");
                dato = 'J';
                continuar = true;
            } else {
                System.out.println("Opcio no valida. Torna a intentar...");
            }
        } while (!continuar);
    }

    /**
     * Consultar el jugador de un equip en concret
     */
    private static void consultarJugadorEquip() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introdueix el nom del equip a buscar: ");
        String nombreEquipo = scanner.nextLine().toLowerCase();

        System.out.println("Introduce el nombre del jugador: ");
        String nombreJugador = scanner.nextLine();

        Jugador.consultarJugadorEnEquipo(equips, nombreEquipo, nombreJugador);
    }

    /**
     * Transferir el jugador o entrenador a un altre equip
     */
    private static void transferirJugadorOEntrenador() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el nom del equip des del qual vols transferir: ");
        String nomEquipTransferir = sc.nextLine();

        Equip equipTransferir = null;
        for (Equip equip : equips) {
            if (equip.getNom().equalsIgnoreCase(nomEquipTransferir)) {
                equipTransferir = equip;
                break;
            }
        }

        if (equipTransferir == null) {
            System.out.println("No s'ha trobat el equip " + nomEquipTransferir);
        } else {
            equipTransferir.gestionarTransferencia(personas, equips);
        }
    }

    /**
     * Realitza un entrenament tant per jugador com per entrenador
     */
    private static void realitzarEntrenament() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Iniciant sessio de entrenament");
        System.out.println("Vols entrenar un jugador o un entrenador?: ");
        String respuesta = sc.nextLine().toLowerCase();
        if (respuesta.equals("jugador")) {
            System.out.println("Introdueix el nom del jugador: ");
            String nomJugador = sc.nextLine();
            for (Persona persona : personas) {
                if (persona instanceof Jugador) {
                    Jugador jugador = (Jugador) persona;
                    if (nomJugador.equals(jugador.getNom())) {
                        jugador.entrenament();
                        jugador.canviPosicio();
                        System.out.println("El jugador " + jugador.getNom() + "ha sigut entrenat");
                        return;
                    }
                }
            }
            System.out.println("No s'ha trobat ningun jugador amb aquest nom");
        } else if (respuesta.equals("entrenador")) {
            System.out.println("Introdueix el nom del entrenador: ");
            String nomEntrenador = sc.nextLine();
            for (Persona persona : personas) {
                if (persona instanceof Entrenador) {
                    Entrenador entrenador = (Entrenador) persona;
                    if (nomEntrenador.equals(entrenador.getNom())) {
                        entrenador.entrenament();
                        entrenador.incrementarSou();
                        System.out.println("El entrenador " + entrenador.getNom() + "ha sigut entrenat");
                        return;
                    }
                }
            }
            System.out.println("No s'ha trobat cap entrenador amb aquest nom");
        } else {
            System.out.println("La resposta no es valida introdueix jugador o entrenador");
        }
    }

    /**
     * Modifica el president
     */
    private static void modificarPresident() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el nom del Equip on vols modificar el president: ");
        String nomEquip = sc.nextLine().toLowerCase();

        Equip equipSeleccionat = null;
        for (Equip equip : equips) {
            if (equip.getNom().equalsIgnoreCase(nomEquip)) {
                equipSeleccionat = equip;
                break;
            }
        }
        if (equipSeleccionat == null) {
            System.out.println("No s'ha trobar el nom del equip");
            return;
        }
        System.out.println("Introdueix el nom del Equip: ");
        String nouPresident = sc.nextLine().toLowerCase();

        equipSeleccionat.modificaPresident(nouPresident);
    }

    /**
     * Mostrar el menu secundario cuando el usuario selecciona el numero 2
     */

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
                continuar2 = true;
            } else {
                switch (opcio2) {
                    case 1:
                        Equip.eliminarEquip(equips);
                        Equip.mostrarEquips(equips);
                        break;
                    case 2:
                        modificarPresident();
                    case 3:
                        Entrenador.eliminarEntrenador(personas);
                    case 4:
                        mostrarJugadorFitxatge();
                    case 0:
                        continuar2 = true;
                }
            }
        } while (!continuar2);

    }

    /**
     * Mostrar el jugador para ficharlo
     */
    private static void mostrarJugadorFitxatge(){
        Scanner scanner = new Scanner(System.in);
        boolean continuar = false;
        do {
            System.out.println("Quieres fichar jugador o entrenador?");
            String tipo = scanner.nextLine().toLowerCase().trim();
            System.out.println("Introdueix el nom del jugador: ");
            String nomJugador = scanner.nextLine().trim();

            Equip equipo = seleccionarEquipo();

            if (tipo.equals("jugador")) {
                Jugador.fitxarJugador(nomJugador, personas, equipo);
            } else if (tipo.equals("entrenador")) {
                Entrenador.fitxarEntrenador(nomJugador, personas, equipo);
            } else {
                System.out.println("Opció no valida. Torna a intentar...");
            }

        } while (!continuar);

    }

    /**
     * Selecciona el equip y enseñar datos
     * @return
     */

    private static Equip seleccionarEquipo() {
        if (equips.isEmpty()) {
            System.out.println("No hi ha equips disponibles. Crea un equip primer.");
            return null; // O manejar de otra forma
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecciona un equip:");

        for (int i = 0; i < equips.size(); i++) {
            System.out.println((i + 1) + "- " + equips.get(i).getNom());
        }

        int opcioEquip = scanner.nextInt();
        scanner.nextLine();

        if (opcioEquip > 0 && opcioEquip <= equips.size()) {
            return equips.get(opcioEquip - 1); // Devolver el equipo seleccionado
        } else {
            System.out.println("Opció no vàlida. Torna a intentar...");
            return seleccionarEquipo(); // Llamar de nuevo si la opción no es válida
        }

    }

    /**
     * Carga los ficheros en un filepath
     * @return
     * @throws IOException
     */
    private static List<String> cargarFichajes() throws IOException {
        if (Files.exists(filePath)) {
            return (Files.readAllLines(filePath));
        } else {
            return (new ArrayList<>());
        }
    }
}