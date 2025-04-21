package gfutria;

import java.io.*;
import java.util.ArrayList;

import gfutria.view.Consola;

public class Memory {
    // Archivo para almacenar los datos de aprendizaje
    private static final String DATA_FILE = "TTT/dist/data/triky.dat";
    
    private int row, col;    
    private ArrayList<String> gameStates;
    private boolean learning;
    private Neurons neurons;

    public Memory() { 
        row = col = 0;     
        gameStates = new ArrayList<>();
        learning = false;
        neurons = new Neurons();
        loadMemory();
    }

    private void loadMemory() {
        // Cargar el estado de aprendizaje previo desde el archivo
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                neurons = (Neurons) ois.readObject();
            } catch (Exception e) {
                System.err.println("Error al cargar la memoria: " + e.getMessage());
                neurons = new Neurons();
            }
        }
    }

    private void saveMemory() {
        // Guardar el estado actual de aprendizaje en el archivo
        try {
            File file = new File(DATA_FILE);
            file.getParentFile().mkdirs(); // Crear directorios si no existen
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(neurons);
            }
        } catch (Exception e) {
            System.err.println("Error al guardar la memoria: " + e.getMessage());
        }
    }

    public boolean remember(int[][] board) {
        // Intenta recordar un movimiento aprendido para el estado actual del tablero
        if (!learning) {
            return false;
        }

        String key = convertBoardToString(board);
        gameStates.add(key); // Añade cada estado del tablero a gameStates
        
        if (neurons.verify(key)) {
            var movement = neurons.getFrequency(key);
            row = movement.getBestRow();
            col = movement.getBestCol();
            return true;
        }
        return false;
    }

    public int getRow() { return row; }

    public int getCol() { return col; }
    
    public void setLearning(boolean learning) {
        this.learning = learning;
    }
    
    public boolean isLearning() {
        return learning;
    }
    
    public void gameOver(boolean machineWon) {
        if (!learning) {
            return;
        }

        // Save all game states regardless of outcome
        for (String key : gameStates) {
            neurons.save(key);
        }
        saveMemory();
        gameStates.clear();
    }
    
    public void clear() {
        neurons.clear();
        gameStates.clear();
        neurons = new Neurons();
        File file = new File(DATA_FILE);
        if (file.exists()) {
            file.delete();
        }
        Consola.mostrarMensaje("\nBase de datos de aprendizaje limpiada.");
    }        
    
    public ArrayList<String> brain() {
        return neurons.getNeurons();
    }
    
    private String convertBoardToString(int[][] board) {
        // Convierte el estado del tablero a una cadena de texto
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }
}

