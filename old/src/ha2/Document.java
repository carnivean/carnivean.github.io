/* 
 * Die Suchmaschine soll später in Dokumenten suchen können. Entwerfen Sie daher
eine Klasse Document, die ein solches Dokument beschreibt. Ein Dokument hat 
zusätzlich zum beinhaltenden Text einen Titel und ist in einer bestimmten Sprache
verfasst. Zu jedem Dokument gibt es außerdem eine Zusammenfassung, 
ein Veröffentlichungsdatum und einen Autor. 
Verwenden Sie hierzu die Klassen Date und
Author aus 1 und 2.
 */
public class Document {
	private String title, content, language, summary;
	private Author author;
	private Date releaseDate;
	
	public Date getReleaseDate() {
		return releaseDate;
	}

	public Author getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public String getLanguage() {
		return language;
	}

	public String getSummary() {
		return summary;
	}


	public String getContent() {
		return content;
	}
	
	public Document(String title, String language, String summary,
			Date releaseDate, Author author, String content) {
		this.title = title;
		this.language = language;
		this.summary = summary;
		this.releaseDate = releaseDate;
		this.author = author;
		this.content = content;
	}


	
	
	
	@Override
	public String toString() {
		return "Document: Der Titel des Dokuments ist " + title + ", content=" + content + ", language=" + language + 
				", summary=" + summary
				+ ", author=" + author + ", releaseDate=" + releaseDate + "]";
	}

	public int getAgeAt(Date today) {
		return releaseDate.getAgeInDaysAt(today);
	}
	
	public static void main(String[] args) {
		Document doc = new Document("test1", "deutsch", "Kurzfassung", new Date(1, 1, 2000),
				new Author("Max", "Mustermann", new Date(1, 1, 2018), "Test1", "Test2")
				, "content1");
		System.out.print("" + doc);
	}
}
