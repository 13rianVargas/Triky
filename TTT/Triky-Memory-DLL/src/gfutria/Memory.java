package gfutria;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Memory {
    private int row, col;    
    private Neurons neurons;
    private String currentGameState;
    private boolean learning;

    public Memory() { 
        row = col = 0;     
        neurons = new Neurons();
        learning = false;
    }

    public boolean remember(int triky[][]) { 
        currentGameState = convertBoardToString(triky);
        
        if (neurons.verify(currentGameState)) {
            // Si el estado ya está en la memoria, no se hace nada
            return false;
        }
        
        //Movimiento aleatorio
        do {
            row = (int)(Math.random() * 3);
            col = (int)(Math.random() * 3);
        } while (triky[row][col] != 0);
        
        return true;
    }

    public int getRow() { return row; }

    public int getCol() { return col; }
    
    public void setLearning(boolean learning) {
        this.learning = learning;
    }
    
    public boolean isLearning() {
        return learning;
    }
    
    public void gameOver(boolean win) {
        if (learning && currentGameState != null) {
            if (win) {
                // Guardar el estado del juego si la máquina ganó
                neurons.save(currentGameState);
            }
        }
    }
    
    public void clear() {
        neurons = new Neurons();
        JOptionPane.showMessageDialog(null, "Memory has been cleared.", "Memory", JOptionPane.INFORMATION_MESSAGE);      
    }        
    
    public ArrayList<String> brain() {
        return neurons.getNeurons();
    }
    
    private String convertBoardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }
}

