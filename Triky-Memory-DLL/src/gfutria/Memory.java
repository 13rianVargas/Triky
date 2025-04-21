package gfutria;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.JOptionPane;

/**
 * @since 10/06/2024
 * @version 1.0
 * @author gfutria
 */
public class Memory
{
  private int row, col;    
  /**
   * Constructor.
   */  
  public Memory() 
  { row = col = 0;     
  }

  /**
   * Define where to play.
   * AI is learning.
   * @param triky : Game board .
   * @return true   
   */
  public boolean remember(int triky[][])
  { 
    System.out.println("Knowledge...");
        
    return false;
  }

  /**
   * @return game row.
   */
  public int getRow() { return row; }

  /**
   * @return game col.
   */
  public int getCol() { return col; }
  
  /**
   * AI is informed, as the game ends. 
   */
  public void gameOver()
  {       
  } 
  
  /**
   * Clean memory.
   */
  public void clear()
  { JOptionPane.showMessageDialog(null, "This requirement was not implemented.", "Memory", JOptionPane.INFORMATION_MESSAGE);      
  }        
  
  /**
   * Memory list.
   * @return lst : Memory list.
   */
  public ArrayList <String> brain()
  {  
    return null;
  }        
}

