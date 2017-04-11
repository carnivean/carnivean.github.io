/**
 * Created by Erik Kynast on 11.04.2017.
 */
public class CNGBus extends Bus implements LiquidFuel {
    private int range;
    private int emissionTier;

    public CNGBus(int capacity, double cost, int range,
                  int emissionTier) {
        super(capacity, cost);
        this.range = range;
        this.emissionTier = emissionTier;
    }

    @Override
    public double getAccel() {
        return 3.0;
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public int getEmissionTier() {
        return emissionTier;
    }
}
