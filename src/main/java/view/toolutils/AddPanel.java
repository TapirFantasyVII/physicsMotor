package view.toolutils;

import javax.swing.*;
import java.awt.*;

public class AddPanel extends JPanel {

    public AddPanel() {

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(new JLabel("Figura"));

        JComboBox<String> combo = new JComboBox<>();

        combo.addItem("Círculo");
        combo.addItem("Rectángulo");
        combo.addItem("Triángulo");

        add(combo);

        add(new JButton("Crear"));

    }

}