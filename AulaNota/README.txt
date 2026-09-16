AulaNota — Proyecto Java para IntelliJ IDEA

Requisitos:
- JDK 17 o superior (usa switch moderno).
- IntelliJ IDEA.

Cómo abrir:
1. Descomprime AulaNota_IntelliJ.zip.
2. En IntelliJ IDEA, selecciona File > Open y abre la carpeta AulaNota.
3. Configura un JDK si IntelliJ lo solicita.
4. Abre src/Main.java y pulsa el triángulo verde junto a main.

Accesos de demostración:
- Docente: usuario `profe`, contraseña `1234`
- Estudiante Ana: usuario `ana`, contraseña `1111`
- Estudiante Luis: usuario `luis`, contraseña `2222`

Los datos se guardan en memoria mientras el programa está abierto. Al cerrar, se reinician.
Este proyecto es educativo: las contraseñas son de demostración y no se guardan de forma segura.

POO utilizada:
- Encapsulamiento: atributos privados en Usuario.
- Herencia: Estudiante y Docente heredan de Usuario.
- Abstracción: Usuario es una clase abstracta.
- Objetos y métodos: cada estudiante administra sus propias notas.
