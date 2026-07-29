package forms;

import java.awt.*;
import javax.swing.JPanel;

import physics.CircleBody;
import physics.World;

public class Circle extends Figure {
    private int radio;

    public Circle(World world, int x, int y, Color color, int radio, double weight , String id) {
        super(x, y, color, id);
        this.body = new CircleBody(world, x, y, radio, weight);
        this.radio = radio;
        setSize(radio * 2, radio * 2);

    }
 
    public double getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    @Override
    public String toString() {
        return super.toString() + "type=circle " + "radio=" + radio;
    }

}
