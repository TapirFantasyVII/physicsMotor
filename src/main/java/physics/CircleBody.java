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

    @Override
    public void resolveCollision(Body other) {

        if (other instanceof CircleBody) {
            resolveCollision_CircleToCircle((CircleBody) other);
        }

    }

    public void resolveCollision_CircleToCircle(CircleBody other) {

        double dx = other.getCenterX() - this.getCenterX();
        double dy = other.getCenterY() - this.getCenterY();
        double distSq = dx * dx + dy * dy;
        double sumRadii = this.radius + other.radius;

        if (distSq >= sumRadii * sumRadii) {
            return; // no colisionan
        }
        System.out.println("circle Collision");
        double distance = Math.sqrt(distSq);
        double nx, ny;
        if (distance < 1e-6) {

            nx = 1;
            ny = 0;
            distance = 0.0001;
        } else {
            nx = dx / distance;
            ny = dy / distance;
        }

        double overlap = sumRadii - distance;
        double totalMass = this.getMass() + other.getMass();
        double moveThis = overlap * (other.getMass() / totalMass);
        double moveOther = overlap * (this.getMass() / totalMass);

        this.translate(-nx * moveThis, -ny * moveThis);
        other.translate(nx * moveOther, ny * moveOther);

        Vector2d v1 = this.getVelocity();
        Vector2d v2 = other.getVelocity();

        double rvx = v2.x - v1.x;
        double rvy = v2.y - v1.y;
        double velAlongNormal = rvx * nx + rvy * ny;

        if (velAlongNormal > 0)
            return;

        double restitution = world.getRestitution();
        double j = -(1 + restitution) * velAlongNormal;
        j /= (1 / this.getMass() + 1 / other.getMass());

        double impulseX = j * nx;
        double impulseY = j * ny;

        this.setVelocity(new Vector2d(v1.x - impulseX / this.getMass(), v1.y - impulseY / this.getMass()));
        other.setVelocity(new Vector2d(v2.x + impulseX / other.getMass(), v2.y + impulseY / other.getMass()));
    }
}