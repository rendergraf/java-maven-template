# TODO — CuinaLab

## Paso 1 — Actualizar `pom.xml`
- [x] Cambiar `groupId` a `com.cuinalab`
- [x] Cambiar `artifactId` a `CuinaLab`
- [x] Cambiar `exec.mainClass` a `com.cuinalab.app.CuinaLab`

## Paso 2 — Renombrar package base
- [x] Renombrar `com/template/app` → `com/cuinalab/app`
- [x] Renombrar clase `App` → `CuinaLab`
- [x] Eliminar directorios `com/template` antiguos

## Paso 3 — Capa `model/`
- [x] Crear `model/DiaSemana.java` (enum)
- [x] Crear `model/Curs.java` (abstracta)
- [x] Crear `model/CursPresencial.java` (hereda de Curs)
- [x] Crear `model/CursOnline.java` (hereda de Curs)
- [x] Crear `model/Alumne.java`
- [x] Crear `model/Plataforma.java` (enum)
- [x] Añadir comentarios justificando cada colección

## Paso 4 — Capa `exception/`
- [x] Crear `exception/CuinaLabException.java`
- [x] Crear `exception/PersistenciaException.java`

## Paso 5 — Capa `persistence/`
- [x] Crear `persistence/PersistenciaAlumnes.java` (solo lectura de `students.txt`)

## Paso 6 — Capa `controller/`
- [x] Crear `controller/GestorCuinaLab.java`
- [x] Implementar `registrarCurso()`
- [x] Implementar `registrarAlumno()`
- [x] Implementar `inscribirAlumno()`
- [x] Implementar `infoAlumno()`
- [x] Implementar `verEscuela()` (cursos ordenados por código con TreeMap)

## Paso 7 — Capa `view/`
- [x] Crear `view/AskData.java`
- [x] Crear `view/Menu.java`
- [x] Replicar formato exacto del menú
- [x] Replicar formato exacto de salida de cada opción

## Paso 8 — Clase principal
- [x] Crear `CuinaLab.java` con `main()`

## Paso 9 — Fichero de datos
- [x] Crear `students.txt` con alumnos de ejemplo

## Paso 10 — Verificaciones finales
- [x] Compilar sin errores (`mvn clean compile`) — 13 clases, BUILD SUCCESS
- [x] Ejecutar sin errores (`mvn exec:java`) — menú y salida funcionan
- [x] Verificar que no hay `break`/`continue`/lambdas/bucles infinitos
- [x] Verificar que no hay código comentado
- [x] Verificar formato I/O coincide con ejemplos del enunciado
- [x] Verificar comentarios en todas las colecciones
