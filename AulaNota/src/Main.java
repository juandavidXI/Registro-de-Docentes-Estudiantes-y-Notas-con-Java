import java.util.ArrayList;
import java.util.Scanner;

// Sistema escolar de consola: AulaNota
public class Main {
    private static final Scanner teclado = new Scanner(System.in);
    private static final ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private static final Docente docente = new Docente("Profe Alex", "profe", "1234");

    public static void main(String[] args) {
        // Datos de ejemplo para probar el programa
        estudiantes.add(new Estudiante("Ana Torres", "ana", "1111"));
        estudiantes.add(new Estudiante("Luis Pérez", "luis", "2222"));
        estudiantes.get(0).agregarNota(4.5);
        estudiantes.get(0).agregarNota(3.8);

        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║       ¡Bienvenido a AulaNota!    ║");
        System.out.println("║ Tu colegio, tus logros,tus notas ║");
        System.out.println("╚══════════════════════════════════╝");

        int opcion;
        do {
            System.out.println("Bienvenido al Sistema de notas de la UCO");
            System.out.println("\n¿Quién entra al aula?");
            System.out.println("1. Estudiante");
            System.out.println("2. Docente");
            System.out.println("0. Salir");
            opcion = leerEntero("Elige una opción: ");

            switch (opcion) {
                case 1 -> ingresarEstudiante();
                case 2 -> ingresarDocente();
                case 0 -> System.out.println("¡Hasta pronto! Sigue aprendiendo.");
                default -> System.out.println("Esa opción no existe. ¡Intenta otra vez!");
            }
        } while (opcion != 0);
    }

    private static void ingresarEstudiante() {
        System.out.print("Usuario: ");
        String usuario = teclado.nextLine();
        System.out.print("Contraseña: ");
        String clave = teclado.nextLine();

        for (Estudiante e : estudiantes) {
            if (e.validarAcceso(usuario, clave)) {
                System.out.println("\n¡Hola, " + e.getNombre() + "! Bienvenido a tu rincón académico.");
                e.mostrarNotas();
                return;
            }
        }
        System.out.println("No encontramos ese usuario. Revisa tus datos.");
    }

    private static void ingresarDocente() {
        System.out.print("Usuario docente: ");
        String usuario = teclado.nextLine();
        System.out.print("Contraseña: ");
        String clave = teclado.nextLine();

        if (!docente.validarAcceso(usuario, clave)) {
            System.out.println("Acceso denegado. ¡Solo el equipo docente puede entrar aquí!");
            return;
        }

        int opcion;
        do {
            System.out.println("\n--- Panel docente: " + docente.getNombre() + " ---");
            System.out.println("1. Ver estudiantes");
            System.out.println("2. Registrar estudiante");
            System.out.println("3. Ingresar nota");
            System.out.println("4. Modificar nota");
            System.out.println("0. Cerrar sesión");
            opcion = leerEntero("¿Qué haremos hoy?: ");

            switch (opcion) {
                case 1 -> listarEstudiantes();
                case 2 -> registrarEstudiante();
                case 3 -> gestionarNota(false);
                case 4 -> gestionarNota(true);
                case 0 -> System.out.println("Sesión docente cerrada. ¡Gracias por enseñar!");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void listarEstudiantes() {
        if (estudiantes.isEmpty()) {
            System.out.println("Aún no hay estudiantes registrados.");
            return;
        }
        System.out.println("\nLista del curso:");
        for (int i = 0; i < estudiantes.size(); i++) {
            System.out.println((i + 1) + ". " + estudiantes.get(i).getNombre());
        }
    }

    private static void registrarEstudiante() {
        System.out.print("Nombre completo: ");
        String nombre = teclado.nextLine().trim();
        System.out.print("Usuario nuevo: ");
        String usuario = teclado.nextLine().trim();
        System.out.print("Contraseña: ");
        String clave = teclado.nextLine();

        if (nombre.isEmpty() || usuario.isEmpty() || clave.isEmpty()) {
            System.out.println("Todos los campos son necesarios.");
            return;
        }
        for (Estudiante e : estudiantes) {
            if (e.getUsuario().equalsIgnoreCase(usuario)) {
                System.out.println("Ese usuario ya existe. Prueba con otro.");
                return;
            }
        }
        estudiantes.add(new Estudiante(nombre, usuario, clave));
        System.out.println("¡Estudiante registrado! Una nueva historia comienza.");
    }

    private static void gestionarNota(boolean modificar) {
        listarEstudiantes();
        if (estudiantes.isEmpty()) return;

        int indice = leerEntero("Número del estudiante: ") - 1;
        if (indice < 0 || indice >= estudiantes.size()) {
            System.out.println("Número de estudiante incorrecto.");
            return;
        }

        Estudiante e = estudiantes.get(indice);
        if (modificar && e.getNotas().isEmpty()) {
            System.out.println("Este estudiante todavía no tiene notas.");
            return;
        }

        if (modificar) {
            e.mostrarNotas();
            int numero = leerEntero("Número de la nota que deseas modificar: ") - 1;
            if (numero < 0 || numero >= e.getNotas().size()) {
                System.out.println("Ese número de nota no existe.");
                return;
            }
            double nota = leerNota();
            e.modificarNota(numero, nota);
            System.out.println("Nota actualizada. ¡Cada avance cuenta!");
        } else {
            double nota = leerNota();
            e.agregarNota(nota);
            System.out.println("Nota guardada para " + e.getNombre() + ". ¡Buen trabajo!");
        }
    }

    private static double leerNota() {
        double nota;
        do {
            System.out.print("Ingresa la nota (0.0 a 5.0): ");
            while (!teclado.hasNextDouble()) {
                System.out.print("Escribe un número válido: ");
                teclado.next();
            }
            nota = teclado.nextDouble();
            teclado.nextLine();
            if (nota < 0 || nota > 5) System.out.println("La nota debe estar entre 0 y 5.");
        } while (nota < 0 || nota > 5);
        return nota;
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!teclado.hasNextInt()) {
            System.out.print("Escribe un número válido: ");
            teclado.next();
        }
        int valor = teclado.nextInt();
        teclado.nextLine();
        return valor;
    }
}

// Herencia: Estudiante y Docente son tipos de Usuario.
abstract class Usuario {
    private final String nombre;
    private final String usuario;
    private final String clave;

    public Usuario(String nombre, String usuario, String clave) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getNombre() { return nombre; }
    public String getUsuario() { return usuario; }

    public boolean validarAcceso(String usuario, String clave) {
        return this.usuario.equals(usuario) && this.clave.equals(clave);
    }
}

class Estudiante extends Usuario {
    private final ArrayList<Double> notas = new ArrayList<>();

    public Estudiante(String nombre, String usuario, String clave) {
        super(nombre, usuario, clave);
    }

    public ArrayList<Double> getNotas() { return notas; }

    public void agregarNota(double nota) {
        notas.add(nota);
    }

    public void modificarNota(int indice, double nota) {
        notas.set(indice, nota);
    }

    public double calcularPromedio() {
        if (notas.isEmpty()) return 0;
        double suma = 0;
        for (double nota : notas) suma += nota;
        return suma / notas.size();
    }

    public void mostrarNotas() {
        System.out.println("\nTus resultados:");
        if (notas.isEmpty()) {
            System.out.println("Todavía no tienes notas. ¡Pronto llegarán tus primeros logros!");
        } else {
            for (int i = 0; i < notas.size(); i++) {
                System.out.printf("%d. Nota: %.1f%n", i + 1, notas.get(i));
            }
            System.out.printf("Promedio: %.2f%n", calcularPromedio());
        }
    }
}

class Docente extends Usuario {
    public Docente(String nombre, String usuario, String clave) {
        super(nombre, usuario, clave);
    }
}
