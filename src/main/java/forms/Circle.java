package forms;

import java.awt.*;
import javax.swing.JPanel;

public class Circle extends Figure {
    private int radio;
    
    public Circle(int x, int y, Color color, int radio, String id){
        super(x,y,color, id);
        this.radio =radio;
        setSize(radio * 2, radio * 2);
        
    }
    
 @Override
  protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(getColor());
      g.fillOval(0, 0, radio * 2, radio * 2);
  }

    public double getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    @Override
    public String toString() {
        return  super.toString() + "type=circle " +  "radio="+radio;
    }

}



