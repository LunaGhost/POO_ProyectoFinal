/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo_proyecto_final;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author EquipoC
 * Clase que representa el sistema de gestión de alumnos.
 * Permite la creación, impresión, búsqueda y modificación de datos de alumnos.
 * Utiliza archivos CSV para almacenar y recuperar datos de los alumnos.
 * 
 * @author EquipoC
 */
public class Sistema {

    /**
     * Constructor por defecto de la clase Sistema.
     */
    public Sistema() {
    }
    
    /**
     * Crea la cantidad especificada de objetos Alumno y los agrega a la lista dada.
     * 
     * @param cantidad La cantidad de alumnos a crear.
     * @param alumnos Lista donde se agregarán los nuevos alumnos.
     */
    public void crearAlumnos(int cantidad, List<Alumno> alumnos){
        for (int i = 0; i < cantidad; i++) {
            Alumno alumno = new Alumno();
            alumno.setNombreCompleto();
            alumno.setSemestreEnCurso();
            alumno.setDireccion();
            alumno.generaNumCuenta();
            alumnos.add(alumno);
        }
    }
    
    /**
     * Imprime los datos individuales de un alumno junto con sus materias cursadas y calificaciones.
     * 
     * @param alumno El objeto Alumno cuyos datos se imprimirán.
     */
    public void imprimirAlumnoIndividual(Alumno alumno){
        System.out.println("Datos del alumno:");
        System.out.println(alumno);

        TiraDeMaterias tira = new TiraDeMaterias();
        tira.asignarMaterias(alumno.getSemestreEnCurso());
        List<String> materiasCursadas = tira.getMateriasCursadas();
        List<Integer> calificaciones = tira.getCalificaciones();
        
        System.out.println("Materias cursadas y calificaciones:");
        for (int i = 0; i < materiasCursadas.size(); i++) {
            System.out.println("Materia: " + materiasCursadas.get(i) + ", Calificación: " + calificaciones.get(i));
        }

        System.out.println(); // Separador entre alumnos
        
        // Cálculo del número de inscripción
        tira.calculoNumInscripción(alumno.getSemestreEnCurso());
    }
    
    /**
     * Imprime los datos de todos los alumnos en la lista y guarda los datos en un archivo CSV.
     * 
     * @param alumnos Lista de alumnos cuyos datos se imprimirán y guardarán.
     */
    public void imprimirAlumnos(List<Alumno> alumnos ){
        for (Alumno alumno : alumnos) {
            imprimirAlumnoIndividual(alumno);
        }
        
        DatosEnArchivo guardar= new DatosEnArchivo();
        guardar.guardaDatosAlumnos(alumnos);
        guardar.datosFinales(alumnos);
    }
    
    /**
     * Imprime los datos de un alumno a partir de un array de strings.
     * 
     * @param datos Array de strings que contiene los datos del alumno.
     */
    private static void imprimirDatosAlumno(String[] datos) {
        // Imprimir los datos del alumno
        System.out.println("Nombre Completo: " + datos[0].trim());
        System.out.println("Edad: " + datos[1].trim());
        System.out.println("Semestre en Curso: " + datos[2].trim());
        System.out.println("Dirección: " + datos[3].trim() + datos[4].trim() + datos[5].trim() + datos[6].trim());
        System.out.println("Número de Cuenta: " + datos[7].trim());
    }

    /**
     * Busca un alumno por su número de cuenta en un archivo CSV.
     * 
     * @param numCuentaBuscar Número de cuenta del alumno a buscar.
     */
    public static void buscarAlumnoPorCuenta(String numCuentaBuscar) {
        String csvFile = "datosAlumnos.csv"; // Reemplaza con la ruta correcta de tu archivo CSV
        String line;
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");

                // Verificar si la línea contiene datos de un alumno
                if (datos.length > 1 && datos[datos.length - 1].trim().equals(numCuentaBuscar)) {
                    imprimirDatosAlumno(datos);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Alumno no encontrado con el número de cuenta: " + numCuentaBuscar);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Método principal que inicia la interacción con el usuario.
     * Permite buscar, imprimir y modificar datos de alumnos.
     * 
     * @throws IOException Excepción en caso de error de entrada/salida.
     */
    public void inicio() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Alumno> alumnos = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            
            crearAlumnos(10, alumnos);
            
            do {
                System.out.println("\n### Buscar alumno ###");
                System.out.println("1. Buscar alumno");
                System.out.println("2. Imprimir alumnos");
                System.out.println("3. Salir");

                // Solicitar la entrada del usuario
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();

                // Switch para manejar las opciones
                switch (opcion) {
                    case 1 -> {
                        // Obtener el número de cuenta del usuario
                        System.out.print("Ingrese el número de cuenta del alumno a buscar: ");
                        String numCuentaBuscar = reader.readLine();
                        
                        // Buscar alumno por número de cuenta en el archivo CSV
                        Sistema.buscarAlumnoPorCuenta(numCuentaBuscar);
                        
                        // Menú para modificar o eliminar datos del alumno encontrado
                        System.out.println("\n### OPCIONES ###");
                        System.out.println("1. Modificar");
                        System.out.println("2. Eliminar");
                        System.out.println("3. Regresar.");
                        System.out.print("Seleccione una opción: ");
                        int opcion2 = scanner.nextInt();
                        Alumno alumnoModificar=null;
                        for (Alumno alumno : alumnos) {
                            if(alumno.getNumCuenta().equals(numCuentaBuscar)){
                                alumnoModificar=alumno;
                                break;
                            }
                        }
                        switch (opcion2) {
                            case 1 -> {
                                if (alumnoModificar !=null){
                                    System.out.println("\n¿Qué dato desea modificar?");
                                    System.out.println("1. Nombre completo");
                                    System.out.println("2. Número de Cuenta");
                                    System.out.println("3. Direccion");
                                    System.out.print("Seleccione una opción: ");
                                    int opcion3 = scanner.nextInt();
                                    switch (opcion3) {
                                        case 1 -> {
                                            System.out.println("\nIngrese el nuevo nombre completo");
                                            String nuevoNombre = reader.readLine();
                                            alumnoModificar.actualizarNombreCompleto(nuevoNombre);
                                            System.out.println("----Nombre Actualizado---\n");
                                            imprimirAlumnoIndividual(alumnoModificar);
                                            break;
                                        }
                                        case 2 ->{
                                            System.out.println("\nIngrese el nuevo número de cuenta");
                                            String nuevoNumCuenta= reader.readLine();
                                            alumnoModificar.actualizarNumCuenta(nuevoNumCuenta);
                                            System.out.println("----Número de Cuenta Actualizado---\n");
                                            imprimirAlumnoIndividual(alumnoModificar);
                                            break;
                                        }
                                        case 3 -> {
                                            System.out.println("\nIngrese la nueva dirección");
                                            String nuevaDireccion = reader.readLine();
                                            alumnoModificar.actualizarDireccion(nuevaDireccion);
                                            System.out.println("----Direccion Actualizada---\n");
                                            imprimirAlumnoIndividual(alumnoModificar);
                                            break; 
                                        }
                                        default -> throw new AssertionError();
                                    }
                                }
                            }
                            case 2 -> {
                                if(alumnoModificar!=null){
                                    alumnos.remove(alumnoModificar);
                                    System.out.println("----Datos eliminados---\n");
                                    imprimirAlumnos(alumnos);
                                }
                                break;
                            }
                            case 3 -> System.out.println("");
                            default -> System.out.println("Opción no válida");
                        }
                    }
                    case 2 -> imprimirAlumnos(alumnos);
                    case 3 -> {
                        System.out.println("\nSaliendo del programa. ¡Hasta luego!\n");
                        System.exit(0);
                        }
                    default -> System.out.println("Opción no válida");
                }
            } while (true); // El bucle se repetirá hasta que el usuario elija la opción 3
        }
    }
}