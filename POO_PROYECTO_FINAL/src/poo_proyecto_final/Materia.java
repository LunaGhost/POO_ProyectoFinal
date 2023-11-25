/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo_proyecto_final;

/**
 *
 * @author EquipoC
 */
public class Materia {
    private int creditos;
    private int semestre;
    private String nombreMateria;

    public Materia(int creditos, int semestre, String nombreMateria) {
        this.creditos = creditos;
        this.semestre = semestre;
        this.nombreMateria = nombreMateria;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    @Override
    public String toString() {
        return "Materia{" + "creditos=" + creditos + ", semestre=" + semestre + ", nombreMateria=" + nombreMateria + '}';
    }
    
    
}
