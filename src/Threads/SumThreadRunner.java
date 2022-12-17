/**
 * Created by Erik Kynast on 12.04.2017.
 */
public class SumThreadRunner{

    public static long sum(int[] input, int numThreads) {
        // 1. Berechne die Länge der Abschnitte
        // Formel siehe Aufgabenstellung
        int lenAbschnitt = input.length / numThreads;

        // 2. Array von Threads deklarieren
        SumThread[] threads = new SumThread[numThreads];
        // 2.1. Threads initialisieren & starten
        for (int i = 0; i < numThreads; i++) {
            // normale Bereichsgrenzen
            if (i < numThreads - 1) {
                threads[i] = new SumThread(input, lenAbschnitt * i,
                        lenAbschnitt * (i + 1) - 1);
            } else {
                // Sonderbehandlung für den letzten Bereich
                // siehe Aufgabenstellung
                threads[i] = new SumThread(input, lenAbschnitt * i,
                        input.length - 1);
            }

            threads[i].start();
        }

        // 3. alle Threads fertig?
        for(int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 3. 1. Einzelergebnisse aufaddieren
        long endresultat = 0;

        for (int i = 0; i < numThreads; i++) {
            endresultat = endresultat + threads[i].resultat;
        }
        return endresultat;
    }
}
