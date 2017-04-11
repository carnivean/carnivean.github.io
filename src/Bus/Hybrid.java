/**
 * Created by Erik Kynast on 11.04.2017.
 */
public class Hybrid extends Bus implements LiquidFuel, Electric {
    private int emissionTier;
    private int range;
    private double voltage;

    public Hybrid(int capacity, double cost, int emissionTier,
                  int range, double voltage) {
        super(capacity, cost);

        this.emissionTier = emissionTier;
        this.range = range;
        this.voltage = voltage;
    }

    @Override
    public double getAccel() {
        return 4.0;
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public int getEmissionTier() {
        return emissionTier;
    }

    @Override
    public double getVoltage() {
        return voltage;
    }
}
