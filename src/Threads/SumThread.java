/**
 * Created by Erik Kynast on 12.04.2017.
 */
public class SumThread extends Thread {
    public long resultat;
    int[] input;
    int left, right;

    public SumThread(int[] input, int left, int right) {
        this.input = input;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        // summiere meinen Bereich auf
        resultat = 0;

        for (int i = left; i <= right; i++) {
            resultat = resultat + input[i];
        }
    }
}
