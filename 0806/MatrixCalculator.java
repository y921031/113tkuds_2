public class MatrixCalculator {
    public static void main(String[] args) {
        int[][] A = {{1, 2, 3}, {4, 5, 6}};
        int[][] B = {{7, 8, 9}, {10, 11, 12}};
        int[][] sum = addMatrices(A, B);
        int[][] product = multiplyMatrices(A, transpose(B));
        int[][] transposeA = transpose(A);
        int[] maxMinA = findMaxMin(A);
        System.out.println("A+B：");
        printMatrix(sum);
        System.out.println("A*B^T：");
        printMatrix(product);
        System.out.println("A的轉置：");
        printMatrix(transposeA);
        System.out.println("A最大值：" + maxMinA[0] + " 最小值：" + maxMinA[1]);
    }

    static int[][] addMatrices(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++)
            for (int j = 0; j < m1[0].length; j++)
                res[i][j] = m1[i][j] + m2[i][j];
        return res;
    }

    static int[][] multiplyMatrices(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++)
            for (int j = 0; j < m2[0].length; j++)
                for (int k = 0; k < m2.length; k++)
                    res[i][j] += m1[i][k] * m2[k][j];
        return res;
    }

    static int[][] transpose(int[][] m) {
        int[][] t = new int[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                t[j][i] = m[i][j];
        return t;
    }

    static int[] findMaxMin(int[][] m) {
        int max = m[0][0], min = m[0][0];
        for (int[] row : m)
            for (int v : row) {
                if (v > max) max = v;
                if (v < min) min = v;
            }
        return new int[]{max, min};
    }

    static void printMatrix(int[][] m) {
        for (int[] row : m) {
            for (int v : row) System.out.print(v + " ");
            System.out.println();
        }
    }
}
