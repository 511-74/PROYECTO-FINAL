/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 * Clase Usuario con sus respectivos atributos.
 * @author Rojas Nava Silvia Jamile
 */
public class Usuario{
    private String nombre;
    private String apellido;
    private int contraseña;

/**
 * Constructir vacío, inicializa todos los atributos en null para String.
 */
    public Usuario() {
    }
    
/**
 * Constructor lleno.
 * @param nombre nombre del usuario de servicios académicos para ingresar al programa.
 * @param apellido apellido del usuario de servicios académicos para ingresar al programa.
 * @param contraseña contraseña del usuario de servicios académicos para ingresar al programa.
 */
    public Usuario(String nombre, String apellido, int contraseña) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña=contraseña;
    }

/**
 * Constructor de la clase Usuario que recibe una línea de texto con los datos del usuario.
 * @param linea es una cadena de texto que contiene los datos del usuario separados por comas. 
 *              El formato esperado es "nombre,apellido,contraseña", donde la contraseña 
 *              es un número entero.
 */
    public Usuario(String linea) {
        String[] requisito = linea.split(",");
        this.nombre = requisito[0];
        this.apellido = requisito[1];
        this.contraseña = Integer.parseInt(requisito[2]);
    }

/**
 * Devuelve una representación del objeto Usuario en formato de archivo.
 * @return retorna una cadena de texto que contiene el nombre, apellido y contraseña del usuario
 *         separados por comas, en el formato "nombre,apellido,contraseña".
 */
    public String obtenerFormatoArchivo() {
        return nombre + "," + apellido + ","+ contraseña;
    }

/**
 * Métodos de servicio. Se regresa un nombre de un usuario.
 * @return regresa una variable nombre.
 */
    public String getNombre() {
        return nombre;
    }

/**
 * Se accede al valor del nombre y se modifica asignando un nombre al objeto.
 * @param nombre asigna el nuevo valor al atributo nombre del objeto.
 */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
/**
 * Se regresa un apellido de un usuario.
 * @return regresa una variable nombre.
 */
    public String getApellido() {
        return apellido;
    }

/**
 * Se accede al valor del apellido y se modifica asignando un nombre al objeto.
 * @param apellido asigna el nuevo valor al atributo apellido del objeto.
 */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
/**
 * Se regresa una contraseña de un usuario.
 * @return regresa una variable contraseña.
 */
    public int getContraseña() {
        return contraseña;
    }

/**
 * Se accede al valor de la contraseña y se modifica asignando un nombre al objeto.
 * @param contraseña asigna el nuevo valor al atributo contraseña del objeto.
 */
    public void setContraseña(int contraseña) {
        this.contraseña = contraseña;
    }
    
}

