package com.cuinalab.app.exception;

/**
 * Excepción personalizada para errores de lógica de negocio
 * en la aplicación CuinaLab.
 */
public class CuinaLabException extends Exception {

    /**
     * Constructor con mensaje descriptivo.
     *
     * @param mensaje descripción del error
     */
    public CuinaLabException(String mensaje) {
        super(mensaje);
    }
}
