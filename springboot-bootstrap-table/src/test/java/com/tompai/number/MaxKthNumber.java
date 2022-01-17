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
public class MaxKthNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 4, 5, 1, 6, 2, 7, 3, 8 };
		int k = 4;

		MaxKthNumber test = new MaxKthNumber();

		int m = test.findKthMax(a, a.length,k);

		System.out.println(m);
	}

	private int findKthMax(int[] a,int n, int K) {
		if (K > a.length || K == 0) {
			return 0;
		}
		//由大到小存
		Queue<Integer> stack = new PriorityQueue<>((num1, num2) -> num2.compareTo(num1));

		for (int i = 0; i < a.length; i++) {
			int t = a[i];
			stack.offer(t);
			if (stack.size() > K) {
				if (stack.peek() < t) {
					stack.poll();
				}
			}
		}
		//剔除前面k-1大的数据
		for (int i = 0; i < K - 1; i++) {
			stack.poll();
		}
		//返回第K大数据
		return stack.poll();
	}

	public int findKth(int[] a, int n, int K) {
		// write code here
		quickSort(a, 0, n - 1);
		int res = a[n - K];
		return res;
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private void quickSort(int[] arr, int left, int right) {
		if (left >= right)
			return;
		int l = left;
		int r = right;
		while (l < r) {
			while (l < r && arr[left] <= arr[r])
				r--;
			while (l < r && arr[left] >= arr[l])
				l++;
			swap(arr, l, r);
		}
		swap(arr, l, left);
		quickSort(arr, left, l - 1);
		quickSort(arr, l + 1, right);
	}
}
