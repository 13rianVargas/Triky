# 🎮 Triky - Tic-Tac-Toe con Aprendizaje Automático

**Triky** es un juego de Tres en Raya (Tic-Tac-Toe) desarrollado en Java que incluye una inteligencia artificial capaz de aprender de sus partidas y mejorar su rendimiento con el tiempo.

## ✨ Características

- 🎲 **Juego Clásico**: Implementación completa del Tres en Raya
- 🤖 **IA con Aprendizaje**: La máquina aprende de cada partida jugada
- 💾 **Memoria Persistente**: Los datos de aprendizaje se guardan automáticamente
- 🎯 **Múltiples Modos de Juego**:
  - **Random**: La máquina juega de forma aleatoria
  - **First Blank**: La máquina utiliza la primera posición disponible
  - **Smart** (con aprendizaje): La máquina utiliza conocimiento previo
- 🖥️ **Interfaz de Consola**: Juega directamente desde la terminal
- 🪟 **Interfaz Gráfica**: Versión con GUI usando Java Swing
- 🧠 **Sistema de Neuronas**: Almacena patrones de juego para toma de decisiones

## 📋 Requisitos

- **Java Development Kit (JDK)** 8 o superior
- **Apache Ant** (para compilación con build.xml)
- O alternativamente, **NetBeans IDE** para desarrollo

## 🚀 Instalación y Ejecución

### Usando la Terminal

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/13rianVargas/Triky.git
   cd Triky
   ```

2. **Compilar el proyecto**:
   ```bash
   cd TTT/Triky-Memory-DLL
   ant compile
   ```

3. **Ejecutar el juego**:
   ```bash
   ant run
   ```

### Usando NetBeans

1. Abrir NetBeans IDE
2. Ir a `File > Open Project`
3. Seleccionar la carpeta `TTT/Triky-Memory-DLL`
4. Ejecutar el proyecto (F6)

## 🎯 Cómo Jugar

### Modo Consola

1. Al iniciar, configura las opciones de juego:
   - Selecciona el modo de juego (First Blank o Random)
   - Activa o desactiva el modo de aprendizaje

2. Juega ingresando las coordenadas:
   - **Fila**: 0, 1 o 2
   - **Columna**: 0, 1 o 2

3. El tablero se muestra así:
   ```
        0   1   2
      +---+---+---+
    0 | X |   |   |
      +---+---+---+
    1 |   | O |   |
      +---+---+---+
    2 |   |   |   |
      +---+---+---+
   ```

4. Al finalizar puedes:
   - Jugar otra vez
   - Limpiar la base de datos de aprendizaje
   - Salir del juego

## 📁 Estructura del Proyecto

```
Triky/
├── TTT/                         # Versión principal del proyecto
│   └── Triky-Memory-DLL/
│       ├── src/
│       │   └── gfutria/
│       │       ├── controller/  # Controladores del juego
│       │       │   ├── AplMain.java
│       │       │   └── Controlador.java
│       │       ├── model/       # Modelo del juego
│       │       │   └── Triky.java
│       │       ├── view/        # Vistas (consola y GUI)
│       │       │   ├── Consola.java
│       │       │   ├── InterfazApp.java
│       │       │   ├── PanelTriky.java
│       │       │   ├── PanelOpciones.java
│       │       │   └── LabelClicMouse.java
│       │       ├── Memory.java      # Sistema de memoria
│       │       └── Neurons.java     # Red de neuronas
│       ├── nbproject/           # Configuración de NetBeans
│       └── build.xml            # Script de compilación Ant
├── Tricky/                      # Versión alternativa
└── Triky-Memory-DLL/            # Configuración adicional
```

## 🧠 Sistema de Aprendizaje

Triky implementa un sistema de aprendizaje basado en memoria que:

1. **Guarda Estados**: Cada estado del tablero se convierte en una cadena única
2. **Almacena Movimientos**: Asocia cada estado con el mejor movimiento conocido
3. **Persistencia**: Los datos se guardan en `TTT/dist/data/triky.dat`
4. **Mejora Continua**: La máquina mejora su rendimiento con cada partida

## 🛠️ Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal
- **Java Swing**: Interfaz gráfica de usuario
- **NetBeans**: IDE de desarrollo
- **Apache Ant**: Sistema de compilación
- **Serialización Java**: Persistencia de datos

## 📝 Modos de Juego Detallados

| Modo | Descripción |
|------|-------------|
| **Random** | La máquina selecciona posiciones aleatorias |
| **First Blank** | La máquina usa la primera posición vacía disponible |
| **Con Aprendizaje** | La máquina utiliza patrones aprendidos de partidas anteriores |

> **Nota**: En todos los modos, la máquina intentará primero ganar si es posible, y luego bloqueará al oponente si está a punto de ganar.

## 👥 Contribuir

Las contribuciones son bienvenidas. Para contribuir:

1. Haz un Fork del proyecto
2. Crea una rama para tu feature (`git checkout -b feature/NuevaCaracteristica`)
3. Haz commit de tus cambios (`git commit -m 'Agregar nueva característica'`)
4. Push a la rama (`git push origin feature/NuevaCaracteristica`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto fue desarrollado como proyecto académico.

## 📧 Contacto

- **GitHub**: [@13rianVargas](https://github.com/13rianVargas)

---

⭐ Si te gusta este proyecto, ¡no olvides darle una estrella!
