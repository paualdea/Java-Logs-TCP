package ut3.act3;

// IMPORTS
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    // Creamos constantes para el puerto del socket TCP
    final static int PUERTO = 8080;
    final static String DIRECCION = "localhost";

    public static void main(String[] args) {
        // Creamos un ServerSocket usando una estructura try-with-resources
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("\n\t.: SERVIDOR FUNCIONANDO EN EL PUERTO " + PUERTO + ":.");

            // Creamos un bucle infinito que espera conexiones de clientes y al recibirlas, crea un nuevo hilo para manejar el cliente
            while (true) {
                // Creamos un socket que espera la conexión desde un cliente
                Socket socket = servidor.accept();

                // Creamos un hilo a partir de la clase hiloServidor y lo iniciamos directamente
                new Thread(new hiloServidor(socket)).start();
            }
        }
        catch (IOException e) {
            System.err.println("\nServidor ya en ejecución");
        }
    }
}
