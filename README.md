# Java Maven Template — MVC + Persistencia CSV

Template genérico de proyecto Java con Maven, arquitectura MVC,
excepciones propias, persistencia en CSV y validaciones comunes.

Estructura pensada para ser clonada y adaptada a cualquier proyecto
de gestión (usuarios, socios, actividades, inventario, etc.)

---

## Estructura del proyecto

```
src/main/java/com/template/app/
├── controller/      → Lógica de negocio (GestorXxx.java)
├── exception/       → Excepciones propias de la aplicación
├── model/           → Clases del dominio (entidades, herencia)
├── persistence/     → Lectura/escritura en ficheros CSV
└── view/            → Interfaz de usuario (menú, entrada/salida)
src/main/resources/  → Recursos adicionales (config, properties, etc.)
src/test/java/       → Tests unitarios
data/                → Directorio donde se almacenan los CSV en tiempo
                       de ejecución (no versionado en git)
```

### Descripción de cada capa

| Capa | Contenido |
|---|---|
| `model/` | Clases del dominio del problema. Incluye la clase abstracta base, las hijas que heredan de ella, y clases auxiliares (ej. `Usuari`, `Activitat`, `Torneig`, `CursPintura`, `Balda`, `Asignacion`). |
| `controller/` | Clase(s) que orquestan la lógica de negocio. Se comunica con `model` para las reglas y con `persistence` para guardar/cargar datos. |
| `view/` | Menús, pantallas e interacción con el usuario. Clase de menú principal y clase de lectura de datos (`AskData`) con validación de entrada. |
| `persistence/` | CRUD sobre ficheros CSV usando `BufferedReader`/`BufferedWriter`. Cada entidad tiene su método de guardado/carga. |
| `exception/` | Excepciones propias que extienden `Exception`. Separadas por ámbito (lógica de negocio vs. persistencia). |

---

## Cómo adaptarlo a un proyecto nuevo

### 1. Clonar o copiar

```bash
git clone <repo> mi-nuevo-proyecto
cd mi-nuevo-proyecto
```

### 2. Renombrar el package base

```bash
# Ejemplo: cambiar de com.template.app a com.miclub.gestion
mkdir -p src/main/java/com/miclub/gestion
mv src/main/java/com/template/app/controller src/main/java/com/miclub/gestion/
mv src/main/java/com/template/app/exception src/main/java/com/miclub/gestion/
mv src/main/java/com/template/app/model src/main/java/com/miclub/gestion/
mv src/main/java/com/template/app/persistence src/main/java/com/miclub/gestion/
mv src/main/java/com/template/app/view src/main/java/com/miclub/gestion/
rm -rf src/main/java/com/template

# Lo mismo para test
mkdir -p src/test/java/com/miclub/gestion
mv src/test/java/com/template/app/* src/test/java/com/miclub/gestion/
rm -rf src/test/java/com/template
```

### 3. Actualizar `pom.xml`

```xml
<groupId>com.miclub</groupId>
<artifactId>gestion-club</artifactId>
<exec.mainClass>com.miclub.gestion.Main</exec.mainClass>
```

### 4. Renombrar la clase principal

```bash
mv src/main/java/com/miclub/gestion/App.java src/main/java/com/miclub/gestion/Main.java
```

Y cambiar su contenido para que el nombre de clase coincida con el
fichero.

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
| **Idioma** | Catalán o español, pero **consistente** en todo el código |
| **Nombres** | Descriptivos, en camelCase (variables/métodos) y PascalCase (clases) |
| **Colecciones** | `HashMap` para búsquedas por clave, `ArrayList` para listas ordenadas. Cada uso debe justificarse con un comentario. |
| **Excepciones** | Propias, extendiendo `Exception`, una por ámbito de error |
| **Persistencia** | CSV con `BufferedReader`/`BufferedWriter` y captura de `IOException` envuelta en `PersistenciaException` |
| **Herencia** | Clase abstracta base con hijas concretas que sobrescriben métodos |

---

## Dependencias

Ninguna. Proyecto Java base sin frameworks externos.
Solo JDK 21+ y Maven.

```bash
mvn clean compile exec:java
```
