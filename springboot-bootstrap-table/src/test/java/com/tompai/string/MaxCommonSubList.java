/**
 * 
 */
package com.tompai.string;

/**
 * @author Administrator
 给定两个字符串str1和str2,输出两个字符串的最长公共子串
题目保证str1和str2的最长公共子串存在且唯一。 

数据范围： 1 \le |str1|,|str2| \le 50001≤∣str1∣,∣str2∣≤5000
要求： 空间复杂度 O(n^2)O(n 
2
 )，时间复杂度 O(n^2)O(n 
2
 )
 */
public class MaxCommonSubList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MaxCommonSubList test=new MaxCommonSubList();
		String s="1AB2345CD";
		String t="12345EF";
		System.out.println(test.LCS2(s, t));

	}
	
	public String LCS(String str1, String str2) {
	    int maxLenth = 0;//记录最长公共子串的长度
	    //记录最长公共子串最后一个元素在字符串str1中的位置
	    int maxLastIndex = 0;
	    int[] dp = new int[str2.length() + 1];
	    for (int i = 0; i < str1.length(); i++) {
	        //注意这里是倒叙
	        for (int j = str2.length() - 1; j >= 0; j--) {
	            //递推公式，两个字符相等的情况
	            if (str1.charAt(i) == str2.charAt(j)) {
	                dp[j + 1] = dp[j] + 1;
	                //如果遇到了更长的子串，要更新，记录最长子串的长度，
	                //以及最长子串最后一个元素的位置
	                if (dp[j + 1] > maxLenth) {
	                    maxLenth = dp[j + 1];
	                    maxLastIndex = i;
	                }
	            } else {
	                //递推公式，两个字符不相等的情况
	                dp[j + 1] = 0;
	            }
	        }
	    }
	    //最字符串进行截取，substring(a,b)中a和b分别表示截取的开始和结束位置
	    return str1.substring(maxLastIndex - maxLenth + 1, maxLastIndex + 1);
	}
	
	 public String LCS2 (String str1, String str2) {
	        // write code here
	        String result = "";
	        int start = 0;
	        int end = 1;
	        while(end<=str2.length()){
	            String subStr = str2.substring(start,end);
	            if(str1.contains(subStr)){
	                result = subStr;
	            }else{
	                start++;
	            }
	            end++;
	        }
	        return result;
	    }
}
