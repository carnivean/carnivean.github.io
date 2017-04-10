/**
 * Created by Erik Kynast on 07.04.2017.
 */
public class Book {
    // Klassenvariablen
    private static long NEXT_ID = 1;

    // Objektvariablen
    private final String author, title;
    private int price;
    private final long id;

    public Book(String author, String title) {
        // Rufe den größeren Konstruktor auf
        // mit standard Wert für den Price
        this(author, title, 0);
    }

    public Book(String author, String title, int price) {
        this.author = author;
        this.title = title;

        // Fehlerbehandlung für den Price
        if (price < 0) {
            throw new IllegalArgumentException("Negativer Preis ?!");
        }
        this.price = price;

        // nehme die nächste freie ID und erhöhe den Counter
        this.id = NEXT_ID;
        NEXT_ID++;
    }

    // Getter
    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public long getId() {
        return id;
    }

    // Setter

    public void setPrice(int price) {
        // Fehlerbehandlung für neg. Preise
        if (price < 0) {
           throw new IllegalArgumentException("Negativer Preis ?!");
        }

        // Verschattung
        this.price = price;
    }

    // a)

    @Override
    public String toString() {
        String ausgabe = author + ": " + title + " (EUR ";
        String euroString = "" + (price / 100);

        // für die Centbeträge müssen wir eine 0 davor schreiben
        // falls der Betrag kleiner als 10 ist !
        int cent = price % 100;
        String centString;
        if (cent < 10) {
            centString = "0" + cent;
        } else {
            centString = "" + cent;
        }

        ausgabe = ausgabe + euroString + "," + centString + ")";

        return ausgabe;
    }

    // b)
    @Override
    public boolean equals(Object o) {
        // ist o überhaupt ein Book
        if (o instanceof Book) {
            // Wir müssen o zu einem Book casten
            if (this.id == ((Book) o).id) {
                return true;
            }
        }
        return false;
    }

    // c)
    public void increasePrice(float euro) {
        int cent = (int) (euro * 100);

        if (price + cent < 0) {
            throw new IllegalArgumentException("Neuer Preis ist negativ!");
        }

        price = price +  cent;
    }

    // d)
    public boolean hasSameAuthor(Book b) {
        return b.author.equals(author);
    }

    // e)
    public int authorLetters() {
        int counter = 0;
        for (int i = 0; i < author.length(); i++) {
            // Ist der aktuelle Buchstabe KEIN Leerzeichen,
            // dann erhöhe unseren Counter
            if (author.charAt(i) != ' ') {
                counter++;
            }
        }

        return counter;
    }
}
