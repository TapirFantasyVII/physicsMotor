package model;

import controller.EditorMode;
import controller.EditorModeListener;
import forms.Circle;
import forms.Figure;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import physics.Body;
import physics.CollisionSolver;
import physics.PhysicsConfig;
import physics.Vector2d;
import physics.World;
import physics.solvers.FrictionSolver;
import physics.solvers.SleepSolver;
import physics.solvers.WorldSolver;

public class Model {

    private final List<Figure> figures = new LinkedList<>();
    private final List<FigureListener> listeners = new LinkedList<>();

    private final World world = new World();

    private final EditorCursor editorCursor = new EditorCursor();

    private EditorMode editorMode = EditorMode.NONE;

    private int figureCnt = 0;
    private int animation = 0;

    public void setupDemo() {

        addCircle(100, 100, Color.GREEN, 40, 1.0);
        addCircle(300, 150, Color.RED, 40, 1.0);

        figures.get(0).getBody().applyForce(new Vector2d(5000, -20000));
        figures.get(1).getBody().applyForce(new Vector2d(-5000, -20000));
    }

    public void actEstate(double dt) {

        animation++;

        // ============================================================
        // FASE 1: Aplicar fuerzas
        // ============================================================
        for (Figure figure : figures) {

            Body body = figure.getBody();

            body.applyForce(
                    world.getGravity().scale(body.getMass()));
        }

        // ============================================================
        // FASE 2: Integración
        // ============================================================
        for (Figure figure : figures) {

            figure.getBody().integrate(dt);
        }

        // ============================================================
        // FASE 3: Colisiones contra el mundo
        // ============================================================
        for (Figure figure : figures) {

            WorldSolver.solve(
                    figure.getBody(),
                    world);
        }

        // ============================================================
        // FASE 4: Colisiones entre cuerpos
        // ============================================================
        final int iterations = PhysicsConfig.COLISION_SOLVER_ITERATIONS;

// ----------------------------
// 4.1 Corregir penetraciones
// ----------------------------
        for (int k = 0; k < iterations; k++) {

            boolean reverse = ((animation + k) & 1) == 1;

            if (!reverse) {

                for (int i = 0; i < figures.size(); i++) {

                    for (int j = i + 1; j < figures.size(); j++) {

                        CollisionSolver.solvePosition(
                                figures.get(i).getBody(),
                                figures.get(j).getBody());

                    }
                }

            } else {

                for (int i = figures.size() - 1; i >= 0; i--) {

                    for (int j = i - 1; j >= 0; j--) {

                        CollisionSolver.solvePosition(
                                figures.get(i).getBody(),
                                figures.get(j).getBody());

                    }
                }
            }
        }

// ----------------------------
// 4.2 Resolver velocidades
// ----------------------------
        for (int i = 0; i < figures.size(); i++) {

            for (int j = i + 1; j < figures.size(); j++) {

                CollisionSolver.solveVelocity(
                        figures.get(i).getBody(),
                        figures.get(j).getBody());

            }
        }

        // ============================================================
        // FASE 5: Fricción
        // ============================================================
        for (Figure figure : figures) {

            FrictionSolver.solve(
                    figure.getBody(),
                    world);
        }

        // ============================================================
        // FASE 6: Dormir cuerpos
        // ============================================================
        for (Figure figure : figures) {

            SleepSolver.solve(
                    figure.getBody());
        }
    }

    private void addFigure(Figure figure) {

        figures.add(figure);

        figureCnt++;

        for (FigureListener listener : listeners) {
            listener.onFigureAdded(figure);
        }
    }

    public void addCircle(
            int x,
            int y,
            Color color,
            int radius,
            double mass) {

        addFigure(
                new Circle(
                        world,
                        x,
                        y,
                        color,
                        radius,
                        mass,
                        "circle_" + figureCnt
                )
        );
    }

    public void addCircle(
            Color color,
            int radius,
            double mass) {

        addCircle(
                editorCursor.getWorldX() - radius,
                editorCursor.getWorldY() - radius,
                color,
                radius,
                mass
        );
    }

    public void addFigureListener(FigureListener listener) {
        listeners.add(listener);
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public World getWorld() {
        return world;
    }

    public EditorCursor getEditorCursor() {
        return editorCursor;
    }

    public EditorMode getEditorMode() {
        return editorMode;
    }

    public void actCursorPosition(int x, int y) {

        if (x >= 0 && x <= world.getWidth()
                && y >= 0 && y <= world.getHeight()) {

            editorCursor.setPosition(x, y);
        }
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

    public void startWorldEditor() {

        editorMode = EditorMode.WORLD;
        editorCursor.hide();
    }

    public void stopEditorMode() {

        editorMode = EditorMode.NONE;
        editorCursor.hide();
    }

}
