package gfutria.model;

import gfutria.Memory;

public class Triky {
    private char[][] tablero; // Matriz para representación en consola
    private int[][] intTablero; // Matriz para representación en interfaz gráfica
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
        // Actualiza la representación numérica del tablero
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

    private void saveState() {
        // Guarda el estado actual del tablero si el aprendizaje está activado
        if (memory.isLearning()) {
            memory.remember(intTablero);
        }
    }

    public boolean humanPlayed(int i, int j) {
        if (tablero[i][j] == ' ' || tablero[i][j] == '\0') {
            tablero[i][j] = jugador;
            intTablero[i][j] = 1;
            saveState();
            return true;
        }
        return false;
    }

    public void randomMachinePlays() {
        // Primero intenta ganar
        if (attackMachinePlays()) {
            return;
        }
        // Luego intenta bloquear al oponente
        if (defenseMachinePlays()) {
            return;
        }
        // Si no hay amenazas inmediatas, juega aleatoriamente
        int i, j;
        do {
            i = (int)(Math.random() * 3);
            j = (int)(Math.random() * 3);
        } while (tablero[i][j] != ' ' && tablero[i][j] != '\0');
        
        tablero[i][j] = maquina;
        intTablero[i][j] = 2;
        saveState();
    }

    public void smartMachinePlays() {
        // Primero intenta ganar
        if (attackMachinePlays()) {
            return;
        }
        // Luego intenta bloquear al oponente
        if (defenseMachinePlays()) {
            return;
        }
        // Si no hay amenazas inmediatas, usa la estrategia de aprendizaje
        if (memory.remember(intTablero)) {
            int row = memory.getRow();
            int col = memory.getCol();
            if (tablero[row][col] == ' ' || tablero[row][col] == '\0') {
                tablero[row][col] = maquina;
                intTablero[row][col] = 2;
            } else {
                randomMachinePlays();
                return;
            }
        } else {
            randomMachinePlays();
            return;
        }
        saveState();
    }
    
    public void blankMachinePlays() {
        // Primero intenta ganar
        if (attackMachinePlays()) {
            return;
        }
        // Luego intenta bloquear al oponente
        if (defenseMachinePlays()) {
            return;
        }
        
        // Verifica si es el primer movimiento (tablero vacío)
        boolean isFirstMove = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] != ' ' && tablero[i][j] != '\0') {
                    isFirstMove = false;
                    break;
                }
            }
        }
        
        // Si es el primer movimiento, coloca en (0,0)
        if (isFirstMove && (tablero[0][0] == ' ' || tablero[0][0] == '\0')) {
            tablero[0][0] = maquina;
            intTablero[0][0] = 2;
            saveState();
            return;
        }
        
        // Si no, usa el primer espacio disponible
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ' || tablero[i][j] == '\0') {
                    tablero[i][j] = maquina;
                    intTablero[i][j] = 2;
                    saveState();
                    return;
                }
            }
        }
    }

    private boolean attackMachinePlays() {
        if (makeMove(maquina)) {
            saveState();
            return true;
        }
        return false;
    }

    private boolean defenseMachinePlays() {
        if (makeMove(jugador)) {
            saveState();
            return true;
        }
        return false;
    }

    private boolean makeMove(char player) {
        // Verifica filas
        for (int i = 0; i < 3; i++) {
            if (checkLine(tablero[i][0], tablero[i][1], tablero[i][2], player)) {
                for (int j = 0; j < 3; j++) {
                    if (tablero[i][j] == ' ' || tablero[i][j] == '\0') {
                        tablero[i][j] = maquina;
                        intTablero[i][j] = 2;
                        return true;
                    }
                }
            }
        }

        // Verifica columnas
        for (int j = 0; j < 3; j++) {
            if (checkLine(tablero[0][j], tablero[1][j], tablero[2][j], player)) {
                for (int i = 0; i < 3; i++) {
                    if (tablero[i][j] == ' ' || tablero[i][j] == '\0') {
                        tablero[i][j] = maquina;
                        intTablero[i][j] = 2;
                        return true;
                    }
                }
            }
        }

        // Verifica diagonal principal
        if (checkLine(tablero[0][0], tablero[1][1], tablero[2][2], player)) {
            for (int i = 0; i < 3; i++) {
                if (tablero[i][i] == ' ' || tablero[i][i] == '\0') {
                    tablero[i][i] = maquina;
                    intTablero[i][i] = 2;
                    return true;
                }
            }
        }

        // Verifica diagonal secundaria
        if (checkLine(tablero[0][2], tablero[1][1], tablero[2][0], player)) {
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
        // Verifica si hay dos símbolos iguales y un espacio en línea
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
}