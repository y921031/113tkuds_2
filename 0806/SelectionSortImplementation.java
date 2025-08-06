import java.util.Arrays;

public class SelectionSortImplementation {
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        selectionSort(arr);
        System.out.println("排序結果：" + Arrays.toString(arr));
    }

    static void selectionSort(int[] a) {
        int n = a.length, compare = 0, swap = 0;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                compare++;
                if (a[j] < a[min]) min = j;
            }
            if (min != i) {
                int t = a[i];
                a[i] = a[min];
                a[min] = t;
                swap++;
            }
            System.out.println("第" + (i + 1) + "輪：" + Arrays.toString(a));
        }
        System.out.println("比較次數：" + compare + " 交換次數：" + swap);
    }
}
