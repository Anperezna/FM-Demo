package FootballManager;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Lliga {
    private String nom;
    private HashSet<Equip> equips; // Almacena los equipos

    public Lliga(String nom) {
        this.nom = nom;
        this.equips = new HashSet<>();
    }

    public static void crearLliga(List<Lliga> lligues) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre de la liga: ");
        String nom = scanner.nextLine().trim();
        if (nom.isEmpty()) {
            System.out.println("El nombre de la liga no puede estar vacío.");
            return;
        }

        System.out.println("Ingrese el número de equips: ");
        int numEquips;
        try {
            numEquips = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea
            if (numEquips <= 0) {
                System.out.println("El número de equipos debe ser un número positivo.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.nextLine(); // Limpiar el buffer
            return;
        }

        // Aquí creamos el objeto `Lliga` con el nombre proporcionado
        Lliga lliga = new Lliga(nom);
        lligues.add(lliga);  // Agregamos la liga a la lista

        System.out.println("Lliga " + nom + " creada!");

        for (int i = 0; i < numEquips; i++) {
            System.out.println("Introdueix el nom de l'equip " + (i + 1) + ": ");
            String nomEquip = scanner.nextLine().trim();
            lliga.afegirEquip(nomEquip);  // Usamos la variable `lliga`
        }

        lliga.disputarPartits();  // Usamos la variable `lliga`
    }



    public void afegirEquip(String nomEquip) {
        if (nomEquip.isEmpty()) {
            System.out.println("El nom de l'equip no pot estar buit.");
            return;
        }

        Equip e = new Equip(nomEquip);
        if (equips.add(e)) {
            System.out.println("Equip " + nomEquip + " afegit amb èxit!");
        } else {
            System.out.println("L'equip " + nomEquip + " ja està afegit a la lliga.");
        }
    }

    public void disputarPartits() {
        // Lógica para disputar partidos
        System.out.println("Disputant partits de la lliga " + nom + "...");
        for (Equip equip1 : equips) {
            for (Equip equip2 : equips) {
                if (!equip1.equals(equip2)) {
                    System.out.println(equip1.getNom() + " vs " + equip2.getNom());
                }
            }
        }
    }
}
