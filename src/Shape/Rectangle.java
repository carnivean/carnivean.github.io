import sun.security.provider.SHA;

/**
 * Created by Erik Kynast on 12.04.2017.
 */
public class Rectangle extends Shape implements Resizable{
    private double width, height;

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Rectangle(double width, double height) {
        super(4);

        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return height * width;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "Rectangle: " +
                "width: " + width +
                " height: " + height +
                super.toString();
    }

    @Override
    public void resize(double factor) {
        height = height * factor;
        width = width * factor;
    }
}
