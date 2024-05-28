/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 * Clase GestorUsuarios con sus importaciones.
 * @author Rojas Nava Silvia Jamile
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GestorUsuarios {
// Declaración de atributos privados.
    private List<Usuario> usuarios;
    private String archivoUsuarios;

/**
 * Constructor vacío.
 */
    public GestorUsuarios() {
    }

/**
 * Constructor que inicializa la lista de usuarios y el archivo.
 * @param usuarios usuarios que se tomarán de un archivo.
 * @param archivoUsuarios archivo de usuarios.
 */
    public GestorUsuarios(List<Usuario> usuarios, String archivoUsuarios) {
        this.usuarios = usuarios;
        this.archivoUsuarios = archivoUsuarios;
    }

/**
 * Constructor que solo recibe el archivo y carga los usuarios desde él.
 * @param archivoUsuarios archivo con usuarios leídos.
 */
    public GestorUsuarios(String archivoUsuarios) {
        this.archivoUsuarios = archivoUsuarios;
        this.usuarios = new ArrayList<>();
        cargarUsuariosDesdeArchivo();
    }
// Método privado para cargar usuarios desde un archivo.
    private void cargarUsuariosDesdeArchivo() {
        try (Scanner scanner = new Scanner(new File(archivoUsuarios))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                Usuario usuario = new Usuario(linea);
                usuarios.add(usuario);
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo de usuarios no se encontró. Por favor introduzca el archivo en el proyecto.");
        }
    }
// Método para buscar un usuario por nombre.
    public Optional<Usuario> buscarUsuariosPorNombre(String nombreBuscado) {
        return usuarios.stream()
                .filter(usuario -> usuario.getNombre().equalsIgnoreCase(nombreBuscado))
                .findFirst();
    }
// Método privado para guardar los usuarios en el archivo.    
    private void guardarUsuariosEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoUsuarios))) {
            for (Usuario usuario : usuarios) {
                writer.write(usuario.obtenerFormatoArchivo());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
