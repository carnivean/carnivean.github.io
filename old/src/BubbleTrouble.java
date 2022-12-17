import java.util.Arrays;

public class BubbleTrouble extends MiniJava {
  public static void main (String[] args){
    int[] test = new int[]{8,9,3,2,1};
    int[] test2 = new int[]{9,8,1};
    int[] test3 = new int[]{1,2,1};
    int[] test4 = new int[]{1,1};

    System.out.println(toString(mulWithLatex(test, test2, 16)));
    System.out.println(toString(mul(test3, test3, 3)));
    System.out.println(toString(mul(test4, test4, 2)));

    System.out.println("\n" + toString(mulDigit(test, 3, 0, 16)));
    System.out.println(toString(mulDigit(test, 3, 2, 16)));
    System.out.println(toString(mulDigit(new int[]{2,2,2}, 2, 2, 3)));

    System.out.println(toString(add(test2, test2, 16)));
  }

  public static int[] readNumber(){
    int base = read("Gib eine Basis ein (2-36)");
    while(base<2 || base >36){
      base = read("Ungültig, bitte gib eine Basis zwischen 2 und 36 ein.");
    }
    String number = readString("Gib eine Zahl zu deiner Basis " + base + " ein.");
    while(!validNum(number, base)){
      number = readString("Ungültig, bitte gib eine Zahl zu dieser Basis " + base + " ein.\nDie Zahl darf nur aus Ziffern und Großbuchstaben bestehen.");
    }
    int[] digits = new int[number.length()];
    for (int i=digits.length-1; i>=0; i--){
      if(Character.isDigit(number.charAt(i)))
        digits[number.length()-1-i] = number.charAt(i)-'0';
      else
        digits[number.length()-1-i] = (number.charAt(i)-'A'+10);
    }

    return digits;
  }

  public static boolean validNum(String number, int base){
    for(int i=0; i<number.length(); i++){
      if(number.charAt(i)<'0' || (number.charAt(i)>'9' && number.charAt(i)<'A') || number.charAt(i)>'Z')
        return false;
      if(base < 10){
        if((number.charAt(i)-'0') >= base)
          return false;
      } else {
        if((number.charAt(i)-'A'+10)>=base)
          return false;
      }
    }
    return true;
  }

  public static String toString(int[] number){
    String output = "";
    for (int i=0; i<number.length; i++){
      output += numToChar(number[number.length-1-i]);
    }
    return output;
  }

  public static int[] add(int[] a, int[] b, int base){
    //a soll länger oder gleichlang b sein
    if(a.length<b.length)
      return add(b, a, base);

    int[] res;
    res = new int[a.length+1];
    int übertrag = 0;
    int i=0;
    while(i<a.length && i<b.length){
      int tmp = a[i]+b[i]+übertrag;
      res[i] = tmp%base;
      übertrag = tmp/base;
      i++;
    }
    while(i<a.length){
      int tmp=a[i]+übertrag;
      res[i] = tmp%base;
      übertrag = tmp/base;
      i++;
    }
    res[i] = übertrag;

    return adjustLength(res);
  }

  private static int[] adjustLength(int[] num) {
    //entfernt führende Nullen der Zahl
    int i=0;
    while(num.length-i-1 != 0 && num[num.length-1-i]==0){
      i++;
    }
    if(i==0)
      return num;
    else {
      int[] sol = new int[num.length - i];
      System.arraycopy(num, 0, sol, 0, num.length-i);
      return sol;
    }
  }

  public static int[] mulDigit(int[] a, int digit, int shift, int base){
    //Annahme: das Digit und die Zahl sind zu der gegebenen Basis
    int[] res = new int[a.length+1+shift];
    int übertrag = 0;
    int index = shift;

    for (int i=0; i<a.length; i++){
      int tmp = a[i]*digit + übertrag;
      res[index] = tmp%base;
      übertrag = tmp/base;
      index++;
    }
    res[index] = übertrag;

    return adjustLength(res);
  }

  public static int[] mul(int[] a, int[] b, int base){
    int[] res;
    int[] finalRes = new int[0];

    for(int i=0; i<b.length; i++){
      res = mulDigit(a, b[i], i, base);
      finalRes = add(finalRes, res, base);
    }

    return finalRes;
  }

  //Identisch zu mul, erweitert um Latex-Ausgabe
  public static int[] mulWithLatex(int[] a, int[] b, int base){
    int[] res;
    int[] finalRes = new int[0];
    String latex = "";
    int length = a.length+b.length+2;

    latex += "\\begin{tabular}{";
    int i=0;
    while (i<length) {
      latex += "c";
      i++;
    }
    latex += "}\n";
    for(int j=a.length-1; j>=0; j--){
      latex += "& " + a[j];
    }
    latex += " & $\\ast$ ";
    for(int j=b.length-1; j>=0; j--){
      latex += "& " + b[j];
    }
    latex += "\\\\\n\\hline\n";

    for(int j=0; j<b.length; j++){
      res = mulDigit(a, b[j], j, base);
      latex += "+";
      for (int n=0; n<(length-res.length)-1; n++){
        latex += "&";
      }
      for (int m=res.length-1; m>=0; m--){
        latex += "& " + numToChar(res[m]);
      }
      latex += "\\\\\n";
      finalRes = add(finalRes, res, base);
    }

    latex += "\\hline\n=";
    for (int n=0; n<(length-finalRes.length)-1; n++){
      latex += "&";
    }
    for (int m=finalRes.length-1; m>=0; m--){
      latex += "& " + numToChar(finalRes[m]);
    }
    latex += "\\\\\n\\end{tabular}";

    System.out.println(latex);

    return finalRes;
  }

  private static char numToChar(int digit) {
    if(digit<10)
      return (char)(digit+'0');
    else
      return (char)(digit-10+'A');
  }
}
