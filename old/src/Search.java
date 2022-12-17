/**
 * Created by Erik Kynast on 10.04.2017.
 */
public class Search {
    public static void main(String[] args) {
        int[] test = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        System.out.println("10 Gefunden?: " + find(test, 10));
        System.out.println("3 Gefunden?: " + find(test, 3));
        System.out.println("19 Gefunden?: " + find(test, 19));
        System.out.println("2 Gefunden?: " + find(test, 2));

    }

    public static int findTrinaer(int[] arr, int x) {
        return findTrinaerHelper(arr, x, 0, arr.length);
    }
    
    public static int findTrinaerHelper(int[] arr, int x,
                                    int left, int right) {
        if (left > right) {
            return -1;
        }
        if (left == right) {
            if (arr[left] == x) {
                return left;
            }
            return -1;
        }
        if (left == right - 1) {
            if (arr[left] == x) {
                return left;
            }
            if (arr[left + 1] == x) {
                return left + 1;
            }
            return -1;
        }

        int count = 1 + (right - left);
        count = count / 3;

        int compare = arr[left + count];
        if (x <= compare) {
            return findTrinaerHelper(arr, x, left, left + count);
        }

        compare = arr[right - count];
        if (x >= compare) {
            return findTrinaerHelper(arr, x, right - count, right);
        }

        return findTrinaerHelper(arr, x, left + count + 1,
                right - count - 1);
    }

    public static int find(int[] arr, int x) {
        return findHelper(arr, x, 0, arr.length - 1);
    }

    // gibt -1 zurück, wenn die Zahl x nicht gefunden wurde,
    // ansonsten den index der Zahl x im Array
    private static int findHelper(int[] arr, int x, int left,
                                 int right) {
        int middle = (left + right) / 2;

        if (arr[middle] == x) {
            return middle;
        } else if (left == right) {
            return -1;
        } else if (x > arr[middle]) {
            return findHelper(arr, x, middle + 1, right);
        } else if (left < middle) {
            return findHelper(arr, x, left, middle - 1);
        } else {
            return -1;
        }
    }
}
