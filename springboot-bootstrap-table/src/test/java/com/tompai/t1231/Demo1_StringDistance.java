/**
 * 
 */
package com.tompai.t1231;

/**
 * @author Administrator
给定一个源串和目标串，能够进行如下操作：
在任意位置上
插入一个字符；
替换掉任意字符；
删除任意字符。
写一个程序，实现返回最小操作次数，使得对源串进行上述这些操作后等于目标串。
 */
public class Demo1_StringDistance {
	
	public static void main(String[] args) {
    	Demo1_StringDistance test = new Demo1_StringDistance();
        String A = "ALGORITHM";
        String B = "ALTRUISTIC";
        test.getResult(A, B);
    }
	

	public void getResult(String A, String B) {
        if(A.equals(B)) {
            System.out.println(0);
            return;
        }
        //dp[i][j]表示源串A位置i到目标串B位置j处最低需要操作的次数
        int[][] dp = new int[A.length() + 1][B.length() + 1];
        for(int i = 1;i <= A.length();i++)
            dp[i][0] = i;
        for(int j = 1;j <= B.length();j++)
            dp[0][j] = j;
        for(int i = 1;i <= A.length();i++) {
            for(int j = 1;j <= B.length();j++) {
                if(A.charAt(i - 1) == B.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    dp[i][j] = Math.min(dp[i - 1][j] + 1,
                            Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1));
                }
            }
        }
        System.out.println(dp[A.length()][B.length()]);
        return;
    }
    
    

}
