/**
 * 
 */
package com.tompai.t1224;

/**
 * @author Administrator
 *
 */
public class Demo9_NQueen_1 {

	/**
	 * @param args
	 */
	private static int n = 27;
    private static int count = 0;

    //14772512
    public static void main(String[] args) {
        count = 0;
        long tic = System.currentTimeMillis();
        DFS(0, 0, 0, 0);
        long toc = System.currentTimeMillis();
        System.out.println("Total solutions: " + count);
        System.out.println("Elapsed time: " + (toc - tic) + " ms");
    }

    private static void DFS(int row, int shu, int pie, int na) {
        int available = ((1 << n) - 1) & ~(shu | pie | na);
        while (available != 0) {
            int p = available & -available;
            available ^= p;
            if (row == n - 1) {
                count++;
            }
            else {
                DFS(row + 1, shu ^ p, (pie ^ p) >> 1, (na ^ p) << 1);
            }
        }
    } 
}
