package view.toolutils;

import controller.ToolPanelListener;

import javax.swing.*;
import java.awt.*;

public class AddPanel extends JPanel {

    private ToolPanelListener listener;

    private final JComboBox<String> figureCombo;
    private final JPanel propertiesPanel;
    private final CardLayout cards;

    // --------- Campos círculo ---------

    private final JTextField idField;
    private final JSpinner radiusSpinner;
    private final JSpinner massSpinner;
    private final JButton colorButton;

    private Color selectedColor = Color.YELLOW;

    public AddPanel() {

        setLayout(new BorderLayout(10, 10));

        //-------------------------------
        // Selección de figura
        //-------------------------------

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));

        top.add(new JLabel("Figura"));

        figureCombo = new JComboBox<>();

        figureCombo.addItem("Círculo");
        figureCombo.addItem("Rectángulo");
        figureCombo.addItem("Triángulo");

        top.add(figureCombo);

        add(top, BorderLayout.NORTH);

        //-------------------------------
        // Panel de propiedades
        //-------------------------------

        cards = new CardLayout();
        propertiesPanel = new JPanel(cards);

        //-------------------------------
        // CÍRCULO
        //-------------------------------

        JPanel circlePanel = new JPanel(new GridLayout(4, 2, 8, 8));

        idField = new JTextField("circle1");

        radiusSpinner = new JSpinner(
                new SpinnerNumberModel(30, 5, 500, 1));

        massSpinner = new JSpinner(
                new SpinnerNumberModel(1.0, 0.1, 1000.0, 0.1));

        colorButton = new JButton("Color");

        colorButton.addActionListener(e -> {

            Color c = JColorChooser.showDialog(
                    this,
                    "Seleccionar color",
                    selectedColor);

            if (c != null)
                selectedColor = c;

        });
        circlePanel.add(new JLabel("Radio"));
        circlePanel.add(radiusSpinner);

        circlePanel.add(new JLabel("Masa"));
        circlePanel.add(massSpinner);

        circlePanel.add(new JLabel("Color"));
        circlePanel.add(colorButton);

        //-------------------------------
        // RECTÁNGULO (vacío)
        //-------------------------------

        JPanel rectanglePanel = new JPanel();

        //-------------------------------
        // TRIÁNGULO (vacío)
        //-------------------------------

        JPanel trianglePanel = new JPanel();

        //-------------------------------

        propertiesPanel.add(circlePanel, "CIRCLE");
        propertiesPanel.add(rectanglePanel, "RECTANGLE");
        propertiesPanel.add(trianglePanel, "TRIANGLE");

        add(propertiesPanel, BorderLayout.CENTER);

        //-------------------------------
        // Crear
        //-------------------------------

        JButton createButton = new JButton("Crear");

        createButton.addActionListener(e -> {

            if (listener == null)
                return;

            switch (figureCombo.getSelectedIndex()) {

                case 0:
                   listener.onCreateCircle(getRadius(), getMass()  , selectedColor);
                    break;

                case 1:
                    //listener.onToolSelected(Tool.ADD_RECTANGLE);
                    break;

                case 2:
                   // listener.onToolSelected(Tool.ADD_TRIANGLE);
                    break;

            }

        });

        add(createButton, BorderLayout.SOUTH);

        //-------------------------------
        // Cambio de figura
        //-------------------------------

        figureCombo.addActionListener(e -> {

            switch (figureCombo.getSelectedIndex()) {

                case 0:
                    cards.show(propertiesPanel, "CIRCLE");
                    break;

                case 1:
                    cards.show(propertiesPanel, "RECTANGLE");
                    break;

                case 2:
                    cards.show(propertiesPanel, "TRIANGLE");
                    break;

            }

        });

        cards.show(propertiesPanel, "CIRCLE");

    }

    public void setToolPanelListener(ToolPanelListener listener) {
        this.listener = listener;
    }

    //==========================
    // Getters
    //==========================

    public String getFigureId() {
        return idField.getText();
    }

    public int getRadius() {
        return (Integer) radiusSpinner.getValue();
    }

    public double getMass() {
        return (Double) massSpinner.getValue();
    }

    public Color getColor() {
        return selectedColor;
    }

}