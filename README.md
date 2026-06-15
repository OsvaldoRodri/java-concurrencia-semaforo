# Concurrencia con Semáforos en Java 🔒

Simulación de acceso concurrente a una **impresora compartida** usando `Semaphore` de `java.util.concurrent`.

## ¿Qué problema resuelve?

Cuando múltiples hilos intentan usar un recurso al mismo tiempo sin coordinación, ocurre una **race condition**: los mensajes se mezclan, los datos se corrompen, el sistema se comporta de forma impredecible.

Este proyecto demuestra cómo un `Semaphore(1)` actúa como un candado que permite que **solo un hilo a la vez** acceda al recurso crítico.

## Estructura

```
src/
└── main/java/dev/osvaldo/semaforo/
    └── Main.java          # Impresora, UsuarioThread y punto de entrada
```

## Conceptos demostrados

| Concepto | Implementación |
|----------|---------------|
| `Semaphore` | `java.util.concurrent.Semaphore` con permiso = 1 |
| `acquire()` | Bloquea el hilo hasta que el recurso esté libre |
| `release()` | Libera el recurso en bloque `finally` (siempre ejecuta) |
| `Thread` | Cada usuario es un hilo independiente (`extends Thread`) |
| Exclusión mutua | Solo un usuario imprime a la vez, los demás esperan en cola |

## Cómo correrlo

**Requisitos:** Java 17+, Maven

```bash
# Clonar
git clone https://github.com/OsvaldoRodri/java-concurrencia-semaforo.git
cd java-concurrencia-semaforo

# Compilar y ejecutar
mvn compile exec:java -Dexec.mainClass="dev.osvaldo.semaforo.Main"
```

## Salida esperada

```
=== Iniciando 5 usuarios concurrentes ===

Usuario 1 esperando impresora...
Usuario 2 esperando impresora...
Usuario 3 esperando impresora...
Usuario 1 IMPRIMIENDO...
Usuario 4 esperando impresora...
Usuario 5 esperando impresora...
Usuario 1 terminó de imprimir.
Usuario 2 IMPRIMIENDO...
...
```

El orden de espera puede variar (depende del scheduler del OS), pero **nunca** dos usuarios imprimen al mismo tiempo.

## Relación con java.util.concurrent

`Semaphore` es parte del paquete de concurrencia de Java estándar desde Java 5. En aplicaciones reales se usa para:
- Limitar acceso a pools de conexiones de base de datos
- Controlar acceso a APIs con rate limiting
- Gestionar recursos compartidos en sistemas multi-hilo

## Tecnologías

![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![Maven](https://img.shields.io/badge/Maven-3.x-red?logo=apache-maven)

---

*Proyecto de aprendizaje — parte del portafolio de [Osvaldo Rodríguez](https://github.com/OsvaldoRodri)*
