package gfutria.model;

import gfutria.Memory;
import javax.swing.JOptionPane;

public class Triky {
    private char[][] tablero; //Para consola
    private int[][] intTablero; //Para GUI
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
//            Consola.mostrarMensaje("Jugador coloca en (" + i + "," + j + ")");
            return true;
        }
        //TODO: Cambiar el mensaje y el JOptionPane
            JOptionPane.showMessageDialog(null, "Memory has been cleared.", "Memory", JOptionPane.INFORMATION_MESSAGE);
//        Consola.mostrarMensaje("Casilla ocupada, seleccione otra posición");
        return false;
    }

    public boolean attackMachinePlays() {
        // Revisar filas
        for (int i = 0; i < 3; i++) {
            if (checkLine(tablero[i][0], tablero[i][1], tablero[i][2], maquina)) {
                for (int j = 0; j < 3; j++) {
                    if (tablero[i][j] == ' ' || tablero[i][j] == '\0') {
                        tablero[i][j] = maquina;
                        intTablero[i][j] = 2;
                        return true;
                    }
                }
            }
        }

        // Revisar columnas
        for (int j = 0; j < 3; j++) {
            if (checkLine(tablero[0][j], tablero[1][j], tablero[2][j], maquina)) {
                for (int i = 0; i < 3; i++) {
                    if (tablero[i][j] == ' ' || tablero[i][j] == '\0') {
                        tablero[i][j] = maquina;
                        intTablero[i][j] = 2;
                        return true;
                    }
                }
            }
        }

        // Revisar diagonal principal
        if (checkLine(tablero[0][0], tablero[1][1], tablero[2][2], maquina)) {
            for (int i = 0; i < 3; i++) {
                if (tablero[i][i] == ' ' || tablero[i][i] == '\0') {
                    tablero[i][i] = maquina;
                    intTablero[i][i] = 2;
                    return true;
                }
            }
        }

        // Revisar diagonal secundaria
        if (checkLine(tablero[0][2], tablero[1][1], tablero[2][0], maquina)) {
            for (int i = 0; i < 3; i++) {
                if (tablero[i][2-i] == ' ' || tablero[i][2-i] == '\0') {
                    tablero[i][2-i] = maquina;
                    intTablero[i][2-i] = 2;
                    return true;
                }
            }
        }

        return false;
    }

    public boolean defenseMachinePlays() {
        // Revisar filas
        for (int i = 0; i < 3; i++) {
            if (checkLine(tablero[i][0], tablero[i][1], tablero[i][2], jugador)) {
                for (int j = 0; j < 3; j++) {
                    if (tablero[i][j] == ' ' || tablero[i][j] == '\0') {
                        tablero[i][j] = maquina;
                        intTablero[i][j] = 2;
                        return true;
                    }
                }
            }
        }

        // Revisar columnas
        for (int j = 0; j < 3; j++) {
            if (checkLine(tablero[0][j], tablero[1][j], tablero[2][j], jugador)) {
                for (int i = 0; i < 3; i++) {
                    if (tablero[i][j] == ' ' || tablero[i][j] == '\0') {
                        tablero[i][j] = maquina;
                        intTablero[i][j] = 2;
                        return true;
                    }
                }
            }
        }

        // Revisar diagonal principal
        if (checkLine(tablero[0][0], tablero[1][1], tablero[2][2], jugador)) {
            for (int i = 0; i < 3; i++) {
                if (tablero[i][i] == ' ' || tablero[i][i] == '\0') {
                    tablero[i][i] = maquina;
                    intTablero[i][i] = 2;
                    return true;
                }
            }
        }

        // Revisar diagonal secundaria
        if (checkLine(tablero[0][2], tablero[1][1], tablero[2][0], jugador)) {
            for (int i = 0; i < 3; i++) {
                if (tablero[i][2-i] == ' ' || tablero[i][2-i] == '\0') {
                    tablero[i][2-i] = maquina;
                    intTablero[i][2-i] = 2;
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkLine(char a, char b, char c, char player) {
        int count = 0;
        int spaces = 0;
        
        if (a == player) count++;
        if (b == player) count++;
        if (c == player) count++;
        
        if (a == ' ' || a == '\0') spaces++;
        if (b == ' ' || b == '\0') spaces++;
        if (c == ' ' || c == '\0') spaces++;
        
        return count == 2 && spaces == 1;
    }

    public void randomMachinePlays() {
        int i, j;
        do {
            i = (int)(Math.random() * 3);
            j = (int)(Math.random() * 3);
        } while (tablero[i][j] != ' ' && tablero[i][j] != '\0');
        
        tablero[i][j] = maquina;
        intTablero[i][j] = 2;
    }

    public void smartMachinePlays() {
        if (memory.remember(intTablero)) {
            int row = memory.getRow();
            int col = memory.getCol();
            if (tablero[row][col] == ' ' || tablero[row][col] == '\0') {
                tablero[row][col] = maquina;
                intTablero[row][col] = 2;
            } else {
                randomMachinePlays();
            }
        } else {
            randomMachinePlays();
        }
    }
    
    public void blankMachinePlays() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ' || tablero[i][j] == '\0') {
                    tablero[i][j] = maquina;
                    intTablero[i][j] = 2;
                    return;
                }
            }
        }
    }
}