# Programación en Java: Servidor de Logs Remotos y Estructura Multihilo

Este proyecto ha sido desarrollado como parte de la **Actividad 3** de la Unidad de Trabajo 3 (UT3): "Programación de comunicaciones en red".

El programa implementa un sistema **cliente-servidor** utilizando sockets TCP para gestionar un servicio de logs remoto. Varios clientes pueden conectarse simultáneamente para enviar mensajes de registro (INFO, WARN, ERROR) que el servidor almacena de forma persistente y ordenada en el fichero `logs.txt`.

## Características Principales

* **Modelo Cliente-Servidor Multihilo**: El servidor es capaz de atender a múltiples clientes de forma simultánea gracias a la implementación de hilos en la clase `hiloServidor.java`.
* **Sockets TCP**: Uso de las clases `Socket` y `ServerSocket` del paquete `java.net` para garantizar una conexión e intercambio de información correctas.
* **Programación Multihilo**: Implementación de la interfaz `Runnable` en la clase `hiloServidor` para gestionar cada conexión de cliente en un hilo independiente, evitando bloqueos en el hilo principal.
* **Persistencia de Datos (protegida)**: Uso de la clase `Logs` co el método `synchronized` para asegurar que la escritura en el fichero `logs.txt` sea segura entre hilos y no corrompa los datos.
* **Lanzador Automatizado**: Inclusión de un menú en la clase `Main` que utiliza `ProcessBuilder` para ejecutar el servidor y múltiples instancias del cliente en terminales independientes de forma automática, adaptandose a cada sistema operativo (Windows, Linux, macOS).

## Funcionamiento

El programa sigue el siguiente flujo de ejecución:

1. **Inicio**: La clase `Main` actúa como lanzador, permitiendo abrir el servidor (puerto `8080`) y tantos clientes como se requiera.
2. **Identificación**: Al conectarse, el cliente envía un nombre de usuario que el servidor utiliza para identificar la procedencia de los logs.
3. **Protocolo de Comunicación**: 
    * El cliente solicita el nivel del log (`INFO`, `WARN`, `ERROR`) y el mensaje a través de la consola.
    * El servidor recibe estos datos, los valida y les añade una marca de tiempo.
    * La información se guarda en `logs.txt` a través de la clase `Logs.java`.
4. **Finalización**: Si el cliente envía la palabra `"SALIR"`, el hilo correspondiente cierra la conexión de forma limpia y el servidor notifica la desconexión.

## Instrucciones de Uso

Para ejecutar este programa, se puede usar el fichero `.jar` o compilar el *código fuente* que se pueden descargar desde las [releases](https://git.paualdea.com/paualdea/Java-Logs-TCP/-/releases) del proyecto.

---
Este proyecto sirve como evidencia del aprendizaje sobre el desarrollo de aplicaciones distribuidas, gestión de concurrencia y comunicaciones seguras en red para la asignatura de **Programación de Servicios y Procesos**.