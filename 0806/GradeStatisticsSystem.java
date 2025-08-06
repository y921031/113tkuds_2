public class GradeStatisticsSystem {
    
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};
        
        printReport(scores);
    }

    static double calculateAverage(int[] arr) {
        int sum = 0;
        for (int score : arr) sum += score;
        return (double) sum / arr.length;
    }

    static int findMax(int[] arr) {
        int max = arr[0];
        for (int score : arr) if (score > max) max = score;
        return max;
    }

    static int findMin(int[] arr) {
        int min = arr[0];
        for (int score : arr) if (score < min) min = score;
        return min;
    }

    static int[] gradeCount(int[] arr) {
        int[] count = new int[5]; // A,B,C,D,F
        for (int s : arr) {
            if (s >= 90) count[0]++;
            else if (s >= 80) count[1]++;
            else if (s >= 70) count[2]++;
            else if (s >= 60) count[3]++;
            else count[4]++;
        }
        return count;
    }

    static int countAboveAverage(int[] arr, double avg) {
        int cnt = 0;
        for (int s : arr) if (s > avg) cnt++;
        return cnt;
    }

    static void printReport(int[] arr) {
        double avg = calculateAverage(arr);
        int max = findMax(arr);
        int min = findMin(arr);
        int[] gradeCnt = gradeCount(arr);
        int aboveAvg = countAboveAverage(arr, avg);

        System.out.println("===== 成績統計報表 =====");
        System.out.printf("平均分：%.2f\n", avg);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
        System.out.println("等第統計：A=" + gradeCnt[0] + ", B=" + gradeCnt[1] +
                           ", C=" + gradeCnt[2] + ", D=" + gradeCnt[3] + ", F=" + gradeCnt[4]);
        System.out.println("高於平均分的人數：" + aboveAvg);
        System.out.println("所有成績：");
        for (int s : arr) System.out.print(s + " ");
        System.out.println("\n=======================");
    }
}
