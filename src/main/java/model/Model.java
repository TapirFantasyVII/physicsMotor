package model;

import controller.*;
import forms.*;
import java.awt.Color;
import java.awt.Cursor;
import java.util.LinkedList;
import java.util.List;
import physics.*;

public class Model {

    private final List<Figure> figures;
    private final List<FigureListener> listeners;
    World world;

    private final EditorCursor editorCursor;

    private EditorMode editorMode;

    private int figureCnt = 0;
    int animation = 0;

    public Model() {
        world = new World();
        figures = new LinkedList<>();
        listeners = new LinkedList<>();
        editorCursor = new EditorCursor();
        editorMode = EditorMode.NONE;

    }

    public void setupDemo() {

        addCircle(100, 100, Color.GREEN, 40, 1.0);
        addCircle(300, 150, Color.RED, 40, 1.0);

        figures.get(0).applyForce(new Vector2d(5000, -20000));
        figures.get(1).applyForce(new Vector2d(-5000, -20000));
    }

    public void actEstate(double dt) {
        animation++;
        for (Figure f : figures) {
            f.applyForce(world.getGravity().scale(f.getMass()));
            f.applyFriction();
            f.integrate(dt);
            f.getBody().trySleep();
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

    public void actCursorPosition(int x, int y) {
        if (x <= world.getWidth() && x >= 0
                && y >= 0 && y <= world.getHeight()) {

            editorCursor.setPosition(x, y);
        }
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
    public void addCircle(int posX, int posY, Color color, int rad, double weight) {
        addFigure(
                new Circle(world, posX, posY, color,
                        rad, weight, "circle_" + figureCnt)
        );
    }

    public void addCircle(Color color, int rad, double weight) {
        addFigure(
                new Circle(world, editorCursor.getWorldX()-rad, editorCursor.getWorldY()-rad, color,
                        rad, weight, "circle_" + figureCnt)
        );
    }

    public void startAddMode() {
        editorMode = EditorMode.ADD;
        editorCursor.show();

    }

    public void startModifyMode() {
        editorMode = EditorMode.MODIFY;
        editorCursor.show();

    }

    public void startDeleteMode() {
        editorMode = EditorMode.DELETE;
        editorCursor.show();

    }

    public void stopEditorMode() {
        editorMode = EditorMode.NONE;
        editorCursor.hide();
    }

    public void startWorldEditor() {
        editorMode = EditorMode.WORLD;
        editorCursor.hide();
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

    public EditorMode getEditorMode() {
        return editorMode;
    }

    public EditorCursor getEditorCursor() {
        return editorCursor;
    }
}
