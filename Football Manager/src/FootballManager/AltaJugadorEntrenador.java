package FootballManager;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AltaJugadorEntrenador {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean opcioValida = false;
        while (!opcioValida) {
            System.out.println("Vols donar d'alta un jugador o un entrenador?: ");
            String opcio = sc.nextLine();
            switch (opcio) {
                case "jugador":
                    System.out.println("Nom del Jugador: ");
                    String nombre = sc.nextLine();
                    System.out.println("Cognom del Jugador: ");
                    String apellido = sc.nextLine();
                    System.out.println("Data Naixement: ");
                    int dataNaixement = sc.nextInt();
                    System.out.println("Dorsal del Jugador: ");
                    int dorsal = sc.nextInt();
                    System.out.println("Posicio del jugador: ");
                    String posicio = sc.nextLine();
                    int qualitat = new Random().nextInt(101);
                    int motivacio = 5;
                    System.out.println("Dades del Jugador: \n" +
                            "Nom: " + nombre + "\n" +
                            "Cognom: " + apellido + "\n" +
                            "Data Naixement: " + dataNaixement  + "\n" +
                            "Dorsal: " + dorsal + "\n" +
                            "Posicio: " + posicio + "\n" +
                            "Qualitat: " + qualitat + "\n" +
                            "Motivacio: " + motivacio);
                    break;
                case "entrenador":
                    System.out.println("Nom del entrenador: ");
                    String nom = sc.nextLine();
                    System.out.println("Cognom del entrenador: ");
                    String cognom = sc.nextLine();
                    System.out.println("Salari del entrenador: ");
                    double salario = sc.nextDouble();
                    System.out.println("Dades del Entrenador: \n" + "Nom: " + nom + "\n" + "Cognom: " + cognom + "\n" + "Salari: " + salario);
                    break;
                default:
                    System.out.println("Opcio no valida");
            }
        }
    }
}