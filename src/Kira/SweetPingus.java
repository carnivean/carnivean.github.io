public class SweetPingus extends Maze {
  public static int [][] maze;
  public static int width;
  public static int height;

  public static void main (String[] args){
    int maxD;
    do{
      width = MiniJava.read("Bitte gib die Breite ein: ");
    } while (width<1);
    do{
      height = MiniJava.read("Bitte gib die Höhe ein: ");
    } while (height<1);
    do{
      maxD = MiniJava.read("Gib ein wie mutig du bist. Mutig, nicht faul (mehr als 0): ");
    } while (maxD<1);

    maze = generateStandardPenguinMaze(width, height);
    draw(maze);
    int pingus = walk(1, 0, maxD);
    System.out.println("Du hast " + pingus + " Pinguine auf dem Arm. Frohes Kuscheln!");
  }

  private static int walk(int x, int y, int maxD) {
    int pingus = 0;

    //Dürfen wir hierhin?
    if(!checkField(x, y))
      return pingus;

    //Pingu einsammeln
    if (maze[x][y] == PENGUIN)
      pingus++;

    //Auf dem Feld auf der Stelle treten
    maze[x][y] = PLAYER;
    draw(maze);
    maze[x][y] = OLD_PATH_ACTIVE;

    //Weitergehen, falls wir uns noch weiter trauen
    if(maxD > 0){
      //Einmal alles bitte!
      pingus +=walk(x+1, y, maxD-1);

      pingus +=walk(x, y+1, maxD-1);

      pingus +=walk(x-1, y, maxD-1);

      pingus +=walk(x, y-1, maxD-1);
    }

    maze[x][y] = PLAYER;
    draw(maze);
    maze[x][y] = OLD_PATH_DONE;

    return pingus;
  }

  private static boolean checkField(int x, int y) {
    //Sind wir noch im Spielfeld?
    if(x<0 || y<0 || x>=width || y>=height)
      return false;

    //Ist da wo wir hinwollen eine Wand?
    if(wall(x, y))
      return false;

    //Waren wir an der Stelle wo wir hinwollen bereits?
    if(maze[x][y] == OLD_PATH_ACTIVE || maze[x][y]==OLD_PATH_DONE)
      return false;

    //Ist um das Feld herum eine Wand?
    if(!wall(x+1,y-1) && !wall(x+1,y) && !wall(x+1,y+1) && !wall(x,y-1) && !wall(x,y+1) && !wall(x-1,y-1) && !wall(x-1,y) && !wall(x-1,y+1))
      return false;

    return true;
  }

  private static boolean wall(int x, int y) {
    if(x<0 || y<0 || x>=width || y>=height)
      return false;

    return maze[x][y]==WALL;
  }
}