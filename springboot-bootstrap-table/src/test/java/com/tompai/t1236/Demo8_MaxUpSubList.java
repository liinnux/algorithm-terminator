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

public class Demo8_MaxUpSubList<K, V> {
	/*
	给定数组 arr ，设长度为 n ，输出 arr 的最长上升子序列。
	（如果有多个答案，请输出其中 按数值(注：区别于按单个字符的ASCII码值)进行比较的 字典序最小的那个）
	
	输入：[2,1,5,3,6,4,8,9,7]
	返回值：[1,3,4,8,9]
	 */

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {
			{
				while (scanner.hasNext()) {

					String input = scanner.nextLine();
					input = input.substring(1, input.length() - 1);
					String[] res = input.split(",", -1);
					int[] arrs = new int[res.length];
					for (int i = 0; i < res.length; i++) {
						arrs[i] = Integer.parseInt(res[i]);
					}
					
					int[] r=LIS(arrs);
					for(int i=0;i<r.length;i++)
					System.out.print(r[i]+" ");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	/**
	 * retrun the longest increasing subsequence
	 * 
	 * @param arr int整型一维数组 the array
	 * @return int整型一维数组
	 * 
	 *         动态规划+二分
	 */
	public static int[] LIS(int[] arr) {
		int[] dp = new int[arr.length];
		int[] subset = new int[arr.length + 1];
		int len = 0;
		for (int i = 0; i < arr.length; i++) {
			if (subset[len] < arr[i]) {
				len += 1;
				subset[len] = arr[i];
				dp[i] = len;
			} else {
				int idx = Arrays.binarySearch(subset, 0, len, arr[i]);
				if (idx < 0) {
					idx = -(idx + 1);
				}
				subset[idx] = arr[i];
				dp[i] = idx;
			}
		}
		int[] res = new int[len];
		for (int i = arr.length - 1; i >= 0; i--) {
			if (dp[i] == len) {
				res[--len] = arr[i];
			}
		}
		return res;
	}
}
