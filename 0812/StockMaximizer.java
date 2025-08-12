import java.util.*;

public class StockMaximizer {
    public static int maxProfitUnlimited(int[] prices){
        int profit=0;
        for(int i=1;i<prices.length;i++) if(prices[i]>prices[i-1]) profit += prices[i]-prices[i-1];
        return profit;
    }
    public static int maxProfitK(int K, int[] prices){
        int n = prices.length;
        if(n==0) return 0;
        if(K >= n/2) return maxProfitUnlimited(prices);
        int[] buy = new int[K+1];
        int[] sell = new int[K+1];
        Arrays.fill(buy, Integer.MIN_VALUE/2);
        Arrays.fill(sell, 0);
        for(int p: prices){
            for(int k=1;k<=K;k++){
                buy[k] = Math.max(buy[k], sell[k-1] - p);
                sell[k] = Math.max(sell[k], buy[k] + p);
            }
        }
        return sell[K];
    }
    public static void main(String[] args){
        System.out.println(maxProfitK(2, new int[]{2,4,1})); //2
        System.out.println(maxProfitK(2, new int[]{3,2,6,5,0,3})); //7
        System.out.println(maxProfitK(2, new int[]{1,2,3,4,5})); //4
    }
}
