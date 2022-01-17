/**
 * 
 */
package com.tompai.t1231;

import java.util.Scanner;

/**
 * @author Administrator
有n*n个格子，每个格子里有正数或者0，从最左上角往最右下角走，只能向下和向右走，一共走两次(即从左上角往右下角走两趟)，把所有经过的格子里的数加起来，求总和的最大值。如果两次经过同一个格子，则最后求得的总和中该格子中的数只加一次。
 */
public class Demo1_Grid_GetNumber {
	
	public static void main(String[] args) {
		Demo1_Grid_GetNumber test = new Demo1_Grid_GetNumber();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] value = new int[n][n];
        for(int i = 0;i < n;i++)
            for(int j = 0;j < n;j++)
                value[i][j] = in.nextInt();
        test.getResult(value);
    }
	
	
	public boolean judge(int s, int i ,int j ,int len) {
        int x1 = s - i, x2 = s - j;
        if(x1 >= 0 && x1 < len && x2 >= 0 && x2 < len && i >= 0 && j >= 0 && i < len && j < len)
            return true;
        return false;
    }
    
    public int getValue(int[][][] dp, int s, int i , int j, int len) {
        if(judge(s, i, j, len))
            return dp[s][i][j];
        return -1;
    }
    
    public void getResult(int[][] value) {
        int len = value.length;
        int[][][] dp = new int[len * 2][len][len];
        dp[0][0][0] = value[0][0];
        for(int s = 1;s <= len * 2 - 2;s++) {
            for(int i = 0;i < len;i++) {
                for(int j = 0;j < len;j++) {
                    if(judge(s, i, j, len)) {
                        if(i != j) 
                            dp[s][i][j] = Math.max(Math.max(getValue(dp,s-1,i-1,j-1,len),getValue(dp,s-1,i,j,len)), 
                                    Math.max(getValue(dp,s-1,i-1,j,len), getValue(dp,s-1,i,j-1,len))) + value[i][s-i] + value[j][s-j];
                        else
                            dp[s][i][j] = Math.max(getValue(dp,s-1,i-1,j-1,len), 
                                    Math.max(getValue(dp,s-1,i-1,j,len), getValue(dp,s-1,i,j,len))) + value[i][s-i];
                    }
                }
            }
        }
        System.out.println(dp[2 * len - 2][len - 1][len - 1]);
        return;
    }
    
    
    

}
