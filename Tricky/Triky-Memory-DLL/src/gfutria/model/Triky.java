package gfutria.model;

import gfutria.view.Consola;

public class Triky {
    private char[][] tablero;
    private char jugador = 'X';
    private char maquina = 'O';

    public Triky() {
        tablero = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    public char[][] getTablero() {
        return tablero;
    }

    public void setTablero(char[][] tablero) {
        this.tablero = tablero;
    }

    public boolean humanPlayed(int i, int j) {
        if (tablero[i][j] == ' ' || tablero[i][j] == '\0') {
            tablero[i][j] = jugador;
            Consola.mostrarMensaje("Jugador coloca en (" + i + "," + j + ")");
            return true;
        }
        Consola.mostrarMensaje("Casilla ocupada, seleccione otra posición");
        return false;
    }

    public void machinePlays() {
        boolean movimientoValido = false;
        while (!movimientoValido) {
            int i = (int) (Math.random() * 3);
            int j = (int) (Math.random() * 3);
            if (tablero[i][j] == ' ' || tablero[i][j] == '\0') {
                tablero[i][j] = maquina;
                Consola.mostrarMensaje("Máquina coloca en (" + i + "," + j + ")");
                movimientoValido = true;
            }
        }
    }
    
}