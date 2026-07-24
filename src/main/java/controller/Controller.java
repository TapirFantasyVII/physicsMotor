package controller;

import java.awt.Color;
import javax.swing.Timer;

import model.Model;
import view.View;

public class Controller implements ToolPanelListener {

    private final Model model;
    private final View view;

    private final Timer loopTimer;
    private long lastTime;

    public Controller(Model model, View view) {

        this.model = model;
        this.view = view;

        view.getToolPanel().setToolPanelListener(this);

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
    public void onCreateCircle(  int radius, double mass, Color color) {
        model.addCircle(0, 0, color, radius, mass);
    }

    public void startMotor() {
        lastTime = System.nanoTime();
        loopTimer.start();
    }

}
