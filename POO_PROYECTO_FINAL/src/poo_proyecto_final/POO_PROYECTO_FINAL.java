/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poo_proyecto_final;

/**
 *
 * @author EquipoC
 */
public class POO_PROYECTO_FINAL {

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            Alumno alumno = new Alumno();
            alumno.setNombreCompleto();
            alumno.setSemestreEnCurso();
            alumno.setDireccion();
            
            TiraDeMaterias tira = new TiraDeMaterias(alumno.getSemestreEnCurso());
            alumno.setTira(tira);

            System.out.println(alumno);
        }
    }
}