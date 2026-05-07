package com.cuinalab.app;

import com.cuinalab.app.exception.PersistenciaException;
import com.cuinalab.app.view.Menu;

/**
 * Clase principal de CuinaLab - Escuela de Cocina.
 * Inicia la aplicación y muestra el menú principal.
 */
public class CuinaLab {

    /**
     * Método principal de la aplicación.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        try {
            Menu menu = new Menu();
            menu.start();
        } catch (PersistenciaException e) {
            System.out.println("Error al iniciar la aplicación: " + e.getMessage());
        }
    }
}
