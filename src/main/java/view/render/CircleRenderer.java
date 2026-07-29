package view.render;

import java.awt.Color;
import java.awt.Graphics2D;

import forms.Circle;
import forms.Figure;
import physics.CircleBody;
import view.DebugRenderer;

public class CircleRenderer implements FigureRenderer {

    @Override
    public boolean supports(Figure figure) {
        return figure instanceof Circle;
    }

    @Override
    public void render(Graphics2D g2, Figure figure) {

        Circle circle = (Circle) figure;
        CircleBody body = (CircleBody) circle.getBody();

        int r = (int) body.getRadius();
        int cx = (int) body.getCenterX();
        int cy = (int) body.getCenterY();

        // Creamos una copia para no afectar al resto del render
        Graphics2D g = (Graphics2D) g2.create();

        // Origen en el centro del círculo
        g.translate(cx, cy);

        // Rotación del cuerpo (en radianes)
        g.rotate(body.getRotation());

        g.setColor(circle.getColor());
        g.fillOval(-r, -r, r * 2, r * 2);



        if (DebugRenderer.SHOW_BODY_DEBUG) {

            g.setColor(Color.BLACK);

            g.drawLine(-r, 0, r, 0);
            g.drawLine(0, -r, 0, r);

            int d = (int) (r / Math.sqrt(2));

            g.drawLine(-d, -d, d, d);
            g.drawLine(-d, d, d, -d);

            g.setColor(Color.RED);
            g.drawOval(-r, -r, r * 2, r * 2);
        }

        g.dispose();
    }
}