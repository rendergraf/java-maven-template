# Plan de desarrollo — CuinaLab

Basado en `DESCRIPTION.md` (enunciado del examen) y `SKILL.md` (normativa de corrección).
Se aprovecha la estructura base del template Maven + MVC.

---

## 1. Actualizar `pom.xml`

Cambiar `groupId`, `artifactId` y `exec.mainClass`:

```xml
<groupId>com.cuinalab</groupId>
<artifactId>CuinaLab</artifactId>
<exec.mainClass>com.cuinalab.app.CuinaLab</exec.mainClass>
```

---

## 2. Renombrar el package base

```
com.template.app → com.cuinalab.app
```

Renombrar clase principal de `App` a `CuinaLab`.

---

## 3. Capa `model/` — 5 clases

| Fichero | Tipo | Descripción |
|---|---|---|
| `Curs.java` | `abstract class` | Atributos comunes: `codigo` (String), `nombre`, `plazasMax` (int), `precio` (double). Atributo `alumnosInscritos: Map<String, Alumne>` (justificar HashMap). Método abstracto `obtenerInfoExtra()`. Métodos: `inscribir()`, `plazasOcupadas()`, `hayPlaza()`, `getCodigo()`, etc. |
| `CursPresencial.java` | `extends Curs` | Atributos: `aula`, `incluyeMaterial` (boolean), `diaSemana` (enum DiaSemana). Implementa `obtenerInfoExtra()` → devuelve día. |
| `CursOnline.java` | `extends Curs` | Atributos: `plataforma` (enum Plataforma: ZOOM, MEET, TEAMS), `numSesiones` (int 1-12). Implementa `obtenerInfoExtra()` → devuelve plataforma y nº sesiones. |
| `Alumne.java` | `class` | Atributos: `dni`, `nombre`, `apellidos`, `edad`, `cursosInscritos: Map<String, Curs>` (justificar HashMap para evitar duplicados por código). Métodos: `inscribirCurso()`, `getCursosInscritos()`, getters. |
| `DiaSemana.java` | `enum` | `LUNES, MARTES, MIERCOLES, JUEVES, VIERNES` |

**Regla SKILL**: añadir comentario en cada declaración de colección justificando por qué se eligió ese tipo.

---

## 4. Capa `exception/` — 2 clases

| Fichero | Descripción |
|---|---|
| `CuinaLabException.java` | `extends Exception`. Excepción general de lógica de negocio. |
| `PersistenciaException.java` | `extends Exception`. Excepción para errores de lectura de fichero. |

---

## 5. Capa `persistence/` — 1 clase

| Fichero | Descripción |
|---|---|
| `PersistenciaAlumnes.java` | Solo lectura. Método `carregarAlumnes(): Map<String, Alumne>` que lee `students.txt`. Formato CSV: `DNI;nombre;apellidos;edad`. Si el fichero no existe, devuelve mapa vacío. Captura `IOException` y la envuelve en `PersistenciaException`. |

---

## 6. Capa `controller/` — 1 clase

| Fichero | Descripción |
|---|---|
| `GestorCuinaLab.java` | Tiene `Map<String, Curs>` para cursos (key = código) y `Map<String, Alumne>` para alumnos (key = DNI). Justificar colecciones con comentarios. Métodos: `registrarCurso()`, `registrarAlumno()`, `inscribirAlumno()`, `infoAlumno()`, `verEscuela()`. |

**Reglas de negocio a implementar**:

- `registrarCurso`: validar código único (5 chars), plazas (5-20), precio > 0, datos específicos según tipo.
- `registrarAlumno`: validar DNI único (9 chars), edad (16-99).
- `inscribirAlumno`: validar DNI existe, curso existe, no duplicado, plazas disponibles, edad mínima (menores 18 solo presencial).
- `infoAlumno`: mostrar datos del alumno + cursos inscritos con formato exacto del ejemplo.
- `verEscuela`: mostrar cursos **ordenados por código** (`TreeMap` o `ArrayList` ordenado con `Collections.sort()`), con formato exacto del ejemplo y totales.

---

## 7. Capa `view/` — 2 clases

| Fichero | Descripción |
|---|---|
| `AskData.java` | Métodos de lectura con validación: `askString`, `askInt(min, max)`, `askDouble(min)`, `askBoolean(s/n)`. |
| `Menu.java` | Bucle `do-while` (sin `break` ni `continue`). Switch con casos 1-6. En cada opción llama al `GestorCuinaLab` correspondiente y captura `CuinaLabException` y `PersistenciaException`. |

**Menú exacto** (replicar formato):

```
*** CuinaLab - Escuela de Cocina ***

Registrar curso

Registrar alumno

Inscribir alumno a curso

Info alumno

Ver escuela

Salir
Indica qué quieres hacer:
```

---

## 8. Clase principal

| Fichero | Descripción |
|---|---|
| `CuinaLab.java` | `main()`: instancia `Menu` y llama a `start()`. Captura `PersistenciaException` inicial. Sin `throws` en main. |

---

## 9. Fichero de datos

`students.txt` en la raíz del proyecto (no versionado en git). Formato CSV de ejemplo:

```
12345678A;Laia;Ferrer Puig;22
87654321B;Marc;Vila Casas;17
```

---

## 10. Restricciones SKILL a verificar en cada paso

- Sin `break` (excepto switch), `continue`, `exit`, bucles infinitos, `return` sin valor, lambdas
- Sin código comentado
- Sin errores de sintaxis/ejecución
- Nombres definitorios
- Organización en packages
- Formato I/O exacto
- Comentarios justificando colecciones

---

## Resumen de ficheros a crear (10 clases + 2 enums)

```
src/main/java/com/cuinalab/app/
├── CuinaLab.java
├── controller/
│   └── GestorCuinaLab.java
├── exception/
│   ├── CuinaLabException.java
│   └── PersistenciaException.java
├── model/
│   ├── Alumne.java
│   ├── Curs.java              (abstracta)
│   ├── CursOnline.java
│   ├── CursPresencial.java
│   └── DiaSemana.java          (enum)
├── persistence/
│   └── PersistenciaAlumnes.java
└── view/
    ├── AskData.java
    └── Menu.java
```

---

## Orden de implementación recomendado

1. `pom.xml` + renombrar package
2. `model/` (enum → Curs → CursPresencial → CursOnline → Alumne)
3. `exception/` (CuinaLabException → PersistenciaException)
4. `persistence/` (PersistenciaAlumnes)
5. `controller/` (GestorCuinaLab)
6. `view/` (AskData → Menu)
7. `CuinaLab.java` (main)
8. Test de compilación y ejecución (`mvn clean compile exec:java`)
