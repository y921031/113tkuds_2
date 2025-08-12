import java.util.*;

public class PriorityQueueWithHeap {
    static class Task{
        String name; int priority; long time;
        Task(String n,int p,long t){ name=n; priority=p; time=t; }
    }
    private PriorityQueue<Task> pq;
    private Map<String, Task> map;
    private long counter = 0;
    public PriorityQueueWithHeap(){
        pq = new PriorityQueue<>((a,b)-> a.priority!=b.priority ? Integer.compare(b.priority,a.priority) : Long.compare(a.time,b.time));
        map = new HashMap<>();
    }
    public void addTask(String name,int priority){
        Task t = new Task(name, priority, counter++);
        pq.offer(t);
        map.put(name, t);
    }
    public String executeNext(){
        while(!pq.isEmpty()){
            Task t = pq.poll();
            Task current = map.get(t.name);
            if(current!=null && current.time==t.time){
                map.remove(t.name);
                return t.name;
            }
        }
        return null;
    }
    public String peek(){
        while(!pq.isEmpty()){
            Task t = pq.peek();
            Task current = map.get(t.name);
            if(current!=null && current.time==t.time) return t.name;
            pq.poll();
        }
        return null;
    }
    public boolean changePriority(String name,int newPriority){
        Task t = map.get(name);
        if(t==null) return false;
        Task updated = new Task(name, newPriority, counter++);
        map.put(name, updated);
        pq.offer(updated);
        return true;
    }
    public static void main(String[] args){
        PriorityQueueWithHeap q = new PriorityQueueWithHeap();
        q.addTask("備份",1);
        q.addTask("緊急修復",5);
        q.addTask("更新",3);
        System.out.println(q.executeNext()); // 緊急修復
        System.out.println(q.executeNext()); // 更新
        System.out.println(q.executeNext()); // 備份
    }
}
