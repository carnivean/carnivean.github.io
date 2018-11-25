
public class WordCountsArray {
	private WordCount[] wordCountArray;
	private int size;
	private int nextIndex;
	
	public WordCountsArray(int initSize) {
		if (initSize < 1)
			initSize = 1;
		this.size = initSize;
		wordCountArray = new WordCount[initSize];
		nextIndex = 0;
	}
	
	/*
	 * die das gegebene Wort word mit der Häufigkeit count zum Array hinzufügt. Beachten Sie, dass die Methode hierzu zunächst ein WordCount-Objekt erzeugen muss. Ist
			das übergebene Wort der leere String, so soll die Methode keine Änderung am Array
			vornehmen. Sie müssen sich an dieser Stelle keine Gedanken darüber machen, was
			geschieht wenn diese Methode mehrmals mit demselben Wert für word aufgerufen
			wird. Diese Problematik werden wir auf dem kommenden Übungsblatt behandeln.
	 */
	public void add(String word, int count) {
		if (word == null || word.length() == 0) {
			System.out.println("Falsche Parameter bei wordCountsArray.add()");
			return;
		}
		
		// Array voll?!
		if (nextIndex == size) {
			if (size == 0)
				size = 1;
			else
				size = size * 2;
			WordCount[] tmp = new WordCount[size];
			for (int i = 0; i < nextIndex; i++) {
				tmp[i] = wordCountArray[i];
			}
			wordCountArray = tmp;
		}
		
		wordCountArray[nextIndex] = new WordCount(word, count);
		nextIndex++;
	}
	
	public int size() {
		return nextIndex;
	}
	
	/* Bei illegalen Indizes soll der Rückgabewert der leere String ("") bzw. -1 sein. */
	public String getWord(int index) {
		if (index < nextIndex && index >= 0) {
			return wordCountArray[index].getWord();
		} else {
			return "";	
		}
	}
	
	/* Bei illegalen Indizes soll der Rückgabewert der leere String ("") bzw. -1 sein. */
	public int getCount(int index) {
		if (index < nextIndex && index >= 0) {
			return wordCountArray[index].getCount();
		} else {
			return -1;	
		}
	}
	
	public void setCount(int index, int count) {
		if (index < nextIndex && index >= 0 &&  count >= 0) {
			wordCountArray[index].setCount(count);
		}
		System.out.println("Falscher Parameter: setCount() @WordCountsArray");
	}
	
	
	
	
}
