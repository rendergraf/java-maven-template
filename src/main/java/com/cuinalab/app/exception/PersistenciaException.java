package com.cuinalab.app.exception;

/**
 * Excepción personalizada para errores de lectura o escritura
 * de ficheros en la capa de persistencia.
 */
public class PersistenciaException extends Exception {

    /**
     * Constructor con mensaje descriptivo.
     *
     * @param mensaje descripción del error
     */
    public PersistenciaException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor con mensaje y causa original.
     *
     * @param mensaje descripción del error
     * @param causa   excepción original que la provocó
     */
    public PersistenciaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
