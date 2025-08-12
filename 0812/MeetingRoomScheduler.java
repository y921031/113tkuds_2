import java.util.*;

public class MeetingRoomScheduler {
    public static int minMeetingRooms(int[][] intervals){
        if(intervals.length==0) return 0;
        Arrays.sort(intervals, (a,b)->Integer.compare(a[0],b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(intervals[0][1]);
        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0] >= pq.peek()) pq.poll();
            pq.offer(intervals[i][1]);
        }
        return pq.size();
    }
    static class Interval{ int s,e; Interval(int a,int b){s=a;e=b;} }
    public static int maxTotalTimeOneRoom(int[][] intervals){
        int n = intervals.length;
        if(n==0) return 0;
        Interval[] arr = new Interval[n];
        for(int i=0;i<n;i++) arr[i] = new Interval(intervals[i][0], intervals[i][1]);
        Arrays.sort(arr, (a,b)->Integer.compare(a.e,b.e));
        int[] dp = new int[n];
        int[] ends = new int[n];
        for(int i=0;i<n;i++) ends[i] = arr[i].e;
        for(int i=0;i<n;i++){
            int dur = arr[i].e - arr[i].s;
            int idx = Arrays.binarySearch(ends, 0, i, arr[i].s);
            if(idx < 0) idx = -idx - 1;
            int include = dur + (idx-1 >= 0 ? dp[idx-1] : 0);
            int exclude = i>0?dp[i-1]:0;
            dp[i] = Math.max(include, exclude);
        }
        return dp[n-1];
    }
    public static void main(String[] args){
        int[][] a = {{0,30},{5,10},{15,20}};
        System.out.println(minMeetingRooms(a)); //2
        int[][] b = {{9,10},{4,9},{4,17}};
        System.out.println(minMeetingRooms(b)); //2
        int[][] c = {{1,5},{8,9},{8,9}};
        System.out.println(minMeetingRooms(c)); //2
        int[][] d = {{1,4},{2,3},{4,6}};
        System.out.println(maxTotalTimeOneRoom(d)); //5
    }
}
