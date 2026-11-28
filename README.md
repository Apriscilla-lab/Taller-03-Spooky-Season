# Taller 03 - Spooky Season

## Descripción

Este proyecto corresponde al Taller 03 de Programación Orientada a Objetos, desarrollado en Java.

El programa consiste en un juego donde los jugadores pueden utilizar distintos monstruos para participar en cuatro minijuegos, conseguir puntos y superar sus récords.

Se utilizan conceptos de POO como herencia, polimorfismo, interfaces y colecciones.

## Integrantes

- Nombre completo: Priscilla Valentina Álvarez Ugarte
- RUT: 20.988.062-8
- Carrera: Ingeniería en tecnologías de información
- GitHub: Apriscilla-lab

## Estructura del proyecto

El proyecto está organizado en el paquete `taller03`.

Sus clases principales son:

- `Monstruo` y sus clases hijas: `Zombi`, `Vampiro`, `Momia` y `HombreLobo`.
- Interfaces de habilidades: `Mordedor`, `Volador`, `Regenerable` y `Furioso`.
- `Minijuego` y sus cuatro implementaciones.
- `Jugador`, `ResultadoMinijuego`, `Sistema` y `Main`.

## Cómo ejecutar el programa

1. Clonar o descargar este repositorio.
2. Abrir Eclipse e importar el proyecto mediante **File → Import → Existing Projects into Workspace**.
3. Utilizar Java 21.
4. Verificar que `Monstruos.txt` y `Registros.txt` estén en la carpeta principal del proyecto.
5. Ejecutar `Main.java` mediante **Run As → Java Application**.

## Funcionamiento

El programa permite iniciar una sesión, elegir monstruos y minijuegos, utilizar habilidades especiales, consultar el historial de partidas y revisar el ranking de jugadores.

Los récords se guardan en el archivo `Registros.txt`.

## Diagrama de clases

El repositorio incluye el diagrama UML del proyecto en el archivo `DiagramaClases.drawio.png`.
