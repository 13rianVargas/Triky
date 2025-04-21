package gfutria.view;

import javax.swing.*;

public class LabelClicMouse extends JPanel {

    public LabelClicMouse() {
        JLabel jLabel10 = new JLabel("Hello, triky world...");
        JRadioButton jRadioButton1 = new JRadioButton("Random");
        JRadioButton jRadioButton2 = new JRadioButton("First blank");
        JLabel jLabel11 = new JLabel("Winner?");
        JLabel jLabel12 = new JLabel("HRHR");

        jRadioButton1.addActionListener(evt -> jRadioButton1ActionPerformed());
        jRadioButton2.addActionListener(evt -> jRadioButton2ActionPerformed());

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addComponent(jRadioButton1)
                        .addComponent(jRadioButton2)
                        .addComponent(jLabel11)
                        .addComponent(jLabel12))
                    .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(jLabel10)
                    .addGap(35, 35, 35)
                    .addComponent(jRadioButton1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jRadioButton2)
                    .addGap(48, 48, 48)
                    .addComponent(jLabel11)
                    .addGap(40, 40, 40)
                    .addComponent(jLabel12)
                    .addGap(46, 46, 46))
        );
    }

    private void jRadioButton1ActionPerformed() {
        // TODO: Add your handling code here
    }

    private void jRadioButton2ActionPerformed() {
        // TODO: Add your handling code here
    }
}
