package com.cuinalab.app.model;

import java.time.LocalDate;

/**
 * Representa un curso presencial en CuinaLab.
 * Incluye aula, material incluido y día de la semana.
 */
public class CursPresencial extends Curs {

    private String aula;
    private boolean incluyeMaterial;
    private DiaSemana diaSemana;

    /**
     * Constructor del curso presencial.
     *
     * @param codigo          código del curso
     * @param nombre          nombre del curso
     * @param plazasMax       capacidad máxima
     * @param precio          precio del curso
     * @param aula            aula donde se imparte
     * @param incluyeMaterial true si incluye material
     * @param diaSemana       día de la semana
     */
    public CursPresencial(String codigo, String nombre, int plazasMax,
                          double precio, String aula, boolean incluyeMaterial,
                          DiaSemana diaSemana) {
        super(codigo, nombre, plazasMax, precio);
        this.aula = aula;
        this.incluyeMaterial = incluyeMaterial;
        this.diaSemana = diaSemana;
    }

    /**
     * Devuelve el aula donde se imparte el curso.
     *
     * @return aula
     */
    public String getAula() {
        return aula;
    }

    /**
     * Indica si el curso incluye material.
     *
     * @return true si incluye material
     */
    public boolean isIncluyeMaterial() {
        return incluyeMaterial;
    }

    /**
     * Devuelve el día de la semana del curso.
     *
     * @return día de la semana
     */
    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    @Override
    public String getTipo() {
        return "Presencial";
    }

    @Override
    public String obtenerInfoExtra() {
        return diaSemana.name();
    }
}
