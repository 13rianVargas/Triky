package gfutria.controller;

import gfutria.view.Consola;
import gfutria.model.Triky;

public class Controlador {
    private Triky tablero;
    private boolean finJuego;
    private boolean aprender;
    private boolean firstBlank;
    private boolean random;

    public void run() {
        tablero = new Triky();
        inicializarOpciones();
        
        Consola.mostrarMensaje("¡Bienvenido a Triky!");
        Consola.mostrarMensaje("\nConfiguracion de la máquina:");
        Consola.mostrarMensaje("Modo de juego: " + (random ? "Random" : "First Blank"));
        Consola.mostrarMensaje("I Want to Learn: " + (aprender ? "Activado" : "Desactivado"));
        Consola.mostrarMensaje("\n¿Desea cambiar la configuración? (1: Si, 0: No):");
        
        if (Consola.pedirInt() == 1) {
            configurarJuego();
        }

        Consola.mostrarMensaje("\nJugador: X  |  Máquina: O\n");
        mostrarTableroPorConsola(tablero.getTablero());

        while (finJuego) {
            // Solicita y procesa la jugada del jugador humano
            Consola.mostrarMensaje("\nTurno del jugador");
            
            boolean jugadaValida = false;
            while (!jugadaValida && finJuego) {
                try {
                    Consola.mostrarMensaje("Ingrese fila (0-2):");
                    int fila = Consola.pedirInt();
                    Consola.mostrarMensaje("Ingrese columna (0-2):");
                    int columna = Consola.pedirInt();
                    
                    jugadaValida = tablero.humanPlayed(fila, columna);
                    if (!jugadaValida) {
                        Consola.mostrarMensaje("Posición inválida. Intente de nuevo.");
                    }
                } catch (Exception e) {
                    Consola.mostrarMensaje("Por favor ingrese números válidos.");
                }
            }

            // Verifica victoria del jugador o empate
            if (hayGanador()) {
                mostrarTableroPorConsola(tablero.getTablero());
                Consola.mostrarMensaje("\n¡El jugador ha ganado!");
                if (tablero.isLearning()) {
                    tablero.gameOver(false);
                }
                finJuego = false;
                continue;
            } else if (tableroLleno()) {
                mostrarTableroPorConsola(tablero.getTablero());
                Consola.mostrarMensaje("\n¡El juego ha terminado en empate!");
                finJuego = false;
                continue;
            }

            // Procesa el turno de la máquina y muestra el resultado
            Consola.mostrarMensaje("\nTurno de la máquina:");
            machinePlays();

            // Verificar si la máquina ganó
            if (hayGanador()) {
                mostrarTableroPorConsola(tablero.getTablero());
                Consola.mostrarMensaje("\n¡La máquina ha ganado!");
                if (tablero.isLearning()) {
                    tablero.gameOver(true);
                }
                finJuego = false;
            } else if (tableroLleno()) {
                mostrarTableroPorConsola(tablero.getTablero());
                Consola.mostrarMensaje("\n¡El juego ha terminado en empate!");
                finJuego = false;
            }
        }

        boolean menuActivo = true;
        while (menuActivo) {
            Consola.mostrarMensaje("\nSeleccione una opción:");
            Consola.mostrarMensaje("1. Jugar otra vez");
            Consola.mostrarMensaje("2. Limpiar base de datos");
            Consola.mostrarMensaje("3. Salir");
            
            try {
                int opcion = Consola.pedirInt();
                switch (opcion) {
                    case 1:
                        menuActivo = false;
                        reiniciarJuego();
                        run();
                        break;
                    case 2:
                        Consola.mostrarMensaje("\n¿Está seguro que desea limpiar la base de datos? (1: Si, 0: No)");
                        if (Consola.pedirInt() == 1) {
                            limpiarMemoria();
                            reiniciarJuego();
                            menuActivo = false;
                            run();
                        }
                        break;
                    case 3:
                        menuActivo = false;
                        Consola.mostrarMensaje("¡Gracias por jugar!");
                        break;
                    default:
                        Consola.mostrarMensaje("Opción inválida. Por favor intente de nuevo.");
                        break;
                }
            } catch (Exception e) {
                Consola.mostrarMensaje("Error: Por favor ingrese un número válido.");
            }
        }
    }
    
    public void limpiarMemoria(){
        tablero.getMemory().clear();
        Consola.mostrarMensaje("\nBase de datos de aprendizaje limpiada.");
    }

    public boolean humanPlayed(int i, int j) {
        return tablero.humanPlayed(i, j);
    }

    public void machinePlays() {
        if (!finJuego) {
            return;
        }

        // Ejecutamos la estrategia de juego seleccionada
        if (firstBlank) {
            tablero.blankMachinePlays();
        } else if (random) {
            tablero.randomMachinePlays();
        }

        mostrarTableroPorConsola(tablero.getTablero());

        // Verificar estado del juego
        if (hayGanador()) {
            Consola.mostrarMensaje("La máquina ha ganado!");
            if (tablero.isLearning()) {
                tablero.gameOver(true);
            }
            finJuego = false;
        } else if (tableroLleno()) {
            Consola.mostrarMensaje("El juego ha terminado en empate!");
            finJuego = false;
        }
    }

    public Triky getStatus() {
        return tablero;
    }
    
    public void mostrarTableroPorConsola(char[][] tablero) {
        Consola.mostrarMensaje("\n     0   1   2");
        Consola.mostrarMensaje("   +---+---+---+");
        for (int i = 0; i < tablero.length; i++) {
            StringBuilder fila = new StringBuilder(" " + i + " | ");
            for (int j = 0; j < tablero[i].length; j++) {
                char symbol = (tablero[i][j] == '\0') ? ' ' : tablero[i][j];
                fila.append(symbol).append(" | ");
            }
            Consola.mostrarMensaje(fila.toString());
            Consola.mostrarMensaje("   +---+---+---+");
        }
    }

    public boolean tableroLleno() {
        char[][] board = tablero.getTablero();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ' || board[i][j] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hayGanador() {
        char[][] board = tablero.getTablero();
        
        // Verifica líneas horizontales
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] != ' ' && board[i][0] != '\0') && 
                board[i][0] == board[i][1] && 
                board[i][1] == board[i][2]) {
                return true;
            }
        }
        
        // Verifica líneas verticales
        for (int j = 0; j < 3; j++) {
            if ((board[0][j] != ' ' && board[0][j] != '\0') && 
                board[0][j] == board[1][j] && 
                board[1][j] == board[2][j]) {
                return true;
            }
        }
        
        // Verifica diagonal principal (esquina superior izquierda a inferior derecha)
        if ((board[0][0] != ' ' && board[0][0] != '\0') && 
            board[0][0] == board[1][1] && 
            board[1][1] == board[2][2]) {
            return true;
        }
        
        // Verifica diagonal secundaria (esquina superior derecha a inferior izquierda)
        if ((board[0][2] != ' ' && board[0][2] != '\0') && 
            board[0][2] == board[1][1] && 
            board[1][1] == board[2][0]) {
            return true;
        }
        
        return false;
    }

    private void inicializarOpciones() {
        aprender = true; // Aprender siempre activado por defecto
        firstBlank = false;
        random = true; //Random por defecto
        finJuego = true;
        tablero.setLearning(aprender);
    }

    private void configurarJuego() {
        Consola.mostrarMensaje("\nSeleccione modo de juego:");
        Consola.mostrarMensaje("1. First Blank");
        Consola.mostrarMensaje("2. Random");
        
        try {
            int modo = Consola.pedirInt();
            // Establecer modo de juego
            firstBlank = modo == 1;
            random = !firstBlank; // Si no es firstBlank, será random
            
            // Configurar aprendizaje independientemente
            Consola.mostrarMensaje("\n¿Activar modo aprendizaje? (1: Si, 0: No)");
            aprender = Consola.pedirInt() == 1;
            tablero.setLearning(aprender);
            
            if (aprender) {
                Consola.mostrarMensaje("Modo aprendizaje activado - Los estados se guardarán automáticamente");
            }
        } catch (Exception e) {
            Consola.mostrarMensaje("Error: Selección inválida, se usará modo Random sin aprendizaje");
            firstBlank = false;
            random = true;
            aprender = false;
            tablero.setLearning(false);
        }
    }

    public void reiniciarJuego() {
        tablero = new Triky();
        inicializarOpciones();
    }
}