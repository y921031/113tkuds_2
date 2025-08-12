// MultiLevelCacheSystem.java
import java.util.*;

public class MultiLevelCacheSystem {
    static class Entry{ int key; String val; int freq; long time; Entry(int k,String v,long t){key=k;val=v;freq=1;time=t;} }
    private final int cap1=2, cap2=5, cap3=10;
    private Map<Integer, Entry> store;
    private LinkedHashSet<Integer> L1, L2, L3;
    private long timer = 0;
    public MultiLevelCacheSystem(){
        store = new HashMap<>();
        L1 = new LinkedHashSet<>();
        L2 = new LinkedHashSet<>();
        L3 = new LinkedHashSet<>();
    }
    private void touchUp(int key){
        Entry e = store.get(key);
        if(e==null) return;
        e.freq++; e.time = timer++;
        if(L1.contains(key)) return;
        if(L2.contains(key)){
            L2.remove(key); L1.add(key);
            if(L1.size() > cap1) demote(L1, L2, cap1);
        } else if(L3.contains(key)){
            L3.remove(key); L2.add(key);
            if(L2.size() > cap2) demote(L2, L3, cap2);
        }
    }
    private void demote(LinkedHashSet<Integer> from, LinkedHashSet<Integer> to, int cap){
        Iterator<Integer> it = from.iterator();
        if(it.hasNext()){
            Integer rem = it.next();
            it.remove();
            to.add(rem);
            if(to.size() > (to==L2 ? cap2 : cap3)){
                Iterator<Integer> it2 = to.iterator();
                if(it2.hasNext()) it2.remove();
            }
        }
    }
    public void put(int key, String value){
        if(store.containsKey(key)){
            Entry e = store.get(key);
            e.val = value; touchUp(key); return;
        }
        Entry e = new Entry(key, value, timer++);
        store.put(key, e);
        L2.add(key);
        if(L2.size() > cap2) demote(L2, L3, cap2);
    }
    public String get(int key){
        Entry e = store.get(key);
        if(e==null) return null;
        touchUp(key);
        if(L3.contains(key) && e.freq > 2){
            L3.remove(key); L2.add(key);
            if(L2.size() > cap2) demote(L2, L3, cap2);
        }
        return e.val;
    }
    public void debugPrint(){
        System.out.println("L1: " + L1);
        System.out.println("L2: " + L2);
        System.out.println("L3: " + L3);
    }
    public static void main(String[] args){
        MultiLevelCacheSystem c = new MultiLevelCacheSystem();
        c.put(1,"A"); c.put(2,"B"); c.put(3,"C");
        c.debugPrint();
        c.get(1); c.get(1); c.get(2);
        c.debugPrint();
        c.put(4,"D"); c.put(5,"E"); c.put(6,"F");
        c.debugPrint();
    }
}
