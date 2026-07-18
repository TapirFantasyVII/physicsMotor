package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import model.Model;
import view.View;
public class Controller {

    private Model model;
    private View view;
    private final Timer loopTimer;
    private int worldWidth = 1080;
    private int worldHeight = 720;
    private long lastTime;   // sin inicializar aquí

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        this.loopTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long now = System.nanoTime();
                double dt = (now - lastTime) / 1_000_000_000.0;
                lastTime = now;
                dt = Math.min(dt, 0.05); 
                model.actEstate(dt, worldWidth, worldHeight);
                view.render();
            }
        });
    }

    public void startMotor() {
        lastTime = System.nanoTime();   // <-- se captura justo antes de arrancar
        loopTimer.start();
    }
}