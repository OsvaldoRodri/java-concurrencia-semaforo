# java-concurrencia-semaforo

Simulación de acceso concurrente a una impresora compartida usando `Semaphore` de `java.util.concurrent`.

## El problema

Cuando varios hilos usan un recurso compartido sin coordinación, los mensajes se mezclan y el estado se corrompe.

## La solución

`Semaphore(1)` permite que solo un hilo a la vez acceda al recurso. `acquire()` bloquea si está ocupado; `release()` en el bloque `finally` garantiza que siempre se libere.

```java
semaforo.acquire();
try {
    // sección crítica
} finally {
    semaforo.release();
}
```

## Estructura

```
src/main/java/dev/osvaldo/semaforo/Main.java
    Impresora       recurso compartido con Semaphore(1)
    UsuarioThread   hilo que solicita acceso
    Main            crea 5 usuarios concurrentes
```

## Cómo correr

```bash
git clone https://github.com/OsvaldoRodri/java-concurrencia-semaforo.git
cd java-concurrencia-semaforo
mvn compile exec:java -Dexec.mainClass="dev.osvaldo.semaforo.Main"
```
