package FootballManager;

import java.util.ArrayList;
import java.util.Scanner;

public class DarAltaEquipos {
    private ArrayList<String> equips;

    // Constructor
    public DarAltaEquipos() {
        this.equips = new ArrayList<>();
    }

    public void darAltaEquipos() {
        Scanner sc = new Scanner(System.in);
        boolean afegirmes = true;


        System.out.println("Quants equips vols crear?");
        int quantitat = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < quantitat; i++) {
            System.out.print("Nom del equip " + (i + 1) + ": ");
            String nombreEquipo = sc.nextLine();
            if (equips.contains(nombreEquipo)) {
                System.out.println("Error: El Equips: " + nombreEquipo + " ya existeix.");
            } else {
                equips.add(nombreEquipo);
            }
        }
    }

    public void mostrarEquipos() {
        System.out.println("\nLista de equips:");
        for (String equipo : equips) {
            System.out.println("- " + equipo);
        }
    }
    public ArrayList<String> getEquips() {
        return equips;
    }

    }


