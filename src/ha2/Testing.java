
public class Testing {
	
	public static void main(String[] args) {
		Date heute  = new Date();
		Date gestern = new Date(9, 11, 2019);
		// String firstName, String lastName,
		 // Date birthday, String residence, String email
		Author me = new Author("Erik", "Kynast", new Date(22, 10, 1988), 
				"Dietersheim", "kynast@in.tum.de");
		System.out.println("Testing: " + heute + " - " + gestern); 
		System.out.println("" + me.getBirthday());
		System.out.println("Kontakt: " + Terminal.NEWLINE+  me.getContactInformation());
		
		Date ursprung = new Date(1, 1, 2019);
		Date daysLater = new Date(11, 1, 2019); // sollte 10 sein
		Date monthLater = new Date(1, 11, 2019);    // sollte 300 sein
		Date dayMonthLater = new Date(11, 11, 2019); // sollte 310 sein
		Date yearLater = new Date(1, 1, 2020); // sollte 360 sein
		
		
		System.out.println("daysLater Testing: " + ursprung.getAgeInDaysAt(daysLater));
		System.out.println("monthLater Testing: " + ursprung.getAgeInDaysAt(monthLater));
		System.out.println("dayMonthLater Testing: " + ursprung.getAgeInDaysAt(dayMonthLater));
		System.out.println("yearLater Testing: " + ursprung.getAgeInDaysAt(yearLater));
		System.out.println("2 dates: " + ursprung.getAgeInDaysAt(daysLater));
	}
}
