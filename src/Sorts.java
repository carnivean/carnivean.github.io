import java.util.Arrays;

/**
 * Created by Erik Kynast on 10.04.2017.
 */
public class Sorts {
    public static void main(String[] args) {
        int[] test = {3, 2, 6, 7, 4, 1, 5};
        int[] sortiert = insertionSort(test);
        System.out.println(Arrays.toString(sortiert));
    }

    public static void mergeSort(int[] arr, int left, int right) {
        final int rightBorder = right;
        final int leftBorder = left;

        // Basisfall: Bereich hat die Länge 0
        if (left >= right) {
            return;
        }

        // Sortieren
        int middle = left + (right - left) / 2;
        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);

        // Mergen
        int[] sorted = new int[right - left + 1];
        right = middle + 1;
        for (int i = 0; i < sorted.length; i++) {
            if (left > middle ||
                    (right <= rightBorder &&
                    arr[left] > arr[right])) {
                sorted[i] = arr[right];
                right++;
            } else {
                sorted[i] = arr[left];
                left++;
            }
        }

        // sichern
        for (int i = 0; i < sorted.length; i++) {
            arr[leftBorder + i] = sorted[i];
        }
    }

    public static int[] insertionSort(int[] a) {
        int len = a.length;
        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            // Füge das Element a[i] in das neue Array ein,
            // wobei bereits i - 1 Elemnte eingefügt wurden

            // 1. Finde die richtige Position im neuen Array
            int j = 0;
            while (j < i && a[i] > result[j]) {
                j++;
            }

            // 2. Mache Platz für das neue Element, 
            // indem wir im Resultat alle
            // bisher eingefügten Elemnte mit Index >= j 
            // eins nach rechts schieben
            for (int k = i - 1; k >= j; k--) {
                result[k + 1] = result[k];
            }


            // 3. Schreibe das neue Elemente in das 
            // Resultat
            result[j] = a[i];
        }
        return result;
    }
}
