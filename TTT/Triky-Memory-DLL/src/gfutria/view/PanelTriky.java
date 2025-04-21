package gfutria.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelTriky extends JPanel {

    public PanelTriky() {
        setBackground(new Color(0, 0, 0));
        setPreferredSize(new Dimension(500, 500));
        setLayout(new GridLayout(3, 3, 10, 10)); // Grid layout for 3x3 grid

        for (int i = 1; i <= 9; i++) {
            JLabel label = new JLabel("X", SwingConstants.CENTER);
            label.setFont(new Font("Tahoma", Font.BOLD, 60));
            label.setForeground(new Color(255, 0, 51));
            label.setOpaque(true);
            label.setBackground(Color.WHITE);
            label.setPreferredSize(new Dimension(100, 100));
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    labelMousePressed(evt);
                }
            });
            add(label);
        }
    }

    private void labelMousePressed(MouseEvent evt) {
        // TODO: Add your handling code here
    }
}
