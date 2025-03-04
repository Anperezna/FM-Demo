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
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        DarAltaEquipos gestorEquips = new DarAltaEquipos();
        BaixaEquips eliminador = new BaixaEquips(gestorEquips.getEquips());
        AltaJugadorEntrenador jugadorEntrenador = new AltaJugadorEntrenador();
        fichajes = cargarFichajes();
        menuPrincipal(gestorEquips, eliminador, jugadorEntrenador);
    }

    public static void menuPrincipal(DarAltaEquipos gestorEquips, BaixaEquips eliminador, AltaJugadorEntrenador jugadorEntrenador) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = false;
        do {
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
            System.out.println("0- Sortir \n");

            System.out.println("Introdueix la teva opcio: ");

            int opcio = sc.nextInt();
            if (opcio < 0 || opcio > 10) {
                System.out.println("Opcio no valida. Torna a intentar");
                continuar = false;
            } else {
                switch (opcio) {
                    case 1:
                        break;
                    case 2:
                        mostrarmenusecundario(eliminador);
                    case 3:
                        gestorEquips.darAltaEquipos();
                        gestorEquips.mostrarEquipos();
                        break;
                    case 4:
                        jugadorEntrenador.main(new String[]{});
                        break;
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 0:
                        continuar = true;
                }
            }
        } while (!continuar);
    }

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