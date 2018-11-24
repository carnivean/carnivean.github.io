
/*
 * Verschiedene Klassen sollen später mittels eines Datums genauer beschrieben werden
können. Entwerfen Sie daher zunächst eine Klasse Date, die ein Datum repräsentiert.
Ein Datum besteht dabei aus einem Tag, einem Monat und einem Jahr. Achtung:
Die Verwendung von java.util.Date ist hier nicht erlaubt!
 */
public class Date {
	private int day, month, year;
	
	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}
	
	public Date(int day, int month, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
	public Date() {
		this.day = Terminal.TODAYS_DAY;
		this.month = Terminal.TODAYS_MONTH;
		this.year = Terminal.TODAYS_YEAR;
	}
	
	public String toString() {
		String result = "Date: " + day + "." + month + "." + year;
		return result;
	}
	
	private int daysSince1970() {
		int diffYear, diffMonth, diffDays;
		diffYear = (year - 1970) * 360;
		diffMonth = (month - 1) * 30;
		diffDays = day;
		
		return diffYear + diffMonth + diffDays;
	}
	
	public int getAgeInDaysAt(Date today) {
		return today.daysSince1970() - this.daysSince1970();
	}
	
	/*
	public int getAgeInDaysAt(Date today, Date old) {
		return old.daysSince1970() - today.daysSince1970();
	} */
	
	public int getAgeInYearsAt(Date today) {
		return getAgeInDaysAt(today) / 360;
	}
}
