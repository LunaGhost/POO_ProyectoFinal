/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package poo_proyecto_final;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author EquipoC
 * Este código representa la definición de la clase Alumno en el proyecto final de POO.
 * La clase Alumno se encarga de representar a un estudiante con atributos como nombre completo, edad y semestre en curso.
 * Además, proporciona métodos para obtener y establecer estos atributos, así como para generar un nombre completo aleatorio
 * y calcular el semestre en curso del estudiante.
 */

/**
 * La clase Alumno representa a un estudiante con atributos como nombre completo, edad y semestre en curso.
 */
public class Alumno {
    private String nombreCompleto;
    private final int edad;
    private int semestreEnCurso;
    private String direccion;
    private String numCuenta;

    /**
     * Constructor de la clase Alumno.
     * Inicializa la edad del estudiante de forma aleatoria dentro de un rango específico.
     */
    public Alumno() {
        this.edad = new Random().nextInt(10) + 18;
    }

    /**
     * Obtiene el nombre completo del estudiante.
     *
     * @return El nombre completo del estudiante.
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Establece el nombre completo del estudiante generando uno aleatorio.
     */
    public void setNombreCompleto() {
        this.nombreCompleto = generarNombreCompleto();
    }

    /**
     * Obtiene la edad del estudiante.
     *
     * @return La edad del estudiante.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Obtiene el semestre en curso del estudiante.
     *
     * @return El semestre en curso del estudiante.
     */
    public int getSemestreEnCurso() {
        return semestreEnCurso;
    }

    /**
     * Establece el semestre en curso del estudiante.
     */
    public void setSemestreEnCurso() {
        this.semestreEnCurso = calcularSemestre();
    }

    /**
     * Obtiene la dirección del estudiante.
     *
     * @return La dirección del estudiante.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del estudiante generando una aleatoria.
     */
    public void setDireccion() {
        this.direccion = generarDireccion();
    }

    /**
     * Obtiene el número de cuenta del estudiante.
     *
     * @return El número de cuenta del estudiante.
     */
    public String getNumCuenta() {
        return numCuenta;
    }

    /**
     * Establece el número de cuenta del estudiante.
     *
     * @param numCuenta El número de cuenta a establecer.
     */
    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    /**
     * Genera una dirección aleatoria para el estudiante utilizando un archivo de direcciones.
     *
     * @return La dirección generada.
     */
    private String generarDireccion() {
        List<String> direcciones = leerArchivo("Direcciones.txt");

        // Verificar si la lista de direcciones no está vacía
        if (!direcciones.isEmpty()) {
            return seleccionarDireccionAleatoria(direcciones);
        } else {
            System.out.println("Error: La lista de direcciones está vacía.");
            return ""; // O devuelve otra cadena que indique un problema
        }
    }

    /**
     * Selecciona aleatoriamente una dirección de la lista de direcciones proporcionada.
     *
     * @param listaDirecciones Lista de direcciones desde la cual seleccionar.
     * @return La dirección seleccionada aleatoriamente.
     */
    private String seleccionarDireccionAleatoria(List<String> listaDirecciones) {
        Random rand = new Random();
        String direccionCompleta;

        // Seleccionar aleatoriamente una dirección de la lista
        String direccionElegida = listaDirecciones.get(rand.nextInt(listaDirecciones.size()));

        // Combinar las partes para formar la dirección completa
        direccionCompleta = direccionElegida.trim();

        return direccionCompleta;
    }

    /**
     * Genera un nombre completo aleatorio para el estudiante utilizando listas de nombres y apellidos.
     *
     * @return El nombre completo generado.
     */
    private String generarNombreCompleto() {
        List<String> nombres = leerArchivo("Nombres.txt");
        List<String> apellidos = leerArchivo("Apellidos.txt");

        String nombre = seleccionarNombreAleatorio(nombres);
        if (new Random().nextBoolean()) {
            // Agregar un segundo nombre en un 50% de las veces
            nombre += " " + seleccionarNombreAleatorio(nombres);
        }

        String apellido1 = seleccionarNombreAleatorio(apellidos);
        String apellido2 = seleccionarNombreAleatorio(apellidos);

        return nombre + " " + apellido1 + " " + apellido2;
    }

    /**
     * Selecciona un nombre aleatorio de la lista de nombres proporcionada.
     *
     * @param listaNombres Lista de nombres desde la cual seleccionar.
     * @return El nombre seleccionado aleatoriamente.
     */
    private String seleccionarNombreAleatorio(List<String> listaNombres) {
        String[] nombresArray = listaNombres.get(0).split(","); // Dividir por comas
        Random rand = new Random();
        return nombresArray[rand.nextInt(nombresArray.length)];
    }

    /**
     * Lee el contenido de un archivo y devuelve una lista de líneas.
     *
     * @param nombreArchivo Nombre del archivo a leer.
     * @return Lista de líneas leídas desde el archivo.
     */
    private List<String> leerArchivo(String nombreArchivo) {
        List<String> lineas = new ArrayList<>();
        try {
            lineas = Files.readAllLines(Paths.get(nombreArchivo));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return lineas;
    }

    /**
     * Calcula y devuelve el semestre en curso del estudiante según su edad.
     *
     * @return El semestre en curso calculado.
     */
    public int calcularSemestre() {
        int semestre;

        semestre = switch (edad) {
            case 18, 19 -> (int) (Math.random() * 3) + 1;
            case 20, 21 -> (int) (Math.random() * 4) + 2;
            case 22, 23 -> (int) (Math.random() * 4) + 4;
            default -> (int) (Math.random() * 4) + 7;
        };
        return semestre;
    }
    
    /**
     * Genera un número de cuenta aleatorio para el estudiante.
     *
     * @return El número de cuenta generado.
     */
    public String generaNumCuenta (){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            int digito = random.nextInt(10);
            sb.append(digito);
        }

        this.numCuenta = sb.toString();
        return numCuenta;    
    }

    /**
     * Actualiza el nombre completo del estudiante con un nuevo nombre proporcionado.
     *
     * @param nuevoNombre El nuevo nombre completo a establecer.
     */
    public void actualizarNombreCompleto(String nuevoNombre){
        this.nombreCompleto = nuevoNombre;
    }
    
    /**
     * Actualiza el número de cuenta del estudiante con un nuevo número proporcionado.
     *
     * @param nuevoNumCuenta El nuevo número de cuenta a establecer.
     */
    public void actualizarNumCuenta(String nuevoNumCuenta){
        this.numCuenta = nuevoNumCuenta;
    }
    
    /**
     * Actualiza la dirección del estudiante con una nueva dirección proporcionada.
     *
     * @param nuevaDireccion La nueva dirección a establecer.
     */
    public void actualizarDireccion(String nuevaDireccion){
        this.direccion = nuevaDireccion;
    }
    
    
    /**
     * Override del método toString para proporcionar una representación de cadena de la instancia de Alumno.
     *
     * @return Cadena que representa al objeto Alumno.
     */
    @Override
    public String toString() {
        return "Alumno{" + "nombreCompleto=" + nombreCompleto + ", edad=" + edad + ", semestreEnCurso=" + semestreEnCurso + ", direccion=" + direccion + ", numCuenta=" + numCuenta + '}';
    }

}