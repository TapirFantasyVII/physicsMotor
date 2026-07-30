package physics;

import forms.Circle;

public class CircleBody extends Body {

    private final double radius;

    public CircleBody(World world, double x, double y, double radius, double mass) {
        super(world, x, y, radius * 2, radius * 2, mass);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getCenterX() {
        return getX() + radius;
    }

    public double getCenterY() {
        return getY() + radius;
    }

}
