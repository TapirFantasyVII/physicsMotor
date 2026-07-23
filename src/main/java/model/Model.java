package model;

import forms.*;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import physics.*;

public class Model {

    private final List<Figure> figures;
    private final List<FigureListener> listeners;
    World world;

    private int figureCnt = 0;
    int animation = 0;

    public Model() {
        world = new World();
        figures = new LinkedList<>();
        listeners = new LinkedList<>();
    }

    public void setupDemo() {
        addCircle(0, 200, 80);
        figures.get(0).applyForce(new Vector2d(-5000, -20000));
        addCircle(50, 100, 80);
        figures.get(0).setColor(Color.RED);
    }

    public void actEstate(double dt) {
        animation++;
        for (Figure f : figures) {
            f.applyForce(world.getGravity().scale(f.getMass()));
            f.applyFriction();
            f.integrate(dt);
            f.resolveWorldBounds(
                    (int) world.getWidth(),
                    (int) world.getHeight(),
                    world.getRestitution());
        }

        for (int i = 0; i < figures.size(); i++) {
            for (int j = i + 1; j < figures.size(); j++) {
                figures.get(i).resolveCollision(figures.get(j));
            }
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
    /*
     * 
     * public void addTriangle() {
     * addFigure(new Triangle(10, 20, Color.ORANGE, 40, 40, "triangle_" +
     * figureCnt));
     * }
     * 
     * public void addRectangle() {
     * addFigure(new Rectangle(100, 50, Color.BLACK, 40, 40, "rectangle_" +
     * figureCnt));
     * }
     */

    public void addCircle(int posX, int posY, int tam) {
        addFigure(new Circle(world, posX, posY, Color.yellow, tam, "circle_" + figureCnt));
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public int getFigureCnt() {
        return figureCnt;
    }

    public World getWorld() {
        return world;
    }
}