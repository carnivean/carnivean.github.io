public class peng extends Maze {
  static int[][] maze;

  public static void main(String[] args) {
    int breite = MiniJava.readInt("Breite?");
    int hoehe = MiniJava.readInt("Hoehe?");
    int maxDistance = MiniJava.readInt("Maximale Distanz?");
    if (breite < 3 || hoehe < 3 || maxDistance < 1) {
      MiniJava.write("Fehlerhafte Eingabe!");
      return;
    }
    maze = generateStandardPenguinMaze(breite, hoehe);
    int rescued = walk(1, 0, maxDistance);
    MiniJava.write("Wir haben " + rescued + " Pinguine gerettet!");
  }

  public static int walk(int x, int y, int maxDistance) {
    if (maxDistance < 0)
      return 0;

    if (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length) {
      return 0;
    }

    if (maze[x][y] == WALL || maze[x][y] == OLD_PATH_DONE || maze[x][y] == OLD_PATH_ACTIVE) {
      return 0;
    }

    if (!isWall(x - 1,y) && !isWall(x,y - 1) && !isWall(x + 1,y) && !isWall(x,y + 1) &&
        !isWall(x + 1,y + 1) && !isWall(x + 1,y - 1) && !isWall(x - 1,y - 1) && !isWall(x - 1,y + 1)) {
      return 0;
    }

    int penguins = 0;
    if (maze[x][y] == PENGUIN) {
      penguins = 1;
    }
    maze[x][y] = PLAYER;
    draw(maze);

    // rekursive Aufrufe - 4 mal für alle Richtungen einen
    maze[x][y] = OLD_PATH_ACTIVE;
    // Links
    penguins += walk(x - 1, y, maxDistance - 1);
    maze[x][y] = PLAYER;
    draw(maze);

    // Oben
    maze[x][y] = OLD_PATH_ACTIVE;
    penguins += walk(x, y - 1, maxDistance - 1);
    maze[x][y] = PLAYER;
    draw(maze);

    // Unten
    maze[x][y] = OLD_PATH_ACTIVE;
    penguins += walk(x, y + 1, maxDistance - 1);
    maze[x][y] = PLAYER;
    draw(maze);

    // Rechts
    maze[x][y] = OLD_PATH_ACTIVE;
    penguins += walk(x + 1, y, maxDistance - 1);
    maze[x][y] = PLAYER;
    draw(maze);

    maze[x][y] = OLD_PATH_DONE;

    return penguins;
  }

  private static boolean isWall(int x, int y) {
    if (x < 0 || x > maze.length || y < 0 || y > maze[0].length) {
      return false;
    }
    return maze[x][y] == WALL;
  }
}
