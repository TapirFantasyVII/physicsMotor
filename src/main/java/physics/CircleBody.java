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

        if (!(other instanceof CircleBody circle)) {
            return;
        }

        resolvePosition(circle);
        resolveVelocity(circle);
    }
    @Override
    public void resolvePosition(Body Cother) {
        CircleBody other = (CircleBody) Cother;
        double dx = other.getCenterX() - this.getCenterX();
        double dy = other.getCenterY() - this.getCenterY();

        double distSq = dx * dx + dy * dy;
        double sumRadii = this.radius + other.radius;

        if (distSq >= sumRadii * sumRadii) {
            return;
        }

        double distance = Math.sqrt(distSq);

        double nx;
        double ny;

        if (distance < 1e-6) {
            nx = 1;
            ny = 0;
            distance = 0.0001;
        } else {
            nx = dx / distance;
            ny = dy / distance;
        }

        double overlap = sumRadii - distance;

        double correction
                = Math.max(overlap - PhysicsConfig.COLISION_PENETRATION, 0.0)
                * PhysicsConfig.COLISION_CORRECTION;

        double totalMass = getMass() + other.getMass();

        double moveThis = correction * other.getMass() / totalMass;
        double moveOther = correction * getMass() / totalMass;

        translate(-nx * moveThis, -ny * moveThis);
        other.translate(nx * moveOther, ny * moveOther);
    }
    
    @Override
    public void resolveVelocity(Body Cother) {
        CircleBody other = (CircleBody) Cother;
        double dx = other.getCenterX() - this.getCenterX();
        double dy = other.getCenterY() - this.getCenterY();

        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < 1e-6) {
            return;
        }

        double nx = dx / distance;
        double ny = dy / distance;

        Vector2d v1 = getVelocity();
        Vector2d v2 = other.getVelocity();

        double rvx = v2.x - v1.x;
        double rvy = v2.y - v1.y;

        double velAlongNormal = rvx * nx + rvy * ny;

        if (velAlongNormal > 0) {
            return;
        }

        double restitution = world.getRestitution();

        if (Math.abs(velAlongNormal) < 0.5) {
            restitution = 0;
        }

        double j = -(1 + restitution) * velAlongNormal;
        j /= (1 / getMass() + 1 / other.getMass());

        double impulseX = j * nx;
        double impulseY = j * ny;

        setVelocity(new Vector2d(
                v1.x - impulseX / getMass(),
                v1.y - impulseY / getMass()));

        other.setVelocity(new Vector2d(
                v2.x + impulseX / other.getMass(),
                v2.y + impulseY / other.getMass()));
    }
}
