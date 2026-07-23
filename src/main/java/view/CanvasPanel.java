package view;

import javax.swing.*;

import model.Model;
import physics.World;

import java.awt.*;

public class CanvasPanel extends JPanel {

    private static final int GRID = 50;
    private final Model model;

    public CanvasPanel(Model model) {

        this.model = model;

        setLayout(null);
        setBackground(Color.WHITE);

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        World world = model.getWorld();

        g2.setColor(Color.RED);

        g2.drawRect(
                0,
                0,
                (int) world.getWidth() - 1,
                (int) world.getHeight() - 1);

        if (DebugRenderer.SHOW_GRID) {

            g2.setColor(new Color(235, 235, 235));

            for (int x = 0; x < getWidth(); x += GRID) {

                g2.drawLine(x, 0, x, getHeight());

            }

            for (int y = 0; y < getHeight(); y += GRID) {

                g2.drawLine(0, y, getWidth(), y);

            }

        }

        if (DebugRenderer.SHOW_COORDINATES) {

            g2.setColor(Color.GRAY);

            for (int x = 0; x < getWidth(); x += GRID) {

                g2.drawString(String.valueOf(x), x + 2, 12);

            }

            for (int y = 0; y < getHeight(); y += GRID) {

                g2.drawString(String.valueOf(y), 2, y + 12);

            }

        }

        if (DebugRenderer.SHOW_BOUNDS) {

            g2.setColor(Color.BLACK);

            g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        }

        if (DebugRenderer.SHOW_AXIS) {

            g2.setColor(Color.RED);
            g2.drawLine(0, 0, 100, 0);

            g2.setColor(Color.BLUE);
            g2.drawLine(0, 0, 0, 100);

        }

    }

}