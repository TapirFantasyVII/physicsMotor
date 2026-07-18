package model;

import forms.*;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import physics.PhysicsConfig;
import physics.Vector2d;

public class Model {

    private final List<Figure> figures;
    private final List<FigureListener> listeners;

    private int figureCnt = 0;
    int animation = 0;

    public Model() {
        figures = new LinkedList<>();
        listeners = new LinkedList<>();
    }

    public void setupDemo() {
        addCircle();
        addRectangle();
        addTriangle();
        figures.get(0).applyForce(new Vector2d(-5000, -20000));
    }

    public void actEstate(double dt, int worldWidth, int worldHeight) {
        animation++;
        for (Figure f : figures) {
            f.applyForce(PhysicsConfig.GRAVITY_VECTOR.scale(f.getMass())); // F = m*g
            f.applyFriction();
            f.integrate(dt);
            f.resolveWorldBounds(worldWidth, worldHeight, PhysicsConfig.RESTITUTION);

        }
    }

    public void addFigureListener(FigureListener listener) {
        listeners.add(listener);
    }

    private void addFigure(Figure f) {
        figures.add(f);
        figureCnt++;
        for (FigureListener l : listeners) {
            l.onFigureAdded(f);
        }
    }

    public void addTriangle() {

        addFigure(new Triangle(10, 20, Color.ORANGE, 40, 40, "triangle_" + figureCnt));

    }

    public void addRectangle() {

        addFigure(new Rectangle(100, 50, Color.BLACK, 40, 40, "rectangle_" + figureCnt));

    }

    public void addCircle() {
        addFigure(new Circle(00, 200, Color.yellow, 80, "circle_" + figureCnt));

    }

    public List<Figure> getFigures() {
        return figures;
    }

    public int getFigureCnt() {
        return figureCnt;
    }
}
