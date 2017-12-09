import java.util.Arrays;

public class GrausGauß extends MiniJava {
  private static int lines;
  private static int spalten;


  public static void main(String[] args) {
    lines = read("Geben Sie die Anzahl der Gleichungen ein.");

    int[] matrix = readMatrix();
    int[] ergebnis = solveSystem(matrix);
    writeLineConsole("Das Ergebnis ist: " + Arrays.toString(ergebnis));
  }

  public static void searchSwap(int[] matrix, int fromLine) {
    int value = get(matrix, fromLine, fromLine);
    if (value != 0) {
      return;
    }
    for (int i = fromLine + 1; i < lines; i++) {
      value = get(matrix, i, fromLine);
      if (value != 0){
        swap(matrix, fromLine, i);
        return;
      }
    }
  }

  public static int kgv(int a, int b) {
    int o = ggt(a, b);
    int p = (a * b) / o;
    return p;
   }

  private static int ggt(int a, int b) {
    if (b==0)
      return a;
    else
      return ggt(b, a % b);
  }

  public static int[] solveSystem(int[] matrix) {
    for (int i = 0; i < lines; i++) {
      // 1. Schritt laut beschreibung
      searchSwap(matrix, i);

      int d = get(matrix, i, i);
      for (int j = i + 1; j < lines; j++) {
        int ei = get(matrix, j, i);
        int kgvi = kgv(d, ei);
        int kgviei = kgvi / ei;
        multLine(matrix, j, kgviei);
        int kgvd = - kgvi / d;
        multAddLine(matrix, j, i, kgvd);
      }
    }

    printMatrix(matrix);
    // Matrix ist in ZSF --> Ergebnis zurückliefern
    int[] ergebnis = rowEchelonToResult(matrix);
    return ergebnis;
  }

  public static int[] rowEchelonToResult(int[] matrix) {
    int[] vector = new int[spalten - 1];

    for (int i = vector.length - 1; i >= 0; i--) {
      int factor = get(matrix, i, i );
      int value = get(matrix, i, spalten - 1);

      for (int j = i; j < vector.length; j++) {
        value +=  -1 *get(matrix, i, j) * vector[j];
      }

      int result = value / factor;
      vector[i] = result;
    }
    return vector;
  }

  public static void multLine(int[] matrix, int line, int factor) {
    for (int i = 0; i < spalten; i++) {
      int newValue = factor * get(matrix, line, i);
      set(matrix, line, i, newValue);
    }
  }

  public static void multAddLine(int[] matrix, int line, int otherLine, int factor) {
    for (int i = 0; i < spalten; i++) {
      int newValue = get(matrix, line, i);
      int other = get(matrix, otherLine, i) * factor;
      newValue = newValue + other;
      set(matrix, line, i, newValue);
    }

  }

  public static void swap(int[] matrix, int lineA, int lineB) {
    for (int i = 0; i < spalten; i++) {
      int tmp = get(matrix, lineA, i);
      int valueOfB = get(matrix, lineB, i);
      set(matrix, lineA, i, valueOfB);
      set(matrix, lineB, i, tmp);
    }
  }

  public static int get(int[] matrix, int line, int column) {
    return matrix[getIndex(line, column)];
  }

  private static int getIndex(int line, int column) {
    return line * spalten + column;
  }

  public static void set(int[] matrix, int line, int column, int value) {
    matrix[getIndex(line, column)] = value;
  }

  public static void printMatrix(int[] matrix) {
    int counter = 0;
    for (int i = 0; i < lines; i++) {
      for (int j = 0; j < spalten; j++) {
            writeConsole("" + matrix[counter] + "     ");
            counter++;
      }
      writeConsole("\n");
      // writeLineConsole();
    }
  }

  public static int[] readMatrix() {
    spalten = -1;
    lines = -1;
    while (spalten <= 0) {
      spalten = readInt("Wie viele Unbekannte hat das Gleichungssystem? (>= 1)");
    }
    while (lines <= 0) {
      lines = readInt("Wie viele Gleichungen gibt es in dem Gleichungssystem? >= 1");
    }
    spalten += 1;

    int[] result = new int[spalten * lines];
    for (int i = 0; i < spalten*lines; i++) {
      result[i] = readInt("Bitte geben Sie den " + i + ". Wert der Matrix ein");
    }
    return result;
  }
}