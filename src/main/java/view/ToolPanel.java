package view;

import controller.EditorModeListener;
import controller.ToolPanelListener;
import view.toolutils.*;

import javax.swing.*;
import java.awt.*;

public class ToolPanel extends JPanel {

    private ToolPanelListener toolListener;
    private EditorModeListener modeListener;

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

        addButton.addActionListener(e -> {

            cards.show(contentPanel, ADD);
            if (modeListener != null) {
                modeListener.onAddMode();
            }
        });

        deleteButton.addActionListener(e -> {
            cards.show(contentPanel, DELETE);

            if (modeListener != null) {
                modeListener.onDeleteMode();
            }
        });

        modifyButton.addActionListener(e -> {
            cards.show(contentPanel, MODIFY);

            if (modeListener != null) {
                modeListener.onModifyMode();
            }
        });

        worldButton.addActionListener(e -> {
            cards.show(contentPanel, WORLD);

            if (modeListener != null) {
                modeListener.onModifyWorld();
            }
        });

        cards.show(contentPanel, ADD);
        
    }

    public void setToolPanelListeners(ToolPanelListener listener0, EditorModeListener listener1) {
        this.toolListener = listener0;
        this.modeListener = listener1;
        addPanel.setToolPanelListener(listener0);
        modeListener.onAddMode(); //esto se debe modificar
    }
}
