/**
 * Created by Erik Kynast on 12.04.2017.
 */
public class SumThreadTest {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        long resultat = SumThreadRunner.sum(arr, 3);

        System.out.println("Resultat " + resultat);

        int[] ar = new int[1000000];

        for (int i = 0; i < ar.length; i++) {
            ar[i] = i + 1;
        }
        System.out.println("Resultat " + SumThreadRunner.sum(ar, 15));
    }
}
