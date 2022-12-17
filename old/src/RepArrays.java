import java.util.Arrays;

/**
 * Created by Erik Kynast on 04.04.2017.
 */
public class RepArrays {
    double[] t = {6.2, 2.5, 3.1, 3.3, 1.15, 5.7, 8.1, 3.2};

    public static void main(String[] arg)
    {
        RepArrays rep = new RepArrays();
        System.out.println("Durchschnittstemp: " + rep.avgTemp());
        System.out.println("Min Temp am morgen: " + rep.minTempMorning());

        System.out.println("Temp der letzten 3 Tage morgens: " + rep.maxTemp(3, true));
        System.out.println("Temp der letzten 3 Tage abends: " + rep.maxTemp(3, false));

        System.out.println("Temp der letzten 2 Tage morgens: " + rep.maxTemp(2, true));
        System.out.println("Temp der letzten 2 Tage abends: " + rep.maxTemp(2, false));

        System.out.println("Temp der letzten 1 Tage morgens: " + rep.maxTemp(1, true));
        System.out.println("Temp der letzten 1 Tage abends: " + rep.maxTemp(1, false));

        System.out.println("Array vor reverse: " + Arrays.toString(rep.t));
        RepArrays.reverse(rep.t);
        System.out.println("Array nach reverse: " + Arrays.toString(rep.t));

    }

    public double avgTemp() {
        double sum = 0.0;

        for (double elem: t) {
            sum = sum + elem;
        }

        return sum / t.length;
    }

    public double minTempMorning() {
        double min = t[0];

        for (int i = 2; i < t.length; i = i + 2) {
            if (t[i] < min) {
                min = t[i];
            }
        }

        return min;
    }

    public double maxTemp(int days, boolean morning) {
        int index = t.length - 1;

        // nur morgen werte
        if (morning) {
            if (index % 2 != 0) {
                index--;
            }
        } else {
            // nur abend werte
            if (index % 2 == 0) {
                index--;
            }
        }

        double max = t[index];

        for (int i = 1; i < days; i++) {
            index = index - 2;

            if (t[index] > max) {
                max = t[index];
            }
        }

       return max;
    }

    public static void reverse(double[] a) {
        for (int i = 0; i < a.length / 2; i++) {
            double tmp = a[i];
            a[i] = a[a.length - 1 - i];
            a[a.length - 1 - i] = tmp;
        }
    }
}