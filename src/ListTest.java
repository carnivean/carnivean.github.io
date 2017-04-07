import java.util.Arrays;

/**
 * Created by Erik Kynast on 07.04.2017.
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> stringListe = new List<>();

        System.out.println("Leere Liste: " + stringListe);

        // Tests für addFirst
        stringListe.addFirst("Vorne eingefügt 1");
        stringListe.addFirst("Vorne eingefügt 2");
        stringListe.addFirst("Vorne eingefügt 3");
        System.out.println("Liste: " + stringListe);

        // Test für addLast
        stringListe.addLast("Hinten eingefügt 1");
        stringListe.addLast("Hinten eingefügt 2");
        stringListe.addLast("Hinten eingefügt 3");
        System.out.println("Liste: " + stringListe);

        // addAtIndex Tests
        stringListe.addAtIndex("Eingefügt @Index 0", 0);
        stringListe.addAtIndex("Eingefügt @Index 3", 3);
        stringListe.addAtIndex("Eingefügt @Index 5", 5);
        System.out.println("Liste: " + stringListe);

        // removeFirst Tests
        stringListe.removeFirst();
        stringListe.removeFirst();
        System.out.println("Liste: " + stringListe);

        // removeLast Tests
        stringListe.removeLast();
        stringListe.removeLast();
        System.out.println("Liste: " + stringListe);

        // removeAtIndex Tests
        System.out.println("Remove @ Index 5: " + stringListe.removeAtIndex(5));
        System.out.println("Remove @ Index 0: " + stringListe.removeAtIndex(0));
        System.out.println("Liste: " + stringListe);

        // reverse Test
        System.out.println("Reverse Liste: " + stringListe.reverse());

        // clear Test
        stringListe.clear();
        System.out.println("Liste gecleart: " + stringListe);

        stringListe.addLast("" + 1);
        stringListe.addLast("" + 2);
        stringListe.addLast("" + 3);
        stringListe.addLast("" + 4);
        stringListe.addLast("" + 5);
        stringListe.addLast("" + 6);
        stringListe.addLast("" + 7);
        stringListe.addLast("" + 8);
        stringListe.addLast("" + 9);
        stringListe.addLast("" + 10);
        System.out.println("Liste: " + stringListe);

        // replace Test
        stringListe.replace("999", 5);
        System.out.println("Liste (replaced @ Index 5): " + stringListe);
        System.out.println("Indexof 999: " + stringListe.indexOf("999"));
        System.out.println("Indexof 10: " + stringListe.indexOf("10"));
        String[] arr = new String[10];
        stringListe.toArray(arr);
        System.out.println("ToArray: " + Arrays.toString(arr));
    }
}
