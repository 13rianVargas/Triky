package gfutria.controller;

import gfutria.view.Consola;
import gfutria.model.Triky;
import gfutria.view.InterfazApp;
import javax.swing.JOptionPane;

public class Controlador {
    private Triky tablero;
    private boolean finJuego = true;

    public void run() {
        //GUI CAMI//
        InterfazApp panta = new InterfazApp(this); // Pasar el controlador
        panta.setVisible(true);
        panta.setLocationRelativeTo(null);
        
        //CONSOLE BRI//
        tablero = new Triky();
        
        //TODO: Opcion "I Want to Learn"
        int aprender = Consola.pedirInt();
        tablero.setLearning(aprender == 1);

//        Consola.mostrarMensaje("El jugador es -> X");
//        Consola.mostrarMensaje("La maquina es -> O");
//
//        mostrarTableroPorConsola(tablero.getTablero());
//        Consola.mostrarMensaje("El jugador empieza");
        
        while (finJuego) {
            //JUGADOR//
            boolean movimientoValido = false;
            while (!movimientoValido) {
                // FILA
//                Consola.mostrarMensaje("Ingrese la fila donde desea colocar su simbolo:");
                int i = Consola.pedirInt(); //TODO: Guardar posición en X de la Ventana
                while (i < 0 || i > 2) {
//                    Consola.mostrarMensaje("Posicion invalida, ingrese nuevamente la fila:");
                    i = Consola.pedirInt(); //TODO: Guardar posición en X de la Ventana
                }
                // COLUMNA
//                Consola.mostrarMensaje("Ingrese la columna donde desea colocar su simbolo:");
                int j = Consola.pedirInt(); //TODO: Guardar posición en Y de la Ventana
                while (j < 0 || j > 2) {
//                    Consola.mostrarMensaje("Posicion invalida, ingrese nuevamente la columna:");
                    j = Consola.pedirInt(); //TODO: Guardar posición en Y de la Ventana
                }

                movimientoValido = humanPlayed(i, j);
            }

            if (!finJuego) {
                break;
            }

            machinePlays();
        }
    }
    
    public void limpiarMemoria(){ //TODO: Conectar con botón "Reset D.B."
        tablero.getMemory().clear();
    }

    public boolean humanPlayed(int i, int j) {
        boolean movimientoValido = false;
        movimientoValido = tablero.humanPlayed(i,j);
        getStatus(); //TODO: Revisar que se envie correctamente el tablero a la GUI
//        mostrarTableroPorConsola(tablero.getTablero());
        if (hayGanador()) {
//            Consola.mostrarMensaje("El jugador ha ganado!");
            //TODO: Cambiar el mensaje y el JOptionPane
            JOptionPane.showMessageDialog(null, "Memory has been cleared.", "Memory", JOptionPane.INFORMATION_MESSAGE);      
            if (tablero.isLearning()) {
                tablero.gameOver(false);
            }
            finJuego = false;
        } else if (tableroLleno()) {
//            Consola.mostrarMensaje("¡El juego ha terminado en empate!");
            //TODO: Cambiar el mensaje y el JOptionPane
            JOptionPane.showMessageDialog(null, "Memory has been cleared.", "Memory", JOptionPane.INFORMATION_MESSAGE);     
            finJuego = false;
        }
        return movimientoValido;
    }

    public void machinePlays() {
        tablero.machinePlays();
        getStatus(); //TODO: Revisar que se envie correctamente el tablero a la GUI
//        mostrarTableroPorConsola(tablero.getTablero());
        if (hayGanador()) {
//            Consola.mostrarMensaje("La máquina ha ganado!");
            //TODO: Cambiar el mensaje y el JOptionPane
            JOptionPane.showMessageDialog(null, "Memory has been cleared.", "Memory", JOptionPane.INFORMATION_MESSAGE);
            if (tablero.isLearning()) {
                tablero.gameOver(true);
            }
            finJuego = false;
        } else if (tableroLleno()) {
//            Consola.mostrarMensaje("¡El juego ha terminado en empate!");
            //TODO: Cambiar el mensaje y el JOptionPane
            JOptionPane.showMessageDialog(null, "Memory has been cleared.", "Memory", JOptionPane.INFORMATION_MESSAGE);
            finJuego = false;
        }
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