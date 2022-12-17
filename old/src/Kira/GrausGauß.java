public class GrausGauß extends MiniJava{
    private static int lines;

    public static void main(String[] args) {
        lines = read("Geben Sie die Anzahl der Gleichungen ein.");
        int[] matrix = readMatrix();
        printMatrix(matrix);
        int []sol = solveSystem(matrix);
        for(int i=0; i<sol.length; i++){
            System.out.print("x" + i + ": " + sol[i] + ", ");
        }

    }

    public static int[] readMatrix(){
        int[] matrix = new int[lines*(lines+1)];
        for (int i=0; i<matrix.length; i++){
            matrix[i] = read("Zeile " + (i/(lines+1) + 1) + ", Stelle " + (i%(lines+1)));
        }
        return matrix;
    }

    public static void printMatrix(int[] matrix){
        System.out.print("|  ");
        for (int i=0; i<matrix.length; i++){
            if(i!=0 && i%(lines+1)==0){
                System.out.print("| \n|  ");
            }
            System.out.print(matrix[i] + "  ");
        }
        System.out.println("|\n");
    }

    public static int get(int[] matrix, int line, int column){
        return matrix[(line-1)*(lines+1) + (column-1)];
    }

    public static void set(int[] matrix, int line, int column, int value){
        matrix[(line-1)*(lines+1) + (column-1)] = value;
    }

    public static void multLine(int[] matrix, int line, int factor){
        for(int i=0; i<lines+1; i++){
            matrix[(line-1)*(lines+1)+i] =  matrix[(line-1)*(lines+1)+i]*factor;
        }
    }

    public static void multAddLine(int[] matrix, int line, int otherLine, int factor){
        multLine(matrix, otherLine, factor);
        for (int i=0; i<lines+1; i++){
            matrix[(line-1)*(lines+1)+i] = matrix[(line-1)*(lines+1)+i]+ matrix[(otherLine-1)*(lines+1)+i];
        }
    }

    public static void swap(int[] matrix, int lineA, int lineB){
        int tmp;
        for (int i=0; i<lines+1; i++){
            tmp = matrix[(lineA-1)*(lines+1)+i];
            matrix[(lineA-1)*(lines+1)+i] = matrix[(lineB-1)*(lines+1)+i];
            matrix[(lineB-1)*(lines+1)+i] = tmp;
        }
    }

    public static void searchSwap(int[] matrix, int fromLine){
        if(matrix[(fromLine-1)*(lines+1)+fromLine-1]==0){
            for (int i=fromLine; i<lines; i++){
                if(matrix[i*(lines+1)+i-1]!=0){
                    swap(matrix, fromLine, i+1);
                }
            }
        }
    }

    public static int kgv(int a, int b){
        int kgv;
        if(a==0 || b==0){
            return 0;
        }
        if(a>b){
            kgv = a;
            while (kgv % b !=0){
                kgv += a;
            }
        } else {
            kgv = b;
            while (kgv % a != 0){
                kgv += b;
            }
        }
        return kgv;
    }

    public static int[] rowEchelonToResult(int[] matrix){
        int[] lösung = new int [lines];
        for(int i=lines-1; i>=0; i--){
            lösung[i] = matrix[i*(lines+1)+lines];
            for(int j=0; j<lines; j++){
                if(j!=i){
                    lösung[i] = lösung[i]-lösung[j]*matrix[i*(lines+1)+j];
                }
            }
            lösung [i] = lösung[i]/matrix[i*(lines+1) + i];
        }
        return lösung;
    }

    public static int[] solveSystem(int[] matrix){
        for (int i=0; i<lines; i++){
            searchSwap(matrix, i+1);
            for (int j=i+1; j<lines; j++){
                if(get(matrix, j+1, i+1)!=0){
                    int kgv = kgv(get(matrix, i+1, i+1), get(matrix, j+1, i+1));
                    multLine(matrix, i+1, kgv/get(matrix, i+1, i+1));
                    multAddLine(matrix, j+1, i+1, -(kgv/get(matrix, j+1, i+1)));
                }
            }
        }

        return rowEchelonToResult(matrix);
    }
}
