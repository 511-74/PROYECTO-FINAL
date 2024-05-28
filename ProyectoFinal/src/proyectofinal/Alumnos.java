/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;
/**
 *
 * @author Rojas Nava Silvia Jamile
 */
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Alumnos {
    private String nombre;
    private long numeroCuenta;
    private float promedio;
    private String direccion;
    private int semestre;
    private int edad;
    private List<Materia> materias;
    private double numInscripcion;

    // Constructor
    public Alumnos() {
    }

    public Alumnos(String nombre, long numeroCuenta, float promedio, String direccion, int semestre, int edad, List<Materia> materias, double numInscripcion) {
        this.nombre = nombre;
        this.numeroCuenta = numeroCuenta;
        this.promedio = promedio;
        this.direccion = direccion;
        this.semestre = semestre;
        this.edad = edad;
        this.materias = materias;
        this.numInscripcion = numInscripcion;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public float getPromedio() {
        return Math.round(promedio * 100.0f) / 100.0f;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public double getNumInscripcion() {
        return numInscripcion;
    }

    public void setNumInscripcion(double numInscripcion) {
        this.numInscripcion = numInscripcion;
    }

    @Override
    public String toString() {
        return "Alumnos{" + "nombre=" + nombre + ", numeroCuenta=" + numeroCuenta + ", promedio=" + promedio + ", direccion=" + direccion + ", semestre=" + semestre + ", edad=" + edad + ", materias=" + materias + '}';
    }

    public double calcularNumeroInscripcion() {
        int asignaturasAprobadas = 0;
        int creditosAprobados = 0;
        int creditosCursados = 0;
        double velocidad = 0;

        for (Materia materia : materias) {
            if (materia.getCalificacion() > 5) {
                asignaturasAprobadas++;
                creditosAprobados += materia.getCreditos();
            }
            creditosCursados += materia.getCreditos();
        }

        double escolaridad = (double) asignaturasAprobadas / materias.size() * 100;
        if (creditosCursados > 0) {
            velocidad = (double) creditosAprobados / creditosCursados * 100;
        }

        return Math.round((promedio * escolaridad * velocidad) * 100.0) / 100.0;
    }

    public void ordenarPorNumeroInscripcion(List<Alumnos> listaAlumnos) {
        Collections.sort(listaAlumnos, new Comparator<Alumnos>() {
            @Override
            public int compare(Alumnos a1, Alumnos a2) {
                return Double.compare(a1.getNumInscripcion(), a2.getNumInscripcion());
            }
        });
    }
}
