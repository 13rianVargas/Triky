package gfutria.model;

import gfutria.Memory;
import gfutria.view.Consola;

public class Triky {
    private char[][] tablero;
    private int[][] intTablero;
    private char jugador = 'X';
    private char maquina = 'O';
    private Memory memory;

    public Triky() {
        tablero = new char[3][3];
        intTablero = new int[3][3];
        memory = new Memory();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = ' ';
                intTablero[i][j] = 0;
            }
        }
    }

    public char[][] getTablero() {
        return tablero;
    }

    public int[][] getIntTablero() {
        return intTablero;
    }

    public void setTablero(char[][] tablero) {
        this.tablero = tablero;
        updateIntTablero();
    }

    private void updateIntTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == 'X') {
                    intTablero[i][j] = 1;
                } else if (tablero[i][j] == 'O') {
                    intTablero[i][j] = 2;
                } else {
                    intTablero[i][j] = 0;
                }
            }
        }
    }

    public boolean humanPlayed(int i, int j) {
        if (tablero[i][j] == ' ' || tablero[i][j] == '\0') {
            tablero[i][j] = jugador;
            intTablero[i][j] = 1;
            Consola.mostrarMensaje("Jugador coloca en (" + i + "," + j + ")");
            return true;
        }
        Consola.mostrarMensaje("Casilla ocupada, seleccione otra posición");
        return false;
    }

    public void machinePlays() {
        boolean randomMove = memory.remember(intTablero);
        int i, j;
        
        if (randomMove) {
            // Make random move if state is unknown
            boolean movimientoValido = false;
            while (!movimientoValido) {
                i = (int) (Math.random() * 3);
                j = (int) (Math.random() * 3);
                if (tablero[i][j] == ' ' || tablero[i][j] == '\0') {
                    tablero[i][j] = maquina;
                    intTablero[i][j] = 2;
                    Consola.mostrarMensaje("Máquina coloca en (" + i + "," + j + ")");
                    movimientoValido = true;
                }
            }
        } else {
            // Use remembered position
            i = memory.getRow();
            j = memory.getCol();
            tablero[i][j] = maquina;
            intTablero[i][j] = 2;
            Consola.mostrarMensaje("Máquina (usando memoria) coloca en (" + i + "," + j + ")");
        }
    }
    
    public Memory getMemory() {
        return memory;
    }
    
    public void setLearning(boolean learning) {
        memory.setLearning(learning);
    }
    
    public boolean isLearning() {
        return memory.isLearning();
    }
    
    public void gameOver(boolean machineWon) {
        memory.gameOver(machineWon);
    }
}