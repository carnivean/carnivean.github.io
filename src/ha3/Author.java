/**
 * The class {@code Author} represents an author of a {@link Document} or a
 * {@link Review}.
 * 
 * This class ensures that neither of first name, last name, residence and email
 * is <code>null</code>. There is no <code>setter</code> for the birthday of the
 * author, since this field can never ever change.
 * 
 * @see Document
 * @see Review
 * @see Date
 *
 */
public class Author {
  /**
   * the first name of the author
   */
  private String firstName;

  /**
   * the last name of the author
   */
  private String lastName;

  /**
   * the birthday of the author
   * 
   * @see Date
   */
  private Date birthday;

  /**
   * the residence of the author
   */
  private String residence;

  /**
   * the email address of the author
   */
  private String email;

  /**
   * Constructs an author with the given values.
   * 
   * @param firstName the author's first name
   * @param lastName  the author's last name
   * @param birthday  the author's birthday
   * @param residence the author's residence
   * @param email     the author's email address
   */
  public Author(String firstName, String lastName, Date birthday, String residence, String email) {
    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.setBirthday(birthday);
    this.setResidence(residence);
    this.setEmail(email);
  }

  /**
   * Returns the first name of the author.
   * 
   * @return the first name of the author
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Returns the last name of the author.
   * 
   * @return the last name of the author
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Returns the birthday of the author.
   * 
   * @return the birthday of the author
   * @see Date
   */
  public Date getBirthday() {
    return birthday;
  }

  /**
   * Returns the residence of the author.
   * 
   * @return the residence of the author
   */
  public String getResidence() {
    return residence;
  }

  /**
   * Returns the email address of the author.
   * 
   * @return the email address of the author
   */
  public String getEmail() {
    return email;
  }

  /**
   * Returns a string representation of this author.
   * 
   * @return a string representation of this author
   */
  public String toString() {
    return this.firstName + " " + this.lastName;
  }

  /**
   * Returns the contact information of the author.
   * 
   * The information has three lines as follows:<br/>
   * <ol>
   * <li>name of the author</li>
   * <li>email address of the author</li>
   * <li>residence of the author</li>
   * </ol>
   * 
   * @return the contact information of the author
   */
  public String getContactInformation() {
    return this.firstName + " " + this.lastName + Terminal.NEWLINE + "<" + this.email + ">" + Terminal.NEWLINE
        + this.residence;

//		return "<" + this.email + ">"+ Terminal.NEWLINE
//				+ this.firstName + " " + this.lastName  + Terminal.NEWLINE
//				+ this.residence;

  }

  /**
   * Returns the age of this author at the specified date in years.
   * 
   * @param today the specified date
   * @return the age of this author at the specified date
   * @see Date
   */
  public int getAgeAt(Date today) {
    return this.birthday.getAgeInYearsAt(today);
  }

  /**
   * Sets the first name of the author.
   * 
   * If the specified first name is <code>null</code>, then the first name is set
   * to an empty {@link String}.
   * 
   * @param firstName the new first name
   */
  public void setFirstName(String firstName) {
    if (firstName == null) {
      this.firstName = "";
    } else {
      this.firstName = firstName;
    }
  }

  /**
   * Sets the last name of the author.
   * 
   * If the specified last name is <code>null</code>, then the last name is set to
   * an empty {@link String}.
   * 
   * @param lastName the new last name
   */
  public void setLastName(String lastName) {
    if (lastName == null) {
      this.lastName = "";
    } else {
      this.lastName = lastName;
    }
  }

  /**
   * Sets the birthday of the author.
   * 
   * If the specified birthday is <code>null</code>, then the birthday is set
   * to the 1st of January, 1999.
   * 
   * @param birthday the new birthday
   */
  public void setBirthday(Date birthday) {
    if(birthday == null)
      this.birthday = new Date(1, 1, 1999);
    else
      this.birthday = birthday;
  }

  /**
   * Sets the residence of the author.
   * 
   * If the specified residence is <code>null</code>, then the residence is set to
   * an empty {@link String}.
   * 
   * @param residence the new residence
   */
  public void setResidence(String residence) {
    if (residence == null) {
      this.residence = "";
    } else {
      this.residence = residence;
    }
  }

  /**
   * Sets the email address of the author.
   * 
   * If the specified email address is <code>null</code> or does not contain an @-symbol,
   * then the email address is set to invalid@peguins.com.
   * 
   * @param email the new email address
   */
  public void setEmail(String email) {
    if (email == null || !email.contains("@")) {
      this.email = "invalid@penguins.com";
    } else {
      this.email = email;
    }
  }
}
