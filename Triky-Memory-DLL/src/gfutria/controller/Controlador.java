package gfutria.controller;

import gfutria.view.Consola;

import gfutria.model.Triky;

public class Controlador {
    private Triky tablero;
    private boolean finJuego = true;

    public void run() {
        tablero = new Triky();
        
        Consola.mostrarMensaje("Bienvenido al juego de Triky");
        Consola.mostrarMensaje("El jugador es -> X");
        Consola.mostrarMensaje("La maquina es -> O");

        mostrarTablero(tablero.getTablero());
        Consola.mostrarMensaje("El jugador empieza");

        while (finJuego) {

            //JUGADOR//
            boolean movimientoValido = false;
            while (!movimientoValido) {
                // FILA
                Consola.mostrarMensaje("Ingrese la fila donde desea colocar su simbolo:");
                int i = Consola.pedirInt();
                while (i < 0 || i > 2) {
                    Consola.mostrarMensaje("Posicion invalida, ingrese nuevamente la fila:");
                    i = Consola.pedirInt();
                }
                // COLUMNA
                Consola.mostrarMensaje("Ingrese la columna donde desea colocar su simbolo:");
                int j = Consola.pedirInt();
                while (j < 0 || j > 2) {
                    Consola.mostrarMensaje("Posicion invalida, ingrese nuevamente la columna:");
                    j = Consola.pedirInt();
                }

                movimientoValido = humanPlayed(i, j);
            }
            
            machinePlays();
        }
    }

    public boolean humanPlayed(int i, int j) {
        boolean movimientoValido = false;
        movimientoValido = tablero.humanPlayed(i,j);
        mostrarTablero(tablero.getTablero());
        if (hayGanador()) {
            Consola.mostrarMensaje("El jugador ha ganado!");
            finJuego = false;
        } else if (tableroLleno()) {
            Consola.mostrarMensaje("¡El juego ha terminado en empate!");
            finJuego = false;
        }
        return movimientoValido;
    }

    public void machinePlays() {
        tablero.machinePlays();
        mostrarTablero(tablero.getTablero());
        if (hayGanador()) {
            Consola.mostrarMensaje("La máquina ha ganado!");
            finJuego = false;
        } else if (tableroLleno()) {
            Consola.mostrarMensaje("¡El juego ha terminado en empate!");
            finJuego = false;
        }
    }
    
    public void mostrarTablero(char[][] tablero) {
        Consola.mostrarMensaje("\n     0   1   2");
        Consola.mostrarMensaje("   +---+---+---+");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(" " + i + " | ");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print((tablero[i][j] == '\0' ? " " : tablero[i][j]) + " | ");
            }
            Consola.mostrarMensaje("\n   +---+---+---+");
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
        
        // Revisar horizontales
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] != ' ' && board[i][0] != '\0') && 
                board[i][0] == board[i][1] && 
                board[i][1] == board[i][2]) {
                return true;
            }
        }
        
        // Revisar verticales
        for (int j = 0; j < 3; j++) {
            if ((board[0][j] != ' ' && board[0][j] != '\0') && 
                board[0][j] == board[1][j] && 
                board[1][j] == board[2][j]) {
                return true;
            }
        }
        
        // Revisar diagonal principal
        if ((board[0][0] != ' ' && board[0][0] != '\0') && 
            board[0][0] == board[1][1] && 
            board[1][1] == board[2][2]) {
            return true;
        }
        
        // Revisar diagonal secundaria
        if ((board[0][2] != ' ' && board[0][2] != '\0') && 
            board[0][2] == board[1][1] && 
            board[1][1] == board[2][0]) {
            return true;
        }
        
        return false;
    }

}