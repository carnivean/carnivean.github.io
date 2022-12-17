public class HilfPingu extends PenguinPen {

  private static int zooX = 1;
  private static int zooY = 0;
  private static final int[][] penguinPen = generateStandardPenguinPen(10, 10);
  //Array das die Richtungen von allen Wechsulinen an ihrer Position abspeichert
  private static int[][] wechsulinos = getWechsus();

  public static void move(int direction) {
    System.out.println("Richtung: " + direction);
    if (direction == NO_MOVE)
      return;
    if (!canMove(direction)) {
      System.out.println("Auf dieses Feld kann nicht gezogen werden.");
      return;
    }

    //Spielschritt
    //1. Füttern
    feed();

    //2. Tierpfleger läuft (in Piazza erlaubt Schritt 2 und 3 zu vertauschen, damit kein Pinguin zertrampelt wird)
    penguinPen[zooX][zooY] = FREE;
    System.out.print("(" + zooX + "," + zooY + ") ==> ");
    if (direction == MOVE_LEFT) {
      penguinPen[zooX - 1][zooY] = ZOOKEEPER;
      zooX--;
    } else if (direction == MOVE_DOWN) {
      penguinPen[zooX][zooY + 1] = ZOOKEEPER;
      zooY++;
    } else if (direction == MOVE_RIGHT) {
      penguinPen[zooX + 1][zooY] = ZOOKEEPER;
      zooX++;
    } else if (direction == MOVE_UP) {
      penguinPen[zooX][zooY - 1] = ZOOKEEPER;
      zooY--;
    }
    System.out.println("(" + zooX + "," + zooY + ") -- Tierpfleger läuft");

    //3. Pinguine flüchten
    int[][] tmp = new int[penguinPen.length][penguinPen[0].length];
    for(int i=0; i<penguinPen.length; i++){
      System.arraycopy(penguinPen[i], 0, tmp[i], 0, penguinPen[i].length);
    }
    for (int i = 0; i < tmp.length; i++) {
      for (int j = 0; j < tmp[0].length; j++) {
        if (tmp[i][j] == PENGUIN_OOO)
          fauluin(i, j);
        else if (tmp[i][j] == PENGUIN_OOI)
          zufullin(i, j);
        else if (tmp[i][j] == PENGUIN_OIO)
          wechsulin(i, j);
        else if (tmp[i][j] == PENGUIN_OII)
          springuin(i, j);
        else if (tmp[i][j] == PENGUIN_IOO)
          schlauin(i, j);
      }
    }

    draw(penguinPen);
  }

  private static void wechsulin(int i, int j) {
    //Annahme: pro Zug laufen sie falls möglich immer einen Schritt, drehen wird nicht als Schritt betrachtet
    if (i == 1 && j == 0 && wechsulinos[i][j] == 2) {
      wechsulinos[i][j] = 0;
      wechsulin(i, j);
    } else if (wechsulinos[i][j] == 4) { //Die Pinguine laufen noch anfangs nach rechts bis sie eine Wand treffen
      if (checkField(i+1, j) && penguinPen[i+1][j] == WALL) {
        wechsulinos[i][j] = 2;
        wechsulin(i, j);
      } else if (penguinPen[i + 1][j] != FREE) {
        System.out.println("(" + i + "," + j + ") ==> (" + i + "," + j + ") -- Weg nicht frei.");
        return;
      } else {
        penguinPen[i][j] = FREE;
        penguinPen[i + 1][j] = PENGUIN_OIO;
        wechsulinos[i][j] = FREE;
        wechsulinos[i + 1][j] = 4;
        System.out.println("(" + i + "," + j + ") ==> (" + (i + 1) + "," + j + ") -- Pinguin flüchtet");
      }
    } else { //RHR (0 unten, 1 rechts, 2 oben, 3 links) //Präsenz-Lösung aus MazeSolution angepasst
      int direction = wechsulinos[i][j];
      if (direction == 0 && wall(i - 1, j) || direction == 1 && wall(i, j + 1)
          || direction == 2 && wall(i + 1, j) || direction == 3 && wall(i, j - 1)) {
        // Is there an obstacle directly in front of us?
        if (direction == 0 && wall(i, j + 1) || direction == 1 && wall(i + 1, j)
            || direction == 2 && wall(i, j - 1) || direction == 3 && wall(i - 1, j)) {
          wechsulinos[i][j] = (direction + 1) % 4;
          // We can turn counterclockwise, having the obstacle on our
          // right-hand side.
          wechsulin(i, j);
        } else {
          // We walk straigt on
          switch (direction) {
            case 0:
              if (penguinPen[i][j + 1] == FREE) {
                penguinPen[i][j] = FREE;
                wechsulinos[i][j] = FREE;
                penguinPen[i][j + 1] = PENGUIN_OIO;
                wechsulinos[i][j + 1] = direction;
                System.out.println("(" + i + "," + j + ") ==> (" + (i) + "," + (j + 1) + ") -- Pinguin flüchtet");
              } else
                System.out.println("(" + i + "," + j + ") ==> (" + i + "," + j + ") -- Weg nicht frei.");
              break;
            case 1:
              if (penguinPen[i + 1][j] == FREE) {
                penguinPen[i][j] = FREE;
                wechsulinos[i][j] = FREE;
                penguinPen[i + 1][j] = PENGUIN_OIO;
                wechsulinos[i + 1][j] = direction;
                System.out.println("(" + i + "," + j + ") ==> (" + (i + 1) + "," + j + ") -- Pinguin flüchtet");
              } else
                System.out.println("(" + i + "," + j + ") ==> (" + i + "," + j + ") -- Weg nicht frei.");
              break;
            case 2:
              if (penguinPen[i][j - 1] == FREE) {
                penguinPen[i][j] = FREE;
                wechsulinos[i][j] = FREE;
                penguinPen[i][j - 1] = PENGUIN_OIO;
                wechsulinos[i][j - 1] = direction;
                System.out.println("(" + i + "," + j + ") ==> (" + (i) + "," + (j - 1) + ") -- Pinguin flüchtet");
              } else
                System.out.println("(" + i + "," + j + ") ==> (" + i + "," + j + ") -- Weg nicht frei.");
              break;
            case 3:
            default:
              if (penguinPen[i - 1][j] == FREE) {
                penguinPen[i][j] = FREE;
                wechsulinos[i][j] = FREE;
                penguinPen[i - 1][j] = PENGUIN_OIO;
                wechsulinos[i - 1][j] = direction;
                System.out.println("(" + i + "," + j + ") ==> (" + (i - 1) + "," + j + ") -- Pinguin flüchtet");
              } else
                System.out.println("(" + i + "," + j + ") ==> (" + i + "," + j + ") -- Weg nicht frei.");
          }
        }
      } else {
        // There is no wall on the right side, so we walk to the right side
        // and turn until we have a wall on the right side
        switch (direction) {
          case 0:
            if (penguinPen[i - 1][j] == FREE) {
              penguinPen[i][j] = FREE;
              wechsulinos[i][j] = FREE;
              penguinPen[i - 1][j] = PENGUIN_OIO;
              wechsulinos[i - 1][j] = (direction + 3) % 4;
              System.out.println("(" + i + "," + j + ") ==> (" + (i - 1) + "," + j + ") -- Pinguin flüchtet");
            } else
              System.out.println("(" + i + "," + j + ") ==> (" + i + "," + j + ") -- Weg nicht frei.");
            break;
          case 1:
            if (penguinPen[i][j+1] == FREE) {
              penguinPen[i][j] = FREE;
              wechsulinos[i][j] = FREE;
              penguinPen[i][j + 1] = PENGUIN_OIO;
              wechsulinos[i][j + 1] = (direction + 3) % 4;
              System.out.println("(" + i + "," + j + ") ==> (" + (i) + "," + (j+1) + ") -- Pinguin flüchtet");
            } else
              System.out.println("(" + i + "," + j + ") ==> (" + i + "," + j + ") -- Weg nicht frei.");
            break;
          case 2:
            if (penguinPen[i + 1][j] == FREE) {
              penguinPen[i][j] = FREE;
              wechsulinos[i][j] = FREE;
              penguinPen[i + 1][j] = PENGUIN_OIO;
              wechsulinos[i + 1][j] = (direction + 3) % 4;
              System.out.println("(" + i + "," + j + ") ==> (" + (i + 1) + "," + j + ") -- Pinguin flüchtet");
            } else
              System.out.println("(" + i + "," + j + ") ==> (" + i + "," + j + ") -- Weg nicht frei.");
            break;
          case 3:
          default:
            if (penguinPen[i][j-1] == FREE) {
              penguinPen[i][j] = FREE;
              wechsulinos[i][j] = FREE;
              penguinPen[i][j - 1] = PENGUIN_OIO;
              wechsulinos[i][j - 1] = (direction + 3) % 4;
              System.out.println("(" + i + "," + j + ") ==> (" + (i) + "," + (j -1) + ") -- Pinguin flüchtet");
            } else
              System.out.println("(" + i + "," + j + ") ==> (" + i + "," + j + ") -- Weg nicht frei.");
        }
      }
    }
  }

  public static boolean wall(int x, int y) {
    return (penguinPen[x][y] == WALL);
  }

  private static void schlauin(int i, int j) {
    int x, y;
    if (zooX < i)
      x = 1;
    else
      x = -1;
    if (zooY < j)
      y = 1;
    else
      y = -1;

    if (checkField(i + x, j) && penguinPen[i + x][j] == FREE) {
      penguinPen[i][j] = FREE;
      penguinPen[i + x][j] = PENGUIN_IOO;
      System.out.println("(" + i + "," + j + ") ==> (" + (i + x) + "," + j + ") -- Pinguin flüchtet");
    } else if (checkField(i, j + y) && penguinPen[i][j + y] == FREE) {
      penguinPen[i][j] = FREE;
      penguinPen[i][j + y] = PENGUIN_IOO;
      System.out.println("(" + i + "," + j + ") ==> (" + i + "," + (j + y) + ") -- Pinguin flüchtet");
    } else if (zooX == i && checkField(i - x, j) && penguinPen[i - x][j] == FREE) {
      penguinPen[i][j] = FREE;
      penguinPen[i - x][j] = PENGUIN_IOO;
      System.out.println("(" + i + "," + j + ") ==> (" + (i - x) + "," + j + ") -- Pinguin flüchtet");
    } else if (zooY == j && checkField(i, j - y) && penguinPen[i][j - y] == FREE) {
      penguinPen[i][j] = FREE;
      penguinPen[i][j - y] = PENGUIN_IOO;
      System.out.println("(" + i + "," + j + ") ==> (" + i + "," + (j - y) + ") -- Pinguin flüchtet");
    } else {
      System.out.println("(" + i + "," + j + ") -- Schlauin sitzt fest");
    }
  }

  private static void springuin(int i, int j) {
    int x;
    int y;
    do {
      x = (int) (penguinPen.length * Math.random());
      y = (int) (penguinPen[0].length * Math.random());
    } while (penguinPen[x][y] != FREE);
    penguinPen[i][j] = FREE;
    penguinPen[x][y] = PENGUIN_OII;
    System.out.println("(" + i + "," + j + ") ==> (" + x + "," + y + ") -- Pinguin flüchtet");
  }

  private static void zufullin(int i, int j) {
    System.out.print("(" + i + "," + j + ") ==> ");
    int dir = (int) (4 * Math.random());
    for (int n = 0; n < 4; n++) {
      if (walkZufullin(i, j, dir)) {
        return;
      }
      dir = (dir + 1) % 4;
    }
    System.out.println("(" + i + "," + j + ") -- Zufullin sitzt fest");
  }

  private static boolean walkZufullin(int i, int j, int dir) {
    if (dir == 0 && checkField(i + 1, j) && penguinPen[i + 1][j] == FREE) {
      System.out.println("(" + (i + 1) + "," + j + ") -- Pinguin flüchtet");
      penguinPen[i][j] = FREE;
      penguinPen[i + 1][j] = PENGUIN_OOI;
      return true;
    } else if (dir == 1 && checkField(i, j + 1) && penguinPen[i][j + 1] == FREE) {
      System.out.println("(" + i + "," + (j + 1) + ") -- Pinguin flüchtet");
      penguinPen[i][j] = FREE;
      penguinPen[i][j + 1] = PENGUIN_OOI;
      return true;
    } else if (dir == 2 && checkField(i - 1, j) && penguinPen[i - 1][j] == FREE) {
      System.out.println("(" + (i - 1) + "," + j + ") -- Pinguin flüchtet");
      penguinPen[i][j] = FREE;
      penguinPen[i - 1][j] = PENGUIN_OOI;
      return true;
    } else if (dir == 3 && checkField(i, j - 1) && penguinPen[i][j - 1] == FREE) {
      System.out.println("(" + i + "," + (j - 1) + ") -- Pinguin flüchtet");
      penguinPen[i][j] = FREE;
      penguinPen[i][j - 1] = PENGUIN_OOI;
      return true;
    }
    return false;
  }

  private static void fauluin(int i, int j) {
    System.out.println("(" + i + "," + j + ") ==> (" + i + "," + j + ") -- Fauluin steht rum");
  }

  private static void feed() {
    for (int i = -1; i < 2; i++) {
      for (int j = -1; j < 2; j++) {
        if (checkField(zooX + i, zooY + j) && penguinPen[zooX + i][zooY + j] < 6 && penguinPen[zooX + i][zooY + j] > 0) {
          System.out.println("(" + (zooX + i) + "," + (zooY + j) + ") wird frei -- Pinguin ist satt.");
          penguinPen[zooX + i][zooY + j] = FREE;
        }
      }
    }
  }

  private static boolean checkField(int x, int y) {
    //Feld ist innerhalb des Geheges
    if (x >= 0 && x < penguinPen.length && y >= 0 && y < penguinPen[0].length)
      return true;
    return false;
  }

  private static boolean canMove(int direction) {
    if (direction == MOVE_LEFT && zooX > 0)
      return penguinPen[zooX - 1][zooY] == FREE;
    else if (direction == MOVE_DOWN && zooY < penguinPen[0].length - 1)
      return penguinPen[zooX][zooY + 1] == FREE;
    else if (direction == MOVE_RIGHT && zooX < penguinPen.length - 1)
      return penguinPen[zooX + 1][zooY] == FREE;
    else if (direction == MOVE_UP && zooY > 0)
      return penguinPen[zooX][zooY - 1] == FREE;
    return false;
  }


  /*********************************************/
  /* Ab hier soll nichts mehr geändert werden! */

  /*********************************************/

  public static void main(String[] args) {
    draw(penguinPen);
    handleUserInput();
  }

  private static void handleUserInput() {
    while (true) {
      try {
        Thread.sleep(10);
      } catch (InterruptedException ie) {
        /* Intentionally left blank */
      }
      int step = nextStep();
      if (step != NO_MOVE) {
        // System.out.print(step+",");
        move(step);
      }
    }
  }

  public static int[][] getWechsus() {
    int[][] tmp = new int [penguinPen.length][penguinPen[0].length];
    for (int i = 0; i < tmp.length; i++) {
      for (int j = 0; j < tmp[0].length; j++) {
        if (penguinPen[i][j] == PENGUIN_OIO)
          tmp[i][j] = 4;
      }
    }
    return tmp;
  }
}
