package gfutria;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @since 11/11/2024
 * @version 1.1
 * @author gfutria
 */
public class Neurons implements Serializable
{ private ArrayList<String> neurons;

  public Neurons() 
  { neurons = new ArrayList<String>(); }
   
  public ArrayList<String> getNeurons() { return neurons; }

  public boolean verify(String key)
  { if (neurons.contains(key)) return true;
    return false;
  }

  public void save(String key)
  { neurons.add(key);
  }
  
  public int size()
  { return neurons.size();      
  }
  
   
}
