package forms;

import java.awt.Color;
import javax.swing.JPanel;
import physics.Body;
import physics.PhysicsConfig;
import physics.Vector2d;

public abstract class Figure {

    private final String id;
    private final Color color;
    protected final Body body;

    protected Figure(Body body, Color color, String id) {
        this.body = body;
        this.color = color;
        this.id = id;
    }

    public Body getBody() {
        return body;
    }

    public Color getColor() {
        return color;
    }

    public String getId() {
        return id;
    }
}
