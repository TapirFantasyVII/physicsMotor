package view.toolutils;

import javax.swing.*;
import java.awt.*;

public class DeletePanel extends JPanel {

    public DeletePanel() {

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(new JLabel("Haz clic sobre una figura para eliminarla."));

    }

}