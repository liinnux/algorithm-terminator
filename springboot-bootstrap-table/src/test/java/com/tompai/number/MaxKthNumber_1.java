/**
 * 
 */
package com.tompai.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Administrator 有一个整数数组，请你根据快速排序的思路，找出数组中第 k 大的数。
 * 
 *         给定一个整数数组 a ,同时给定它的大小n和要找的 k ，请返回第 k 大的数(包括重复的元素，不用去重)，保证答案存在。
 *         要求：时间复杂度 O(nlogn)O(nlogn)，空间复杂度 O(1)O(1) 数据范围：0\le n \le
 *         10000≤n≤1000， 1 \le K \le n1≤K≤n，数组中每个元素满足 0 \le val \le
 *         100000000≤val≤10000000 示例1 输入：[1,3,5,2,2],5,3
 * 
 *         返回值：2
 * 
 */
public class MaxKthNumber_1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 4, 5, 1, 6, 2, 7, 3, 8 };
		int k = 4;

		MaxKthNumber_1 test = new MaxKthNumber_1();

		int m = test.findKthMax(a, a.length, k);

		System.out.println(m);
	}

	public int findKthMax(int[] a, int n, int K) {
		// write code here

		return findK(a, 0, n - 1, K);
	}

	public int partition(int[] arr, int left, int right) {
		int pivot = arr[left];

		while (left < right) {
			while (left < right && arr[right] <= pivot) {
				right--;
			}
			arr[left] = arr[right];
			while (left < right && arr[left] >= pivot) {
				left++;
			}
			arr[right] = arr[left];
		}
		arr[left] = pivot;
		return left;
	}

	public int findK(int[] arr, int left, int right, int k) {
		if (left <= right) {
			int pivot = partition(arr, left, right);

			if (pivot == k - 1) {
				return arr[pivot];
			} else if (pivot < k - 1) {
				return findK(arr, pivot + 1, right, k);
			} else {
				return findK(arr, left, pivot - 1, k);
			}
		}
		return -1;

	}
}
