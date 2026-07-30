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

        // ============================================================
        // FASE 1: Aplicar fuerzas e integrar movimiento
        // ============================================================
        for (Figure f : figures) {

            // Gravedad
            f.applyForce(world.getGravity().scale(f.getMass()));

            // Integración (Euler)
            f.integrate(dt);
        }

        // ============================================================
        // FASE 2: Resolver colisiones con los límites del mundo
        // ============================================================
        for (Figure f : figures) {

            f.resolveWorldBounds(
                    (int) world.getWidth(),
                    (int) world.getHeight(),
                    world.getRestitution());
        }

        // ============================================================
        // FASE 3: Solver de colisiones
        // ============================================================
        final int ITERATIONS = PhysicsConfig.COLISION_SOLVER_ITERATIONS;

        for (int k = 0; k < ITERATIONS; k++) {

            boolean reverse = ((animation + k) & 1) == 1;

            // ----------------------------
            // 3.1 Corregir penetraciones
            // ----------------------------
            if (!reverse) {

                for (int i = 0; i < figures.size(); i++) {
                    for (int j = i + 1; j < figures.size(); j++) {
                        figures.get(i).resolvePosition(figures.get(j));
                    }
                }

            } else {

                for (int i = figures.size() - 1; i >= 0; i--) {
                    for (int j = i - 1; j >= 0; j--) {
                        figures.get(i).resolvePosition(figures.get(j));
                    }
                }

            }

            // ----------------------------
            // 3.2 Resolver velocidades
            // ----------------------------
            if (!reverse) {

                for (int i = 0; i < figures.size(); i++) {
                    for (int j = i + 1; j < figures.size(); j++) {
                        figures.get(i).resolveVelocity(figures.get(j));
                    }
                }

            } else {

                for (int i = figures.size() - 1; i >= 0; i--) {
                    for (int j = i - 1; j >= 0; j--) {
                        figures.get(i).resolveVelocity(figures.get(j));
                    }
                }

            }
        }

        // ============================================================
        // FASE 4: Fricción y reposo
        // ============================================================
        for (Figure f : figures) {

            f.applyFriction();
            f.getBody().trySleep();
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
                new Circle(world, editorCursor.getWorldX() - rad, editorCursor.getWorldY() - rad, color,
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
