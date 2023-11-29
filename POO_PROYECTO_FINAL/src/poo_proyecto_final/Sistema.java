/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo_proyecto_final;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author cesarin
 */
public class Sistema {

    public Sistema() {
    }
    
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
            
            //calculo del numero de inscripcion
            tira.calculoNumInscripción(alumno.getSemestreEnCurso());
    }
    
    public void imprimirAlumnos(List<Alumno> alumnos ){
        for (Alumno alumno : alumnos) {
            imprimirAlumnoIndividual(alumno);
        }
        
        DatosEnArchivo guardar= new DatosEnArchivo();
        guardar.guardaDatosAlumnos(alumnos);
    }
    
    private static void imprimirDatosAlumno(String[] datos) {
        // Imprimir los datos del alumno
        System.out.println("Nombre Completo: " + datos[0].trim());
        System.out.println("Edad: " + datos[1].trim());
        System.out.println("Semestre en Curso: " + datos[2].trim());
        System.out.println("Dirección: " + datos[3].trim() + datos[4].trim() + datos[5].trim() + datos[6].trim());
        System.out.println("Número de Cuenta: " + datos[7].trim());
    }

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
}
