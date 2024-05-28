/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 * Clase Materia con sus respectivos atributos.
 * @author Rojas Nava Silvia Jamile
 */
public class Materia {
    private int semestre;
    private String nombreMateria;
    private int codigoMateria;
    private int creditos;
    private int calificacion;

/**
 * Constructir vacío, inicializa todos los atributos en null para String.
 */
    public Materia() {
    }

/**
 * Constructor lleno. 
 * @param semestre semestre que puede cursar el alumno.
 * @param nombreMateria materia que puede cursar el alumno.
 * @param codigoMateria código de la materia que puede cursar el alumno.
 * @param creditos creditos que puede tener cada materia que cursará el alumno.
 * @param calificacion calificación que puede tener el alumno al cursar sus materias.
 */
    public Materia(int semestre, String nombreMateria, int codigoMateria, int creditos, int calificacion) {
        this.semestre = semestre;
        this.nombreMateria = nombreMateria;
        this.codigoMateria = codigoMateria;
        this.creditos = creditos;
        this.calificacion = calificacion;
    }
    
/**
 * Métodos de servicio. Se regresa un nombre de una materia.
 * @return regresa una variable nombre de una materia.
 */
    public String getNombreMateria() {
        return nombreMateria;
    }

/**
 * Se accede al valor del nombre de la materia y se modifica asignando un nombre al objeto.
 * @param nombreMateria asigna el nuevo valor al atributo nombre de materia del objeto.
 */
    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

/**
 * Se regresa un código de una materia.
 * @return regresa una variable codigo de de una materia.
 */
    public int getCodigoMateria() {
        return codigoMateria;
    }

/**
 * Se accede al valor del codigo de la materia y se modifica asignando un codigo de una materia al objeto.
 * @param codigoMateria asigna el nuevo valor al atributo codigo de materia del objeto.
 */
    public void setCodigoMateria(int codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

/**
 * Se regresa un semestre
 * @return regresa una variable semestre.
 */
    public int getSemestre() {
        return semestre;
    }

/**
 * Se accede al valor del semestre y se modifica asignando un semestre al objeto.
 * @param semestre asigna el nuevo valor al atributo semestre del objeto.
 */
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    
/**
 * Se regresa creditos.
 * @return regresa una variable creditos.
 */
    public int getCreditos() {
        return creditos;
    }

/**
 * Se accede al valor de los creditos y se modifica asignando creditos al objetos.
 * @param creditos asigna el nuevo valor al atributo creditos del objeto.
 */
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

/**
 * Se regresa una calificación.
 * @return regresa una variable calificacion.
 */
    public int getCalificacion() {
        return calificacion;
    }

/**
 * Se accede al valor de la calificacion y se modifica asignando una calificacion al objetos.
 * @param calificacion asigna el nuevo valor al atributo calificacion del objeto.
 */
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
    
}