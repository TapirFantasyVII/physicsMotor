package controller;

import java.awt.Color;
import javax.swing.Timer;

import model.Model;
import view.View;

public class Controller implements ToolPanelListener, EditorModeListener, CanvasListener {

    private final Model model;
    private final View view;

    private final Timer loopTimer;
    private long lastTime;

    public Controller(Model model, View view) {

        this.model = model;
        this.view = view;

        view.getToolPanel().setToolPanelListeners(this,this);
        view.getCanvas().setCanvasListener(this);

        loopTimer = new Timer(16, e -> update());
    }

    private void update() {

        long now = System.nanoTime();
        double dt = (now - lastTime) / 1_000_000_000.0;
        lastTime = now;

        dt = Math.min(dt, 0.05);

        model.actEstate(dt);
        view.render();

    }

    @Override
    public void onCreateCircle(int radius, double mass, Color color) {
        model.addCircle(color, radius, mass);
    }

    public void startMotor() {
        lastTime = System.nanoTime();
        loopTimer.start();
    }

    @Override
    public void onAddMode() {
        System.out.println("add");
        model.startAddMode();
    }

    @Override
    public void onModifyMode() {
        System.out.println("modify");
        model.startModifyMode();
    }

    @Override
    public void onDeleteMode() {
        System.out.println("delete");
        model.startDeleteMode();
    }

    @Override
    public void onExitMode() {
        System.out.println("none");
        model.stopEditorMode();
    }
    
    @Override
    public void onModifyWorld(){
        System.out.println("world");
        model.startWorldEditor();
    }

    @Override
    public void onCanvasClicked(int x, int y) {
        model.actCursorPosition(x, y);
    }
}
