package com.cuinalab.app.model;

/**
 * Representa un curso online en CuinaLab.
 * Incluye plataforma y número de sesiones.
 */
public class CursOnline extends Curs {

    private Plataforma plataforma;
    private int numSesiones;

    /**
     * Constructor del curso online.
     *
     * @param codigo      código del curso
     * @param nombre      nombre del curso
     * @param plazasMax   capacidad máxima
     * @param precio      precio del curso
     * @param plataforma  plataforma virtual (ZOOM, MEET, TEAMS)
     * @param numSesiones número de sesiones
     */
    public CursOnline(String codigo, String nombre, int plazasMax,
                      double precio, Plataforma plataforma, int numSesiones) {
        super(codigo, nombre, plazasMax, precio);
        this.plataforma = plataforma;
        this.numSesiones = numSesiones;
    }

    /**
     * Devuelve la plataforma del curso.
     *
     * @return plataforma
     */
    public Plataforma getPlataforma() {
        return plataforma;
    }

    /**
     * Devuelve el número de sesiones del curso.
     *
     * @return número de sesiones
     */
    public int getNumSesiones() {
        return numSesiones;
    }

    @Override
    public String getTipo() {
        return "Online";
    }

    @Override
    public String obtenerInfoExtra() {
        return numSesiones + " sesiones";
    }
}
