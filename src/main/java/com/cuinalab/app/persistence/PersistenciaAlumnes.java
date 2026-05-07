package com.cuinalab.app.persistence;

import com.cuinalab.app.model.Alumne;
import com.cuinalab.app.exception.PersistenciaException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase encargada de la persistencia de datos de CuinaLab.
 * Gestiona la lectura de alumnos desde un fichero CSV.
 */
public class PersistenciaAlumnes {

    private static final String FICHERO_ALUMNOS = "students.txt";

    /**
     * Carga los alumnos desde el fichero students.txt.
     * El formato esperado es: DNI;nombre;apellidos;edad
     *
     * Se ha elegido HashMap para almacenar los alumnos porque permite
     * acceso directo por DNI y evita duplicados de forma eficiente.
     *
     * @return mapa de alumnos cargados, vacío si el fichero no existe
     * @throws PersistenciaException si ocurre un error de lectura
     */
    public static Map<String, Alumne> carregarAlumnes() throws PersistenciaException {
        Map<String, Alumne> alumnos = new HashMap<>();
        File fichero = new File(FICHERO_ALUMNOS);

        if (!fichero.exists()) {
            return alumnos;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) {
                    continue;
                }
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    String dni = partes[0].trim();
                    String nombre = partes[1].trim();
                    String apellidos = partes[2].trim();
                    int edad = Integer.parseInt(partes[3].trim());
                    Alumne alumno = new Alumne(dni, nombre, apellidos, edad);
                    alumnos.put(dni, alumno);
                }
            }
        } catch (IOException e) {
            throw new PersistenciaException("Error al leer el fichero de alumnos.", e);
        } catch (NumberFormatException e) {
            throw new PersistenciaException("Formato de edad inválido en el fichero.", e);
        }

        return alumnos;
    }
}
