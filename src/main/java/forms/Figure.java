package forms;

import java.awt.Color;
import javax.swing.JPanel;
import physics.PhysicsConfig;
import physics.Vector2d;

public abstract class Figure extends JPanel {

    private static final Vector2d GRAVITY = new Vector2d(0, 500);
    private Color color;
    private String id;

    // --- estado físico ---
    protected Vector2d position;
    protected Vector2d velocity = Vector2d.zero();
    protected Vector2d forceAccum = Vector2d.zero();
    protected double mass = 1.0;
    protected boolean grounded = false;

    public Figure(int x, int y, Color color, String id) {
        this.position = new Vector2d(x, y);
        super.setLocation(x, y);
        setOpaque(false);
        this.id = id;
        this.color = color;
    }

    public void applyForce(Vector2d f) {
        forceAccum = forceAccum.add(f);
    }

    public void integrate(double dt) {
        Vector2d acceleration = forceAccum.scale(1.0 / mass);
        velocity = velocity.add(acceleration.scale(dt));
        position = position.add(velocity.scale(dt));

        setLocation((int) Math.round(position.x), (int) Math.round(position.y));

        forceAccum = Vector2d.zero();
    }

    public void resolveWorldBounds(int worldWidth, int worldHeight, double restitution) {
        boolean bounced = false;

        // eje X
        if (position.x < 0) {
            position.x = 0;
            velocity.x = -velocity.x * restitution;
            bounced = true;
        } else if (position.x + getWidth() > worldWidth) {
            position.x = worldWidth - getWidth();
            velocity.x = -velocity.x * restitution;
            bounced = true;
        }

        // eje Y
        if (position.y < 0) {
            position.y = 0;
            velocity.y = -velocity.y * restitution;
            bounced = true;
        } else if (position.y + getHeight() > worldHeight) {
            position.y = worldHeight - getHeight();
            velocity.y = -velocity.y * restitution;
            bounced = true;
        }

        if (bounced) {
            setLocation((int) Math.round(position.x), (int) Math.round(position.y));
        }
    }

    protected void updateGroundedState(int worldHeight) {
        double epsilon = 0.5;  
        grounded = (position.y + getHeight() >= worldHeight - epsilon);
    }
    public void applyFriction() {
    if (grounded) {
        double frictionForceX = -velocity.x * PhysicsConfig.GROUND_FRICTION;
        applyForce(new Vector2d(frictionForceX, 0));
        if (Math.abs(velocity.x) < 0.5) {
            velocity.x = 0;
        }
    } else {
        Vector2d airFriction = velocity.scale(-PhysicsConfig.AIR_FRICTION);
        applyForce(airFriction);
    }
}

    public double getMass() {
        return mass;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "ID= " + id + " color=R" + color.getRed() + " G" + color.getGreen() + " B" + color.getBlue() + " pos=" + getX() + " , " + getY() + " | ";
    }
}
