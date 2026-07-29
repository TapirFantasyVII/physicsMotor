package view.render;

import java.awt.Graphics2D;
import forms.Figure;

public interface FigureRenderer {

    boolean supports(Figure figure);

    void render(Graphics2D g2, Figure figure);

}