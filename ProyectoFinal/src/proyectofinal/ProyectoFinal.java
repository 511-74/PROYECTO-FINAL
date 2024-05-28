/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectofinal;

/**
 * Clase padre, clase proyectofinal (main), con sus importaciones.
 * @author Rojas Nava Silvia Jamile
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProyectoFinal {
// Método para leer un archivo de texto y devolver una lista de líneas.
    public static List<String> leerArchivo(String nombreArchivo) throws IOException {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        }
        return lineas;
    }
// Método para generar un nombre aleatorio de alumno a partir de listas de nombres y apellidos.
    public static String generarNombre(List<String> nombres, List<String> apellidos, Random rand) {
        String nombreAlumno;
        if (rand.nextBoolean()) {
            nombreAlumno = nombres.get(rand.nextInt(nombres.size())) + " " +
                    apellidos.get(rand.nextInt(apellidos.size())) + " " +
                    apellidos.get(rand.nextInt(apellidos.size()));
        } else {
            nombreAlumno = nombres.get(rand.nextInt(nombres.size())) + " " +
                    nombres.get(rand.nextInt(nombres.size())) + " " +
                    apellidos.get(rand.nextInt(apellidos.size())) + " " +
                    apellidos.get(rand.nextInt(apellidos.size()));
        }
        return nombreAlumno;
    }
// Método para generar un archivo CSV con los datos de los alumnos.
    public static void generarArchivoCSVAlumnos(List<Alumnos> alumnos, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("Nombre,Numero de Cuenta,Promedio,Direccion,Colonia,Delegación,Semestre,Edad,Materias\n");

            for (Alumnos alumno : alumnos) {
                StringBuilder materiasConcatenadas = new StringBuilder();
                List<Materia> materias = alumno.getMaterias();
                for (Materia materia : materias) {
                    materiasConcatenadas.append(materia.getNombreMateria()).append(", ");
                }
//Se concatenan los datos del alumno.
                String materiasString = materiasConcatenadas.toString();
                String datosAlumno =
                        alumno.getNombre() + "," +
                        alumno.getNumeroCuenta() + "," +
                        alumno.getPromedio() + "," +
                        alumno.getDireccion() + "," +
                        alumno.getSemestre() + "," +
                        alumno.getEdad() + "," +
                        materiasString + "\n";
                writer.write(datosAlumno);
            }

            System.out.println("\t\t ==== El archivo CSV '" + nombreArchivo + "' se creó correctamente ==== \t\t\t");
        } catch (IOException ex) {
            Logger.getLogger(ProyectoFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// Método para mostrar la lista de alumnos en la consola.
    public static void mostrarListaAlumnos(List<Alumnos> alumnos) {
        for (Alumnos alumno : alumnos) {
            System.out.println("Nombre: " + alumno.getNombre());
            System.out.println("Número de cuenta: " + alumno.getNumeroCuenta());
            System.out.println("Promedio: " + alumno.getPromedio());
            System.out.println("Dirección: " + alumno.getDireccion());
            System.out.println("Semestre: " + alumno.getSemestre());
            System.out.println("Edad: " + alumno.getEdad());
            System.out.println("Materias:");
            for (Materia materia : alumno.getMaterias()) {
                System.out.println("- " + materia.getNombreMateria() + " (Código: " + materia.getCodigoMateria() +
                        ", Créditos: " + materia.getCreditos() + ", Calificación: " + materia.getCalificacion() + ')');
            }
            System.out.println("-----------------------------------------------------------------------------------");
        }
    }
// Método para generar una lista de materias a partir de los datos de un archivo de texto.
    public static List<Materia> generarListaMaterias(List<String> materiasData) {
        List<Materia> todasLasMaterias = new ArrayList<>();
        for (String materiaData : materiasData) {
            String[] partes = materiaData.split(",");
            if (partes.length == 4) {
                int semestreMateria = Integer.parseInt(partes[0]);
                String nombreMateria = partes[1];
                int codigo = Integer.parseInt(partes[2]);
                int creditos = Integer.parseInt(partes[3]);
                int calificacion = 0;

                Materia nuevaMateria = new Materia(semestreMateria, nombreMateria, codigo, creditos, calificacion);
                todasLasMaterias.add(nuevaMateria);
            }
        }
        return todasLasMaterias;
    }
// Método principal del programa, lanza un aviso de que el código es suceptible a errores.
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int bandera = 0, opc = 0, opc2 = 0;
        Random rand = new Random();
        List<Alumnos> alumnos = new ArrayList<>();
        String archivoUsuarios = "usuarios.txt";
        GestorUsuarios gestorUsuarios = new GestorUsuarios(archivoUsuarios);
        GestorAlumnos gestorAlumno = new GestorAlumnos();
// Mensaje de bienvenida y solicitud de usuario.
        System.out.println("////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("\t\t PROGRAMA PARA GENERAR NÚMEROS DE INSCRIPCIÓN ");
        System.out.println("////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("⫸ ⫸ ⫸ ⫸  Para ingresar introduzca su nombre de usuario de servicios académicos: ");
        String usuario = sc.nextLine();
// Búsqueda del usuario en el sistema.
        Optional<Usuario> usuarioEncontrado = gestorUsuarios.buscarUsuariosPorNombre(usuario);
// Verificación del usuario y su contraseña.
        if (usuarioEncontrado.isPresent()) {
            System.out.println("\t\t ==== Usuario encontrado ==== \t\t\t");
            System.out.println("⫸ ⫸ ⫸ ⫸  Introduzca su contraseña: ");
            int contraseña = sc.nextInt();
            if (contraseña == usuarioEncontrado.get().getContraseña()) {
                System.out.println("\t\t ==== Contraseña correcta ==== \t\t\t");
                System.out.println("\t\t\t ✥✥✥ Le damos la bienvenida ✥✥✥ \t\t\t");
                bandera = 1;
            } else {
                System.out.println("\t\t ==== Contraseña incorrecta. Introduzca la correcta ==== \t\t\t");
                return;
            }

        } else {
            System.out.println("\t\t ==== No existe un usuario con ese nombre. Introduzca uno correcto ==== \t\t\t");
            return;
        }
//Si la autenticación fue exitosa, procede con el menú de opciones.
        if (bandera == 1) {
//// Lectura de archivos necesarios para generar datos de alumnos.            
            try {
                List<String> nombres = leerArchivo("nombres.txt");
                List<String> apellidos = leerArchivo("apellidos.txt");
                List<String> direcciones = leerArchivo("direcciones.txt");
                List<String> materiasData = leerArchivo("materias.txt");
                List<Materia> todasLasMaterias = generarListaMaterias(materiasData);
// Menú principal del programa.
                do {
                    System.out.println("\\ 1. Generar base de datos /");
                    System.out.println("\\ 2. Mostrar lista de alumnos /");
                    System.out.println("\\ 3. Modificar alumno (CRUD) /");
                    System.out.println("\\ 4. Generar números de inscripción /");
                    System.out.println("\\ 5. Salir del programa. /");
                    System.out.println("⫸ ⫸ ⫸ ⫸  Introduzca la opción que desee que se realice: ");
                    opc = sc.nextInt();
                    switch (opc) {                   
                        case 1:
// Se generar una base de datos de 1000 alumnos.                                 
                            for (int i = 0; i < 1000; i++) {
                                float promedio = 0;
                                String nombreAlumno = generarNombre(nombres, apellidos, rand);
                                long numeroCuenta = (long) (Math.random() * 900_000_000L) + 100_000_000L;
                                String direccion = direcciones.get(rand.nextInt(direcciones.size()));
                                int semestre = rand.nextInt(10) + 1;
                                int edad = 18 + (semestre - 1);

                                List<Materia> materiasAlumno = new ArrayList<>();
                                Collections.shuffle(todasLasMaterias);
                                for (int j = 0; j < (5 * semestre); j++) {
                                    todasLasMaterias.get(j).setCalificacion((int) (Math.random() * 6) + 5);
                                    promedio += todasLasMaterias.get(j).getCalificacion();
                                    materiasAlumno.add(todasLasMaterias.get(j));
                                }

                                promedio /= (5 * semestre);
                                Alumnos nuevoAlumno = new Alumnos(nombreAlumno, numeroCuenta, promedio, direccion, semestre, edad, materiasAlumno, 0);
                                double num = nuevoAlumno.calcularNumeroInscripcion();
                                nuevoAlumno.setNumInscripcion(num);
                                alumnos.add(nuevoAlumno);
                            }
                            generarArchivoCSVAlumnos(alumnos, "alumnos.csv");
                            break;
                        case 2:
// Se muestra la lista de alumnos.
                            mostrarListaAlumnos(alumnos);
                            break;
                        case 3:
                            do {
// Menú secundario para operaciones CRUD sobre los alumnos                                
                                System.out.println("\t\t ✥✥✥✥ Opciones para modificar o crear a un alumno (CRUD) ✥✥✥✥ ");
                                System.out.println(" 1. Crear ");
                                System.out.println(" 2. Actualizar ");
                                System.out.println(" 3. Borrar ");
                                System.out.println(" 4. Buscar ");
                                System.out.println(" 5. Regresar a menu principal ");
                                System.out.println("⫸ ⫸ ⫸ ⫸ Ingrese la opción a realizar: ");
                                opc2 = sc.nextInt();
                                sc.nextLine();
                                switch (opc2) {
                                    case 1:
                                        gestorAlumno.agregarAlumno();
                                        break;
                                    case 2:
                                        System.out.println("Ingrese el nombre del alumno a actualizar: ");
                                        String nombreActualizar = sc.nextLine();
                                        Alumnos alumnoActualizar = gestorAlumno.buscarAlumno(nombreActualizar, alumnos);
                                        if (alumnoActualizar != null) {
                                            gestorAlumno.modificarDatoAlumno(alumnoActualizar);
                                        } else {
                                            System.out.println("Alumno no encontrado.");
                                        }
                                        break;
                                    case 3:
                                        gestorAlumno.borrarAlumno();
                                        break;
                                    case 4:
// Se generan y muestran los números de inscripción                                        
                                        gestorAlumno.buscarAlumnoPorNombre();
                                        break;
                                    default:
                                        break;
                                }
                            } while (opc2 != 5);
                            break;
                        case 4:
                            List<Alumnos> copiaLista = new ArrayList<>(alumnos);
                            copiaLista.sort((a1, a2) -> Double.compare(a2.getNumInscripcion(), a1.getNumInscripcion())); 

                            int posicion = 1;
                            for (Alumnos a : copiaLista) {
                                System.out.println("Número de inscripción " + posicion + " - Nombre: " + a.getNombre());
                                posicion++;
                            }
                            break;
                        case 5:
// Sale del programa.
                            System.out.println("\t\t ==== Programa finalizado ==== \t\t\t");
                            System.exit(0);
                        default:
                            System.out.println("\t\t ==== Opción no válida ==== \t\t\t");
                    }
                } while (opc != 5);

            } catch (IOException ex) {
                Logger.getLogger(ProyectoFinal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
