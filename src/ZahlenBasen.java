public class ZahlenBasen extends MiniJava{
  public static void main(String[] args) {
    int[] test = readNumber();
    int[] test2 = readNumber();
    int[] resultAddition = add(test, test2, 16);
    int[] resultMulti = mul(test, test2, 16);
    writeConsole(toString(resultAddition));
    writeLineConsole(toString(resultMulti));
    resultAddition = add(test, test2, 23);
    resultMulti = mul(test, test2, 23);
    writeConsole(toString(resultAddition));
    writeLineConsole(toString(resultMulti));
  }

  public static int[] mul(int[] a, int[] b, int base) {
    // Latex Ausgabe
    int[][] zeilen = new int[b.length][];
    for (int i = 0; i < b.length; i++) {
      zeilen[i] = mulDigit(a, b[i], i, base);
    }
    int[] result = zeilen[0];
    for (int i = 1; i < zeilen.length; i++) {
      result = add(result, zeilen[i], base);
    }
    return result;
  }

  public static int[] mulDigit(int[] a, int digit, int shift, int base) {
    if (digit == 0) {
      return new int[]{0};
    }

    int uebertrag = 0;
    for (int i = 0; i < a.length; i++) {
      int sum = a[i] * digit + uebertrag;
      uebertrag = sum / base;
    }

    int len = a.length;
    if (uebertrag > 0)
      len = a.length + 1;

    int[] result = new int[len + shift];
    for (int i = 0; i < len; i++) {
      int sum = valueOfDigit(a, i) * digit + uebertrag;
      uebertrag = sum / base;
      result[i + shift] = sum % base;
    }
    return result;
  }

  public static int[] add(int[] a, int[] b, int base) {
    int len = Math.max(a.length, b.length);

    int uebertrag = 0;
    for (int i = 0; i < len; i++) {
      int sum = valueOfDigit(a, i) + valueOfDigit(b, i) + uebertrag;
      uebertrag = sum / base;
    }

    int[] result;
    if (uebertrag > 0)
      result = new int[len + 1];
    else
      result = new int[len];

    uebertrag = 0;
    for (int i = 0; i < result.length; i++) {
      int sum = valueOfDigit(a, i) + valueOfDigit(b, i) + uebertrag;
      uebertrag = sum / base;
      result[i] = sum % base;
    }

    return result;
  }

  private static int valueOfDigit(int[] a, int i) {
    if (i < a.length) {
      return a[i];
    }
    return 0;
  }

  public static String toString(int[] number) {
    String result = "";

    for (int i = 0; i < number.length; i++) {
      result += digitToString(number[number.length - 1 - i]);
    }

    return result;
  }

  private static String digitToString(int i) {
    if (i >= 10) {
      return "" + (char) ('A' + i - 10);
    }
    return "" + (char) (i + '0');
  }

  public static int[] readNumber() {
    boolean korrekteEingabe = false;
    int[] result = null;

    while(!korrekteEingabe) {
      korrekteEingabe = true;
      String input = readString("Bitte geben Sie eine beliebige Zahl ein. [A..Z0..9]");
      input = input.toUpperCase();

      result = new int[input.length()];
      for (int i = input.length() - 1; i >= 0; i--) {
        char c = input.charAt(i);

        // Character.isDigit()
        if (c >= '0' && c <= '9') {
          result[input.length() - 1 - i] = c - '0';
        } else if (Character.isUpperCase(c)) {
          result[input.length() - 1 - i] = c - 'A' + 10;
        } else {
          // Fehlerbehandlung
          result[i] = -1;
          korrekteEingabe = false;
        }
      }
    }

    return result;
  }
}
