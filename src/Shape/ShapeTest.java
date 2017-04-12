import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Erik Kynast on 12.04.2017.
 */
public class ShapeTest {
    public static void main (String[] args) {
        Rectangle rect = new Rectangle(2.0, 3.0);
        RtTriangle tri = new RtTriangle(7.5, 3);

        System.out.println("" + tri);
        System.out.println("" + rect);
        rect.resize(2.0);
        System.out.println("Resized " + rect);

        ArrayList<Shape> shapes = new ArrayList<Shape>();

        shapes.add(rect);
        shapes.add(tri);


        // normale Variante
        System.out.println("Normale Variante: ");
        for (Shape s: shapes) {
            if (s instanceof Resizable) {
                ((Resizable) s).resize(0.5);
            }
            System.out.println("" + s);
        }

        // Lambda Expression Variante
        System.out.println("Lambda Variante: ");
        shapes.stream()
                .map(shape -> {
                    if (shape instanceof Resizable) {
                        ((Resizable) shape).resize(0.5);
                    }
                    return shape;
                })
                .peek(shape -> System.out.println("" + shape))
                .collect(Collectors.toList());
    }
}
