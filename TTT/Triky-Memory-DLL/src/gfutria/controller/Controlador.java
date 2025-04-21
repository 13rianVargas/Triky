package gfutria.controller;

import gfutria.view.Consola;
import gfutria.model.Triky;
import gfutria.view.InterfazApp;
import javax.swing.JOptionPane;

public class Controlador {
    private Triky tablero;
    private boolean finJuego = true;
    private boolean ataque = false;
    private boolean defensa = false; 
    private boolean aprender = false; 
    private boolean firstBlank = false;
    private boolean random = false;

    public void run() {
        //GUI CAMI//
        InterfazApp panta = new InterfazApp(this); // Pasar el controlador
        panta.setVisible(true);
        panta.setLocationRelativeTo(null);
        
        //CONSOLE BRI//
        tablero = new Triky();

        ataque = false;
        defensa = false;
        aprender = false; //TODO: Opcion "I Want to Learn" de la GUI
        firstBlank = false; //TODO: Opcion "First blank" de la GUI
        random = false; //TODO: Opcion "Random" de la GUI

        tablero.setLearning(aprender);
        
        while (finJuego) {
            // La lógica de entrada por consola ya no es necesaria
            // El flujo de juego ahora depende de los clics en la interfaz gráfica
            if (!finJuego) {
                break;
            }

            machinePlays(); // La máquina juega después del movimiento del jugador
        }
    }
    
    public void limpiarMemoria(){ //TODO: Conectar con botón "Reset D.B." de la GUI
        tablero.getMemory().clear();
    }

    public boolean humanPlayed(int i, int j) {
        boolean movimientoValido = false;
        movimientoValido = tablero.humanPlayed(i,j);
        getStatus();
        if (hayGanador()) {
            //TODO: PRIMERO LA X LUEGO EL MENSAJE Y CORTAR LA EJECUCIÓN
            JOptionPane.showMessageDialog(null, "El jugador ha ganado!", "Ganador", JOptionPane.INFORMATION_MESSAGE);      
            if (tablero.isLearning()) {
                tablero.gameOver(false);
            }
            finJuego = false;
        } else if (tableroLleno()) {
            JOptionPane.showMessageDialog(null, "¡El juego ha terminado en empate!", "Empate", JOptionPane.INFORMATION_MESSAGE);     
            finJuego = false;
        }
        return movimientoValido;

        
    }

    public void machinePlays() {
        if (!finJuego) { // Verificar si el juego ya terminó
            return;
        }
        getStatus(); // Actualizar el estado del tablero
        boolean movimientoRealizado = false;

        // Primera prioridad: Ataque
        if (ataque) {
            movimientoRealizado = tablero.attackMachinePlays();
        }

        // Segunda prioridad: Defensa
        if (!movimientoRealizado && defensa) {
            movimientoRealizado = tablero.defenseMachinePlays();
        }

        // Tercera prioridad: Random o First Blank
        if (!movimientoRealizado) {
            if (aprender) {
                tablero.smartMachinePlays();
            } else if (random) {
                tablero.randomMachinePlays();
            } else if (firstBlank) {
                tablero.blankMachinePlays();
            }
        }

        // Verificar estado del juego
        if (hayGanador()) {
            //TODO: PRIMERO LA O LUEGO EL MENSAJE Y CORTAR LA EJECUCIÓN
            JOptionPane.showMessageDialog(null, "La máquina ha ganado!", "Ganador", JOptionPane.INFORMATION_MESSAGE);
            if (tablero.isLearning()) {
                tablero.gameOver(true);
            }
            finJuego = false;
        } else if (tableroLleno()) {
            JOptionPane.showMessageDialog(null, "¡El juego ha terminado en empate!", "Empate", JOptionPane.INFORMATION_MESSAGE);
            finJuego = false;
        }
    }

    private int contarFichas(char a, char b, char c, char ficha) {
        int count = 0;
        if (a == ficha) count++;
        if (b == ficha) count++;
        if (c == ficha) count++;
        return count;
    }

    private int contarEspaciosVacios(char a, char b, char c) {
        int count = 0;
        if (a == ' ' || a == '\0') count++;
        if (b == ' ' || b == '\0') count++;
        if (c == ' ' || c == '\0') count++;
        return count;
    }

    public Triky getStatus() {
        return tablero;
    }
    
    //Esto ya no se usa pero lo dejamos para el debug
    public void mostrarTableroPorConsola(char[][] tablero) {
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