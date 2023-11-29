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
 * La clase tira de materias representa la información sobre las
 * materias que un alumno ha cursado. Almacena detalles como el semestre, las
 * materias cursadas, las calificaciones, el promedio, las materias aprobadas,
 * reprobadas, entre otros datos relevantes.
 */
public class TiraDeMaterias extends Alumno{
    
    private int semestre, creditosAlumno,creditosTotales=0,aprobadas=0,reprobadas=0;
    private String materia;
    private List<String> materiasCursadas;
    private List<Integer> calificaciones;
    private float promedio;
    
    /**
     *  Constructor predeterminado que inicializa las listas materiasCursadas y calificaciones.
     */
    public TiraDeMaterias() {
        materiasCursadas = new ArrayList<>();
         calificaciones = new ArrayList<>();
    }
    
    /**
     * Constructor que inicializa los atributos con los valores proporcionados.
     * @param semestre  Representa el semestre actual del alumno.
     * @param creditosAlumno Número de créditos actuales del alumno.
     * @param materia Nombre de la materia actual.
     * @param materiasCursadas Lista de materias cursadas por el alumno.
     * @param calificaciones Lista de calificaciones obtenidas por el alumno en las materias cursadas.
     * @param promedio Promedio de calificaciones del alumno.
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
     * @return returna el semestre
     */
    public int getSemestre() {
        return semestre;
    }
    
    /**
     * modifica el semestre
     * @param semestre 
     */
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    
    /**
     * 
     * @return returna la materia
     */
    public String getMateria() {
        return materia;
    }
    
    /**
     * 
     * @return returna la lista de materias cursadas
     */
    public List<String> getMateriasCursadas() {
        return materiasCursadas;
    }
    
    /**
     * 
     * @return returna la lista de calificaciones
     */
    public List<Integer> getCalificaciones() {
        return calificaciones;
    }

   /**
    * 
    * @return returna el promedio
    */
    public float getPromedio() {
        return promedio;
    }

    /**
     * 
     * @return returna el numero de materias aprobadas
     */
    public int getAprobadas() {
        return aprobadas;
    }

    /**
     * 
     * @return returna el numero de materias reprobadas
     */
    public int getReprobadas() {
        return reprobadas;
    }

    /**
     * 
     * @return returna los creditos del alumno
     */
    public int getCreditosAlumno() {
        return creditosAlumno;
    }

    /**
     * 
     * @return returna los creditos totales
     */
    public int getCreditosTotales() {
        return creditosTotales;
    }

    /**
     * Lee un archivo ("Asignaturas.txt") que contiene información sobre las
     * materias y sus creditos correspondientes. Añade las materias cursadas por
     * el alumno, genera calificaciones aleatorias para estas materias y
     * actualiza los créditos y el estado de aprobación/reprobación.
     * @param semestreEnCurso recibe el semestre que cursa el alumno
     */
    public void asignarMaterias( int semestreEnCurso){
        try{
            float sumaCalificaciones=0.0f;
            
            FileReader fr =new FileReader("Asignaturas.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String linea;
            while ((linea = br.readLine()) != null){
    
                StringTokenizer tokenizer = new StringTokenizer(linea, ",");
                int semestreA=Integer.parseInt(tokenizer.nextToken());
                String materias=tokenizer.nextToken();
                int creditosActuales=Integer.parseInt(tokenizer.nextToken());
                
                 // Verificar si el semestre del archivo coincide con el semestre del estudiante
                if (semestreA <=semestreEnCurso) {
                    materiasCursadas.add(materias);
                    int calificacionAleatoria = generarCalificacion();
                    calificaciones.add(calificacionAleatoria);
                    if (calificacionAleatoria>5){
                        aprobadas++;
                        creditosAlumno+=creditosActuales;
                    }
                    else{
                        reprobadas++;
                    }
                    sumaCalificaciones+=calificacionAleatoria;
                    creditosTotales+=creditosActuales;
                }  
            }

            promedio=sumaCalificaciones/(semestreEnCurso*5);
            /*System.out.println("Promedio: "+promedio);
            System.out.println("Aprobadas: "+aprobadas);
            System.out.println("Reprobadas: "+reprobadas);
            System.out.println("Creditos del alumno: "+creditosAlumno);
            System.out.println("Creditos totales: "+creditosTotales);*/
            
         br.close();            
        }catch (IOException e){
            e.printStackTrace();
        }
    }
   
    /**
     * Genera una calificación aleatoria entre 5 y 10 para una materia.
     * @return returna la calificación aleatoria generada
     */
    private int generarCalificacion() {
        Random rand = new Random();
        return rand.nextInt(6) + 5; // Números aleatorios entre 5 y 10
    }
    
    /**
     * Calcula un indicador escolar basado en el promedio, la escolaridad y la
     * velocidad de progreso del alumno.
     * @param semestreEnCurso recibe el semestre que cursa el alumno
     */
    public void calculoNumInscripción(int semestreEnCurso){
        float indicadorEscolar, escolaridad,velocidad;
        escolaridad= ((float)aprobadas/(float)(semestreEnCurso*5));
        velocidad= ((float)creditosAlumno/(float)creditosTotales);
        indicadorEscolar=promedio*escolaridad*velocidad;
        //System.out.println("escolaridad: "+escolaridad);
        //System.out.println("velocidad: "+velocidad);
        System.out.println("indicadorEscolar: "+indicadorEscolar);
        System.out.println("");
    }
    
    /**
     * Método sobreescrito
     * @return returna la información detallada sobre las materias cursadas por el alumno.
     */
    @Override
     public String toString() {
        return super.toString() + "\nMaterias cursadas: " + materiasCursadas;
    }
    
}
