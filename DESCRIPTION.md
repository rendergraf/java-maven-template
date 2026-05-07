# EXAMEN F049CP - M0485 Programación

**Fecha:** 16/04/2026  
**Curso y Grupo:** DAM1  
**Módulo Profesional:** M0485 Programación  
**Título:** Programación Orientada a Objetos, Herencia, Excepciones, Colecciones y Ficheros

## Instrucciones generales

- Entregar un archivo `.zip` con el proyecto completo del código fuente. El archivo debe llamarse: `M0485Ex4ApellidosNombre.zip`
- Si el formato del proyecto no es Maven o no se envía el proyecto completo, la nota del examen será **0**.
- No se corregirá código que esté comentado (que no se ejecute).
- No se corregirá código con errores de sintaxis o errores de ejecución.
- No se pueden usar conceptos que no se hayan dado en clase.
- Se valorará que el nombre de las variables, métodos, packages y clases sean definitorios y hagan el código más comprensible. Es imprescindible organizar el código en clases y estas en packages.
- Si se usan bucles infinitos se invalidará la totalidad del examen.
- No se puede usar `break` (excepto en `switch`), `continue`, `exit` ni bucles infinitos. Tampoco `return` sin valor ni funciones lambda.
- Cuando el enunciado muestre ejemplos de ejecución se debe replicar el formato de entrada y salida mostrado.
- No se puede usar ningún tipo de IA ni Internet para resolver el examen.
- Se debe añadir un comentario justificando la elección de cada colección que uséis en vuestra solución.
- Es imprescindible crear y hacer uso de Excepciones propias tal como se ha enseñado en clase y gestionar correctamente todas las excepciones.

---

## Enunciado: CuinaLab - Escuela de Cocina

### Introducción

La escuela de cocina "CuinaLab" ofrece cursos de cocina de diferentes tipos y gestiona las inscripciones de sus alumnos. Hasta ahora lo hacían en papel y se perdía información. Se contrata para crear una aplicación de consola que gestione esta información de manera eficiente.

El sistema debe gestionar **Cursos** de cocina de diferentes tipos y **Alumnos** que se inscriben. La escuela pide tener los cursos ordenados por **código**.

### Modelo de datos

#### Cursos

Cada curso tiene:
- **código** (único, formato: 3 letras + 2 números, ej: PAS01)
- **nombre** del curso
- **número máximo de plazas** (entre 5 y 20)
- **precio** (número positivo con decimales)

Cuando se registra un curso no tiene alumnos inscritos.

#### Dos tipos de cursos

**Curso Presencial (`PRESENCIAL`)**:
- Aula donde se imparte (texto libre, ej: "Aula 3")
- ¿Incluye material? (Sí/No)
- Día de la semana (`LUNES`, `MARTES`, `MIÉRCOLES`, `JUEVES` o `VIERNES`)

**Curso Online (`ONLINE`)**:
- Plataforma (`ZOOM`, `MEET` o `TEAMS`)
- Número de sesiones (entre 1 y 12)

#### Alumnos

De cada alumno se registra:
- **DNI** (8 números + 1 letra)
- **nombre**
- **apellidos**
- **edad** (entre 16 y 99)

No pueden existir dos alumnos con el mismo DNI.

### Restricciones de inscripción

- **Plazas**: Si el curso ya tiene todas las plazas ocupadas, no se puede inscribir a más alumnos.
- **Edad mínima**: Los alumnos menores de 18 años solo se pueden inscribir en cursos **presenciales** (no online).
- **Duplicados**: Un alumno no se puede inscribir al mismo curso dos veces.

---

## Funcionalidades del menú

La aplicación muestra un menú repetitivo. En todos los casos se informa al usuario del resultado. La aplicación **no es case-sensitive** (mayúsculas y minúsculas son equivalentes).

*** CuinaLab - Escuela de Cocina ***

Registrar curso

Registrar alumno

Inscribir alumno a curso

Info alumno

Ver escuela

Salir
Indica qué quieres hacer:


### 1. Registrar curso

Se pide código, nombre, plazas máximas, precio, tipo de curso (`PRESENCIAL` u `ONLINE`) y los datos específicos según el tipo.

- El código debe tener **5 caracteres** (no se valida el formato interno).
- No puede existir un curso con el mismo código.
- Las plazas: entre 5 y 20.
- El precio: positivo.
- Si los datos no son correctos se vuelven a pedir.

**Ejemplos**:

Código del curso: PAS01
Nombre: Pastelería Francesa
Capacidad: 12
Precio: 149.90
¿Presencial u online? presencial
Aula: Aula 8
¿Incluye material? (s/n) s
Día de la semana: martes
Curso registrado.


### 2. Registrar alumno

Se pide DNI, nombre, apellidos y edad.

- El DNI debe tener **9 caracteres** (no se valida internamente).
- No puede existir un alumno con el mismo DNI.
- Edad entre 16 y 99.

**Ejemplo**:

NIF del alumno: 12345678a
Nombre: Laia
Apellidos: Ferrer Puig
Edad: 22
Alumno registrado.


### 3. Inscribir alumno a curso

Se pide el DNI del alumno y el código del curso. Si los datos son correctos y se cumplen las restricciones, el alumno queda inscrito.

**Ejemplos**:
- Inscripción correcta → `¡Inscripción realizada!`
- Ya inscrito → `El alumno ya está inscrito al curso.`
- No existe el alumno → `No existe ningún alumno con el nif indicado.`
- Menor de 18 años en curso online → `El alumno es menor de 18 años. No se puede inscribir a cursos online.`

### 4. Info alumno

Se muestra toda la información de un alumno y los cursos a los que está inscrito.

**Ejemplo**:

--- Información del alumno ---
Nif: 12345678A
Nombre: Laia Ferrer Puig
Edad: 22
Cursos inscritos:
PAS01 - Pastelería Francesa (Presencial) - 149.9€ - MARTES
SUS03 - Sushi para torpes (Online) - 200.5€ - 6 sesiones


Si el alumno no está inscrito a ningún curso:


### 5. Ver escuela

Muestra los datos de **todos los cursos ordenados por código**. Para cada curso:
- Código, nombre, tipo, precio y dato específico (día o sesiones)
- Plazas ocupadas / plazas totales

Al final se muestran totales:

Total cursos: X. Plazas totales ocupadas: Y. Plazas totales libres: Z.


**Ejemplo**:

COCO4 - Cocina Molecular Espacial (Online) - 320.0€ - 12 sesiones | Plazas: 1/5
PAS01 - Pastelería Francesa (Presencial) - 149.9€ - MARTES | Plazas: 2/12
SUS02 - Sushi Pro (Online) - 200.55€ - 6 sesiones | Plazas: 0/8
SUS03 - Sushi para torpes (Online) - 200.5€ - 6 sesiones | Plazas: 2/10
TAP02 - Tapas de supervivencia (Presencial) - 75.0€ - LUNES | Plazas: 2/8

Total cursos: 5. Plazas totales ocupadas: 7. Plazas totales libres: 36.


### 6. Salir

Cierra la aplicación y muestra:

Cerrando CuinaLab... ¡Buen provecho! Hasta pronto.


---

## Gestión de ficheros (persistencia)

### Carga inicial (lectura)

Al iniciar la aplicación, se comprueba si existe un archivo llamado **`students.txt`** en el directorio actual.

- Si existe → se cargan los alumnos desde el fichero.
- Si no existe → la aplicación comienza vacía, sin dar error.

> **Nota:** En este examen **no es necesario escribir nada en ficheros**, solo leer el fichero de alumnos si existe.

---

## Mensajes generales

- Si el resultado de una consulta está vacío, se debe mostrar un mensaje indicándolo.
- Cada operación del menú debe dar un mensaje detallado de éxito o un mensaje de error conciso.
- Se debe respetar el formato de salida de los ejemplos.
- Si el usuario elige una opción fuera del rango del menú: `"Opción incorrecta"`.

---

## Rúbrica de evaluación

| Concepto | Peso |
|----------|------|
| Modelo (clases, herencia, encapsulación) | 15% |
| Uso de métodos, colecciones, packages y reutilización | 15% |
| Excepciones propias y gestión | 10% |
| Leer alumnos desde fichero | 10% |
| Alta curso | 10% |
| Alta alumno | 5% |
| Inscribir alumno a curso | 15% |
| Info alumno | 10% |
| Info escuela | 10% |

**Otros criterios** (RA1 a RA7) ponderados hasta el 100% incluyendo indentación, nomenclatura, estructura, herencia, documentación, etc.

---

## Recomendaciones finales

- Recuerda **justificar la elección de cada colección** en un comentario.
- Crea **excepciones propias** y gestiónalas correctamente.
- Organiza el código en **packages** y **clases** bien definidas.
- No uses `break` (excepto en switch), `continue`, `exit`, `return` sin valor, ni bucles infinitos.
- No se permite el uso de lambdas ni funciones no vistas en clase.
- Comprueba que tu código **compila y se ejecuta** sin errores antes de entregarlo.

---
