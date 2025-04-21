package gfutria.view;

import gfutria.controller.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelTriky extends JPanel {
    private final Controlador controlador;

    public PanelTriky(Controlador controlador) {
        this.controlador = controlador; // Guardar referencia al controlador
        setBackground(new Color(0, 0, 0));
        setPreferredSize(new Dimension(500, 500));
        setLayout(new GridLayout(3, 3, 10, 10)); // Grid layout para 3x3

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JLabel label = new JLabel("", SwingConstants.CENTER);
                label.setFont(new Font("Tahoma", Font.BOLD, 60));
                label.setForeground(new Color(255, 0, 51));
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                label.setPreferredSize(new Dimension(100, 100));
                int finalI = i; // Necesario para usar en el evento
                int finalJ = j;
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent evt) {
                        labelMousePressed(finalI, finalJ, label);
                    }
                });
                add(label);
            }
        }
    }

    private void labelMousePressed(int i, int j, JLabel label) {
        // Notificar al controlador del movimiento del jugador
        boolean movimientoValido = controlador.humanPlayed(i, j);
        if (movimientoValido) {
            label.setText("X"); // Actualizar la celda con el símbolo del jugador
            label.setEnabled(false); // Deshabilitar la celda después de jugar
        }
    }
}
