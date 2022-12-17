import sun.security.provider.SHA;

/**
 * Created by Erik Kynast on 12.04.2017.
 */
public class RtTriangle extends Shape {
    private double width, height;

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public RtTriangle(double width, double height) {
        super(3);

        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height / 2;
    }

    @Override
    public double getPerimeter() {
        return width + height + Math.sqrt(height*height + width * width);
    }

    @Override
    public String toString() {
        return "Triangle: " +
                "width: " + width +
                " height: " + height +
                super.toString();
    }
}
