/**
 * The class {@code Document} represents a document.
 * 
 * This class ensures that neither the title nor the language nor the
 * summary of the document is <code>null</code>.
 * 
 * @see Date
 * @see Author *
 */
public class Document {
	public static final String[] SUFFICES = {
			"ab", "al", "ant", "artig", "bar", "chen", "ei", "eln", "en", "end", "ent", "er", "fach", 
			"fikation", "fizieren", "fähig", "gemäß", "gerecht", "haft", "haltig", "heit", "ie", 
			"ieren", "ig", "in", "ion", "iren", "isch", "isieren", "isierung",
			"ismus", "ist", "ität", "iv", "keit", "kunde", "legen", "lein", "lich", 
			"ling", "logie", "los", "mal", "meter", "mut", "nis", "or", "sam", "schaft", "tum", 
			"ung", "voll", "wert", "würdig" };
	
	private WordCountsArray wordCounts;
	

	public WordCountsArray getWordCounts() {
		return wordCounts;
	}

	public static String[] tokenize(String content) {
		int counter = 0;
		for (int i = 0; i < content.length(); i++) {
			if (content.charAt(i) == ' ') {
				counter++;
			}
		}
		counter++;
		
		String[] words = new String[counter];
		
		String word = "";
		int zaehler = 0;
		for (int i = 0; i < content.length(); i++) {
			if (content.charAt(i) != ' ') {
				word = word + content.charAt(i);
			} else {
				words[zaehler] = word;
				zaehler++;
				word = "";
			}
		}
		words[zaehler] = word;
		return words;
	}
	
	private static boolean sufficesEqual(String w1, String w2, int n) {
		if (w1.length() < n || w2.length() < n) {
			return false;
		}
		int n1 = w1.length();
		int n2 = w2.length();
		for (int i = 0; i < n; i++) {
			if (w2.charAt(n2 - 1 - i) == w1.charAt(n1 - 1 - i)) {
				
			} else {
				return false;
			}
		}
		return true;
	}
	
	private static String findSuffix(String word) {
		int maxLength = 0;
		String bestSuffix = "";
		
		for (int i = 0; i < SUFFICES.length; i++) {
			if (sufficesEqual(word, SUFFICES[i], SUFFICES[i].length())) {
				if (SUFFICES[i].length() > maxLength) {
					bestSuffix = SUFFICES[i];
					maxLength = SUFFICES[i].length();
				}
			}
		}
		return bestSuffix;
	}
	
	private static String cutSuffix(String word, String suffix) {
		if (!(sufficesEqual(word, suffix, suffix.length()))) {
			return word;
		}
		
		String newWord = "";
		for (int i = 0; i < (word.length() - suffix.length()); i++) {
			newWord = newWord + word.charAt(i);
		}
		return newWord;
	}
	
	/*
	 * die den Fließtext des Dokumentes, d.h. content, als Parameter hat. Die Methode
soll den Fließtext zunächst in die einzelnen Wörter zerlegen (vgl. 3). Bei jedem
einzelnen Wort soll dann gegebenenfalls das Suffix (vgl. 1) entfernt werden. Das
resultierende Wort (bzw. der resultierende Wortstamm) soll dann durch das Attribut
aus 2 verwaltet werden. Beachten Sie, dass Sie hierzu ihre vorherigen Teilergebnisse
verwenden können.
	 */
	private void addContent(String content) {
		String[] tokenized = tokenize(content);
		for (int i = 0; i < tokenized.length; i++) {
			String suff = findSuffix(tokenized[i]);
			if (!suff.equals("")) {
				tokenized[i] = cutSuffix(tokenized[i], suff);
			}
			wordCounts.add(tokenized[i], 1);
		}
	}
/**
   * the title of the document
   */
  private String title;

  /**
   * the language of the document
   */
  private String language;

  /**
   * summary of the document
   */
  private String summary;

  /**
   * content of the document
   */
  private String content;

  /**
   * the release date of the document
   * 
   * @see Date
   */
  private Date releaseDate;

  /**
   * the {@link Author} of the document
   * 
   * @see Author
   */
  private Author author;

  /**
   * Constructs a document with the given values.
   * 
   * @param title       the document's title
   * @param language    the language the document is written in
   * @param summary     short summary of the document
   * @param releaseDate the release date of the document
   * @param author      the author of the document
   */
  public Document(String title, String language, String summary, Date releaseDate, Author author, String content) {
    this.setTitle(title);
    this.setLanguage(language);
    this.setSummary(summary);
    this.setReleaseDate(releaseDate);
    this.setAuthor(author);
    this.setContent(content);
  }

  /**
   * Returns the title of the document.
   * 
   * @return the title of the document
   */
  public String getTitle() {
    return title;
  }

  /**
   * Returns the language the document is written in.
   * 
   * @return the language the document is written in
   */
  public String getLanguage() {
    return language;
  }

  /**
   * Returns the text content of the document.
   * 
   * @return the text content of the document
   */
  public String getContent() {
    return content;
  }

  /**
   * Returns a short summary of the document.
   * 
   * @return a short summary of the document
   */
  public String getSummary() {
    return summary;
  }

  /**
   * Returns the release date of the document.
   * 
   * @return the release date of the document
   */
  public Date getReleaseDate() {
    return releaseDate;
  }

  /**
   * Returns the author of the document.
   * 
   * @return the author of the document
   * @see Author
   */
  public Author getAuthor() {
    return author;
  }

  /**
   * Returns the age of this document at the specified date in days.
   * 
   * @param today the specified date
   * @return the age of this document at the specified date:
   */
  public int getAgeAt(Date today) {
    return this.releaseDate.getAgeInDaysAt(today);
  }
  
  /**
   * Returns a brief string representation of this document.
   * 
   * @return a brief string representation of this document
   */
  public String toString() {
    return this.title + " by " + this.author.toString();
  }

  /**
   * Sets the title of the document.
   * 
   * If the new title is <code>null</code>, then the title is set to an empty
   * {@link String}.
   * 
   * @param title the new title
   */
  public void setTitle(String title) {
    if (title == null) {
      this.title = "";
    } else {
      this.title = title;
    }
  }

  /**
   * Sets the language of the document.
   * 
   * If the new language is <code>null</code>, then the language is set to an
   * empty {@link String}.
   * 
   * @param language the new language
   */
  public void setLanguage(String language) {
    if (language == null) {
      this.language = "";
    } else {
      this.language = language;
    }
  }

  /**
   * Sets the summary of the document.
   * 
   * If the new summary is <code>null</code>, then the summary is set to
   * an empty {@link String}.
   * 
   * @param summary the new summary
   */
  public void setSummary(String summary) {
    if (summary == null) {
      this.summary = "";
    } else {
      this.summary = summary;
    }
  }

  /**
   * Sets the content of the document.
   * 
   * If the new content is <code>null</code>, then the content is set to
   * an empty {@link String}.
   * 
   * @param content the new content
   */
  public void setContent(String content) {
    if (content == null) {
      this.content = "";
    } else {
      this.content = content;
    }
  }

  /**
   * Sets the release date of the document.
   * 
   * @param releaseDate the release date
   */
  public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
  }

  /**
   * Sets the author of the document.
   * 
   * @param author the new author
   */
  public void setAuthor(Author author) {
    this.author = author;
  }
}
