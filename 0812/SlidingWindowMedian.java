import java.util.*;

public class SlidingWindowMedian {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    private Map<Integer,Integer> delayed;
    private int maxSize, minSize;
    public SlidingWindowMedian(){
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        delayed = new HashMap<>();
        maxSize = 0; minSize = 0;
    }
    private void prune(PriorityQueue<Integer> heap){
        while(!heap.isEmpty()){
            int x = heap.peek();
            if(delayed.getOrDefault(x,0) > 0){
                delayed.put(x, delayed.get(x)-1);
                if(delayed.get(x)==0) delayed.remove(x);
                heap.poll();
            } else break;
        }
    }
    private void balance(){
        if(maxSize > minSize + 1){
            minHeap.offer(maxHeap.poll());
            maxSize--; minSize++;
            prune(maxHeap);
        } else if(maxSize < minSize){
            maxHeap.offer(minHeap.poll());
            maxSize++; minSize--;
            prune(minHeap);
        }
    }
    public void add(int num){
        if(maxHeap.isEmpty() || num <= maxHeap.peek()){
            maxHeap.offer(num); maxSize++;
        } else {
            minHeap.offer(num); minSize++;
        }
        balance();
    }
    public void remove(int num){
        if(!maxHeap.isEmpty() && num <= maxHeap.peek()){
            maxSize--;
            delayed.put(num, delayed.getOrDefault(num,0)+1);
            if(maxHeap.peek()!=null && maxHeap.peek()==num) prune(maxHeap);
        } else {
            minSize--;
            delayed.put(num, delayed.getOrDefault(num,0)+1);
            if(minHeap.peek()!=null && minHeap.peek()==num) prune(minHeap);
        }
        balance();
    }
    public double getMedian(){
        prune(maxHeap); prune(minHeap);
        if(maxSize + minSize == 0) return 0;
        if(((maxSize+minSize)&1)==1) return (double) maxHeap.peek();
        return ((double)maxHeap.peek() + (double)minHeap.peek())/2.0;
    }
    public static double[] medianSlidingWindow(int[] nums, int k){
        if(k==0) return new double[0];
        SlidingWindowMedian swm = new SlidingWindowMedian();
        double[] res = new double[nums.length - k + 1];
        for(int i=0;i<nums.length;i++){
            swm.add(nums[i]);
            if(i >= k-1){
                res[i-k+1] = swm.getMedian();
                swm.remove(nums[i-k+1]);
            }
        }
        return res;
    }
    public static void main(String[] args){
        int[] a = {1,3,-1,-3,5,3,6,7};
        double[] r = medianSlidingWindow(a,3);
        System.out.println(Arrays.toString(r)); // [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]
        int[] b = {1,2,3,4};
        System.out.println(Arrays.toString(medianSlidingWindow(b,2))); // [1.5,2.5,3.5]
    }
}
