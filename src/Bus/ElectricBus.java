/**
 * Created by Erik Kynast on 11.04.2017.
 */
public class ElectricBus extends Bus implements  Electric {
    double voltage;

    @Override
    public double getAccel() {
        return 5.0;
    }

    @Override
    public double getVoltage() {
        return voltage;
    }

    public ElectricBus(int capacity, double cost,
                       double voltage) {
        super(capacity, cost);

        this.voltage = voltage;
    }
}
