/**
 * Created by Erik Kynast on 10.04.2017.
 */
public class MergeSortList {
    public int info;
    public MergeSortList next;

    public MergeSortList(int info, MergeSortList next) {
        this.info = info;
        this.next = next;
    }

    public MergeSortList(int info) {
        this(info, null);
    }

    public void insert(int e) {
        next = new MergeSortList(e, next);
    }

    public void delete() {
        if (next != null) {
            next = next.next;
        }
    }

    public static boolean isEmpty(MergeSortList l) {
        return l.next == null;
    }

    private int length() {
        int result = 1;
        for (MergeSortList l = next; l != null; l = l.next) {
            result++;
        }
        return result;
    }

    /*
     * Mergesort
     */
    public static MergeSortList merge(MergeSortList a, MergeSortList b) {
        if (b == null) {
            return a;
        }
        if (a == null) {
            return b;
        }

        if (b.info > a.info) {
            a.next = merge(a.next, b);
            return a;
        } else {
            b.next = merge(a, b.next);
            return b;
        }
    }

    public static MergeSortList sort(MergeSortList a) {
        if (a == null || a.next == null) {
            return a;
        }

        MergeSortList b = a.half();
        a = sort(a);
        b = sort(b);

        return merge(a, b);
    }

    public MergeSortList half() {
        int n = length();
        MergeSortList t = this;
        for (int i = 0; i < n / 2; i++) {
            t = t.next;
        }
        MergeSortList result = t.next;
        t.next = null;
        return result;
    }
}
