/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo_proyecto_final;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author cesarin
 */
public class TiraDeMaterias {
    Materia materias[];
    
    public TiraDeMaterias(int semestreAlumno) {
        cargarMaterias(semestreAlumno);
    }
    
    private List<String> leerArchivo(String nombreArchivo) {
    List<String> lineas = new ArrayList<>();
    try {
        lineas = Files.readAllLines(Paths.get(nombreArchivo), StandardCharsets.UTF_8);
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Error al leer el archivo: " + e.getMessage());
    }
    return lineas;
}

    
    // Método para cargar las materias desde el archivo de asignaturas
    private void cargarMaterias(int semestreAlumno) {
        List<String> lineas = leerArchivo("Asignaturas.txt");
        // Inicializa el arreglo de materias con la longitud de las líneas leídas
        materias = new Materia[lineas.size()];
        // Itera sobre las líneas del archivo para crear las instancias de Materia
        for (int i = 0; i < lineas.size(); i++) {
            // Divide la línea en partes: semestre, nombre_materia, creditos
            String[] partes = lineas.get(i).split(",");

            // Convierte las partes a los tipos necesarios
            int semestreMateria = Integer.parseInt(partes[0]);
            String nombreMateria = partes[1];
            int creditos = Integer.parseInt(partes[2]);

            // Si el semestre de la materia coincide con el semestre del alumno, la agrega al arreglo
            if (semestreMateria == semestreAlumno) {
                materias[i] = new Materia(creditos, semestreMateria, nombreMateria);
            }
        }
    }

    @Override
    public String toString() {
        return "TiraDeMaterias{" + "materias=" + Arrays.toString(materias) + '}';
    }
}
