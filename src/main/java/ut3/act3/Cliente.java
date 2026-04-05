package ut3.act3;

// IMPORTS
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        // Creamos estructura try-with-resources para crear el socket, PrintWriter, BufferedReader y Scanner
        try (
                // Creamos el socket apuntando a la dirección de las constantes del servidor
                Socket socket = new Socket(Servidor.DIRECCION, Servidor.PUERTO);

                // Creamos el escritor y Scanner para comunicación mediante el socket
                PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
                Scanner sc = new Scanner(System.in)
        ) {
            // Pedimos usuario mediante Scanner
            System.out.print("Nombre de usuario: ");
            String usuario = sc.nextLine();

            // Lo mandamos al hilo del servidor
            escritor.println(usuario);

            // Si el usuario no es nulo, entonces seguimos con el programa
            if (!usuario.isEmpty()) {
                // Creamos un bucle infinito, mientras no salgamos, permite crear logs infinitamente
                while (true) {
                    // Pedimos el nivel del log. Solo se admite INFO, WARN Y ERROR, el resto hará que se rompa el bucle
                    System.out.print("\n\t.:LOG:.\n\nNIVEL (INFO | WARN | ERROR | SALIR): ");
                    String nivel = sc.nextLine();

                    // Sí es uno de los niveles permitidos, lo envíamos al hilo del servidor
                    if (nivel.equalsIgnoreCase("info") || nivel.equalsIgnoreCase("warn") || nivel.equalsIgnoreCase("error")) {
                        escritor.println(nivel);
                    }
                    // En caso de ser 'SALIR' o cualquier otra cosa, cerramos el cliente
                    else {
                        nivel = "SALIR";
                        escritor.println(nivel);

                        // Salimos del bucle
                        break;
                    }

                    // Pedimos el mensaje del log por Scanner y lo mandamos al servidor con el escritor
                    System.out.print("Mensaje log: ");
                    String mensaje = sc.nextLine();
                    escritor.println(mensaje);
                }
            }
        } catch (IOException e) {
            System.err.println("No se pudo conectar al servidor.");
        }
    }
}