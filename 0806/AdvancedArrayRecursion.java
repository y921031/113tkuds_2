import java.util.Arrays;

public class AdvancedArrayRecursion {
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 7};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("快速排序：" + Arrays.toString(arr));

        int[] a = {1, 3, 5};
        int[] b = {2, 4, 6};
        int[] merged = mergeSorted(a, b, 0, 0);
        System.out.println("遞迴合併：" + Arrays.toString(merged));

        int[] arr2 = {7, 10, 4, 3, 20, 15};
        System.out.println("第3小元素：" + kthSmallest(arr2, 0, arr2.length - 1, 3));

        int[] arr3 = {2, 4, 6, 10};
        System.out.println("是否存在和為16的子序列？" + subsetSum(arr3, arr3.length, 16));
    }

    static void quickSort(int[] a, int l, int r) {
        if (l >= r) return;
        int p = partition(a, l, r);
        quickSort(a, l, p - 1);
        quickSort(a, p + 1, r);
    }

    static int partition(int[] a, int l, int r) {
        int pivot = a[r], i = l;
        for (int j = l; j < r; j++) {
            if (a[j] < pivot) {
                int t = a[i]; a[i] = a[j]; a[j] = t; i++;
            }
        }
        int t = a[i]; a[i] = a[r]; a[r] = t;
        return i;
    }

    static int[] mergeSorted(int[] a, int[] b, int i, int j) {
        if (i == a.length) return Arrays.copyOfRange(b, j, b.length);
        if (j == b.length) return Arrays.copyOfRange(a, i, a.length);
        if (a[i] < b[j]) {
            int[] rest = mergeSorted(a, b, i + 1, j);
            int[] res = new int[1 + rest.length];
            res[0] = a[i];
            System.arraycopy(rest, 0, res, 1, rest.length);
            return res;
        } else {
            int[] rest = mergeSorted(a, b, i, j + 1);
            int[] res = new int[1 + rest.length];
            res[0] = b[j];
            System.arraycopy(rest, 0, res, 1, rest.length);
            return res;
        }
    }

    static int kthSmallest(int[] a, int l, int r, int k) {
        quickSort(a, l, r);
        return a[k - 1];
    }

    static boolean subsetSum(int[] a, int n, int sum) {
        if (sum == 0) return true;
        if (n == 0) return false;
        if (a[n - 1] > sum) return subsetSum(a, n - 1, sum);
        return subsetSum(a, n - 1, sum) || subsetSum(a, n - 1, sum - a[n - 1]);
    }
}
