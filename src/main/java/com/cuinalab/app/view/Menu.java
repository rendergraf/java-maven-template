package com.cuinalab.app.view;

import com.cuinalab.app.controller.GestorCuinaLab;
import com.cuinalab.app.exception.CuinaLabException;
import com.cuinalab.app.exception.PersistenciaException;
import com.cuinalab.app.model.DiaSemana;
import com.cuinalab.app.model.Plataforma;
import java.io.IOException;

/**
 * Clase encargada del menú principal y la interacción con el usuario.
 * Muestra las opciones disponibles y procesa cada una llamando al gestor.
 */
public class Menu {

    private GestorCuinaLab gestor;
    private AskData ask;

    /**
     * Constructor del menú.
     *
     * @throws PersistenciaException si hay error al cargar los datos iniciales
     */
    public Menu() throws PersistenciaException {
        gestor = new GestorCuinaLab();
        ask = new AskData();
    }

    /**
     * Inicia el bucle principal del menú.
     *
     * @throws PersistenciaException si hay error de persistencia
     */
    public void start() throws PersistenciaException {
        int opcion;
        do {
            mostrarMenu();
            try {
                opcion = ask.askInt("");
            } catch (IOException e) {
                opcion = -1;
            }

            if (opcion < 0 || opcion > 6) {
                System.out.println("Opción incorrecta");
                continue;
            }

            try {
                switch (opcion) {
                    case 1:
                        registrarCurso();
                        break;
                    case 2:
                        registrarAlumno();
                        break;
                    case 3:
                        inscribirAlumno();
                        break;
                    case 4:
                        infoAlumno();
                        break;
                    case 5:
                        verEscuela();
                        break;
                    case 6:
                        System.out.println("Cerrando CuinaLab... ¡Buen provecho! Hasta pronto.");
                        break;
                    default:
                        break;
                }
            } catch (CuinaLabException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Error de entrada/salida.");
            }
        } while (opcion != 6);
    }

    /**
     * Muestra el menú principal con las opciones disponibles.
     */
    private void mostrarMenu() {
        System.out.println();
        System.out.println("*** CuinaLab - Escuela de Cocina ***");
        System.out.println();
        System.out.println("1. Registrar curso");
        System.out.println();
        System.out.println("2. Registrar alumno");
        System.out.println();
        System.out.println("3. Inscribir alumno a curso");
        System.out.println();
        System.out.println("4. Info alumno");
        System.out.println();
        System.out.println("5. Ver escuela");
        System.out.println();
        System.out.println("6. Salir");
        System.out.println();
        System.out.print("Indica qué quieres hacer: ");
    }

    /**
     * Opción 1: Registra un nuevo curso.
     */
    private void registrarCurso() throws CuinaLabException, IOException {
        String codigo = ask.askString("Código del curso: ");
        String nombre = ask.askString("Nombre: ");
        int capacidad = ask.askInt("Capacidad: ", "La capacidad debe estar entre 5 y 20.", 5, 20);
        double precio = ask.askDouble("Precio: ", "El precio debe ser positivo.", 0);

        String tipoCurso = ask.askString("¿Presencial u online? ");
        boolean esPresencial = tipoCurso.equalsIgnoreCase("presencial");

        String aula = null;
        boolean incluyeMaterial = false;
        DiaSemana diaSemana = null;
        Plataforma plataforma = null;
        int numSesiones = 0;

        if (esPresencial) {
            aula = ask.askString("Aula: ");
            incluyeMaterial = ask.askBoolean("¿Incluye material? (s/n) ");
            String diaStr = ask.askString("Día de la semana: ");
            try {
                diaSemana = DiaSemana.valueOf(diaStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new CuinaLabException("Día de la semana no válido.");
            }
        } else {
            String platStr = ask.askString("Plataforma (ZOOM, MEET, TEAMS): ");
            try {
                plataforma = Plataforma.valueOf(platStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new CuinaLabException("Plataforma no válida.");
            }
            numSesiones = ask.askInt("Número de sesiones: ",
                                     "Debe ser entre 1 y 12.", 1, 12);
        }

        gestor.registrarCurso(codigo, nombre, capacidad, precio,
                              esPresencial, aula, incluyeMaterial,
                              diaSemana, plataforma, numSesiones);
        System.out.println("Curso registrado.");
    }

    /**
     * Opción 2: Registra un nuevo alumno.
     */
    private void registrarAlumno() throws CuinaLabException, IOException {
        String dni = ask.askString("NIF del alumno: ");
        String nombre = ask.askString("Nombre: ");
        String apellidos = ask.askString("Apellidos: ");
        int edad = ask.askInt("Edad: ", "La edad debe estar entre 16 y 99.", 16, 99);

        gestor.registrarAlumno(dni, nombre, apellidos, edad);
        System.out.println("Alumno registrado.");
    }

    /**
     * Opción 3: Inscribe un alumno en un curso.
     */
    private void inscribirAlumno() throws CuinaLabException, IOException {
        String dni = ask.askString("NIF del alumno: ");
        String codigoCurso = ask.askString("Código del curso: ");
        gestor.inscribirAlumno(dni, codigoCurso);
        System.out.println("¡Inscripción realizada!");
    }

    /**
     * Opción 4: Muestra la información de un alumno.
     */
    private void infoAlumno() throws CuinaLabException, IOException {
        String dni = ask.askString("NIF del alumno: ");
        System.out.println(gestor.infoAlumno(dni));
    }

    /**
     * Opción 5: Muestra la información de todos los cursos.
     */
    private void verEscuela() {
        String info = gestor.verEscuela();
        if (info.isEmpty()) {
            System.out.println("No hay cursos registrados.");
        } else {
            System.out.println(info);
        }
    }
}
