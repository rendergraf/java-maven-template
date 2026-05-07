package com.cuinalab.app.controller;

import com.cuinalab.app.model.Alumne;
import com.cuinalab.app.model.Curs;
import com.cuinalab.app.model.CursOnline;
import com.cuinalab.app.model.CursPresencial;
import com.cuinalab.app.model.DiaSemana;
import com.cuinalab.app.model.Plataforma;
import com.cuinalab.app.persistence.PersistenciaAlumnes;
import com.cuinalab.app.exception.CuinaLabException;
import com.cuinalab.app.exception.PersistenciaException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Clase que gestiona toda la lógica de negocio de CuinaLab.
 * Administra alumnos, cursos e inscripciones.
 */
public class GestorCuinaLab {

    /**
     * Mapa de cursos usando el código como clave.
     * Se ha elegido TreeMap porque mantiene los cursos ordenados
     * alfabéticamente por código, requisito del enunciado.
     */
    private TreeMap<String, Curs> cursos;

    /**
     * Mapa de alumnos usando el DNI como clave.
     * Se ha elegido HashMap porque permite acceso directo por DNI
     * y detección eficiente de duplicados.
     */
    private Map<String, Alumne> alumnos;

    /**
     * Constructor del gestor.
     * Carga los alumnos desde el fichero students.txt si existe.
     *
     * @throws PersistenciaException si hay error al cargar los datos
     */
    public GestorCuinaLab() throws PersistenciaException {
        this.cursos = new TreeMap<>();
        this.alumnos = PersistenciaAlumnes.carregarAlumnes();
    }

    /**
     * Registra un nuevo curso en el sistema.
     *
     * @param codigo    código del curso
     * @param nombre    nombre del curso
     * @param plazasMax capacidad máxima
     * @param precio    precio del curso
     * @param esPresencial true si es presencial, false si online
     * @param aula      aula (solo presencial, null si online)
     * @param incluyeMaterial  incluye material (solo presencial)
     * @param diaSemana día de la semana (solo presencial, null si online)
     * @param plataforma plataforma (solo online, null si presencial)
     * @param numSesiones número de sesiones (solo online, 0 si presencial)
     * @throws CuinaLabException si el código ya existe o los datos no son válidos
     */
    public void registrarCurso(String codigo, String nombre, int plazasMax,
                               double precio, boolean esPresencial,
                               String aula, boolean incluyeMaterial,
                               DiaSemana diaSemana, Plataforma plataforma,
                               int numSesiones) throws CuinaLabException {

        if (cursos.containsKey(codigo)) {
            throw new CuinaLabException("Ya existe un curso con ese código.");
        }

        Curs curso;
        if (esPresencial) {
            curso = new CursPresencial(codigo, nombre, plazasMax, precio,
                                       aula, incluyeMaterial, diaSemana);
        } else {
            curso = new CursOnline(codigo, nombre, plazasMax, precio,
                                   plataforma, numSesiones);
        }

        cursos.put(codigo, curso);
    }

    /**
     * Registra un nuevo alumno en el sistema.
     *
     * @param dni       DNI del alumno
     * @param nombre    nombre del alumno
     * @param apellidos apellidos del alumno
     * @param edad      edad del alumno
     * @throws CuinaLabException si el DNI ya existe o los datos no son válidos
     */
    public void registrarAlumno(String dni, String nombre, String apellidos,
                                int edad) throws CuinaLabException {

        if (alumnos.containsKey(dni)) {
            throw new CuinaLabException("Ya existe un alumno con ese DNI.");
        }

        Alumne alumno = new Alumne(dni, nombre, apellidos, edad);
        alumnos.put(dni, alumno);
    }

    /**
     * Inscribe un alumno en un curso.
     *
     * @param dniAlumno  DNI del alumno
     * @param codigoCurso código del curso
     * @throws CuinaLabException si el alumno o curso no existen,
     *                           el curso está completo, el alumno ya está
     *                           inscrito o un menor intenta inscribirse
     *                           a un curso online
     */
    public void inscribirAlumno(String dniAlumno, String codigoCurso)
            throws CuinaLabException {

        Alumne alumno = alumnos.get(dniAlumno);
        if (alumno == null) {
            throw new CuinaLabException("No existe ningún alumno con el nif indicado.");
        }

        Curs curso = cursos.get(codigoCurso);
        if (curso == null) {
            throw new CuinaLabException("No existe ningún curso con ese código.");
        }

        if (curso.tieneAlumno(dniAlumno)) {
            throw new CuinaLabException("El alumno ya está inscrito al curso.");
        }

        if (!curso.hayPlaza()) {
            throw new CuinaLabException("El curso está completo.");
        }

        if (alumno.esMenorEdad() && curso instanceof CursOnline) {
            throw new CuinaLabException(
                "El alumno es menor de 18 años. No se puede inscribir a cursos online.");
        }

        curso.inscribir(alumno);
        alumno.inscribirCurso(curso);
    }

    /**
     * Devuelve la información completa de un alumno.
     *
     * @param dni DNI del alumno
     * @return información formateada del alumno
     * @throws CuinaLabException si el alumno no existe
     */
    public String infoAlumno(String dni) throws CuinaLabException {
        Alumne alumno = alumnos.get(dni);
        if (alumno == null) {
            throw new CuinaLabException("No existe ningún alumno con el nif indicado.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("--- Información del alumno ---\n");
        sb.append("Nif: ").append(alumno.getDni().toUpperCase()).append("\n");
        sb.append("Nombre: ").append(alumno.getNombre())
          .append(" ").append(alumno.getApellidos()).append("\n");
        sb.append("Edad: ").append(alumno.getEdad()).append("\n");
        sb.append("Cursos inscritos:\n");

        if (alumno.getCursosInscritos().isEmpty()) {
            sb.append("El alumno no está inscrito en ningún curso.\n");
        } else {
            for (Curs curso : alumno.getCursosInscritos().values()) {
                sb.append(formatearCursoInfo(curso)).append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * Devuelve la información de todos los cursos ordenados por código.
     *
     * @return información formateada de la escuela
     */
    public String verEscuela() {
        StringBuilder sb = new StringBuilder();
        int totalOcupadas = 0;
        int totalPlazas = 0;

        for (Curs curso : cursos.values()) {
            sb.append(formatearCursoEscuela(curso)).append("\n");
            totalOcupadas += curso.plazasOcupadas();
            totalPlazas += curso.getPlazasMax();
        }

        int totalLibres = totalPlazas - totalOcupadas;
        sb.append("Total cursos: ").append(cursos.size())
          .append(". Plazas totales ocupadas: ").append(totalOcupadas)
          .append(". Plazas totales libres: ").append(totalLibres)
          .append(".");

        return sb.toString();
    }

    /**
     * Verifica si existe un alumno con el DNI indicado.
     *
     * @param dni DNI a comprobar
     * @return true si existe
     */
    public boolean existeAlumno(String dni) {
        return alumnos.containsKey(dni);
    }

    /**
     * Verifica si existe un curso con el código indicado.
     *
     * @param codigo código a comprobar
     * @return true si existe
     */
    public boolean existeCurso(String codigo) {
        return cursos.containsKey(codigo);
    }

    /**
     * Formatea un curso para la información del alumno.
     * Ejemplo: PAS01 - Pastelería Francesa (Presencial) - 149.9€ - MARTES
     */
    private String formatearCursoInfo(Curs curso) {
        return curso.getCodigo() + " - " + curso.getNombre()
               + " (" + curso.getTipo() + ") - " + curso.getPrecio() + "€ - "
               + curso.obtenerInfoExtra();
    }

    /**
     * Formatea un curso para la vista de escuela.
     * Ejemplo: PAS01 - Pastelería Francesa (Presencial) - 149.9€ - MARTES | Plazas: 2/12
     */
    private String formatearCursoEscuela(Curs curso) {
        return curso.getCodigo() + " - " + curso.getNombre()
               + " (" + curso.getTipo() + ") - " + curso.getPrecio() + "€ - "
               + curso.obtenerInfoExtra()
               + " | Plazas: " + curso.plazasOcupadas() + "/" + curso.getPlazasMax();
    }
}
