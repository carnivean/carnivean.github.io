import java.util.Scanner;
import java.util.logging.ErrorManager;

public class Interpreter extends MiniJava {
  public static int[] stack = new int[128];
  public static int sp = -1; //Stacktop
  public static int fp = 0; //Framepointer
  public static int pc = 0; //Programcounter

  public static final int NOP = 0;
  public static final int ADD = 1;
  public static final int SUB = 2;
  public static final int MUL = 3;
  public static final int MOD = 4;
  public static final int LDI = 5;
  public static final int LDS = 6;
  public static final int STS = 7;
  public static final int JUMP = 8;
  public static final int JE = 9;
  public static final int JNE = 10;
  public static final int JLT = 11;
  public static final int IN = 12;
  public static final int OUT = 13;
  public static final int CALL = 14;
  public static final int RETURN = 15;
  public static final int HALT = 16;
  public static final int ALLOC = 17;

  static void error(String message) {
    throw new RuntimeException(message);
  }

  public static String readProgramConsole() {
    @SuppressWarnings("resource")
    Scanner sin = new Scanner(System.in);
    StringBuilder builder = new StringBuilder();
    while (true) {
      String nextLine = sin.nextLine();
      if (nextLine.equals("")) {
        nextLine = sin.nextLine();
        if (nextLine.equals(""))
          break;
      }
      if (nextLine.startsWith("//"))
        continue;
      builder.append(nextLine);
      builder.append('\n');
    }
    return builder.toString();
  }

  public static void main(String[] args) {
    String program = readProgramConsole();
    int[] instructions = parse(program);
    int retVal = execute(instructions);
    write(retVal);
  }

  private static String firstPass(String textProgram) {
    String[] instructions = textProgram.split("\n");
    for (int i = 0; i < instructions.length; i++) {
      if (Character.isLowerCase(instructions[i].charAt(0))) {
        String[] label = instructions[i].split(":");
        if (label.length != 1)
          error("Hinter dem Label steht noch etwas...");
        textProgram = textProgram.replaceAll(label[0] + ":", "" + i);
        textProgram = textProgram.replaceAll(label[0], "" + i);
      }
    }
    return textProgram;
  }

  public static int[] parse(String textProgram) {
    pc = 0;
    textProgram = firstPass(textProgram);
    String[] instructions = textProgram.split("\n");
    int[] output = new int[instructions.length];
    for (String s : instructions) {
      String[] tmp = s.split(" ");
      if (tmp[0].equals("NOP"))
        output[pc] = NOP;
      else if (tmp[0].equals("ADD"))
        output[pc] = ADD;
      else if (tmp[0].equals("SUB"))
        output[pc] = SUB;
      else if (tmp[0].equals("MUL"))
        output[pc] = MUL;
      else if (tmp[0].equals("MOD"))
        output[pc] = MOD;
      else if (tmp[0].equals("LDI"))
        output[pc] = LDI;
      else if (tmp[0].equals("LDS"))
        output[pc] = LDS;
      else if (tmp[0].equals("STS"))
        output[pc] = STS;
      else if (tmp[0].equals("JUMP"))
        output[pc] = JUMP;
      else if (tmp[0].equals("JE"))
        output[pc] = JE;
      else if (tmp[0].equals("JNE"))
        output[pc] = JNE;
      else if (tmp[0].equals("JLT"))
        output[pc] = JLT;
      else if (tmp[0].equals("IN"))
        output[pc] = IN;
      else if (tmp[0].equals("OUT"))
        output[pc] = OUT;
      else if (tmp[0].equals("CALL"))
        output[pc] = CALL;
      else if (tmp[0].equals("RETURN"))
        output[pc] = RETURN;
      else if (tmp[0].equals("HALT"))
        output[pc] = HALT;
      else if (tmp[0].equals("ALLOC"))
        output[pc] = ALLOC;
      else if (Integer.parseInt(tmp[0]) == pc)
        output[pc] = NOP;
      else
        error("Unknown command and conquer...");

      if (((output[pc] == LDI || output[pc] == LDS || output[pc] == STS || output[pc] == JUMP || output[pc] == JE ||
          output[pc] == JNE || output[pc] == JLT || output[pc] == CALL || output[pc] == RETURN || output[pc] == ALLOC) && tmp.length != 2) ||
          (output[pc] == NOP || output[pc] == ADD || output[pc] == SUB || output[pc] == MUL || output[pc] == MOD || output[pc] == OUT ||
              output[pc] == HALT) && tmp.length != 1) {
        error("Falsche Anzahl an Argumenten für den Befehl");
      }
      output[pc] = output[pc] << 16;
      if (tmp.length==2){
        if(!(Character.isDigit(tmp[1].charAt(0)) || tmp[1].charAt(0) == '-')){
          error("No immediate...");
        } else{
          int i = Integer.parseInt(tmp[1]);
          i = i & 0b00000000000000001111111111111111;
          output[pc] += i;
        }
      }
      pc++;
    }
    pc = 0;
    return output;
  }

  public static int pop() {
    if (sp < 0) {
      error("Kein Element für pop mehr auf dem Stack verfügbar.");
    }
    int tmp = stack[sp];
    stack[sp--] = 0;
    return tmp;
  }

  public static void push(int value) {
    if (sp == 127) {
      error("Der Stack unserer Maschine ist zu klein!");
    }
    stack[++sp] = value;
  }

  public static int execute(int[] program) {
    pc = 0;
    fp = 0;
    sp = -1;
    stack = new int[128];

    while (program[pc] != (HALT << 16)) {

      int opcode = program[pc] / 0b10000000000000000;
      int immediate = program[pc] % 0b10000000000000000;
      if ((immediate & (1 << 15)) != 0) {
        immediate = immediate | 0b11111111111111110000000000000000;
      }
      System.out.println("opcode: " + opcode + " imme: " + immediate + " pc: " + pc);

      switch (opcode) {
        case NOP:
          // NOP
          break;
        case ADD:
          add();
          break;
        case SUB:
          sub();
          break;
        case MUL:
          mul();
          break;
        case MOD:
          mod();
          break;
        case LDI:
          ldi(immediate);
          break;
        case LDS:
          lds(immediate);
          break;
        case STS:
          sts(immediate);
          break;
        case JUMP:
          jump(immediate);
          break;
        case JE:
          je(immediate);
          break;
        case JNE:
          jne(immediate);
          break;
        case JLT:
          jlt(immediate);
          break;
        case IN:
          in();
          break;
        case OUT:
          out();
          break;
        case CALL:
          call(immediate, pc + 1);
          break;
        case RETURN:
          metReturn(immediate);
          break;
        case HALT:
          break;
        case ALLOC:
          alloc(immediate);
          break;
      }
      pc++;
    }

    return pop();
  }

  public static void add() {
    int tmp1 = pop();
    int tmp2 = pop();
    push(tmp1 + tmp2);
  }

  public static void sub() {
    int tmp1 = pop();
    int tmp2 = pop();
    push(tmp1 - tmp2);
  }

  public static void mul() {
    int tmp1 = pop();
    int tmp2 = pop();
    push(tmp1 * tmp2);
  }

  public static void mod() {
    int tmp1 = pop();
    int tmp2 = pop();
    push(tmp1 % tmp2);
  }

  public static void ldi(int v) {
    push(v);
  }

  public static void lds(int i) {
    if (fp + i < 0 || fp + i > 127)
      error("Wir zeigen auf ein Element außerhalb des Stacks");
    int tmp = stack[fp + i];
    push(tmp);
  }

  public static void sts(int i) {
    int tmp = pop();
    if (fp + i < 0 || fp + i > 127)
      error("Wir zeigen auf ein Element außerhalb des Stacks");
    stack[fp + i] = tmp;
  }

  public static void jump(int i) {
    pc = i - 1;
  }

  public static void je(int i) {
    int tmp1 = pop();
    int tmp2 = pop();
    if (tmp1 == tmp2)
      jump(i);
  }

  public static void jne(int i) {
    int tmp1 = pop();
    int tmp2 = pop();
    if (tmp1 != tmp2)
      jump(i);
  }

  public static void jlt(int i) {
    int tmp1 = pop();
    int tmp2 = pop();
    if (tmp1 < tmp2)
      jump(i);
  }

  public static void call(int n, int back) {
    jump(pop());
    int[] tmp = new int[n];
    for (int i = 0; i < tmp.length; i++) {
      tmp[i] = pop();
    }
    push(fp);
    push(back);
    for (int i = tmp.length - 1; i >= 0; i--) {
      push(tmp[i]);
    }
    fp = sp;
  }

  public static void metReturn(int n) {
    int tmp = pop();
    for (int i = 0; i < n; i++) {
      pop();
    }
    jump(pop());
    fp = pop();
    push(tmp);
  }

  public static void in() {
    push(read());
  }

  public static void out() {
    write(pop());
  }

  public static void alloc(int n) {
    if (sp + n > 127)
      error("So viele Elemente können nicht mehr reserviert werden auf dem Stack.");
    sp += n;
  }
}