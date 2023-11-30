/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo_proyecto_final;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;


/**
 *
 * @author EquipoC
 * La clase TiraDeMaterias representa la información sobre las
 * materias que un alumno ha cursado. Almacena detalles como el semestre, las
 * materias cursadas, las calificaciones, el promedio, las materias aprobadas,
 * reprobadas, entre otros datos relevantes.
 */
public class TiraDeMaterias extends Alumno {

    private int semestre, creditosAlumno, creditosTotales = 0, aprobadas = 0, reprobadas = 0;
    private String materia;
    private List<String> materiasCursadas;
    private List<Integer> calificaciones;
    private float promedio;

    /**
     * Constructor predeterminado que inicializa las listas materiasCursadas y calificaciones.
     */
    public TiraDeMaterias() {
        materiasCursadas = new ArrayList<>();
        calificaciones = new ArrayList<>();
    }

    /**
     * Constructor que inicializa los atributos con los valores proporcionados.
     * @param semestre         Representa el semestre actual del alumno.
     * @param creditosAlumno   Número de créditos actuales del alumno.
     * @param materia          Nombre de la materia actual.
     * @param materiasCursadas Lista de materias cursadas por el alumno.
     * @param calificaciones   Lista de calificaciones obtenidas por el alumno en las materias cursadas.
     * @param promedio         Promedio de calificaciones del alumno.
     */
    public TiraDeMaterias(int semestre, int creditosAlumno, String materia, List<String> materiasCursadas, List<Integer> calificaciones, float promedio) {
        this.semestre = semestre;
        this.creditosAlumno = creditosAlumno;
        this.materia = materia;
        this.materiasCursadas = materiasCursadas;
        this.calificaciones = calificaciones;
        this.promedio = promedio;
    }

    /**
     * @return El semestre actual.
     */
    public int getSemestre() {
        return semestre;
    }

    /**
     * Modifica el semestre.
     * @param semestre Nuevo valor para el semestre.
     */
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    /**
     * @return El nombre de la materia actual.
     */
    public String getMateria() {
        return materia;
    }

    /**
     * @return La lista de materias cursadas.
     */
    public List<String> getMateriasCursadas() {
        return materiasCursadas;
    }

    /**
     * @return La lista de calificaciones.
     */
    public List<Integer> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @return El promedio de calificaciones del alumno.
     */
    public float getPromedio() {
        return promedio;
    }

    /**
     * @return El número de materias aprobadas.
     */
    public int getAprobadas() {
        return aprobadas;
    }

    /**
     * @return El número de materias reprobadas.
     */
    public int getReprobadas() {
        return reprobadas;
    }

    /**
     * @return Los créditos del alumno.
     */
    public int getCreditosAlumno() {
        return creditosAlumno;
    }

    /**
     * @return Los créditos totales.
     */
    public int getCreditosTotales() {
        return creditosTotales;
    }

    /**
     * Lee un archivo ("Asignaturas.txt") que contiene información sobre las
     * materias y sus créditos correspondientes. Añade las materias cursadas por
     * el alumno, genera calificaciones aleatorias para estas materias y
     * actualiza los créditos y el estado de aprobación/reprobación.
     * @param semestreEnCurso Semestre que cursa el alumno.
     */
    public void asignarMaterias(int semestreEnCurso) {
        try {
            float sumaCalificaciones = 0.0f;

            FileReader fr = new FileReader("Asignaturas.txt");
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {

                StringTokenizer tokenizer = new StringTokenizer(linea, ",");
                int semestreA = Integer.parseInt(tokenizer.nextToken());
                String materias = tokenizer.nextToken();
                int creditosActuales = Integer.parseInt(tokenizer.nextToken());

                // Verificar si el semestre del archivo coincide con el semestre del estudiante
                if (semestreA <= semestreEnCurso) {
                    materiasCursadas.add(materias);
                    int calificacionAleatoria = generarCalificacion();
                    calificaciones.add(calificacionAleatoria);
                    if (calificacionAleatoria > 5) {
                        aprobadas++;
                        creditosAlumno += creditosActuales;
                    } else {
                        reprobadas++;
                    }
                    sumaCalificaciones += calificacionAleatoria;
                    creditosTotales += creditosActuales;
                }
            }

            promedio = sumaCalificaciones / (semestreEnCurso * 5);
            /*System.out.println("Promedio: "+promedio);
            System.out.println("Aprobadas: "+aprobadas);
            System.out.println("Reprobadas: "+reprobadas);
            System.out.println("Créditos del alumno: "+creditosAlumno);
            System.out.println("Créditos totales: "+creditosTotales);*/

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera una calificación aleatoria entre 5 y 10 para una materia.
     * @return La calificación aleatoria generada.
     */
    private int generarCalificacion() {
        Random rand = new Random();
        return rand.nextInt(6) + 5; // Números aleatorios entre 5 y 10
    }

    /**
     * Calcula un indicador escolar basado en el promedio, la escolaridad y la
     * velocidad de progreso del alumno.
     * @param semestreEnCurso Semestre que cursa el alumno.
     */
    public void calculoNumInscripción(int semestreEnCurso) {
        float indicadorEscolar, escolaridad, velocidad;
        escolaridad = ((float) aprobadas / (float) (semestreEnCurso * 5));
        velocidad = ((float) creditosAlumno / (float) creditosTotales);
        indicadorEscolar = promedio * escolaridad * velocidad;
        //System.out.println("escolaridad: "+escolaridad);
        //System.out.println("velocidad: "+velocidad);
        System.out.println("indicadorEscolar: " + indicadorEscolar);
        System.out.println("");
    }

    /**
     * Método sobreescrito
     * @return La información detallada sobre las materias cursadas por el alumno.
     */
    @Override
    public String toString() {
        return super.toString() + "\nMaterias cursadas: " + materiasCursadas;
    }

}