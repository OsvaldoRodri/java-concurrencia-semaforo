package dev.osvaldo.semaforo;

import java.util.concurrent.Semaphore;

/**
 * Impresora compartida: recurso al que solo un hilo puede acceder a la vez.
 * Usa Semaphore(1) de java.util.concurrent para garantizar exclusión mutua.
 */
class Impresora {

    private final Semaphore semaforo = new Semaphore(1);

    public void imprimir(String usuario) {
        try {
            System.out.println(usuario + " esperando impresora...");
            semaforo.acquire();   // bloquea si ya hay alguien imprimiendo
            System.out.println(usuario + " IMPRIMIENDO...");
            Thread.sleep(2000);   // simula tiempo de impresión
            System.out.println(usuario + " terminó de imprimir.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(usuario + " fue interrumpido.");
        } finally {
            semaforo.release();   // siempre libera, aunque haya excepción
        }
    }
}

/**
 * Cada instancia representa un usuario que quiere imprimir.
 * Extiende Thread para correr de forma concurrente.
 */
class UsuarioThread extends Thread {

    private final Impresora impresora;

    public UsuarioThread(Impresora impresora, String nombre) {
        super(nombre);
        this.impresora = impresora;
    }

    @Override
    public void run() {
        impresora.imprimir(getName());
    }
}

/**
 * Punto de entrada: crea 5 usuarios que intentan imprimir al mismo tiempo.
 * Sin el semáforo, los mensajes se mezclarían (race condition).
 * Con el semáforo, cada usuario espera su turno.
 */
public class Main {

    public static void main(String[] args) {
        Impresora impresora = new Impresora();

        System.out.println("=== Iniciando 5 usuarios concurrentes ===\n");

        for (int i = 1; i <= 5; i++) {
            new UsuarioThread(impresora, "Usuario " + i).start();
        }
    }
}
