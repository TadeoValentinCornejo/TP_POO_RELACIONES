import java.util.ArrayList;
import java.util.Scanner;

// Clase Nota
class Nota {
    private String materia;
    private double calificacion;

    public Nota(String materia, double calificacion) {
        this.materia = materia;
        this.calificacion = calificacion;
    }

    public double getNotaExamen() {
        return calificacion;
    }

    @Override
    public String toString() {
        return "Cátedra: " + materia + ", Nota: " + calificacion;
    }
}

// Clase Alumno
class Alumno {
    private String nombreAlumno;
    private long idAlumno;
    private ArrayList<Nota> listaNotas;

    public Alumno(String nombreAlumno, long idAlumno) {
        this.nombreAlumno = nombreAlumno;
        this.idAlumno = idAlumno;
        this.listaNotas = new ArrayList<>();
    }

    public void agregarNota(Nota nota) {
        listaNotas.add(nota);
    }

    public double calcularPromedio() {
        if (listaNotas.isEmpty()) return 0;
        double suma = 0;
        for (Nota nota : listaNotas) {
            suma += nota.getNotaExamen();
        }
        return suma / listaNotas.size();
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("Alumno: ").append(nombreAlumno)
                .append(", Legajo: ").append(idAlumno)
                .append(", Promedio: ").append(String.format("%.2f", calcularPromedio()))
                .append("\nNotas:\n");
        for (Nota nota : listaNotas) {
            info.append("  ").append(nota).append("\n");
        }
        return info.toString();
    }
}


public class Ejercicio_POO_Relaciones1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Alumno> alumnos = new ArrayList<>();

        System.out.println("¿Cuántos alumnos desea cargar?");
        int cantidadAlumnos = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.println("Ingrese el nombre completo del alumno:");
            String nombreAlumno = scanner.nextLine();
            System.out.println("Ingrese el idAlumno del alumno:");
            long idAlumno = scanner.nextLong();
            scanner.nextLine();

            Alumno alumno = new Alumno(nombreAlumno, idAlumno);
            System.out.println("¿Cuántas listaNotas desea cargar para este alumno?");
            int cantidadNotas = scanner.nextInt();
            scanner.nextLine();

            while (cantidadNotas <= 0) {
                System.out.println("Debe ingresar al menos 1 nota. Intente nuevamente.");
                cantidadNotas = scanner.nextInt();
                scanner.nextLine();
            }

            for (int j = 0; j < cantidadNotas; j++) {
                System.out.println("Ingrese la cátedra:");
                String materia = scanner.nextLine();
                System.out.println("Ingrese la nota del examen:");
                double calificacion = scanner.nextDouble();
                scanner.nextLine();

                Nota nota = new Nota(materia, calificacion);
                alumno.agregarNota(nota);
            }

            alumnos.add(alumno);
        }

        // Mostrar la información de los alumnos y sus listaNotas
        System.out.println("\nInformación cargada:");
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }

        scanner.close();
    }
}

