/**
 * Created by Erik Kynast on 06.04.2017.
 */

public class RepRekursiv {
    public static void main(String[] args) {
        // Fakultät Test
        System.out.println("Fakultät von 3: " + fakRec(3));
        System.out.println("Fakultät von 5: " + fakRec(5));
        System.out.println("Fakultät von 7: " + fakRec(7));
        System.out.println("Fakultät von 10: " + fakRec(10));

        // Fibonacci Test
        System.out.println("3. Fibonaccizahl: " + fibRec(3));
        System.out.println("5. Fibonaccizahl: " + fibRec(5));
        System.out.println("7. Fibonaccizahl: " + fibRec(7));

        // reverse Tests
        reverse(12345);
        reverse(1);
        reverse(789);

        // price Tests
        System.out.println("121 TShirts kosten: " + price(121));

        // Binomial tests
        System.out.println("6 über 4: " + binomialRec(6, 4));
        System.out.println("6 über 4 alternativ berechnet: " + binomialAlternativ(6, 4) );

        // Sum / prod Test
        System.out.println("15 + 4: " + sum(15, 4));
        System.out.println("15 * 4: " + prod(15, 4));

        // ggt Tests
        System.out.println("GGT(8, 2): " + ggt(8, 2) );
        System.out.println("GGT(15, 35): " + ggt(15, 35) );
        System.out.println("GGT(4, 4): " + ggt(4, 4) );

        // prime Test
        System.out.println("Ist 13 eine Primzahl: " + isPrime(13));
        System.out.println("Ist 12 eine Primzahl: " + isPrime(12));
        System.out.println("Ist 23 eine Primzahl: " + isPrime(23));

        // ultra Test
        System.out.println("ultraIt(10): " + ultraIt((short) 10) + " ultraRec(10): " + ultraRec((short) 10));
        System.out.println("ultraIt(3): " + ultraIt((short) 3) + " ultraRec(3): " + ultraRec((short) 3));
        System.out.println("ultraIt(7): " + ultraIt((short) 7) + " ultraRec(7): " + ultraRec((short) 7));

        // isPalindrome Tests
        System.out.println("AnnA ist ein Palindrome: " + isPalindrome(new char[] {'A', 'n', 'n', 'A'}));
        System.out.println("Anna ist ein Palindrome: " + isPalindrome(new char[] {'A', 'n', 'n', 'a'}));
        System.out.println("legovogel ist ein Palindrome: " + isPalindrome(new char[] {'l', 'e', 'g', 'o', 'v', 'o', 'g', 'e', 'l'}));

        // fakEndRek Tests
        System.out.println("fakEndRek(5): " + fakEndRec(5));
        System.out.println("fakEndRek(1): " + fakEndRec(1));
        System.out.println("fakEndRek(10): " + fakEndRec(10));

        // potenzEndRek Tests
        System.out.println("3^5: " + potenzEndRek(3, 5));
        System.out.println("5^3: " + potenzEndRek(5, 3));

        // priceEndRek tests
        System.out.println("121 Shirts kosten (endrekursiv): " + priceEndRec(121));

        // ultraEndRec Tests
        System.out.println("ultraIt(7): " + ultraIt((short) 7) + " ultraEndRec(7): " + ultraEndRec((short) 7));

        // summen Tests
        System.out.println("sumIt(12): " + sumIt(12) + " sumRec: " + sumRec(12) + " sumEndRec: " + sumEndRec(12));
    }

    public static long sumIt(int n) {
        long resultat = 0;

        for (int i = 1; i <= n; i++) {
            resultat = resultat + i;
        }

        return resultat;
    }

    public static long sumRec(int n) {
        // Basisfall
        if (n == 1) {
            return 1;
        }

        // rekursiver Fall
        return n + sumRec(n - 1);
    }

    public static long sumEndRec(int n) {
        return sumEndRecHelper(n, 0);
    }

    public static long sumEndRecHelper(int n, long zwischenergebnis) {
        // Basisfall
        if (n == 0) {
            return zwischenergebnis;
        }

        // rekursiver Fall
        return sumEndRecHelper(n - 1, n + zwischenergebnis);
    }


    public static int ultraEndRec(short x) {
        // initialer Aufruf mit unserem Basisfall, sprich 0
        return ultraEndRecHelper(x, 0);
    }

    private static int ultraEndRecHelper(short x, int ze) {
        // Basifall, gib uns das bisher berechnete
        // zwischenergebinis aus
        if (x <= 0) {
            return ze;
        }

        // rekursiver Fall
        return ultraEndRecHelper((short) (x - 2), x * (x - 1) + ze);
    }

    public static int priceEndRec(int n) {
        return priceEndRecHelper(n, 0);
    }

    private static int priceEndRecHelper(int n, int zwischenrgebnis) {
        // Basisfall
        if (n == 0) {
            // wir geben unser bisher berechnetes ergebnis aus
            return zwischenrgebnis;
        }

        // rekursiver Fall
        if (n >= 100) {
            return priceEndRecHelper(n - 100, 250 + zwischenrgebnis);
        } else if (n >= 50) {
            return priceEndRecHelper(n - 50, 130 + zwischenrgebnis);
        } else if (n >= 10) {
            return priceEndRecHelper(n - 10, 28 + zwischenrgebnis);
        } else {
            return priceEndRecHelper(n - 1, 3 + zwischenrgebnis);
        }
    }

    public static int potenzEndRek(int a, int b){
        // Aufruf unserer endrekursiven Hilfsmethode
        int resultat = potenzEndRekHilfe(a, b, 1);

        return resultat;
    }

    public static int potenzEndRekHilfe(int a, int b, int zwischenergebnis) {
        // Basisfall
        if (b == 0) {
            return zwischenergebnis;
        }

        // zwischenergebnis updaten
        zwischenergebnis = zwischenergebnis * a;

        // rekursiver Aufruf
        return potenzEndRekHilfe(a, b - 1, zwischenergebnis);
    }

    public static int fakEndRec(int n) {
        // Initialer Aufruf unserer Helperfunktion mit 1
        // da dies diem Basisfall in der normalen Rekursion
        // entspricht (nicht immer aber FAST immer)
        return fakEndRecHelper(n, 1);
    }

    public static int fakEndRecHelper(int n, int zwischenergebnis) {
        if (n == 1) {
            // fast immer geben wir einfach das
            // zwischenergebnis zurück
            return zwischenergebnis;
        }

        int neuesErgebnis = n * zwischenergebnis;
        return fakEndRecHelper(n - 1, neuesErgebnis);
    }

    public static int ultraRec(short x) {
        // Basisfall der iterativen Implementierung
        // man überspringt die Schleife komplett
        // das gegenteil von x > 0 ist x <= 0
        if (x <= 0) {
            return 0;
        }

        // rekursiver Fall
        return x * (x - 1) + ultraRec((short) (x - 2));
    }

    public static int ultraIt(short x) {
        int ret = 0;

        for (int i = x; i > 0; i = i - 2){
            ret += i * (i - 1);
        }
        return ret;
    }

    public static boolean isPrime(int n) {
        // basisfall 1 ist KEINE primzahl
        if (n <= 1)
            return false;

        // Wir fangen mit der nächstkleineren Zahl an zu testen
        // da auch primzahlen sich selbst teilen dürfen
        return isPrimeHelper(n, n-1);
    }

    private static boolean isPrimeHelper(int n, int i) {
        // Basisfall: wir haben bisher keinen Teiler gefunden
        // --> einziger Teiler ist 1 --> Primzahl
        if (i == 1) {
            return true;
        }

        // rekursiver Fall
        // Wir testen ob unser momentanes i n teilt
        // Wenn dies der Fall ist -> keine Primzahl
        if (n % i == 0) {
            return false;
        }
        // Wenn nicht, müssen wir es mit der nächstkleineren Zahl testen
        return isPrimeHelper(n, i - 1);
    }

    public static int ggt(int a, int b) {
       // Basisfall
        if (a == b) {
            return a;
        }

        // rekursiver Fall 1
        if (a > b) {
            return ggt(a - b, b);
        } else if (a < b) {
            // rekursiver Fall 2
            return ggt(a, b - a);
        }

        // wird niemals erreicht
        return 0;
    }

    public static int sum(int a, int b) {
        // Basisfälle: a oder b sind 0
        if (b == 0) {
            return a;
        }
        if (a == 0) {
            return b;
        }

        // rekursive Fälle
        // 1. a ist positiv --> füge 1 hinzu, verkleinere a um 1
        if  (a > 0) {
            return 1 + sum(a - 1, b);
        } else if (a < 0) {
            // 2. a ist negativ --> ziehe 1 ab, vergrößere a um 1
            return sum(a + 1, b) - 1;
        }

        return -1;
    }

    public static int prod(int a, int b) {
        // Basisfälle a, b == 0 oder a, b == 1
        if (a == 0 || b == 0) {
            return 0;
        }
        if (a == 1) {
            return b;
        }

        // rekursiver Fall
        // 1. a ist positiv
        if (a > 0) {
            return sum(prod(a - 1, b), b);
        } else if (a < 0) {
            return prod(a + 1, b) - b;
        }

        return -1;
    }

    public static int binomialRec(int n, int k) {
        // Basifall
        if (k == 0 || k == n) {
            return 1;
        }

        // rekursiver Fall
        // 2 rekursive Aufrufe
        int resultat = binomialRec(n - 1, k - 1) + binomialRec(n - 1, k);

        return resultat;
    }

    public static int binomialAlternativ(int n, int k) {
        // siehe Formel in der Aufgabenstellung
        return fakRec(n) / (fakRec(k) * fakRec(n - k));
    }

    public static int price(int n) {
        // Basisfall
        if (n == 0) {
            return 0;
        }
        /*
            Alternative Lösung für den Basisfall:
            if (n == 1) {
                return 3;
            } else if (n == 10) {
                return 28;
            } else if (n == 50) {
                return 130;
            } else if (n == 100) {
                return 250;
            }
         */

        // Arbeit und rekursiver Aufruf zusammengefasst
        if (n >= 100) {
            return 250 + price(n - 100);
        } else if (n >= 50) {
            return 130 + price(n - 50);
        } else if (n >= 10) {
            return 28 + price(n - 10);
        } else if (n >= 1){
            return 3 + price(n - 1);
        } else {
            return 0;       // um negative eingaben abzufangen
        }
    }

    public static void reverse(int x) {
        // Basisfall
        if (x < 10) {
            System.out.println(x);
            return;             // <-- wichtig damit NUR das Zeichen ausgegeben wird,
                                // im Basisfall ist man damit wirklich fertig
        }

        // rekursiver Fall
        // Arbeit --> letzte Ziffer ausgeben
        System.out.print(x % 10);
        // Problem verkleinern für rekursiven Aufruf
        int neuesX = x / 10;
        // rekursiver Aufruf
        reverse(neuesX);
    }

    public static int fakRec(int n) {
        // Basisfall
        if (n == 0) {
            return 1;
        }

        // rekursiver Fall mit verkleinertem Problem
        int resultat = n * fakRec(n - 1);

        // rückgabe des Wertes ("Hochreichen")
        return resultat;
    }

    public static int fibRec(int n) {
        // Basisfall
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        // rekursiver Fall
        int resultat = fibRec(n - 1) + fibRec(n - 2);

        // rückgabe des Wertes ("Hochreichen")
        return resultat;
    }

    public static boolean isPalindrome(char[] c) {
        // Basisfall
        if (c.length == 1 || c.length == 0) {
            return true;
        }

        // Arbeit
        // 1. Vergleich 1. und letzter Buchstabe
        if (c[0] != c[c.length - 1]) {
            return false;
        }

        // ich weiß, dass die äußeren beiden Buchstaben passen
        // --> Array verkleinern
        char[] neuesArray = new char[c.length - 2];
        // Werte beginnend bei Index 1 - Index length - 2 reinkopieren
        for (int i = 1; i < c.length - 1; i++) {
            neuesArray[i - 1] = c[i];
        }

        return isPalindrome(neuesArray);
    }
}
