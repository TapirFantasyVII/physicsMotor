package view;

import javax.swing.*;
import java.awt.*;
import view.toolutils.*;

public class ToolPanel extends JPanel {

    private final CardLayout cards;
    private final JPanel contentPanel;

    public static final String ADD = "ADD";
    public static final String DELETE = "DELETE";
    public static final String MODIFY = "MODIFY";
    public static final String WORLD = "WORLD";

    public ToolPanel() {

        setLayout(new BorderLayout());

        // ------------------------------------
        // Barra superior
        // ------------------------------------

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton addButton = new JButton("Añadir");
        JButton deleteButton = new JButton("Eliminar");
        JButton modifyButton = new JButton("Modificar");
        JButton worldButton = new JButton("Mundo");

        toolbar.add(addButton);
        toolbar.add(deleteButton);
        toolbar.add(modifyButton);
        toolbar.add(worldButton);

        // ------------------------------------
        // Panel dinámico
        // ------------------------------------

        cards = new CardLayout();
        contentPanel = new JPanel(cards);

        contentPanel.add(new AddPanel(), ADD);
        contentPanel.add(new DeletePanel(), DELETE);
        contentPanel.add(new ModifyPanel(), MODIFY);
        contentPanel.add(new WorldPanel(), WORLD);

        // ------------------------------------

        add(toolbar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        // ------------------------------------

        addButton.addActionListener(e -> cards.show(contentPanel, ADD));
        deleteButton.addActionListener(e -> cards.show(contentPanel, DELETE));
        modifyButton.addActionListener(e -> cards.show(contentPanel, MODIFY));
        worldButton.addActionListener(e -> cards.show(contentPanel, WORLD));

        cards.show(contentPanel, ADD);

    }

}