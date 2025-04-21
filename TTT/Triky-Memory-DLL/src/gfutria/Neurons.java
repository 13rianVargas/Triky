package gfutria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Neurons implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<String> neurons;
    private HashMap<String, Movement> frequencies;

    public static class Movement implements Serializable {
        // Posición y estadísticas del movimiento
        private int row;
        private int col;
        private float successRate;
        private int timesUsed;

        public Movement(int row, int col) {
            this.row = row;
            this.col = col;
            this.successRate = 0.0f;
            this.timesUsed = 0;
        }

        public void updateLastMove(float result) {
            timesUsed++;
            successRate = ((successRate * (timesUsed - 1)) + result) / timesUsed;
        }

        public int getBestRow() { return row; }
        public int getBestCol() { return col; }
        public float getSuccessRate() { return successRate; }
    }

    public Neurons() { 
        neurons = new ArrayList<>();
        frequencies = new HashMap<>();
    }
   
    public ArrayList<String> getNeurons() { 
        return neurons; 
    }

    public boolean verify(String key) { 
        return frequencies.containsKey(key);
    }

    public void save(String key) {
        if (!frequencies.containsKey(key)) {
            neurons.add(key);
            int position = neurons.size() - 1;
            frequencies.put(key, new Movement(position / 3, position % 3));
        }
    }
  
    public int size() { 
        return neurons.size();      
    }
    
    public Movement getFrequency(String key) {
        return frequencies.get(key);
    }
    
    public void clear() {
        neurons.clear();
        frequencies.clear();
    }
}
