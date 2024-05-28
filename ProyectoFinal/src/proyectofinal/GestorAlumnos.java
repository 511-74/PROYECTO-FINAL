/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 * Clase GestorAlumnos con sus importaciones.
 * @author Rojas Nava Silvia Jamile
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import static proyectofinal.ProyectoFinal.generarListaMaterias;
import static proyectofinal.ProyectoFinal.leerArchivo;

public class GestorAlumnos {

    private static final String NOMBRE_ARCHIVO = "alumnos.csv"; // Nombre del archivo donde se guardan los datos de los alumnos.
    private final List<String> materiasData; // Lista de datos de materias
    
// Constructor que inicializa la lista de materias desde el archivo materias.txt.
    public GestorAlumnos() throws IOException {
        this.materiasData = leerArchivo("materias.txt");
    }

// Método para agregar un nuevo alumno.
    public void agregarAlumno() throws IOException {
        float promedio=0;
        Scanner scanner = new Scanner(System.in);
// Solicitar y leer los datos del nuevo alumno.
        System.out.println("⫸ ⫸ ⫸ ⫸ Ingrese el nombre del alumno que se creará: ");
        String nombre = scanner.nextLine();
        System.out.println("⫸ ⫸ ⫸ ⫸ Ingrese el número de cuenta del alumno: ");
        int numerocuenta = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("⫸ ⫸ ⫸ ⫸ Ingrese la dirección del alumno: ");
        String direccion = scanner.nextLine();
        System.out.println("⫸ ⫸ ⫸ ⫸ Ingrese el semestre a cursar del alumno: ");
        int semestre = scanner.nextInt();
        System.out.println("⫸ ⫸ ⫸ ⫸ Ingrese ls edad del alumno: ");
        int edad = scanner.nextInt();
// Validar la edad del alumno.
        if(edad < 18 + (semestre - 1)){
            System.out.println(" ====== Edad no válida conforme a su semestre a cursar ====== ");
            return;
        }
// Generar la lista de materias del alumno.        
        List<Materia> materiasAlumno = new ArrayList<>();
        List<Materia> todasLasMaterias = generarListaMaterias(materiasData);
        Collections.shuffle(todasLasMaterias); 
// Asignar calificaciones aleatorias a las materias y calcular el promedio.
        for (int j = 0; j < (5*semestre); j++) {
            todasLasMaterias.get(j).setCalificacion((int)(Math.random() * 6) + 5);
            promedio += todasLasMaterias.get(j).getCalificacion();
            materiasAlumno.add(todasLasMaterias.get(j));
        }
        
        promedio /= (5*semestre);
// Crear el objeto Alumnos con los datos proporcionados.
        Alumnos nuevoAlumno = new Alumnos(nombre, numerocuenta, promedio, direccion, semestre, edad, materiasAlumno,0);
// Escribir los datos del nuevo alumno en el archivo alumnos.csv.
        try (FileWriter fw = new FileWriter(NOMBRE_ARCHIVO, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)) {
            pw.print(nombre + "," + numerocuenta + "," + promedio + "," + direccion + "," + semestre + "," + edad+ "," );
            for (Materia materia : materiasAlumno) {
                pw.print(materia.getNombreMateria() +"," );
            }
            pw.println();
            System.out.println("====== Alumno agregado correctamente ======");
        } catch (IOException e) {
            System.err.println("====== Error al agregar el alumno: " + e.getMessage());
        }
    }
// Método para modificar los datos de un alumno existente.    
    public void modificarDatoAlumno(Alumnos alumno) {
        Scanner scanner = new Scanner(System.in);
// Se solicita al usuario el dato que desea modificar.
        System.out.println("¿Qué dato desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Número de cuenta");
        System.out.println("3. Dirección");
        System.out.println("4. Semestre");
        System.out.println("5. Edad");
        System.out.println("6. Cancelar");

        int opcion = scanner.nextInt();
// Se modifica el dato correspondiente del alumno.
        switch (opcion) {
            case 1:
                scanner.nextLine();
                System.out.println("Ingrese el nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();
                alumno.setNombre(nuevoNombre);
                System.out.println("Nombre modificado: " + alumno.getNombre()); 
                break;
            case 2:
                System.out.println("Ingrese el nuevo número de cuenta: ");
                int nuevoNumeroCuenta = scanner.nextInt();
                alumno.setNumeroCuenta(nuevoNumeroCuenta);
                break;
            case 3:
                scanner.nextLine();
                System.out.println("Ingrese la nueva dirección: ");
                String nuevaDireccion = scanner.nextLine();
                alumno.setDireccion(nuevaDireccion);
                 System.out.println("Dirección modificada: " + alumno.getDireccion()); 
                
                break;
            case 4:
                System.out.println("Ingrese el nuevo semestre: ");
                int nuevoSemestre = scanner.nextInt();
                alumno.setSemestre(nuevoSemestre);
                break;
            case 5:
                System.out.println("Ingrese la nueva edad: ");
                int nuevaEdad = scanner.nextInt();
                alumno.setEdad(nuevaEdad);
                break;
            case 6:
                System.out.println("==== Modificación cancelada ====");
                break;
            default:
                System.out.println("==== Opción no existente. Ingrese una correcta ====");
                break;
        }
// Se actualizan los datos del alumno en el archivo alumnos.csv.        
        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            List<String> lineasArchivo = new ArrayList<>();
            String linea;
            br.readLine(); 
            while ((linea = br.readLine()) != null) {
                String[] datosAlumno = linea.split(","); 
// Se actualiza la línea del archivo correspondiente al alumno modificado.
                if (datosAlumno.length > 1 && Long.parseLong(datosAlumno[1].trim()) == alumno.getNumeroCuenta()) {
                    StringBuilder nuevasMaterias = new StringBuilder();
                    for (Materia materia : alumno.getMaterias()) {
                        nuevasMaterias.append(materia.getNombreMateria()).append(",");
                    }
                    
                    String materiasString = nuevasMaterias.toString();
                    String datos = alumno.getNombre() + "," +alumno.getNumeroCuenta() + "," +
                            alumno.getPromedio() +","+
                            alumno.getDireccion() + "," +
                            alumno.getSemestre() + "," +
                            alumno.getEdad() + "," +
                            materiasString;
                    
                    lineasArchivo.add(datos);
                } else {
                    lineasArchivo.add(linea);
                }
            }
// Se escriben las líneas actualizadas de nuevo en el archivo.
            try (PrintWriter pw = new PrintWriter(new FileWriter(NOMBRE_ARCHIVO))) {
                for (String lineaActualizada : lineasArchivo) {
                    pw.println(lineaActualizada);
                }
                System.out.println("==== Los datos se han actualizado correctamente en el archivo ====");
            } catch (IOException e) {
                System.err.println("Se produjo un error al escribir en el archivo: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

    }
// Método para borrar un alumno.
    public void borrarAlumno(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del alumno a borrar: ");
        String nombreABorrar = scanner.nextLine();

        List<String> lineasArchivo = new ArrayList<>();
// Se lee el archivo y se excluyen las líneas que contienen el nombre del alumno a borrar.        
        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.contains(nombreABorrar)) {
                    lineasArchivo.add(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al borrar alumno: " + e.getMessage());
        }
// Se escriben las líneas restantes de nuevo en el archivo.
        try (PrintWriter pw = new PrintWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            for (String linea : lineasArchivo) {
                pw.println(linea);
            }
            System.out.println("=== Alumno borrado correctamente ====");
        } catch (IOException e) {
            System.err.println("Error al borrar alumno: " + e.getMessage());
        }
    }
// Método para buscar un alumno por nombre y mostrar su información.    
    public void buscarAlumnoPorNombre() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del alumno a buscar: ");
        String nombreBuscado = scanner.nextLine();
// Se lee el archivo y busca el nombre del alumno.
        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            boolean encontrado = false;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(nombreBuscado)) {
                    encontrado = true;
                    System.out.println("Información del alumno: ");
                    System.out.println(linea);
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("==== Alumno no encontrado ====");
            }
        } catch (IOException e) {
            System.err.println("Error al buscar alumno: " + e.getMessage());
        }
    }
// Método para buscar un alumno por nombre en una lista de alumnos.    
    public Alumnos buscarAlumno(String nombreBuscar, List<Alumnos> listaAlumnos) {
        for (Alumnos alumno : listaAlumnos) {
            if (alumno.getNombre().equalsIgnoreCase(nombreBuscar)) {
                return alumno;
            }
        }
        return null; 
    }
    
}
