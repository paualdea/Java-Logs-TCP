package ut3.act3;

// IMPORTS
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que interactua con el fichero 'logs.txt' para incluir el log recibido por el cliente formateado correctamente
 */
public class Logs {
    String ficheroLog = "logs.txt";

    /**
     * Función que permitira escribir en el fichero de logs.
     * Está sincronizada para garantizar que no se acceda simultaneamente al archivo mediante dos clientes y lo corrompa.

     * @param cliente
     * Recibe cómo parametro el nombre del cliente
     * @param nivel
     * Recibe el nivel del log
     * @param mensaje
     * Recibe el String del mensaje del log
     */
    public synchronized void registrar(String cliente, String nivel, String mensaje) {
        // Creamos una variable que almacene la hora con el día y la hora exacta
        String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        // Formateamos la línea del log entera
        String lineaLog = "[" + fechaHora + "] (" + cliente + ") " + nivel.toUpperCase() + " " + mensaje;

        // Estructura try-with-resources para escribir sobre el fichero de logs
        try (
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(ficheroLog, true)))
        ) {
            // Imprimimos la línea del log en el documento
            pw.println(lineaLog);

            System.out.println("Log: " + lineaLog);
        } catch (IOException e) {
            System.err.println("Error al acceder al log");
        }
    }
}