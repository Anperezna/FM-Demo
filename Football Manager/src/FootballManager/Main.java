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
        MenuPrincipal(gestorEquips, eliminador, jugadorEntrenador);
    }


    public static void MenuPrincipal(DarAltaEquipos gestorEquips, BaixaEquips eliminador, AltaJugadorEntrenador jugadorEntrenador) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
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
            System.out.println("10- Desar dades");
            System.out.println("0- Sortir");
            System.out.println("Introdueix la teva opcio: ");
            int opcio = sc.nextInt();
            switch (opcio) {
                case 1:
                    break;
                case 2:
                    try {
                        System.out.println("Quin equip vols gestionar?: ");
                    } catch (IOException e) {
                        System.out.println("Equip no trobat");
                    }
                    System.out.println("1- Donar de Baixa equip");
                    System.out.println("2- Modificar President/a");
                    System.out.println("3- Destruir Entrenador");
                    System.out.println("4- Fitxar jugador/a o entrenador/a");
                    int opcio2 = sc.nextInt();
                    switch (opcio2) {
                        case 1:
                            eliminador.eliminarEquip();
                            eliminador.mostrarEquips();
                            break;
                        case 2:
                    }
                    break;
                case 3:
                    gestorEquips.darAltaEquipos();
                    gestorEquips.mostrarEquipos();
                    break;
                case 4:
                    jugadorEntrenador.main(new String[]{});
                    break;
                case 5:
                    break;
                case 6:
                    break;
            }
        }
    }
    private static List<String> cargarFichajes() throws IOException {
        if (Files.exists(filePath)) {
            return (Files.readAllLines(filePath));
            } else {
            return (new ArrayList<>());
        }
    }
}