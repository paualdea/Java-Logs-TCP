package ut3.act3;

// IMPORTS
import java.io.*;
import java.net.Socket;

public class hiloServidor implements Runnable {
    Socket socket;
    // Creamos un objeto de la clase que gestiona el fichero logs.txt
    Logs logs = new Logs();

    // Constructor
    public hiloServidor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // Implementación de estructura try-with-resources
        try (
                // Creamos el lector del socket
                BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            //  Leemos el nombre de usuario que manda el cliente
            String usuario = lector.readLine();

            // Si el usuario no está vacío, seguir con el programa
            if (!usuario.isEmpty()) {
                // Notificamos el acceso del nuevo cliente
                System.out.println("\n\t[CONEXIÓN] " + usuario + " se ha unido.\n");

                // Implementamos un while que pida logs indefinidamente, hasta que reciba 'SALIR'
                String nivel = "";
                while (true){
                    // Leemos el nivel de log que nos pasa el cliente
                    nivel = lector.readLine();

                    // Si recibimos SALIR o un valor nulo, paramos el bucle del cliente
                    if (nivel.equals("SALIR") || nivel.isEmpty()) {
                        break;
                    }

                    // Leemos el mensaje de log
                    String mensaje = lector.readLine();

                    // Creamos un nuevo log, pasando el usuario, el nivel y mensaje filtrados
                    logs.registrar(usuario, nivel, mensaje);
                }

                // En caso de que el cliente indique SALIR, indicamos en la consola que el usuario ha salido y se cierra la ventana
                System.out.println("\n\t[DESCONEXIÓN] " + usuario + " ha abandonado el servidor.\n");
            }
        } catch (IOException e) {
            System.err.println("\nError en la comunicación");
        }
    }
}