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
 */
public class DatosEnArchivo {

    public DatosEnArchivo() {
    }
    
    public void guardaDatosAlumnos(List<Alumno> alumnos){
        
        String archivoCSV = "datosAlumnos.csv";
        try {
            try (FileWriter archivo = new FileWriter(archivoCSV)) {
                
                for (Alumno alumno : alumnos) {
                    // Escribir encabezados
                archivo.write("\nNombreCompleto,Edad,SemestreEnCurso,Direccion,NumCuenta\n");
                 // Obtener la dirección del alumno y agregar comillas si contiene comas
                String direccion = alumno.getDireccion().contains(",") ?
                                   "\"" + alumno.getDireccion() + "\"" :
                                   alumno.getDireccion();
                    String linea = alumno.getNombreCompleto() + "," +
                            alumno.getEdad() + "," +
                            alumno.getSemestreEnCurso() + "," +
                            direccion + "," +
                            alumno.getNumCuenta() + "\n";
                    archivo.write(linea);
                    
                    // Iterar sobre las materias del alumno y guardar sus datos
                    TiraDeMaterias tira = new TiraDeMaterias();
                    tira.asignarMaterias(alumno.getSemestreEnCurso());
                    List<String> materiasCursadas = tira.getMateriasCursadas();
                    List<Integer> calificaciones = tira.getCalificaciones();
                    
                    
                    archivo.write("Materia,Calificacion\n");
                    for (int i = 0; i < materiasCursadas.size(); i++){
                        String datosMateria = materiasCursadas.get(i) + "," + calificaciones.get(i) + "\n";
                        archivo.write(datosMateria);
                    }
                }
            }
            System.out.println("Los datos se han guardado en " + archivoCSV);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
}
