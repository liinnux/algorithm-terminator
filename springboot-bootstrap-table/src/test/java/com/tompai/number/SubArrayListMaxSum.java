/**
 * 
 */
package com.tompai.number;

/**
 * @author Administrator 输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *         数据范围: 1 <= n <= 10^51<=n<=10 5
 * 
 *         -100 <= a[i] <= 100−100<=a[i]<=100
 * 
 *         要求:时间复杂度为 O(n)O(n)，空间复杂度为 O(n)O(n) 进阶:时间复杂度为 O(n)O(n)，空间复杂度为 O(1)O(1)
 */
public class SubArrayListMaxSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SubArrayListMaxSum test = new SubArrayListMaxSum();
		int[] arrs = { 1, -2, 3, 10, -4, 7, 2, -5 };

		int res = test.findGreatestSumOfSubArray(arrs);

		System.out.println(res);
	}

	public int findGreatestSumOfSubArray(int[] array) {
		int now = 0, ans = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if (now < 0) {//now<0,则舍去前面的
				now = array[i];
			} else {
				now += array[i];
				;//比0大则直接加上去
			}
			ans = Math.max(now, ans);//更新ans
		}
		return ans;
	}

	/*
	动态规划：fi表示以i结尾的最大数组和
	fi=fi-1+ai       fi-1>0
	  ai fi-1<=0
	*/
	public int sumOfSubArray(int[] array) {
		int pre = 0;
		int max = Integer.MIN_VALUE;
		int len = array.length;
		for (int i = 0; i < len; i++) {
			if (pre > 0)
				pre += array[i];
			else
				pre = array[i];
			max = Math.max(pre, max);
		}
		return max;
	}

}
