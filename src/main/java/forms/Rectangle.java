package forms;

import java.awt.*;

public class Rectangle extends Figure {

    private int a;
    private int b;

    public Rectangle(int x, int y, Color color, int a, int b, String id) {
        super(x, y, color, id);
        this.a = a;
        this.b = b;
        setSize(a, b);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getColor());
        g.fillRect(0, 0, a, b);
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return super.toString() + "type=rectangle " + "a=" + a + " b=" + b;
    }
}
