/**
 * Created by Erik Kynast on 12.04.2017.
 */
public abstract class Shape {
    private int numSides;

    public Shape(int numSides) {
        this.numSides = numSides;
    }

    public int getNumSides() {
        return numSides;
    }

    public abstract double getArea();
    public abstract double getPerimeter();

    @Override
    public String toString() {
        return " Area: " + getArea() + " Perimeter: " + getPerimeter();
    }
}
