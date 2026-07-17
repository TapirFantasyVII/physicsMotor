 package physics;

public class Vector2d {
    public double x, y;

    public Vector2d(double x, double y) { this.x = x; this.y = y; }

    public Vector2d add(Vector2d o) { return new Vector2d(x + o.x, y + o.y); }
    public Vector2d scale(double s) { return new Vector2d(x * s, y * s); }
    public static Vector2d zero() { return new Vector2d(0, 0); }
}