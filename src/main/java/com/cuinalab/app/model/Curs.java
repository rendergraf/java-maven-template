package com.cuinalab.app.model;

import com.cuinalab.app.exception.CuinaLabException;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase abstracta que representa un curso genérico en CuinaLab.
 * Contiene los atributos comunes a todos los tipos de curso y la lista
 * de alumnos inscritos.
 */
public abstract class Curs {

    protected String codigo;
    protected String nombre;
    protected int plazasMax;
    protected double precio;

    /**
     * Map con los alumnos inscritos en el curso usando el DNI como clave.
     * Se ha elegido HashMap porque ofrece acceso directo por DNI
     * para consultar y evitar inscripciones duplicadas de forma eficiente.
     */
    protected Map<String, Alumne> alumnosInscritos;

    /**
     * Constructor del curso.
     *
     * @param codigo    código único del curso (5 caracteres)
     * @param nombre    nombre del curso
     * @param plazasMax capacidad máxima del curso
     * @param precio    precio del curso
     */
    public Curs(String codigo, String nombre, int plazasMax, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.plazasMax = plazasMax;
        this.precio = precio;
        this.alumnosInscritos = new HashMap<>();
    }

    /**
     * Devuelve el código del curso.
     *
     * @return código del curso
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Devuelve el nombre del curso.
     *
     * @return nombre del curso
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el número máximo de plazas.
     *
     * @return plazas máximas
     */
    public int getPlazasMax() {
        return plazasMax;
    }

    /**
     * Devuelve el precio del curso.
     *
     * @return precio del curso
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Devuelve el mapa de alumnos inscritos.
     *
     * @return mapa de alumnos por DNI
     */
    public Map<String, Alumne> getAlumnosInscritos() {
        return alumnosInscritos;
    }

    /**
     * Comprueba si quedan plazas libres en el curso.
     *
     * @return true si hay al menos una plaza libre
     */
    public boolean hayPlaza() {
        return alumnosInscritos.size() < plazasMax;
    }

    /**
     * Devuelve el número de plazas ocupadas actualmente.
     *
     * @return número de alumnos inscritos
     */
    public int plazasOcupadas() {
        return alumnosInscritos.size();
    }

    /**
     * Devuelve el número de plazas libres.
     *
     * @return plazas libres
     */
    public int plazasLibres() {
        return plazasMax - alumnosInscritos.size();
    }

    /**
     * Inscribe un alumno en el curso si hay plaza disponible.
     *
     * @param alumno alumno a inscribir
     * @throws CuinaLabException si el curso está completo
     */
    public void inscribir(Alumne alumno) throws CuinaLabException {
        if (!hayPlaza()) {
            throw new CuinaLabException("El curso está completo.");
        }
        alumnosInscritos.put(alumno.getDni(), alumno);
    }

    /**
     * Comprueba si un alumno ya está inscrito en este curso.
     *
     * @param dni DNI del alumno
     * @return true si ya está inscrito
     */
    public boolean tieneAlumno(String dni) {
        return alumnosInscritos.containsKey(dni);
    }

    /**
     * Devuelve el tipo de curso en formato texto.
     *
     * @return "Presencial" u "Online"
     */
    public abstract String getTipo();

    /**
     * Devuelve la información específica según el tipo de curso.
     *
     * @return información extra del curso
     */
    public abstract String obtenerInfoExtra();
}
