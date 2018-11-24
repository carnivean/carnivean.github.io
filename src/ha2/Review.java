/* 
 * Entwerfen Sie zusätzlich eine Klasse Review, die eine Rezension repräsentiert. 
 * Eine
solche Rezension enthält einen Text, ist von jeweils einem Autor verfasst und 
bezieht
sich auf ein konkretes Dokument. Eine Rezension ist außerdem in einer bestimmten 
Sprache verfasst und besitzt ein Veröffentlichungsdatum. Schließlich bewertet
eine Rezension das Dokument auf einer Skala von 0 bis 10. Verwenden Sie hierzu
wiederum die Klassen Date, Author und Document aus 1, 2 und 3.
 */

public class Review {
	private String language, content;
	private Date releaseDate;
	private int points;
	private Document reviewedDocument;
	private Author reviewAuthor;
	
	public String getLanguage() {
		return language;
	}

	/**
	 * @return the releaseDate
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}

	public int getPoints() {
		return points;
	}

	public Document getReviewedDocument() {
		return reviewedDocument;
	}

	public Author getReviewAuthor() {
		return reviewAuthor;
	}
	
	public String getContent() {
		return content;
	}

	public Review(Author author, Document reviewedDocument,
			String language, Date releaseDate, int rating, String content) {
		this.reviewAuthor = author;
		this.reviewedDocument = reviewedDocument;
		this.language = language;
		this.points = rating;
		this.content = content;
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "Review [content=" + content + ", points=" + points 
				+ ", reviewedDocument=" + reviewedDocument
				+ ", reviewAuthor=" + reviewAuthor + "]";
	}
	
	public int getAgeAt(Date today) {
		return releaseDate.getAgeInDaysAt(today);
	}
}
