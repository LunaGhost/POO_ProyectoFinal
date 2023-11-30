/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poo_proyecto_final;

import java.io.IOException;
import java.util.Scanner;

/**
 * Clase principal que contiene el método main para iniciar la ejecución del programa.
 * Este programa representa un sistema de Servicios Escolares.
 * Se requiere una contraseña para acceder al sistema, y una vez ingresada correctamente,
 * se llama al método inicio() de la clase Sistema para comenzar la interacción con el usuario.
 * @author EquipoC
 */
public class POO_PROYECTO_FINAL {

    /**
     * Método principal que inicia la ejecución del programa.
     * @param args Los argumentos de la línea de comandos (no se utilizan en este caso).
     * @throws IOException Excepción lanzada en caso de problemas de entrada/salida.
     */
    public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);
        Sistema sistema = new Sistema(); // Instancia de la clase Sistema para gestionar el sistema.
        String password = "equipoC123"; // Contraseña requerida para acceder al sistema.

        System.out.println("---Bienvenido al Sistema Servicios Escolares---");

        // Bucle principal que solicita la contraseña hasta que se ingrese correctamente.
        while (true) {
            System.out.print("Ingresa contraseña:");
            String contra = teclado.nextLine();

            // Verifica si la contraseña ingresada es correcta.
            if (contra.equals(password)) {
                System.out.println("Contraseña correcta. Acceso concedido al sistema.");
                sistema.inicio(); // Llama al método inicio() de la clase Sistema.
                break; // Rompe el ciclo ya que la contraseña es correcta.
            } else {
                System.out.println("Contraseña incorrecta. Por favor, inténtelo de nuevo.");
            }
        }
    }
}