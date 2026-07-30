package physics;

public abstract class Body {

    private Vector2d position;
    private Vector2d velocity = Vector2d.zero();
    private Vector2d forceAccum = Vector2d.zero();

    private double width;
    private double height;

    private double mass;

    private double rotation;

    private final World world;

    protected Body(World world,
                   double x,
                   double y,
                   double width,
                   double height,
                   double mass) {

        this.world = world;

        this.position = new Vector2d(x, y);

        this.width = width;
        this.height = height;

        this.mass = mass;
    }

    //=====================================================
    // Integración
    //=====================================================

    public void applyForce(Vector2d force) {
        forceAccum = forceAccum.add(force);
    }

    public void integrate(double dt) {

        Vector2d acceleration = forceAccum.scale(1.0 / mass);

        velocity = velocity.add(acceleration.scale(dt));

        position = position.add(velocity.scale(dt));

        forceAccum = Vector2d.zero();
    }

    //=====================================================
    // Movimiento
    //=====================================================

    public void translate(double dx, double dy) {
        position = position.add(new Vector2d(dx, dy));
    }

    //=====================================================
    // Getters
    //=====================================================

    public double getX() {
        return position.x;
    }

    public double getY() {
        return position.y;
    }

    public Vector2d getPosition() {
        return position;
    }

    public Vector2d getVelocity() {
        return velocity;
    }

    public double getMass() {
        return mass;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getRotation() {
        return rotation;
    }

    public World getWorld() {
        return world;
    }

    //=====================================================
    // Setters
    //=====================================================

    public void setVelocity(Vector2d velocity) {
        this.velocity = velocity;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public void setSize(double width,double height){
        this.width = width;
        this.height = height;
    }

}