package view.toolutils;
 
import javax.swing.*;
import java.awt.*;

public class ModifyPanel extends JPanel {

    public ModifyPanel() {

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(new JLabel("Selecciona una figura para modificarla."));

    }

}