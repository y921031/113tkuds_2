import java.util.*;

public class MovingAverageStream {
    private int size;
    private Deque<Integer> q;
    private long sum;
    private PriorityQueue<Integer> low;
    private PriorityQueue<Integer> high;
    private Map<Integer,Integer> delayed;
    private TreeMap<Integer,Integer> freq;
    private int lowSize, highSize;
    public MovingAverageStream(int k){
        size = k; q = new LinkedList<>(); sum = 0;
        low = new PriorityQueue<>(Collections.reverseOrder());
        high = new PriorityQueue<>();
        delayed = new HashMap<>();
        freq = new TreeMap<>();
        lowSize = 0; highSize = 0;
    }
    private void addFreq(int x){ freq.put(x, freq.getOrDefault(x,0)+1); }
    private void removeFreq(int x){ int c = freq.getOrDefault(x,0); if(c<=1) freq.remove(x); else freq.put(x, c-1); }
    private void prune(PriorityQueue<Integer> heap){
        while(!heap.isEmpty()){
            int val = heap.peek();
            if(delayed.getOrDefault(val,0) > 0){
                delayed.put(val, delayed.get(val)-1);
                if(delayed.get(val)==0) delayed.remove(val);
                heap.poll();
            } else break;
        }
    }
    private void balance(){
        if(lowSize > highSize + 1){
            high.offer(low.poll()); lowSize--; highSize++;
            prune(low);
        } else if(lowSize < highSize){
            low.offer(high.poll()); lowSize++; highSize--;
            prune(high);
        }
    }
    public double next(int val){
        q.offer(val); sum += val; addFreq(val);
        if(low.isEmpty() || val <= low.peek()){ low.offer(val); lowSize++; } else { high.offer(val); highSize++; }
        balance();
        if(q.size() > size){
            int out = q.poll(); sum -= out; removeFreq(out);
            if(out <= low.peek()){
                lowSize--; delayed.put(out, delayed.getOrDefault(out,0)+1);
                if(low.peek()!=null && low.peek()==out) prune(low);
            } else {
                highSize--; delayed.put(out, delayed.getOrDefault(out,0)+1);
                if(high.peek()!=null && high.peek()==out) prune(high);
            }
            balance();
        }
        return (double) sum / q.size();
    }
    public double getMedian(){
        prune(low); prune(high);
        if((lowSize+highSize)%2==1) return (double) low.peek();
        return ((double)low.peek() + (double)high.peek()) / 2.0;
    }
    public int getMin(){ return freq.isEmpty() ? Integer.MIN_VALUE : freq.firstKey(); }
    public int getMax(){ return freq.isEmpty() ? Integer.MAX_VALUE : freq.lastKey(); }
    public static void main(String[] args){
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1)); //1.0
        System.out.println(ma.next(10)); //5.5
        System.out.println(ma.next(3)); //4.666...
        System.out.println(ma.next(5)); //6.0
        System.out.println(ma.getMedian()); //5.0
        System.out.println(ma.getMin()); //3
        System.out.println(ma.getMax()); //10
    }
}
