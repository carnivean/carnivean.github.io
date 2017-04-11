import java.util.ArrayList;

/**
 * Created by Erik Kynast on 11.04.2017.
 */
public class BusTest {
    public static void main(String[] args) {
        CNGBus c = new CNGBus(50, 1000000, 200, 2);
        Hybrid h = new Hybrid(45, 1200000, 1, 150, 20);
        ElectricBus e = new ElectricBus(55, 900000, 5);

        ArrayList<Bus> arrayList = new ArrayList<>();

        arrayList.add(c);
        arrayList.add(h);
        arrayList.add(e);

        for (Bus b : arrayList) {
            if (b instanceof LiquidFuel) {
                System.out.println("Emission: " +
                        ((LiquidFuel) b).getEmissionTier());
            }
            if (b instanceof Electric) {
                System.out.println("Voltage: " +
                        ((Electric) b).getVoltage());
            }
            System.out.println("ID: " + b.getID());
            System.out.println("------------------");
        }
    }
}
