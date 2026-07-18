package physics;

public class Body {

    private Vector2d position;
    private Vector2d velocity = Vector2d.zero();
    private Vector2d forceAccum = Vector2d.zero();

    private double width;
    private double height;
    private double mass;
    private boolean grounded = false;

    public Body(double x, double y, double width, double height, double mass) {
        this.position = new Vector2d(x, y);
        this.width = width;
        this.height = height;
        this.mass = mass;
    }

    public void applyForce(Vector2d f) {
        forceAccum = forceAccum.add(f);
    }

    public void integrate(double dt) {
        Vector2d acceleration = forceAccum.scale(1.0 / mass);
        velocity = velocity.add(acceleration.scale(dt));
        position = position.add(velocity.scale(dt));
        forceAccum = Vector2d.zero();
    }

    public void resolveWorldBounds(double worldWidth, double worldHeight, double restitution) {
        if (position.x < 0) {
            position.x = 0;
            velocity.x = -velocity.x * restitution;
        } else if (position.x + width > worldWidth) {
            position.x = worldWidth - width;
            velocity.x = -velocity.x * restitution;
        }

        if (position.y < 0) {
            position.y = 0;
            velocity.y = -velocity.y * restitution;
        } else if (position.y + height > worldHeight) {
            position.y = worldHeight - height;
            velocity.y = -velocity.y * restitution;
        }

        updateGroundedState(worldHeight);
    }

    private void updateGroundedState(double worldHeight) {
        double epsilon = 0.5;
        grounded = (position.y + height >= worldHeight - epsilon);
    }

    public void applyFriction(double groundFriction, double airFriction) {
        if (grounded) {
            double frictionForceX = -velocity.x * groundFriction;
            applyForce(new Vector2d(frictionForceX, 0));
            if (Math.abs(velocity.x) < 0.5) {
                velocity.x = 0;
            }
        } else {
            applyForce(velocity.scale(-airFriction));
        }
    }

    // getters
    public double getX() { return position.x; }
    public double getY() { return position.y; }
    public Vector2d getPosition() { return position; }
    public double getMass() { return mass; }
    public boolean isGrounded() { return grounded; }
    public void setSize(double width, double height) { this.width = width; this.height = height; }
}