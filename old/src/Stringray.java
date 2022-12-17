public class Stringray extends MiniJava{
  public static void main(String[] args) {
    String eingabe;

    eingabe = readString("Bitte geben Sie einen beliebigen Text ein, danke.");

    int menuAuswahl = -1;
    while (menuAuswahl < 0 || menuAuswahl > 3) {
      menuAuswahl = readInt("Bitte wählen Sie die Operation aus:\n"
          + "1 für die Häufigkeit der Buchstaben\n"
          + "2 für Buchstabenersetzung\n"
          + "3 für \n"
          + "0 zum Beenden des Programmes");
    }

    // Teil 1 - Häufigkeit der Buchstaben
    if (menuAuswahl == 1) {
      int[] frequency = new int[26];
      for (int index = 0; index < eingabe.length(); index++) {
        char c = eingabe.charAt(index);

        if (c >= 'a' && c <= 'z') {
          frequency[c - 'a']++;
        } else if (c >= 'A' && c <= 'Z') {
          frequency[c - 'A']++;
        }
      }

      for (int index = 0; index < 26; index++) {
        if (frequency[index] > 0) {
          writeConsole("" + (char) (index + 'A') + ": " + frequency[index] + " ");
        }
      }
    }

    if (menuAuswahl == 2) {
      String buchstabe1 = "";
      String buchstabe2 = "";

      while(!(buchstabe1.length() == 1 &&(
              (buchstabe1.charAt(0) >= 'a' && buchstabe1.charAt(0) <= 'z') ||
              (buchstabe1.charAt(0) >= 'A' && buchstabe1.charAt(0) <= 'Z')))) {
        buchstabe1 = readString("Bitte geben Sie einen einzelnen Buchstaben ein.");
      }

      while(!(buchstabe2.length() == 1 &&(
          (Character.isLetter(buchstabe2.charAt(0)))))) {
        buchstabe2 = readString("Bitte geben Sie einen einzelnen Buchstaben ein.");
      }

      char char1 = buchstabe1.charAt(0);
      char char2 = buchstabe2.charAt(0);
      char1 = Character.toUpperCase(char1);
      char2 = Character.toUpperCase(char2);
      char char1Klein = Character.toLowerCase(char1);
      char char2Klein = Character.toLowerCase(char2);

      String result = "";
      for (int index = 0; index < eingabe.length(); index++) {
        if (eingabe.charAt(index) == char1) {
          result += char2;
        } else if (eingabe.charAt(index) == char1Klein) {
          result += char2Klein;
        } else  {
          result += eingabe.charAt(index);
        }
      }
      writeConsole(result);
    }

    if (menuAuswahl == 3) {
      int indexLeerzeichen = 0;
      String result = "";

      for (int index = 0; index < eingabe.length(); index++) {
        if (eingabe.charAt(index) == ' ') {
          for (int indexBackwards = index - 1; indexBackwards >= indexLeerzeichen;
              indexBackwards--) {
              result += eingabe.charAt(indexBackwards);
          }
          result += " ";
          indexLeerzeichen = index + 1;
        }
      }
      for (int indexBackwards = eingabe.length() - 1; indexBackwards >= indexLeerzeichen;
          indexBackwards--) {
        result += eingabe.charAt(indexBackwards);
      }
      writeConsole(result);
    }
  }
}
