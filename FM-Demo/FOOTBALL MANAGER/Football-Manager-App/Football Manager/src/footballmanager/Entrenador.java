package footballmanager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Entrenador extends Persona {

    //Només entrenador:

    protected int tornejos_guanyats;
    protected boolean seleccionador;
    protected LocalDate fecha;

    // Constructor


    public Entrenador(String nom, String cognom, LocalDate fecha, int motivacio, double sou_anual, int tornejos_guanyats) {
        super(nom, cognom, fecha, motivacio, sou_anual);
        this.tornejos_guanyats = tornejos_guanyats;
    }

    public Entrenador(String nom, String cognom, LocalDate fecha, int motivacio, double sou_anual, int tornejos_guanyats, boolean seleccionador) {
        super(nom, cognom, fecha, motivacio, sou_anual);
        this.tornejos_guanyats = tornejos_guanyats;
        this.seleccionador = seleccionador;
        this.fecha = fecha;
    }


    public static void dadesEntrenador(List<Persona> personas) {
        Scanner scanner = new Scanner(System.in);
        String nom = null;
        String cognom = null;
        int motivacio = 5;
        double sou_anual = 0;
        int dorsal = 0;
        String posicio = null;
        LocalDate fecha = null;
        int qualitat = 0;
        int tornejos = 0;
        boolean seleccionador2 = false;
        String seleccionador3 = null;
        boolean continuar = false;
        boolean continuar2 = false;
        boolean fechaCorrecta = false;
        do {
            System.out.println("Nom: ");
            nom = scanner.nextLine();
            System.out.println("Cognom: ");
            cognom = scanner.nextLine();

            do {
                // Fecha
                System.out.println("Data de Naixement (DD/MM/YYYY): ");
                String fechaStr = scanner.nextLine();
                try {
                    fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    continuar2 = true;
                    LocalDate hoy = LocalDate.now();
                    if (fecha.isAfter(hoy)){
                        System.out.println("Error. NO ESTAMOS EN EL FUTURO...");
                    } else {
                        fechaCorrecta = true;
                    }
                } catch (Exception e) {
                    System.out.println("Fecha invalida, intenta de nuevo.");
                }
            } while (!fechaCorrecta);
            do {

                // Motivacio
                System.out.println("Nivell de motivació (1-10): ");
                motivacio = scanner.nextInt();
                if (motivacio < 1 || motivacio > 10) {
                    System.out.println("Motivacio no valida. Torna a intentar");
                } else {
                    continuar2 = true;
                }

                // Seleccionador
                System.out.println("Es seleccionador? (si o no): ");
                seleccionador3 = scanner.nextLine().toLowerCase();
                if (seleccionador3.equals("si")) {
                    continuar = true;
                } else if (seleccionador3.equals("no")) {
                    continuar = true;
                } else {
                    continuar = false;
                }

                // Tornejos
                System.out.println("Tornejos Guanyats: ");
                tornejos = scanner.nextInt();
                if (tornejos < 0) {
                    System.out.println("Numero incorrecto, no puede ser negativo.");
                    continuar2 = false;
                }
            } while (!continuar2);
            // Sou Anual

            System.out.println("Sou Anual: ");
            sou_anual = scanner.nextDouble();

            Entrenador entrenador = new Entrenador( nom,  cognom,  fecha,  motivacio,  sou_anual, tornejos, seleccionador2);
            personas.add(entrenador);
            System.out.println("ENTRENADOR GUARDADO!");

        } while (!continuar);

    }

    public static void eliminarEntrenador(List<Persona> personas) {
        Scanner sc = new Scanner(System.in);
        boolean eliminado = false;
        System.out.println("Posa el nom del entrenador que vols eliminar: ");
        String nombreEntrenador = sc.nextLine();
        for (int i = 0; i < personas.size(); i++) {
            Persona persona = personas.get(i);

            if (persona instanceof Entrenador && persona.getNom().equals(nombreEntrenador)) {
                personas.remove(i);
                eliminado = true;
                System.out.println("Entrenador: " + nombreEntrenador + " eliminat correctament");
                break;
            }
        }
        if (!eliminado) {
            System.out.println("No s'ha trobat un entrenador amb aquest nom: " + nombreEntrenador);

        }
        System.out.println("\nLlista de entrenadors actual: ");
        mostrarEntrenador(personas, nombreEntrenador);
    }

    private static void mostrarEntrenador(List<Persona> personas, String nombreEntrenador) {
        for (Persona persona : personas) {
            if (persona instanceof Entrenador) {
                System.out.println(persona);
            }
        }
    }

    public void entrenament(){
        this.motivacio += 1;
        if (this.motivacio > 10) this.motivacio = 10;
    }

    public void incrementarSou(){
        this.sou_anual *= 1.05;
    }

    public static void fitxarEntrenador(String nomEntrenador, List<Persona> personas, Equip equip) {
        for (int i = 0; i < personas.size(); i++) {
            Persona persona = personas.get(i);
            if (persona instanceof Entrenador && persona.getNom().equalsIgnoreCase(nomEntrenador)) {
                equip.setEntrenador((Entrenador) persona); // Asignar al equipo
                personas.remove(i); // Eliminar del mercado
                System.out.println("Entrenador " + nomEntrenador + " fitxat amb èxit!");
                return;
            }
        }
        System.out.println("No s'ha trobat el entrenador amb nom: " + nomEntrenador);
    }

    // Getters y Setters


    public int getTornejos_guanyats() {
        return tornejos_guanyats;
    }

    public void setTornejos_guanyats(int tornejos_guanyats) {
        this.tornejos_guanyats = tornejos_guanyats;
    }

    public boolean isSeleccionador() {
        return seleccionador;
    }

    public void setSeleccionador(boolean seleccionador) {
        this.seleccionador = seleccionador;
    }
}
