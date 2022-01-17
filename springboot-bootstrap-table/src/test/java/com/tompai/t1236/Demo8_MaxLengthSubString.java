package com.tompai.t1236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Demo8_MaxLengthSubString<K, V> {
//https://blog.csdn.net/u013063153/article/details/49593353
	//给定两个字符串str1和str2,输出两个字符串的最长公共子串,题目保证str1和str2的最长公共子串存在且唯一。 
	//"1AB2345CD","12345EF"
	//"binghaven","jingseven"

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {

			while (scanner.hasNext()) {

				String input = scanner.nextLine();

				input = input.replaceAll("\\\"", "");
				String[] ss = input.split(",");
				String res = LCS(ss[0], ss[1]);

				System.out.println(res);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	//在动态规划矩阵生成方式当中，每生成一行，前面的那一行就已经没有用了，因此这里只需使用一维数组，而不是常用的二位数组  
	public static void getLCString(char[] str1, char[] str2) {
		int len1, len2;
		len1 = str1.length;
		len2 = str2.length;
		int maxLen = len1 > len2 ? len1 : len2;

		int[] max = new int[maxLen];// 保存最长子串长度的数组  
		int[] maxIndex = new int[maxLen];// 保存最长子串长度最大索引的数组  
		int[] c = new int[maxLen];

		int i, j;
		for (i = 0; i < len2; i++) {
			for (j = len1 - 1; j >= 0; j--) {
				if (str2[i] == str1[j]) {
					if ((i == 0) || (j == 0))
						c[j] = 1;
					else
						c[j] = c[j - 1] + 1;//此时C[j-1]还是上次循环中的值，因为还没被重新赋值  
				} else {
					c[j] = 0;
				}

				// 如果是大于那暂时只有一个是最长的,而且要把后面的清0;  
				if (c[j] > max[0]) {
					max[0] = c[j];
					maxIndex[0] = j;

					for (int k = 1; k < maxLen; k++) {
						max[k] = 0;
						maxIndex[k] = 0;
					}
				}
				// 有多个是相同长度的子串  
				else if (c[j] == max[0]) {
					for (int k = 1; k < maxLen; k++) {
						if (max[k] == 0) {
							max[k] = c[j];
							maxIndex[k] = j;
							break; // 在后面加一个就要退出循环了  
						}
					}
				}
			}
			for (int temp : c) {
				System.out.print(temp);
			}
			System.out.println();
		}
		//打印最长子字符串  
		for (j = 0; j < maxLen; j++) {
			if (max[j] > 0) {
				System.out.println("第" + (j + 1) + "个公共子串:");
				for (i = maxIndex[j] - max[j] + 1; i <= maxIndex[j]; i++)
					System.out.print(str1[i]);
				System.out.println(" ");
			}
		}
	}

	//只能找出一个最长公共子串
	public static String LCS(String str1, String str2) {
		// write code here
		String result = "";
		int start = 0;
		int end = 1;
		while (end <= str2.length()) {
			String subStr = str2.substring(start, end);
			if (str1.contains(subStr)) {
				result = subStr;
			} else {
				start++;
			}
			end++;
		}
		return result;
	}
	
	
	//https://blog.csdn.net/qq_31881469/article/details/77892324
	//最长公共子序列（LCS）与最长公共子串(DP)
	public static int lcs(String str1, String str2) {  
	    int len1 = str1.length();  
	    int len2 = str2.length();  
	    int result = 0; //记录最长公共子串长度  
	    int c[][] = new int[len1+1][len2+1];  
	    for (int i = 0; i <= len1; i++) {  
	        for( int j = 0; j <= len2; j++) {  
	            if(i == 0 || j == 0) {  
	                c[i][j] = 0;  
	            } else if (str1.charAt(i-1) == str2.charAt(j-1)) {  
	                c[i][j] = c[i-1][j-1] + 1;  
	                result = Math.max(c[i][j], result);  
	            } else {  
	                c[i][j] = 0;  
	            }  
	        }  
	    }  
	    return result;  
	}  

}
