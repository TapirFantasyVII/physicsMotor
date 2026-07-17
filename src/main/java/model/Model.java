package model;

import forms.*;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import physics.PhysicsConfig;
import physics.Vector2d;

public class Model {
 
    private final List<Figure> figures;
    private int figureCnt = 0;

    int animation =0;

    public Model() {
        figures = new LinkedList<>();
        addCircle();
        addRectangle() ;
        addTriangle();
        
        figures.get(0).applyForce(new Vector2d(-5000,-20000)); //fuerza de ejemplo
    }



public void actEstate(double dt,int worldWidth, int worldHeight) {
    animation++;
    for (Figure f : figures) {
        f.applyForce(PhysicsConfig.GRAVITY_VECTOR.scale(f.getMass())); // F = m*g
        f.applyFriction();
        f.integrate(dt);
        f.resolveWorldBounds(worldWidth, worldHeight, PhysicsConfig.RESTITUTION);
 
    }
}
    public void addTriangle() {

        figures.add(new Triangle(10, 20, Color.ORANGE, 40, 40, "triangle_" + figureCnt));
        figureCnt++;
    }
    public void addRectangle() {

        figures.add(new Rectangle(100, 50, Color.BLACK, 40, 40, "rectangle_" + figureCnt));
        figureCnt++;
    }

    public void addCircle() {
        figures.add(new Circle(00, 200, Color.yellow, 80, "circle_" + figureCnt));
        figureCnt++;
        
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public int getFigureCnt() {
        return figureCnt;
    }
}
