/**
 * 
 */
package com.tompai.t1231;

/**
 * @author Administrator
问题描述
给定一个浮点数组，任意取出数组中的若干个连续的数相乘，请找出其中乘积最大的子数组。
 */
public class Demo1_DP_Subarray_Product {

	public static void main(String[] args) {
		Demo1_DP_Subarray_Product test = new Demo1_DP_Subarray_Product();
		double[] A = { -2.5, 4, 0, 3, 0.5, 8, -1 };
		test.getResult_bf(A);
	}

	

	public void getResult_dp(double[] A) {
		double result = 1;
		double max = 1, min = 1;
		for (int i = 0; i < A.length; i++) {
			max = Math.max(max * A[i], Math.max(min * A[i], A[i]));
			min = Math.min(max * A[i], Math.min(min * A[i], A[i]));
			if (max > result)
				result = max;
		}
		System.out.println(result);
		return;
	}
	
	public void getResult_bf(double[] A) {
		double max = 1;
		for (int i = 0; i < A.length; i++) {
			double temp = 1;
			for (int j = i; j < A.length; j++) {
				temp = temp * A[j];
				if (temp > max)
					max = temp;
			}
		}
		System.out.println(max);
		return;
	}
	
	
	//由 N 个整数元素（有正数也有负数）组成的一维数组 (A[0], A[1],…,A[n-1], A[n])，这个数组有很多连续子数组，那么其中数组之和的最大值是什么呢？
	//子数组必须是连续的。
	public int FindGreatestSumOfSubArray(int[] array) {

        if (array.length == 0) {
            return 0;
        }

        // 新建动态规划数组
        int[] dp = new int[array.length+1];
        // 由于下方遍历从1开始，先写入第一个数进dp[0]
        dp[0] = array[0];

        // 设置最大值：由于最开始的是array[0]，后面如果是负数肯定更小，如果是整数肯定变大
        int maxSum = array[0];

        for (int i = 1; i < dp.length; i++) {

            dp[i] = Math.max(array[i], array[i]+dp[i-1]);

            if (dp[i] > maxSum) {
                maxSum = dp[i];
            }
        }
        return maxSum;
    }

}
