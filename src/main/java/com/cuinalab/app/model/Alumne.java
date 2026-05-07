package com.cuinalab.app.model;

import com.cuinalab.app.exception.CuinaLabException;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa un alumno de CuinaLab.
 * Almacena sus datos personales y los cursos en los que está inscrito.
 */
public class Alumne {

    private String dni;
    private String nombre;
    private String apellidos;
    private int edad;

    /**
     * Map con los cursos en los que está inscrito el alumno usando el
     * código del curso como clave. Se ha elegido HashMap porque permite
     * consultar y evitar inscripciones duplicadas de forma eficiente.
     */
    private Map<String, Curs> cursosInscritos;

    /**
     * Constructor del alumno.
     *
     * @param dni       DNI del alumno (8 números + 1 letra)
     * @param nombre    nombre del alumno
     * @param apellidos apellidos del alumno
     * @param edad      edad del alumno
     */
    public Alumne(String dni, String nombre, String apellidos, int edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.cursosInscritos = new HashMap<>();
    }

    /**
     * Devuelve el DNI del alumno.
     *
     * @return DNI
     */
    public String getDni() {
        return dni;
    }

    /**
     * Devuelve el nombre del alumno.
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve los apellidos del alumno.
     *
     * @return apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Devuelve la edad del alumno.
     *
     * @return edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Devuelve el mapa de cursos inscritos.
     *
     * @return cursos por código
     */
    public Map<String, Curs> getCursosInscritos() {
        return cursosInscritos;
    }

    /**
     * Inscribe al alumno en un curso.
     *
     * @param curso curso al que inscribirse
     * @throws CuinaLabException si ya está inscrito en este curso
     */
    public void inscribirCurso(Curs curso) throws CuinaLabException {
        if (cursosInscritos.containsKey(curso.getCodigo())) {
            throw new CuinaLabException("El alumno ya está inscrito al curso.");
        }
        cursosInscritos.put(curso.getCodigo(), curso);
    }

    /**
     * Comprueba si el alumno es menor de edad.
     *
     * @return true si es menor de 18 años
     */
    public boolean esMenorEdad() {
        return edad < 18;
    }
}
