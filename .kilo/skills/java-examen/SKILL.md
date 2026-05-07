---
name: java-examen
description: |
    Normativa de examen de Java. Se activa cuando se detectan palabras clave
    como Java, examen, programación, tarea, ejercicio, práctica, o similar
    en un contexto académico o de evaluación.

    Se usa cuando el usuario: resuelve un ejercicio de examen de Java,
    pide ayuda con código Java para una entrega evaluable, desarrolla
    un proyecto Maven en Java, necesita implementar excepciones propias,
    o pregunta sobre colecciones en Java.
---

# Skill: Java Examen — Normativa de corrección

Este skill define las reglas estrictas que debe cumplir todo código Java
entregado como examen. El asistente DEBE asegurarse de que el código
generado o revisado cumpla **todas** las siguientes normas.

## Normas obligatorias

### 1. Formato de entrega
- El proyecto debe estar en formato **Maven** o debe enviarse el proyecto completo.
- Si no se cumple, la nota del examen será **0**.

### 2. Código ejecutable
- **No se corregirá código que esté comentado** (todo el código debe ejecutarse).
- **No se corregirá código con errores de sintaxis o errores de ejecución**.

### 3. Contenido permitido
- Solo se pueden utilizar **conceptos impartidos en clase**.
- **No se pueden utilizar**: break (excepto en switch), continue, exit, bucles infinitos, return sin valor, funciones lambda.
- Si se usan bucles infinitos, se **invalidará la totalidad del examen**.

### 4. Estilo y organización
- Los nombres de variables, métodos, packages y clases deben ser **definitorios** y hacer el código comprensible.
- El código debe organizarse en **clases** y estas en **packages**.
- El código debe organizarse en **métodos** (no todo en main).

### 5. Formato de entrada/salida
- Cuando el enunciado muestre ejemplos de ejecución, debe **replicarse exactamente** el formato de entrada y salida mostrado.

### 6. Uso de colecciones
- Se debe **añadir un comentario justificando la elección** de cada colección utilizada en la solución.

### 7. Excepciones
- Es **imprescindible crear y usar excepciones propias** tal y como se ha enseñado en clase.
- Se deben **gestionar correctamente todas las excepciones** (tanto propias como del JDK).

### 8. Restricciones adicionales
- **No se puede utilizar ningún tipo de IA ni Internet** para resolver el examen.
- **No se pueden utilizar conceptos que no se hayan dado en clase** (lambdas, streams, etc. si no se han visto).
