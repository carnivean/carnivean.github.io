/*
 * Entwerfen Sie außerdem eine Klasse Author so, sodass Dokumente später 
 * einem Autor zugeordnet werden können. Ein Autor hat dabei einen Vornamen, 
 * einen Nachnamen, ein Geburtsdatum, einen Wohnort und eine E-Mail-Adresse. 
 * Verwenden Sie hierzu auch die Klasse Date aus 1.
 */
public class Author {
	private Date birthday;
	private String name, surname, location, emailadress;
	
	public Date getBirthday() {
		return birthday;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public String getLocation() {
		return location;
	}
	public String getEmailadress() {
		return emailadress;
	}
	
	public Author(String firstName, String lastName,
			 Date birthday, String residence, String email) {
		name = firstName;
		surname = lastName;
		this.birthday = birthday;
		location = residence;
		this.emailadress = email;
	}
	
	public String toString() {
		String result = "Author: " + name + " " + surname + " ist geboren am " 
	+ birthday.getDay() + "." + birthday.getMonth() + "." + birthday.getYear();
		
		return result;
	}
	
	/* In der ersten Zeile steht der Name, in der zweiten Zeile
die E-Mail-Adresse und in der dritten Zeile der Wohnort des Autors. 
Einen Zeilentrenner erhalten Sie dabei mittels des Attributes NEWLINE der zur Verfügu
	*/
	public String getContactInformation() {
		String result = "Name: " + name + " " + surname + Terminal.NEWLINE;
		result = result + "Email: " + emailadress + Terminal.NEWLINE;
		result += "Residence: " + location;
		
		return result;
	}
	
	public int getAgeAt(Date today) {
		return birthday.getAgeInYearsAt(today);
	}
}
