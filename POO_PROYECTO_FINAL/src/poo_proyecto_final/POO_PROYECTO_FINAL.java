/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poo_proyecto_final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author EquipoC
 */
public class POO_PROYECTO_FINAL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Sistema sistem = new Sistema();
        List<Alumno> alumnos = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            //float indicadorEscolar, escolaridad,velocidad;
            sistem.crearAlumnos(10, alumnos);
            
            do {
                System.out.println("### Buscar alumno ###");
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
                        sistem.buscarAlumnoPorCuenta(numCuentaBuscar);
                        System.out.println("### OPCIONES ###");
                        System.out.println("1. Modificar");
                        System.out.println("2. Eliminar");
                        System.out.println("3. Regresar");
                        System.out.print("Seleccione una opción: ");
                        int opcion2 = scanner.nextInt();
                        switch (opcion2) {
                            case 1 -> System.out.println("Modificanding");
                            case 2 -> System.out.println("Eliminanding");
                            case 3 -> System.out.println("");
                            default -> System.out.println("Opción no válida");
                        }
                    }
                    case 2 -> sistem.imprimirAlumnos(alumnos);
                    case 3 -> {
                        System.out.println("Saliendo del programa. ¡Hasta luego!");
                        System.exit(0);
                        }
                    default -> System.out.println("Opción no válida");
                }
            } while (true); // El bucle se repetirá hasta que el usuario elija la opción 3
        }
    }
}
