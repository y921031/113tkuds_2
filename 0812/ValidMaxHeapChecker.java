import java.util.*;

public class ValidMaxHeapChecker {
    public static boolean isValidMaxHeap(int[] arr){
        int n = arr.length;
        for(int i = 0; i <= (n-2)/2; i++){
            int l = 2*i+1, r = 2*i+2;
            if(l < n && arr[i] < arr[l]) return false;
            if(r < n && arr[i] < arr[r]) return false;
        }
        return true;
    }
    public static Integer firstViolationIndex(int[] arr){
        int n = arr.length;
        for(int i = 0; i <= (n-2)/2; i++){
            int l = 2*i+1, r = 2*i+2;
            if(l < n && arr[i] < arr[l]) return l;
            if(r < n && arr[i] < arr[r]) return r;
        }
        return null;
    }
    public static void main(String[] args){
        int[][] tests = {
            {100,90,80,70,60,75,65},
            {100,90,80,95,60,75,65},
            {50},
            {}
        };
        for(int[] t: tests){
            System.out.println(Arrays.toString(t) + " -> " + isValidMaxHeap(t) + (isValidMaxHeap(t) ? "" : " (violation at index " + firstViolationIndex(t) + ")"));
        }
    }
}
