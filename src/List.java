/**
 * Created by Erik Kynast on 07.04.2017.
 */
public class List<E> {
    private Entry<E> first;

    private class Entry<E> {
        E data;
        Entry<E> next;

        public Entry(E data, Entry<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    public List() {
        this.first = null;
    }

    // Fügt ein Element an den Beginn der Liste
    public void addFirst(E e) {
        if (first == null) {
            // Wenn wir eine leere Liste hatten, müssen wir einen ersten Eintrag erstellen, dieser zeigt ins nirgendwo
            // da es keinen weiteren Eintrag gibt
            first = new Entry<>(e, null);
        }

        // Wenn es bereits einen EIntrag gibt, wird dies jetzt der 2. und dieser der neue erste
        Entry<E> newFirst = new Entry<>(e, first);
        first = newFirst;
    }

    // Fügt ein Item an einer bestimmten Position ein
    public void addAtIndex(E e, int i) {
        // Versuchen wir an einer unmöglichen Stelle einzufügen?
        if (i > size()) {
            throw new IllegalArgumentException();
        }

        // Wollen wir an der ersten Stelle einfügen?
        if (i == 0) {
            addFirst(e);
            return;
        }

        // wir gehen zu dem Eintrag vorher, dieser muss nachher auf den neuen zeigen
        // und der neue muss auf den nächsten des vorherigen zeigen (ähnlich dem remove in der Stunde besprochen)
        Entry<E> prevEntry = getEntry(i - 1);
        Entry<E> newEntry = new Entry<>(e, prevEntry.next);
        prevEntry.next = newEntry;
    }

    // Leert die Liste vollständig
    public void clear() {
        // eine leere Liste ist dadurch gekennzeichnet, dass es von der Liste keinen Verweis auf Elemente gibt
        first = null;
    }

    // Checkt ob ein Item in der Liste ist
    public boolean contains(Object o) {
        // indexOf gibt -1 zurück, wenn es das Element nicht findet --> alle anderen Antworten sind ok
        return indexOf(o) != -1;
    }

    // gibt das Element an der i-ten Stelle zurück
    public E get(int index) {
        return getEntry(index).data;
    }

    // private Hilfsmethode, die einem den Eintrag an der
    // i-ten Stelle zurückgibt
    private Entry<E> getEntry(int index) {
        // Haben wir genug Elemente in der Liste?
        if (index > size()) {
            return null;
        }

        // Wir haben genug Elemente, gehe die Liste entlang bis zu dem entsprechenden Element
        Entry<E> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    // gibt den Index des Elementes in der Liste, falls es
    // in der Liste existiert, -1 sonst
    public int indexOf(Object o) {
        // ist die Liste leer
        if (isEmpty()) {
            return -1;
        }

        // durchlauf alle Elemente und schau ob das jeweilige Element das gesuchte ist
        int index = 0;
        Entry<E> current = first;
        while(current != null) {
            // haben wir das Element gefunden? Wenn ja, können wir aufhören und geben den Index zurück
            if (current.data.equals(o))
                return index;

            current = current.next;
            index++;
        }

        // wir haben alle Objekte verglichen und es nicht gefunden --> nicht in der Liste
        return -1;
    }

    // Ist die Liste leer?
    public boolean isEmpty() {
        return first == null;
    }

    // Gibt die Liste in umgekehrter Reihenfolge zurück
    // Unglaublich schlechte Performanz, da wir eine Einfach verkette Liste haben
    public List<E> reverse() {
        // wir fangen bei dem letzten Element an und fügen nacheinander die Elemente in verkehrter Reihenfolge
        int index = size() - 1;

        List<E> newList = new List<E>();

        // mit höchstem index beginnend bis wir am ende angekommen sind
        for (; index >= 0; index--) {
            newList.addLast(get(index));
        }

        return newList;
    }

    // gibt ein Array mit allen Elementen der Liste zurück
    public void toArray(E[] a){
        // ist das Array groß genug?
        if (a.length < size()) {
            throw  new IllegalArgumentException("Array ist zu klein " + a.length + " < " + size());
        }

        // wir gehen die Liste durch und fügen jedes Element dem Array hinzu und holen uns dann das nächste Element
        Entry<E> current = first;
        for (int i = 0; i < size(); i++) {
            a[i] = current.data;
            current = current.next;
        }
    }

    // Tauscht das Element an der i-ten Stelle mit dem neuen Daten aus
    // und gibt die vorherigen Daten zurück
    public E replace(E e, int i) {
        // Haben wir genug Elemente in unserer Liste?
        if (i > size()) {
            throw new IllegalArgumentException();
        }

        // Wollen wir das erste Element austauschen?
        if (i == 0) {
            Entry<E> oldFirst = first;
            first = new Entry<>(e, oldFirst.next);
            return oldFirst.data;
        }

        // wir starten wieder bei dem vorherigen Element
        Entry<E> prev = getEntry(i - 1);
        Entry<E> old = prev.next;

        // der neue Eintrag soll dahin zeigen, wo der alte hingezeigt hat
        Entry<E> newEntry = new Entry<>(e, prev.next.next);
        // der vorherige zeigt jetzt auf den neuen Eintrag
        prev.next = newEntry;
        // wir geben die Daten des alten Eintrags aus, der ersetzt wurde
        return old.data;
    }

    public void addLast(E e) {
        Entry<E> toAdd = new Entry<>(e, null);

        // Ist die Liste bisher leer?
        if (first == null) {
            first = toAdd;
            return;
        }

        // Gehe zum letzten Element und hänge das neue an :)
        Entry<E> current = first;
        while (current.next != null) {
            current = current.next;
        }
        current.next = toAdd;
    }

    public E removeFirst() {
        //  ist die Liste leer?
        if (first == null) {
            return null;
        }

        // Setz den first Verweis auf das bisher 2. Element und gebe die Daten des 1. aus
        E tmp = first.data;
        first = first.next;
        return tmp;
    }

    public E removeLast() {
        return removeAtIndex(size() - 1);
    }

    public E removeAtIndex(int i) {
        if (i >= size()) {
            throw new IllegalArgumentException("Index zu groß zum Löschen");
        }

        // Löschen wir den ersten Eintrag?
        if (i == 0) {
            E tmp = first.data;
            removeFirst();
            return tmp;
        }

        // greife auf den vorherigen Eintrag zu
        Entry<E> prev = getEntry(i - 1);
        E tmp = prev.next.data;
        // Wir "löschen" den eintrag indem wir den zeiger, des vorherigen Eintrags auf das nächste Element der Liste
        // zeigen lassen
        prev.next = prev.next.next;
        return tmp;
    }

    public int size() {
        if (first == null) {
            return 0;
        }

        // Standard loop um die Liste zu durchlaufen und wir lassen einfach einen Zähler mitlaufen :)
        int counter = 1;
        Entry<E> current = first;
        while (current.next != null) {
            current = current.next;
            counter++;
        }

        return counter;
    }

    public String toString() {
        if (first == null) {
            return "";
        }

        String resultat = "";

        // wir durchlaufen die Liste bis zum vorletzten Element und fügen jeweils die Daten zum String hinzu
        Entry<E> current = first;
        while(current.next != null) {
            resultat = resultat + current.data + ", ";
            current = current.next;
        }

        // beim letzten Element lassen wir das , weg, wie gefordert
        resultat = resultat + current.data;
        return resultat;
    }

    public boolean remove(Object o) {
        Entry<E> current = first;
        Entry<E> prev = null;

        while(current != null) {
            if (current.data.equals(o)) {
                // direkt beim ersten Element ein Treffer
                if (prev == null) {
                    removeFirst();
                    return true;
                } else {
                    // treffer aber nicht beim 1. Element
                    prev.next = current.next;
                    return true;
                }
            }
            prev = current;
            current = current.next;
        }
        // alle elemente verglichen, aber das richtige nicht gefunden :(
        return false;
    }

    public boolean equals(Object o) {
        if (!(o instanceof List)) {
            return false;
        }

        List<E> other = (List<E>) o;

        // meine beiden Elemente in Liste 1 und 2
        Entry<E> currentThis = this.first;
        Entry<E> currentOther = other.first;

        // wenn eines der beiden null wird, sind wir am Ende einer der beiden Listen
        while (currentThis != null && currentOther != null) {
            if (!(currentOther.data.equals(currentThis.data))) {
                // 1 ungleiches element --> insgesamt nicht gleich
                return false;
            }

            // beide listen gehen 1 element weiter
            currentOther = currentOther.next;
            currentThis = currentThis.next;
        }

        // es müssen beide gleichzeitig null werden, wenn sie gleich lang sind
        return currentOther == currentThis;
    }
}
