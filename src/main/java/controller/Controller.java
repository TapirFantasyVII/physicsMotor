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

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        this.loopTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                model.actEstate(0.016, 1080,720 );
                view.repaint();
            }
        });
    }

    public void startMotor() {
        loopTimer.start();
    }
}
