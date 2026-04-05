package ut3.act3;

// IMPORTS
import java.io.IOException;
import java.util.Scanner;

/**
 * Esta clase Main actúa como un launcher para lanzar el Servidor y varios Clientes.

 * Usa ProcessBuilder para abrir estos procesos en hilos separados y en una nueva terminal.
 */
public class Main {
    // Constante de tiempo espera
    final static int TIEMPO_ESPERA = 1250;
    // Constantes nombres clases
    final static String servidor = "Servidor.java", cliente = "Cliente.java";
    // Num. de servidores máximos
    static int numeroServidores = 1;

    public static void main(String[] args) throws IOException {
        String opcion = "";
        Scanner sc = new Scanner(System.in);

        // Mientras la opción no sea 3
        while (!opcion.equals("3")) {
            // Limpiamos la pantalla
            limpiarPantalla();

            // Mostramos el menú de selección al usuario
            System.out.print("\t\t\n.:SISTEMA DE LOGS EN RED:.\n\n1. Iniciar servidor (max. 1)\n2. Iniciar cliente\n3. Salir\n\nOpción: ");
            opcion = sc.nextLine();

            // Estructura de selección
            if (opcion.equals("1")) {
                // Lanzamos el proceso servidor (max. 1)
                if (numeroServidores == 1) {
                    lanzarProceso(servidor);
                    numeroServidores--;

                    System.out.println("Lanzando servidor...");
                } else {
                    System.out.println("\nSólo se puede crear un servidor.");
                }

                espera(0);
            } else if (opcion.equals("2")) {
                // Lanzamos un proceso de cliente
                lanzarProceso(cliente);

                System.out.println("Lanzando cliente...");
                espera(0);
            } else if (opcion.equals("3")) {
                System.out.println("\nCerrando programa...");
                espera(0);
            } else {
                System.out.println("\nOpción incorrecta");
                espera(0);
            }
        }
    }

    /**
     * Esta función lanza un proceso con ProcessBuilder

     * @param clase
     * Recibe como parámetro la clase a lanzar (servidor o cliente)
     */
    public static void lanzarProceso (String clase) throws IOException {
        // Obtenemos la ruta de Java, el programa y el sistema operativo actual
        String java = System.getProperty("java.home") + "/bin/java";
        String ruta = System.getProperty("java.class.path");
        String os = System.getProperty("os.name").toLowerCase();

        // Si se esta usando Windows
        if (os.contains("win")) {
            // Creamos un proceso con ProcessBuilder que lanza con el cmd una nueva ventana de la clase
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start", "Servicio de Logs", java, "-cp", ruta, clase);
            // Iniciamos el ProcessBuilder
            pb.start();
        } else {
            // Creamos un ProcessBuilder que lanza en sistemas Linux y macOS una nueva ventana apuntando a la clase Java
            ProcessBuilder pb = new ProcessBuilder("x-terminal-emulator", "-fa", "Monospace", "-fs", "12","-e", java + " -cp " + ruta + " " + clase);
            pb.start();
        }
    }

    /**
     * Esta función limpia la pantalla dependiendo del sistema operativo que tengas
     */
    public static void limpiarPantalla() {
        try {
            // Obtenemos el sistema operativo desde el que se ejecuta el programa
            String so = System.getProperty("os.name").toLowerCase();

            // Si es windows lanzamos el comando cls para borrar la pantalla
            if (so.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            // Si es Linux o Mac, lanzamos una secuencia de caracteres ANSI que limpia y borra la pantalla
            else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.err.println("Error al limpiar la pantalla.\n" + e.getMessage());
        }
    }

    /**
     * Esta función ejecuta un bloque de código que para la ejecución de espera TIEMPO_ESPERA segundos
     */
    public static void espera(int tiempo) {
        if (tiempo > 0) {
            try {
                Thread.sleep(tiempo);

            } catch (InterruptedException e) {
                System.err.println("No se ha podido hacer la pausa de " + tiempo);
            }
        } else {
            try {
                Thread.sleep(TIEMPO_ESPERA);

            } catch (InterruptedException e) {
                System.err.println("No se ha podido hacer la pausa de " + TIEMPO_ESPERA);
            }
        }
    }
}