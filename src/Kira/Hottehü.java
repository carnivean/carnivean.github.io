import java.util.Arrays;

public class Hottehü extends MiniJava {
  public static int[] quadraticFormula (double[] coeff){
    //Es gibt kein x^2
    if(coeff[0] == 0){
      if(coeff[1]==0 && coeff[2]!=0)
        return new int[0];
      else if(coeff[1]!=0 && coeff[2]==0)
        return new int[]{0};
      else{
        return new int[]{(int)(-coeff[2]/coeff[1])};
      }
    }
    //Größe initialisieren
    int[] sol;
    double diskriminante = Math.pow(coeff[1], 2) - 4 * coeff[0] * coeff[2];
    if(diskriminante > 0){
      sol = new int [2];
    } else if (diskriminante == 0){
      sol = new int [1];
    } else{
      return new int [0];
    }

    for(int i=0; i<sol.length; i++){
      sol[i] = (int)((-coeff[1] + Math.pow(-1, i) * Math.sqrt(diskriminante)) / (2*coeff[0]));
    }
    return sol;
  }

  public static double[] hornerSchema (double[] coeff, int x0){
    double[] sol = new double[3];

    sol[0] = coeff[0];
    for(int i=1; i<sol.length; i++){
      sol[i] = coeff[i] + x0*sol[i-1];
    }

    return sol;
  }

  public static double calculateY (double[] coeff, int x){
    double sol = 0;

    for (int i=0; i< coeff.length; i++){
      sol += coeff[i]*Math.pow(x, coeff.length-1-i);
    }

    return sol;
  }

  public static int[] findIntervalRecursive (double[] coeff, int a, int b, int factor){
    int [] sol = new int [2];

    //Muss das Intervall vergrößert werden?
    if(calculateY(coeff, a)<0 && calculateY(coeff, b)<0)
      return findIntervalRecursive(coeff, a*factor, b*factor, factor);
    else if(calculateY(coeff, a)>=0 && calculateY(coeff, b)>=0)
      return findIntervalRecursive(coeff, a*factor, b*factor, factor);

    //Wir haben ein passendes Intervall gefunden
    sol[0] = a;
    sol[1] = b;
    return sol;
  }

  public static int findRootRecursive (double[] coeff, int a, int b){
    if(calculateY(coeff, a)==0)
      return a;
    else if(calculateY(coeff, b)==0)
      return b;

    //Die Grenzen sind noch keine Nullstellen
    int m = (a+b)/2;
    if(calculateY(coeff, m) == 0)
      return m;

    //Mitte ist auch keine Nullstelle
    if((calculateY(coeff, a)<0 && calculateY(coeff, m)<0) || (calculateY(coeff, a)>=0 && calculateY(coeff, m)>=0))
      return findRootRecursive(coeff, m, b);
    else
      return findRootRecursive(coeff, a, m);
  }

  public static void main (String[] args){
    double[] test2= new double[]{0.5,5,-33.5,-308};

    System.out.println(java.util.Arrays.toString(quadraticFormula(new double[]{0.5,9,38.5})));
    System.out.println(java.util.Arrays.toString(hornerSchema(test2, 8)));
    System.out.println(calculateY(test2, -20));
    System.out.println(java.util.Arrays.toString(findIntervalRecursive(test2, -2, 2, 10)));
    System.out.println(findRootRecursive(test2, -20, 20));

    System.out.println("\n" + java.util.Arrays.toString(quadraticFormula(new double[]{0,3,3})));

    /*TestInputs:
    1, 0, 0, 0 -> 0
    0, 0, 3, 3 -> -1
    1, 1, -1, -1-> 1, -1
    0, 1, 0, -1 -> 1, -1
    */

    //Input
    double[] input = new double[4];
    for (int i=0; i<input.length; i++){
      input[i] = readDouble("Bitte geben Sie die Variable a" + (input.length-1-i) + " ein.");
    }

    //Gibt es x^3?
    double[] pony;
    int nst = 0;
    if(input[0] == 0){
      pony = new double[]{input[1], input[2], input[3]};
    } else {
      //Intervall berechnen
      int[] intervall = findIntervalRecursive(input, -2, 2, 10);

      //Nullstelle berechnen
      nst = findRootRecursive(input, intervall[0], intervall[1]);

      //Reduziertes Polynom
      pony = hornerSchema(input, nst);
    }

    //Restliche Nullstellen
    int[] moreNst = quadraticFormula(pony);

    //Ausgabe
    if(input[0]==0){
      write("Ihr Pferd steht an den Stellen " + Arrays.toString(moreNst) + " auf dem Boden.");
    } else {
      write("Ihr Pferd steht an den Stellen " + Arrays.toString(moreNst) + " und " + nst + " auf dem Boden.");
    }
  }
}