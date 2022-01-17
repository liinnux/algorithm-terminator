/**
 * 
 */
package com.tompai.t1224;

/**
 * @author Administrator
 *
 */
public class Demo9_NQueen_2 {

	private static final int N = 16; // 皇后数量，可拓展为N皇后
    private static int count = 0; // 总方法数
    private static int limit;

    public static void main(String[] args) {
    	long tic = System.currentTimeMillis();
        limit = (1 << N) - 1;
        backtracking(0, 0, 0, 0);
        long toc = System.currentTimeMillis();
        System.out.println(count);
        System.out.println("Total solutions: " + count);
        System.out.println("Elapsed time: " + (toc - tic) + " ms");
    }

    private static void backtracking(int a, int b, int c, int depth) {
        if (depth == N) {
            count++;
            return;
        }
        int d = a | b | c;
        while (d < limit) {
            int bit = (d + 1) & ~d;
            backtracking(a | bit, limit & ((b | bit) >> 1), limit & ((c | bit) << 1), depth + 1);
            d |= bit;
        }
    }

}
