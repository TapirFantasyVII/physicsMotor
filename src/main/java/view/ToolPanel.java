package view;

import controller.Tool;
import controller.ToolPanelListener;
import view.toolutils.*;

import javax.swing.*;
import java.awt.*;

public class ToolPanel extends JPanel {

    private ToolPanelListener listener;

    private final AddPanel addPanel;

    private final CardLayout cards;
    private final JPanel contentPanel;

    public static final String ADD = "ADD";
    public static final String DELETE = "DELETE";
    public static final String MODIFY = "MODIFY";
    public static final String WORLD = "WORLD";

    public ToolPanel() {

        setLayout(new BorderLayout());

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton addButton = new JButton("Añadir");
        JButton deleteButton = new JButton("Eliminar");
        JButton modifyButton = new JButton("Modificar");
        JButton worldButton = new JButton("Mundo");

        toolbar.add(addButton);
        toolbar.add(deleteButton);
        toolbar.add(modifyButton);
        toolbar.add(worldButton);

        cards = new CardLayout();
        contentPanel = new JPanel(cards);

        addPanel = new AddPanel();

        contentPanel.add(addPanel, ADD);
        contentPanel.add(new DeletePanel(), DELETE);
        contentPanel.add(new ModifyPanel(), MODIFY);
        contentPanel.add(new WorldPanel(), WORLD);

        add(toolbar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> cards.show(contentPanel, ADD));

        deleteButton.addActionListener(e -> {
            cards.show(contentPanel, DELETE);

            if (listener != null) {
         //       listener.onToolSelected(Tool.DELETE);
            }
        });

        modifyButton.addActionListener(e -> {
            cards.show(contentPanel, MODIFY);

            if (listener != null) {
         //       listener.onToolSelected(Tool.MODIFY);
            }
        });

        worldButton.addActionListener(e -> {
            cards.show(contentPanel, WORLD);

            if (listener != null) {
        //        listener.onToolSelected(Tool.WORLD_SETTINGS);
            }
        });

        cards.show(contentPanel, ADD);

    }

    public void setToolPanelListener(ToolPanelListener listener) {
        this.listener = listener;
        addPanel.setToolPanelListener(listener);
    }
}
