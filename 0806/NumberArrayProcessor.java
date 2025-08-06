import java.util.Arrays;
import java.util.HashMap;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 3, 4, 4, 5};
        int[] arr2 = {1, 3, 5, 7};
        int[] arr3 = {2, 4, 6, 8};

        int[] noDup = removeDuplicates(arr1);
        int[] merged = mergeSortedArrays(arr2, arr3);
        int most = mostFrequent(arr1);
        int[][] split = splitArray(arr1);

        System.out.println("移除重複：" + Arrays.toString(noDup));
        System.out.println("合併排序陣列：" + Arrays.toString(merged));
        System.out.println("最常出現元素：" + most);
        System.out.println("分割陣列1：" + Arrays.toString(split[0]));
        System.out.println("分割陣列2：" + Arrays.toString(split[1]));
    }

    static int[] removeDuplicates(int[] a) {
        return Arrays.stream(a).distinct().toArray();
    }

    static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] r = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) r[k++] = a[i] < b[j] ? a[i++] : b[j++];
        while (i < a.length) r[k++] = a[i++];
        while (j < b.length) r[k++] = b[j++];
        return r;
    }

    static int mostFrequent(int[] a) {
        HashMap<Integer, Integer> m = new HashMap<>();
        int maxCount = 0, res = a[0];
        for (int x : a) {
            m.put(x, m.getOrDefault(x, 0) + 1);
            if (m.get(x) > maxCount) {
                maxCount = m.get(x);
                res = x;
            }
        }
        return res;
    }

    static int[][] splitArray(int[] a) {
        int mid = a.length / 2;
        return new int[][]{Arrays.copyOfRange(a, 0, mid), Arrays.copyOfRange(a, mid, a.length)};
    }
}
