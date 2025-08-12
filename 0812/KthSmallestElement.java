import java.util.*;

public class KthSmallestElement {
    public static int kthSmallestMaxHeap(int[] arr, int k){
        if(k<1 || k>arr.length) throw new IllegalArgumentException();
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        for(int v: arr){
            max.offer(v);
            if(max.size() > k) max.poll();
        }
        return max.peek();
    }
    public static int kthSmallestMinHeap(int[] arr, int k){
        if(k<1 || k>arr.length) throw new IllegalArgumentException();
        PriorityQueue<Integer> min = new PriorityQueue<>();
        for(int v: arr) min.offer(v);
        for(int i=1;i<k;i++) min.poll();
        return min.poll();
    }
    public static void main(String[] args){
        int[] a1 = {7,10,4,3,20,15};
        System.out.println(kthSmallestMaxHeap(a1,3)); //7
        System.out.println(kthSmallestMinHeap(a1,3)); //7
        System.out.println(kthSmallestMaxHeap(new int[]{1},1)); //1
        int[] a3 = {3,1,4,1,5,9,2,6};
        System.out.println(kthSmallestMaxHeap(a3,4)); //3
    }
}
