/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poo_proyecto_final;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EquipoC
 */
public class POO_PROYECTO_FINAL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Alumno> alumnos = new ArrayList<>();
        //float indicadorEscolar, escolaridad,velocidad;
        for (int i = 0; i < 10; i++) {
            Alumno alumno = new Alumno();
            alumno.setNombreCompleto();
            alumno.setSemestreEnCurso();
            alumno.setDireccion();
            alumno.generaNumCuenta();
            alumnos.add(alumno);
        }

        for (Alumno alumno : alumnos) {
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
            
            //calculo del numero de inscripcion
            tira.calculoNumInscripción(alumno.getSemestreEnCurso());
        }
        
        DatosEnArchivo guardar= new DatosEnArchivo();
        guardar.guardaDatosAlumnos(alumnos);
        
    }    
}
