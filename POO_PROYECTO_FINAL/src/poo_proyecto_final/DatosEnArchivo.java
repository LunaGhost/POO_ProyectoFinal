/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo_proyecto_final;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author EquipoC
 * Clase que gestiona la escritura de datos de alumnos y sus calificaciones en archivos CSV.
 * Se proporcionan métodos para guardar datos generales y datos finales que incluyen las materias cursadas.
 * @author EquipoC
 */
public class DatosEnArchivo {

    /**
     * Constructor por defecto de la clase DatosEnArchivo.
     */
    public DatosEnArchivo() {
    }

    /**
     * Guarda los datos generales de los alumnos en un archivo CSV.
     * @param alumnos Lista de objetos Alumno cuyos datos se guardarán.
     */
    public void guardaDatosAlumnos(List<Alumno> alumnos) {
        // Nombre del archivo CSV para almacenar datos de alumnos
        String archivoCSV = "datosAlumnos.csv";
        
        try {
            try (FileWriter archivo = new FileWriter(archivoCSV)) {
                // Escribir encabezados
                archivo.write("\nNombreCompleto,Edad,SemestreEnCurso,Direccion,NumCuenta\n");
                
                // Iterar sobre los alumnos y escribir sus datos en el archivo
                for (Alumno alumno : alumnos) {
                    // Obtener la dirección del alumno y agregar comillas si contiene comas
                    String direccion = alumno.getDireccion().contains(",") ?
                                       "\"" + alumno.getDireccion() + "\"" :
                                       alumno.getDireccion();
                    // Construir la línea de datos del alumno
                    String linea = alumno.getNombreCompleto() + "," +
                            alumno.getEdad() + "," +
                            alumno.getSemestreEnCurso() + "," +
                            direccion + "," +
                            alumno.getNumCuenta() + "\n";
                    // Escribir la línea en el archivo
                    archivo.write(linea);
                    
                    // Iterar sobre las materias del alumno y guardar sus datos
                    TiraDeMaterias tira = new TiraDeMaterias();
                    tira.asignarMaterias(alumno.getSemestreEnCurso());
                    List<String> materiasCursadas = tira.getMateriasCursadas();
                    List<Integer> calificaciones = tira.getCalificaciones();
                    
                    // Escribir encabezados de materias y calificaciones
                    archivo.write("Materia,Calificacion\n");
                    // Iterar sobre las materias y calificaciones y escribir en el archivo
                    for (int i = 0; i < materiasCursadas.size(); i++) {
                        String datosMateria = materiasCursadas.get(i) + "," + calificaciones.get(i) + " Indicador ecolar: "+tira.indicadorEscolar + "\n";
                        archivo.write(datosMateria);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Guarda los datos finales de los alumnos, incluyendo las materias cursadas y calificaciones, en un archivo CSV.
     * @param alumnos Lista de objetos Alumno cuyos datos finales se guardarán.
     */
    public void datosFinales(List<Alumno> alumnos) {
        // Nombre del archivo CSV para almacenar datos finales
        String archivoCSV = "datosFinales.csv";

        try {
            try (FileWriter archivo = new FileWriter(archivoCSV)) {
                // Escribir encabezados
                archivo.write("NombreCompleto,Edad,SemestreEnCurso,Direccion,NumCuenta,Materia,Calificacion\n");

                // Iterar sobre los alumnos y escribir sus datos finales en el archivo
                for (Alumno alumno : alumnos) {
                    // Obtener la dirección del alumno y agregar comillas si contiene comas
                    String direccion = alumno.getDireccion().contains(",") ?
                                       "\"" + alumno.getDireccion() + "\"" :
                                       alumno.getDireccion();

                    // Construir la línea de datos del alumno
                    String linea = alumno.getNombreCompleto() + "," +
                                   alumno.getEdad() + "," +
                                   alumno.getSemestreEnCurso() + "," +
                                   direccion + "," +
                                   alumno.getNumCuenta();

                    // Iterar sobre las materias del alumno y agregar a la línea
                    TiraDeMaterias tira = new TiraDeMaterias();
                    tira.asignarMaterias(alumno.getSemestreEnCurso());
                    List<String> materiasCursadas = tira.getMateriasCursadas();
                    List<Integer> calificaciones = tira.getCalificaciones();

                    // Iterar sobre las materias y calificaciones y agregar a la línea
                    for (int i = 0; i < materiasCursadas.size(); i++) {
                        linea += "," + materiasCursadas.get(i) + "," + calificaciones.get(i);
                    }

                    // Escribir la línea completa en el archivo
                    archivo.write(linea + "\n");
                }
            }

            // Imprimir mensaje de éxito en la consola
            System.out.println("Los datos se han guardado en " + archivoCSV);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}