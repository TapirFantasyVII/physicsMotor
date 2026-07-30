package forms;

import java.awt.Color;
import physics.CircleBody;
import physics.World;

public class Circle extends Figure {

    public Circle(
            World world,
            int x,
            int y,
            Color color,
            double radius,
            double mass,
            String id) {

        super(
                new CircleBody(world, x, y, radius, mass),
                color,
                id
        );
    }

    @Override
    public CircleBody getBody() {
        return (CircleBody) super.getBody();
    }

    public double getRadius() {
        return getBody().getRadius();
    }

    @Override
    public String toString() {
        return super.toString() + " type=circle radius=" + getRadius();
    }
}
