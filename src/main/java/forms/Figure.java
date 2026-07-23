package forms;

import java.awt.Color;
import javax.swing.JPanel;
import physics.Body;
import physics.PhysicsConfig;
import physics.Vector2d;

public abstract class Figure extends JPanel {

    private Color color;
    private String id;
    protected Body body;

    public Figure(int x, int y, Color color, String id) {
        this.body = null;
        this.id = id;
        this.color = color;
        setLocation(x, y);
        setOpaque(false);
    }

    // --- delegación a Body ---
    public void applyForce(Vector2d f) {
        body.applyForce(f);
    }

    public void integrate(double dt) {
        body.integrate(dt);
        syncViewPosition();
    }

    public void resolveWorldBounds(int worldWidth, int worldHeight, double restitution) {
        body.resolveWorldBounds(worldWidth, worldHeight, restitution);
        syncViewPosition();
    }

    public void applyFriction() {
        body.applyFriction();
    }

    private void syncViewPosition() {
        setLocation((int) Math.round(body.getX()), (int) Math.round(body.getY()));
    }

    public void resolveCollision(Figure other) {
        if (other != this) {
            body.resolveCollision(other.getBody());
            this.syncViewPosition();
            other.syncViewPosition();
        }

    }

    public double getMass() {
        return body.getMass();
    }

    public Body getBody() {
        return body;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "ID= " + id + " color=R" + color.getRed() + " G" + color.getGreen() + " B" + color.getBlue()
                + " pos=" + getX() + " , " + getY() + " | ";
    }
}