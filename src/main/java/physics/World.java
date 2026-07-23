package physics;

public class World {

    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 600;

    private double width;
    private double height;

    private Vector2d gravity;
    private double airFriction;
    private double groundFriction;
    private double restitution;

    public World() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        gravity = new Vector2d(0, PhysicsConfig.DEFAULT_GRAVITY);
        airFriction = PhysicsConfig.DEFAULT_AIR_FRICTION;
        groundFriction = PhysicsConfig.DEFAULT_GROUND_FRICTION;
        restitution = PhysicsConfig.DEFAULT_RESTITUTION;
    }

    public World(double width, double height) {

        this.width = width;
        this.height = height;

        gravity = new Vector2d(0, PhysicsConfig.DEFAULT_GRAVITY);
        airFriction = PhysicsConfig.DEFAULT_AIR_FRICTION;
        groundFriction = PhysicsConfig.DEFAULT_GROUND_FRICTION;
        restitution = PhysicsConfig.DEFAULT_RESTITUTION;

    }

    public World(double width, double height, double gravityValue, double airFriction, double groundFriction,
            double restitution) {

        this.width = width;
        this.height = height;

        this.gravity = new Vector2d(0, gravityValue);
        this.airFriction = airFriction;
        this.groundFriction = groundFriction;
        this.restitution = restitution;

    }

    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Vector2d getGravity() {
        return gravity;
    }

    public double getAirFriction() {
        return airFriction;
    }

    public double getGroundFriction() {
        return groundFriction;
    }

    public double getRestitution() {
        return restitution;
    }

}