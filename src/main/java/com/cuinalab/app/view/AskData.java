package com.cuinalab.app.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase auxiliar para la lectura de datos desde la consola.
 * Proporciona métodos validados para leer enteros, decimales,
 * cadenas y booleanos.
 */
public class AskData {

    private BufferedReader br;

    /**
     * Constructor que inicializa el lector sobre la entrada estándar.
     */
    public AskData() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Lee una cadena no vacía desde la consola.
     *
     * @param mensaje mensaje a mostrar al usuario
     * @return cadena introducida
     * @throws IOException si hay error de lectura
     */
    public String askString(String mensaje) throws IOException {
        String texto;
        do {
            System.out.print(mensaje);
            texto = br.readLine();
            if (texto.isBlank()) {
                System.out.println("No es pot deixar en blanc.");
            }
        } while (texto.isBlank());
        return texto;
    }

    /**
     * Lee un número entero desde la consola.
     *
     * @param mensaje mensaje a mostrar al usuario
     * @return entero introducido
     * @throws IOException si hay error de lectura
     */
    public int askInt(String mensaje) throws IOException {
        int numero;
        while (true) {
            try {
                System.out.print(mensaje);
                numero = Integer.parseInt(br.readLine());
                return numero;
            } catch (NumberFormatException e) {
                System.out.println("Debes poner un número entero.");
            }
        }
    }

    /**
     * Lee un entero validando que esté en un rango.
     *
     * @param mensaje  mensaje a mostrar
     * @param mensajeError mensaje de error si está fuera de rango
     * @param min      valor mínimo inclusive
     * @param max      valor máximo inclusive
     * @return entero dentro del rango
     * @throws IOException si hay error de lectura
     */
    public int askInt(String mensaje, String mensajeError, int min, int max) throws IOException {
        int numero;
        do {
            numero = askInt(mensaje);
            if (numero < min || numero > max) {
                System.out.println(mensajeError);
            }
        } while (numero < min || numero > max);
        return numero;
    }

    /**
     * Lee un número decimal desde la consola.
     *
     * @param mensaje mensaje a mostrar
     * @return double introducido
     * @throws IOException si hay error de lectura
     */
    public double askDouble(String mensaje) throws IOException {
        double numero;
        while (true) {
            try {
                System.out.print(mensaje);
                numero = Double.parseDouble(br.readLine());
                return numero;
            } catch (NumberFormatException e) {
                System.out.println("Debes poner un número.");
            }
        }
    }

    /**
     * Lee un decimal validando que sea mayor o igual a un mínimo.
     *
     * @param mensaje  mensaje a mostrar
     * @param mensajeError mensaje de error si es menor
     * @param min      valor mínimo inclusive
     * @return double >= min
     * @throws IOException si hay error de lectura
     */
    public double askDouble(String mensaje, String mensajeError, double min) throws IOException {
        double numero;
        do {
            numero = askDouble(mensaje);
            if (numero < min) {
                System.out.println(mensajeError);
            }
        } while (numero < min);
        return numero;
    }

    /**
     * Lee una respuesta sí/no desde la consola.
     *
     * @param mensaje mensaje a mostrar
     * @return true si la respuesta es 's', false si es 'n'
     * @throws IOException si hay error de lectura
     */
    public boolean askBoolean(String mensaje) throws IOException {
        String respuesta;
        do {
            System.out.print(mensaje);
            respuesta = br.readLine();
            if (respuesta == null) {
                return false;
            }
            respuesta = respuesta.trim().toLowerCase();
            if (!respuesta.equals("s") && !respuesta.equals("n")) {
                System.out.println("Responde s o n.");
            }
        } while (!respuesta.equals("s") && !respuesta.equals("n"));
        return respuesta.equals("s");
    }
}
