import java.util.*;

public class MergeKSortedArrays {
    static class Node{
        int val, arrIdx, elemIdx;
        Node(int v,int a,int e){val=v;arrIdx=a;elemIdx=e;}
    }
    public static List<Integer> merge(List<int[]> lists){
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->Integer.compare(a.val,b.val));
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<lists.size();i++){
            if(lists.get(i).length>0) pq.offer(new Node(lists.get(i)[0], i, 0));
        }
        while(!pq.isEmpty()){
            Node n = pq.poll();
            res.add(n.val);
            int next = n.elemIdx + 1;
            if(next < lists.get(n.arrIdx).length){
                pq.offer(new Node(lists.get(n.arrIdx)[next], n.arrIdx, next));
            }
        }
        return res;
    }
    public static void main(String[] args){
        List<int[]> a = Arrays.asList(new int[]{1,4,5}, new int[]{1,3,4}, new int[]{2,6});
        System.out.println(merge(a)); // [1,1,2,3,4,4,5,6]
        List<int[]> b = Arrays.asList(new int[]{1,2,3}, new int[]{4,5,6}, new int[]{7,8,9});
        System.out.println(merge(b));
        List<int[]> c = Arrays.asList(new int[]{1}, new int[]{0});
        System.out.println(merge(c));
    }
}
