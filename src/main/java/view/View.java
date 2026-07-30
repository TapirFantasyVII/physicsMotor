package view;

import forms.Figure;
import model.FigureListener;
import model.Model;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements FigureListener {

    private final Model model;

    private final CanvasPanel canvas;
    private final InfoPanel infoPanel;
    private final ToolPanel toolPanel;

    public View(Model model) {

        this.model = model;

        canvas = new CanvasPanel(model);
        infoPanel = new InfoPanel();
        toolPanel = new ToolPanel();

        model.addFigureListener(this);

        setTitle("Motor 2D");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setSize(ViewConfig.GRIDWIDTH, ViewConfig.GRIDHEIGHT);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        add(canvas, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);
        add(toolPanel, BorderLayout.SOUTH);
    }

    public void mostrar() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    @Override
    public void onFigureAdded(Figure figure) {
 
        canvas.repaint();
    }

    public void render() {

        canvas.repaint();
        infoPanel.update(model.getFigures());

    }

    public int getCanvasWidth() {
        return canvas.getWidth();
    }

    public int getCanvasHeight() {
        return canvas.getHeight();
    }

    public ToolPanel getToolPanel() {
        return toolPanel;
    }

    public CanvasPanel getCanvas() {
        return canvas;
    }    
}