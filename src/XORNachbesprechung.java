import java.util.Arrays;

public class XORNachbesprechung extends MiniJava {
  public static void main(String[] args) {
    int key = -1, vector = -1;

    while(key < 0 || key > 63) {
      key = readInt("Geben Sie den Schlüssel (0 - 63) ein, danke.");
    }

    while(vector < 0 || vector > 63) {
      vector = readInt("Geben Sie den Schlüssel (0 - 63) ein, danke.");
    }

    String zuVerschlüsseln = readString("Was möchten Sie verschlüsseln?");

    int[] encoded = encode(zuVerschlüsseln);

    int[] verschlüsselt = new int[encoded.length];
    // Schleife über die einzelnen kodierten Zeichen
    verschlüsselt[0] = verschlüsselung(encoded[0], key, vector);
    for (int i = 1; i < encoded.length; i++) {
      verschlüsselt[i] = verschlüsselung(encoded[i], key, verschlüsselt[i - 1]);
    }
    String verschlüsselterText = decode(verschlüsselt);
    writeLineConsole(verschlüsselterText);

    int[] encodeBack = encode(verschlüsselterText);
    int[] entschlüsselt = new int[encodeBack.length];
    entschlüsselt[0] = verschlüsselung(encodeBack[0], key, vector);
    for (int i = 1; i < entschlüsselt.length; i++) {
      entschlüsselt[i] = verschlüsselung(encodeBack[i], key, encodeBack[i - 1]);
    }
    String entschlüsselterText = decode(entschlüsselt);
    writeLineConsole(entschlüsselterText);
  }

  private static int verschlüsselung(int i, int key, int vector) {
    // das verschlüsselt, irgendwann einmal
    int result = i ^ key ^ vector;

    return result;
  }

  private static String decode(int[] test) {
    String result = "";

    for (int i = 0; i < test.length; i++) {
      if (test[i] == 62) {
        result += " ";
      } else if (test[i] == 63) {
        result += ".";
      } else if (test[i] < 26 && test[i] >= 0) {
        result += (char) (test[i] + 'a');
      } else if (test[i] >= 26 && test[i] <= 51) {
        result += (char) ((test[i] - 26) + 'A');
      } else if (test[i] >= 52 && test[i] <= 61) {
        result += (char) ((test[i] - 52) + '0');
      }
    }

    return result;
  }

  private static int[] encode(String zuVerschlüsseln) {
    int[] result = new int[zuVerschlüsseln.length()];

    for (int i = 0; i < zuVerschlüsseln.length(); i++) {
      char c = zuVerschlüsseln.charAt(i);

      if (c == ' ') {
        result[i] = 62;
      } else if (c == '.') {
        result[i] = 63;
      } else if (Character.isDigit(c)) {
        result[i] = c + 4;
      } else if (Character.isLowerCase(c)) {
        result[i] = c - 'a';
      } else if (Character.isUpperCase(c)) {
        result[i] = c - 'A' + 26;
      } else {
        System.exit(-1);
      }
    }
    return result;
  }

  
}
