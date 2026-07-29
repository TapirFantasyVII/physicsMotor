package view.render;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import forms.Figure;
import model.Model;

public class RenderManager {

    private final List<FigureRenderer> renderers = new ArrayList<>();

    public RenderManager() {

        renderers.add(new CircleRenderer());

        // Futuro
        // renderers.add(new RectangleRenderer());
        // renderers.add(new TriangleRenderer());
    }

    public void render(Graphics2D g2, Model model) {

        for (Figure figure : model.getFigures()) {

            for (FigureRenderer renderer : renderers) {

                if (renderer.supports(figure)) {
                    renderer.render(g2, figure);
                    break;
                }

            }

        }

    }

}