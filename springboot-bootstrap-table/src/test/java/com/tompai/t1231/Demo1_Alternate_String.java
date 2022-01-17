/**
 * 
 */
package com.tompai.t1231;

/**
 * @author Administrator
问题描述
给定一个浮点数组，任意取出数组中的若干个连续的数相乘，请找出其中乘积最大的子数组。
 */
public class Demo1_Alternate_String {

	public static void main(String[] args) {
		Demo1_Alternate_String test = new Demo1_Alternate_String();
        String A = "aabcc";
        String B = "dbbca";
        String C = "aadbbbccca";
        if(test.judge(A, B, C))
            System.out.println("符合交替字符串");
        else
            System.out.println("不符合交替字符串");
    }
	public boolean judge(String A, String B, String C) {
        if(C.length() != A.length() + B.length())
            return false;
        int[][] dp = new int[A.length() + 1][B.length() + 1];
        dp[0][0] = 1;  //代表A和B均为空串时，C也为空串，此时符合交替字符串匹配
        for(int i = 0;i <= A.length();i++) {
            for(int j = 0;j <= B.length();j++) {
                if((i - 1 >= 0 && dp[i-1][j] == 1 && A.charAt(i-1) == C.charAt(i+j-1))||
                  (j - 1 >= 0 && dp[i][j-1] == 1 && B.charAt(j-1) == C.charAt(i+j-1)) )
                    dp[i][j] = 1;
            }
        }
        if(dp[A.length()][B.length()] == 0)
            return false;
        return true;
    }
    
    

}
