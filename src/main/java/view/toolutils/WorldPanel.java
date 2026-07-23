package view.toolutils;

import javax.swing.*;
import java.awt.*;

public class WorldPanel extends JPanel {

    public WorldPanel() {

        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Gravedad"));

        add(new JSlider(0, 1000, 500));

        add(new JLabel("Rebote"));

        add(new JSlider(0, 100, 80));

        add(new JLabel("Fricción aire"));

        add(new JSlider(0, 100, 5));

        add(new JLabel("Fricción suelo"));

        add(new JSlider(0, 100, 40));

    }

}