# TODO — CuinaLab

## Paso 1 — Actualizar `pom.xml`
- [ ] Cambiar `groupId` a `com.cuinalab`
- [ ] Cambiar `artifactId` a `CuinaLab`
- [ ] Cambiar `exec.mainClass` a `com.cuinalab.app.CuinaLab`

## Paso 2 — Renombrar package base
- [ ] Renombrar `com/template/app` → `com/cuinalab/app`
- [ ] Renombrar clase `App` → `CuinaLab`
- [ ] Eliminar directorios `com/template` antiguos

## Paso 3 — Capa `model/`
- [ ] Crear `model/DiaSemana.java` (enum)
- [ ] Crear `model/Curs.java` (abstracta)
- [ ] Crear `model/CursPresencial.java` (hereda de Curs)
- [ ] Crear `model/CursOnline.java` (hereda de Curs)
- [ ] Crear `model/Alumne.java`
- [ ] Añadir comentarios justificando cada colección

## Paso 4 — Capa `exception/`
- [ ] Crear `exception/CuinaLabException.java`
- [ ] Crear `exception/PersistenciaException.java`

## Paso 5 — Capa `persistence/`
- [ ] Crear `persistence/PersistenciaAlumnes.java` (solo lectura de `students.txt`)

## Paso 6 — Capa `controller/`
- [ ] Crear `controller/GestorCuinaLab.java`
- [ ] Implementar `registrarCurso()`
- [ ] Implementar `registrarAlumno()`
- [ ] Implementar `inscribirAlumno()`
- [ ] Implementar `infoAlumno()`
- [ ] Implementar `verEscuela()` (cursos ordenados por código)

## Paso 7 — Capa `view/`
- [ ] Crear `view/AskData.java`
- [ ] Crear `view/Menu.java`
- [ ] Replicar formato exacto del menú
- [ ] Replicar formato exacto de salida de cada opción

## Paso 8 — Clase principal
- [ ] Crear `CuinaLab.java` con `main()`

## Paso 9 — Fichero de datos
- [ ] Crear `students.txt` con alumnos de ejemplo

## Paso 10 — Verificaciones finales
- [ ] Compilar sin errores (`mvn clean compile`)
- [ ] Ejecutar sin errores (`mvn exec:java`)
- [ ] Verificar que no hay `break`/`continue`/lambdas/bucles infinitos
- [ ] Verificar que no hay código comentado
- [ ] Verificar formato I/O coincide con ejemplos del enunciado
- [ ] Verificar comentarios en todas las colecciones
