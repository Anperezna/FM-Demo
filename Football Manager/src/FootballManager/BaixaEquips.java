package FootballManager;

import java.util.ArrayList;
import java.util.Scanner;

public class BaixaEquips {
    private ArrayList<String> equips;

    public BaixaEquips(ArrayList<String> equips) {
        this.equips = equips;
    }
    public void eliminarEquip() {
        Scanner sc = new Scanner(System.in);

        if(equips.isEmpty()) {
            System.out.println("No hi ha equips per eliminar");
            return;
        }
        System.out.println("\nLLisata de equips actual: ");
        mostrarEquips();

        System.out.println("\n Introdueix el nom del equip que vols eliminar: ");
        String nomEliminar = sc.nextLine();

        if(equips.contains(nomEliminar)) {
            equips.remove(nomEliminar);
            System.out.println("\nEquip " + nomEliminar + " eliminat amb exit" );
        } else {
            System.out.println("\nEquip " + nomEliminar + " no existeix el equip");
        }
    }
    public void mostrarEquips() {
        if(equips.isEmpty()) {
            System.out.println("La lista esta vacia");
        } else {
            System.out.println("\nEquips: ");
            for (String equip : equips) {
                System.out.println("- " + equip);
            }
        }
    }
}


