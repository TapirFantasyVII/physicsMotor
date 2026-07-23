/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author adpal
 */
public class Triangle extends Figure {

    private int ancho, alto;

    public Triangle(int x, int y, Color color, int ancho, int alto, String id) {
        super(x, y, color, id);
        this.ancho = ancho;
        this.alto = alto;
        setSize(ancho, alto);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getColor());
        int[] xs = { ancho / 2, 0, ancho };
        int[] ys = { 0, alto, alto };
        g.fillPolygon(xs, ys, 3);
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    @Override
    public String toString() {
        return super.toString() + "type=triangle " + "ancho=" + ancho + " alto=" + alto;
    }
}
