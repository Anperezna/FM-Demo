package FootballManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Lliga {
    private String nom;
    private ArrayList<Equip> equipsParticipantes;

    public Lliga(String nom) {
        this.nom = nom;
        this.equipsParticipantes = new ArrayList<>();
    }

    public void afegirEquip(Equip equip) {
        if (!equipsParticipantes.contains(equip)) {
            equipsParticipantes.add(equip);
        } else {
            System.out.println("L'equip ja està inscrit a la lliga.");
        }
    }

    public void disputarPartits() {
        for (int i = 0; i < equipsParticipantes.size(); i++) {
            for (int j = i + 1; j < equipsParticipantes.size(); j++) {
                Equip equip1 = equipsParticipantes.get(i);
                Equip equip2 = equipsParticipantes.get(j);

                // Simular un partit
                int golsEquip1 = (int) (Math.random() * 5); // Gols aleatoris
                int golsEquip2 = (int) (Math.random() * 5);

                // Actualizar estadístiques
                equip1.actualitzarEstadistiques(golsEquip1, golsEquip2);
                equip2.actualitzarEstadistiques(golsEquip2, golsEquip1);
            }
        }
    }

    public void mostrarClassificacio() {
        System.out.println("Classificació de la lliga " + nom + ":");
        for (Equip equip : equipsParticipantes) {
            System.out.println(equip);
        }
    }

    public void crearLliga(ArrayList<Equip> equips) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el nombre de la nueva liga: ");
        String nomLliga = scanner.nextLine();
        this.nom = nomLliga;

        boolean continuar = false;
        while (!continuar) {
            System.out.println("Introduce el nombre del equipo a añadir (0 para terminar): ");
            String nomEquip = scanner.nextLine().toLowerCase();

            if (nomEquip.equals("0")) {
                continuar = true;
            } else {
                Equip equipoEncontrado = null;
                for (Equip equip : equips) {
                    if (equip.getNom().equals(nomEquip)) {
                        equipoEncontrado = equip;
                        break;
                    }
                }

                if (equipoEncontrado != null) {
                    afegirEquip(equipoEncontrado);
                    System.out.println("Equip afegit a la lliga: " + nomEquip);
                } else {
                    System.out.println("L'equip no existeix. Torna a intentar.");
                }
            }
        }

        // Disputar partits
        disputarPartits();
        // Mostrar la clasificación
        mostrarClassificacio();
    }


    public Equip equipMesGolsAFavor() {
        Equip equipMaxGolsAFavor = equipsParticipantes.get(0);
        for (Equip equip : equipsParticipantes) {
            if (equip.getGolsAFavor() > equipMaxGolsAFavor.getGolsAFavor()) {
                equipMaxGolsAFavor = equip;
            }
        }
        return equipMaxGolsAFavor;
    }

    public Equip equipMesGolsEnContra() {
        Equip equipMaxGolsEnContra = equipsParticipantes.get(0);
        for (Equip equip : equipsParticipantes) {
            if (equip.getGolsEnContra() > equipMaxGolsEnContra.getGolsEnContra()) {
                equipMaxGolsEnContra = equip;
            }
        }
        return equipMaxGolsEnContra;
    }

    @Override
    public String toString() {
        return "Lliga: " + nom + ", Equipos: " + equipsParticipantes;
    }
}
