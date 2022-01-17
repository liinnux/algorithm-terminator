/**
 * 
 */
package com.tompai.t1226;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Administrator
 */
public class Demo7_MaxSubArraySum {

	public static void main(String[] args) {
		int[] arr = { 110, 9, -8, 7, 6, 5, -4, -3, 2, 1 };
		int left = 0, right = 0;
		int sum = maxSubArraySum(arr, left, right);
		System.out.println("sum=" + sum);

	}

	public static int maxSubArraySum(int[] vec, int left, int right) {
		int maxsum = vec[0], currSum = 0;
		int begin = 0;
		for (int i = 0; i < vec.length; i++) {
			if (currSum >= 0)//
			{
				currSum += vec[i];
			} else {
				currSum = vec[i];
				begin = i;
			}

			if (maxsum < currSum) {
				maxsum = currSum;
				left = begin;
				right = i;
			}
		}
		System.out.println("left=" + left);
		System.out.println("right=" + right);
		return maxsum;
	}

}
