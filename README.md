# Java Maven Template — MVC + Persistencia CSV

Template genérico de proyecto Java con Maven, arquitectura MVC,
excepciones propias, persistencia en CSV y validaciones comunes.

Incluye un proyecto de ejemplo completo: **CuinaLab - Escuela de Cocina**
(un sistema de gestión de cursos y alumnos).

---

## Diagrama de arquitectura del sistema

```mermaid
graph TB
    subgraph View["Capa view"]
        Menu["Menu.java"]
        AskData["AskData.java"]
    end

    subgraph Controller["Capa controller"]
        Gestor["GestorCuinaLab.java"]
    end

    subgraph Model["Capa model"]
        Curs["Curs.java<br/>(abstracta)"]
        Presencial["CursPresencial.java"]
        Online["CursOnline.java"]
        Alumne["Alumne.java"]
        DiaSemana["DiaSemana.java<br/>(enum)"]
        Plataforma["Plataforma.java<br/>(enum)"]
    end

    subgraph Exception["Capa exception"]
        CuinaLabEx["CuinaLabException.java"]
        PersistenciaEx["PersistenciaException.java"]
    end

    subgraph Persistence["Capa persistence"]
        Persistencia["PersistenciaAlumnes.java"]
    end

    subgraph Data["Fichero externo"]
        Students["students.txt"]
    end

    User((Usuario)) -->|entrada| AskData
    User -->|selecciona opción| Menu
    Menu -->|1-6| Gestor
    Gestor -->|lanza| CuinaLabEx
    Gestor -->|lanza| PersistenciaEx
    Gestor -->|crea/consulta| Curs
    Gestor -->|crea/consulta| Alumne
    Curs -.->|hereda| Presencial
    Curs -.->|hereda| Online
    Presencial -->|usa| DiaSemana
    Online -->|usa| Plataforma
    Gestor -->|guarda/carga| Persistencia
    Persistencia -->|lee| Students
```

## Diagrama de flujo del menú

```mermaid
flowchart TD
    Inicio([Inicio app]) --> Cargar["Cargar alumnos<br/>desde students.txt"]
    Cargar --> Menu{"Mostrar menú"}
    Menu -->|1| RegCurso["Registrar curso<br/>- Código, nombre, plazas, precio<br/>- Presencial (aula, material, día)<br/>- Online (plataforma, sesiones)"]
    Menu -->|2| RegAlumno["Registrar alumno<br/>- DNI, nombre, apellidos, edad"]
    Menu -->|3| Insc["Inscribir alumno a curso<br/>- Validar DNI existe<br/>- Validar curso existe<br/>- Validar edad mínima<br/>- Validar plazas disponibles<br/>- Evitar duplicados"]
    Menu -->|4| Info["Info alumno<br/>- Datos personales<br/>- Cursos donde está inscrito"]
    Menu -->|5| Escuela["Ver escuela<br/>- Todos los cursos ordenados<br/>- Plazas ocupadas/libres<br/>- Totales"]
    Menu -->|6| Salir["Salir<br/>Mensaje de despedida"]

    RegCurso -->|ok| OkCurso["Curso registrado"]
    RegCurso -->|error| Error["Mensaje de error"]
    RegAlumno -->|ok| OkAlumno["Alumno registrado"]
    RegAlumno -->|error| Error
    Insc -->|ok| OkInsc["¡Inscripción realizada!"]
    Insc -->|error| Error
    Info -->|ok| OkInfo["Datos del alumno"]
    Info -->|error| Error
    Escuela --> OkEscuela["Listado completo"]
    Salir --> Fin([Fin app])

    OkCurso --> Menu
    OkAlumno --> Menu
    OkInsc --> Menu
    OkInfo --> Menu
    OkEscuela --> Menu
    Error --> Menu
```

---

## Estructura del proyecto

```
src/main/java/com/cuinalab/app/
├── CuinaLab.java              → Clase principal (main)
├── controller/
│   └── GestorCuinaLab.java    → Lógica de negocio
├── exception/
│   ├── CuinaLabException.java      → Errores de lógica
│   └── PersistenciaException.java  → Errores de ficheros
├── model/
│   ├── Alumne.java            → Entidad alumno
│   ├── Curs.java              → Clase abstracta curso
│   ├── CursOnline.java        → Curso online (hereda)
│   ├── CursPresencial.java    → Curso presencial (hereda)
│   ├── DiaSemana.java         → Enum LUNES..VIERNES
│   └── Plataforma.java        → Enum ZOOM, MEET, TEAMS
├── persistence/
│   └── PersistenciaAlumnes.java → Lectura CSV
└── view/
    ├── AskData.java           → Entrada validada por consola
    └── Menu.java              → Menú interactivo
src/main/resources/            → Recursos adicionales
src/test/java/                 → Tests unitarios
students.txt                   → Datos de alumnos (CSV)
```

### Descripción de cada capa

| Capa | Contenido |
|---|---|
| `model/` | Clases del dominio: `Curs` (abstracta), `CursPresencial`, `CursOnline`, `Alumne`, enums `DiaSemana` y `Plataforma`. |
| `controller/` | `GestorCuinaLab` orquesta la lógica de negocio: alta de cursos/alumnos, inscripciones, consultas. |
| `view/` | `Menu` con bucle `do-while` y 6 opciones. `AskData` valida entrada de enteros, decimales, cadenas y booleanos. |
| `persistence/` | `PersistenciaAlumnes` lee `students.txt` en formato CSV al arrancar (solo lectura). |
| `exception/` | `CuinaLabException` para errores de negocio. `PersistenciaException` para errores de E/S. |

---

## Funcionalidades

La aplicación ofrece un menú con 6 opciones:

1. **Registrar curso** — crea un curso (presencial u online) con validaciones de código único, capacidad 5-20, precio positivo
2. **Registrar alumno** — da de alta un alumno con DNI único, nombre, apellidos y edad 16-99
3. **Inscribir alumno a curso** — valida existencia, plazas libres, edad mínima (menores 18 solo presencial), sin duplicados
4. **Info alumno** — muestra datos personales y cursos inscritos
5. **Ver escuela** — lista todos los cursos ordenados por código con plazas ocupadas/libres y totales
6. **Salir** — cierra la aplicación

---

## Cómo adaptarlo a un proyecto nuevo

### 1. Clonar o copiar

```bash
git clone <repo> mi-nuevo-proyecto
cd mi-nuevo-proyecto
```

### 2. Renombrar el package base

```bash
# Ejemplo: cambiar de com.cuinalab.app a com.miclub.gestion
mkdir -p src/main/java/com/miclub/gestion
mv src/main/java/com/cuinalab/app/controller src/main/java/com/miclub/gestion/
mv src/main/java/com/cuinalab/app/exception src/main/java/com/miclub/gestion/
mv src/main/java/com/cuinalab/app/model src/main/java/com/miclub/gestion/
mv src/main/java/com/cuinalab/app/persistence src/main/java/com/miclub/gestion/
mv src/main/java/com/cuinalab/app/view src/main/java/com/miclub/gestion/
mv src/main/java/com/cuinalab/app/CuinaLab.java src/main/java/com/miclub/gestion/
rm -rf src/main/java/com/cuinalab

# Lo mismo para test
mkdir -p src/test/java/com/miclub/gestion
mv src/test/java/com/cuinalab/app/* src/test/java/com/miclub/gestion/
rm -rf src/test/java/com/cuinalab
```

### 3. Actualizar `pom.xml`

```xml
<groupId>com.miclub</groupId>
<artifactId>gestion-club</artifactId>
<exec.mainClass>com.miclub.gestion.Main</exec.mainClass>
```

### 4. Renombrar la clase principal

```bash
mv src/main/java/com/miclub/gestion/CuinaLab.java src/main/java/com/miclub/gestion/Main.java
```

Y ajustar el nombre de clase dentro del fichero.

### 5. (Opcional) Renombrar el directorio raíz

```bash
cd ..
mv java-maven-template mi-nuevo-proyecto
cd mi-nuevo-proyecto
```

---

## Convenciones del proyecto

| Aspecto | Convención |
|---|---|
| **Idioma** | Español, catalán o inglés, pero **consistente** en todo el código |
| **Nombres** | Descriptivos, camelCase (variables/métodos) y PascalCase (clases) |
| **Colecciones** | `HashMap` para búsquedas por clave, `TreeMap` para ordenación, `ArrayList` para listas. Cada uso justificado con comentario. |
| **Excepciones** | Propias, extendiendo `Exception`, una por ámbito de error |
| **Persistencia** | CSV con `BufferedReader`/`BufferedWriter` y captura de `IOException` envuelta en `PersistenciaException` |
| **Herencia** | Clase abstracta base con hijas concretas que sobrescriben métodos abstractos |
| **Bucles** | Solo `do-while` y `for`. Prohibido: `break` (excepto switch), `continue`, `exit`, bucles infinitos |
| **Lambdas** | No permitidas (no vistas en clase) |

---

## Compilar y ejecutar

### Requisitos

- **JDK 21+** — verificar con `java -version`
- **Maven 3.8+** — verificar con `mvn --version`

### Compilar

```bash
mvn clean compile
```

Si todo está correcto, muestra `BUILD SUCCESS` y genera los `.class` en `target/`.

### Ejecutar (interactivo)

```bash
mvn exec:java
```

Se muestra el menú principal para interactuar por consola:

```
*** CuinaLab - Escuela de Cocina ***

1. Registrar curso

2. Registrar alumno

3. Inscribir alumno a curso

4. Info alumno

5. Ver escuela

6. Salir

Indica qué quieres hacer:
```

### Ejecutar (prueba automatizada)

Para verificar que el programa arranca y responde sin errores:

```bash
printf "5\n6\n" | mvn -q exec:java
```

Esto selecciona "Ver escuela" (sin cursos registrados) y luego "Salir".

### Compilar y ejecutar en un solo paso

```bash
mvn clean compile exec:java
```
