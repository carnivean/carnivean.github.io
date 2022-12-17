public class madn extends Aerger{
  static int[] arrYellow = {-1, -1, -1, -1};
  static int[] arrBlue = {-1, -1, -1, -1};
  static int[] arrRed = {-1, -1, -1, -1};
  static int[] arrGreen = {-1, -1, -1, -1};

  public static void main(String[] args) {
    Aerger.paintField(arrYellow, arrBlue, arrRed, arrGreen);
    int spielerAmZug = 1;

    while (gewinnerBlue() == -1) {
      // Gelb am Zug
      if (spielerAmZug == 1) {
        int wurf = dice();

        int neuePosition;
        int stein;
        do {
          stein = -1;
          while (stein < 1 || stein > 4) {
            stein = read("Bitte geben Sie den Stein ein, den sie um " + wurf + " vorrücken möchten.");
          }
          neuePosition = arrYellow[stein - 1] + wurf;
        } while (!positionFreiYellow(neuePosition));
        arrYellow[stein - 1] += wurf;
        // arrYellow[stein - 1] += wurf;
        Aerger.paintField(arrYellow, arrBlue, arrRed, arrGreen);
        spielerAmZug += 1;
      } else if (spielerAmZug == 2) {
        // blau ist am Zug
        int wurf = dice();

        int neuePosition;
        int stein;
        do {
          stein = -1;
          while (stein < 1 || stein > 4) {
            stein = read("Bitte geben Sie den Stein ein, den sie um " + wurf + " vorrücken möchten.");
          }
          if (arrBlue[stein - 1] < 0) {
            neuePosition = 9 + wurf;
          } else if (arrBlue[stein - 1] < 40){
            neuePosition = (arrBlue[stein - 1] + wurf) % 40;
            if (neuePosition >= 9 && arrBlue[stein - 1] < 9) {
              neuePosition = 40 + stein;
            }
          } else {
            neuePosition = arrBlue[stein - 1];
          }
        } while (!positionFreiBlue(neuePosition));
        arrBlue[stein - 1] = neuePosition;
        // arrYellow[stein - 1] += wurf;
        Aerger.paintField(arrYellow, arrBlue, arrRed, arrGreen);
        spielerAmZug = 1;
      }


    }

  }

  private static int gewinnerBlue() {
    int gewinner = 1;
    for (int i = 0; i < 4; i++) {
      if (arrBlue[i] < 40) {
        gewinner = -1;
      };
    }
    return 0;
  }

  private static boolean positionFreiBlue(int neuePosition) {
    for (int i = 0; i < 4; i++) {
      if (arrBlue[i] == neuePosition) {
        return false;
      }
      if (arrYellow[i] == neuePosition  && neuePosition < 40) {
        arrYellow[i] = -1;
      }
    }
    return true;
  }

  private static boolean positionFreiYellow(int neuePosition) {
    for (int i = 0; i < 4; i++) {
      if (arrYellow[i] == neuePosition) {
        return false;
      }
      if (arrBlue[i] == neuePosition && neuePosition < 40) {
        arrBlue[i] = -1;
      }
    }
    return true;
  }

  private static boolean positionFrei(int neuePosition) {
    for (int i = 0; i < 4; i++) {
      if (arrYellow[i] == neuePosition) {
        return false;
      }
    }
    return true;
  }
}
