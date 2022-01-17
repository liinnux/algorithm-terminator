package com.tompai.t1233;

import java.util.Arrays;

public class Demo {

	public static void main(String[] args) {
		int[] arr = { 3, 7, 2, 1, -4 };
		int max = findMaxByStream(arr);
		// 根据 stream 查找最大值        
		System.out.println("最大值是：" + max);
	}

	/** * 根据 stream 查找最大值 * @param arr 待查询数组 * @return 最大值 */
	private static int findMaxByStream(int[] arr) {
		return Arrays.stream(arr).max().getAsInt();
	}

}
