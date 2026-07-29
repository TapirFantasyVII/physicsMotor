package view;

import controller.CanvasListener;
import model.EditorCursor;
import model.Model;
import physics.World;
import view.render.RenderManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CanvasPanel extends JPanel {

    private static final int GRID = 50;

    private final Model model;
    private final RenderManager renderManager;

    private CanvasListener listener;

    public CanvasPanel(Model model) {

        this.model = model;
        this.renderManager = new RenderManager();

        setLayout(null);
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                if (listener != null) {
                    listener.onCanvasClicked(e.getX(), e.getY());
                }

            }

        });

    }

    public void setCanvasListener(CanvasListener listener) {
        this.listener = listener;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        drawWorld(g2);

        if (DebugRenderer.SHOW_GRID) {
            drawGrid(g2);
        }

        if (DebugRenderer.SHOW_COORDINATES) {
            drawCoordinates(g2);
        }

        if (DebugRenderer.SHOW_BOUNDS) {
            drawBounds(g2);
        }

        if (DebugRenderer.SHOW_AXIS) {
            drawAxis(g2);
        }

        // Aquí se dibujan TODAS las figuras
        renderManager.render(g2, model);

        drawEditorCursor(g2);

    }

    private void drawWorld(Graphics2D g2) {

        World world = model.getWorld();

        g2.setColor(Color.RED);

        g2.drawRect(
                0,
                0,
                (int) world.getWidth() - 1,
                (int) world.getHeight() - 1);

    }

    private void drawGrid(Graphics2D g2) {

        g2.setColor(new Color(235, 235, 235));

        for (int x = 0; x < getWidth(); x += GRID) {
            g2.drawLine(x, 0, x, getHeight());
        }

        for (int y = 0; y < getHeight(); y += GRID) {
            g2.drawLine(0, y, getWidth(), y);
        }

    }

    private void drawCoordinates(Graphics2D g2) {

        g2.setColor(Color.GRAY);

        for (int x = 0; x < getWidth(); x += GRID) {
            g2.drawString(String.valueOf(x), x + 2, 12);
        }

        for (int y = 0; y < getHeight(); y += GRID) {
            g2.drawString(String.valueOf(y), 2, y + 12);
        }

    }

    private void drawBounds(Graphics2D g2) {

        g2.setColor(Color.BLACK);
        g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

    }

    private void drawAxis(Graphics2D g2) {

        g2.setColor(Color.RED);
        g2.drawLine(0, 0, 100, 0);

        g2.setColor(Color.BLUE);
        g2.drawLine(0, 0, 0, 100);

    }

    private void drawEditorCursor(Graphics2D g2) {

        EditorCursor cursor = model.getEditorCursor();

        if (!cursor.isVisible()) {
            return;
        }

        int x = cursor.getWorldX();
        int y = cursor.getWorldY();

        g2.setColor(Color.BLACK);

        g2.drawLine(x - 10, y, x + 10, y);
        g2.drawLine(x, y - 10, x, y + 10);

        g2.setColor(Color.RED);

        g2.drawOval(x - 5, y - 5, 10, 10);

    }

}