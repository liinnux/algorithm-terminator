/**
 * 
 */
package com.tompai.t1226;

import java.util.Arrays;

/**
 * 最长回文子串判断
 * 
 * @author Administrator
 */
public class Demo4_QuickSort_2 {

	public static void main(String[] args) {
		int[] arr = { 110, 8, 7, 6, 5, 4, 3, 2, 1, 0, 11, 99 };
		quickSort(arr, 0, arr.length - 1);
		System.out.println("QuickSort排序结果：" + Arrays.toString(arr));
	}

	/**
	 * @param arr
	 * @param left  左指针
	 * @param right 右指针
	 */
	public static void quickSort(int[] arr, int left, int right) {
		if (left < right) {
			//获取枢纽值，并将其放在当前待处理序列末尾
			dealPivot(arr, left, right);
			//枢纽值被放在序列末尾
			int pivot = right - 1;
			//左指针
			int i = left;
			//右指针
			int j = right - 1;
			while (true) {
				while (arr[++i] < arr[pivot]) {
				}
				while (j > left && arr[--j] > arr[pivot]) {
				}
				if (i < j) {
					swap(arr, i, j);
				} else {
					break;
				}
			}
			if (i < right) {
				swap(arr, i, right - 1);
			}
			quickSort(arr, left, i - 1);
			quickSort(arr, i + 1, right);
		}

	}

	/**
	 * 处理枢纽值
	 *
	 * @param arr
	 * @param left
	 * @param right
	 */
	public static void dealPivot(int[] arr, int left, int right) {
		int mid = (left + right) / 2;
		if (arr[left] > arr[mid]) {
			swap(arr, left, mid);
		}
		if (arr[left] > arr[right]) {
			swap(arr, left, right);
		}
		if (arr[right] < arr[mid]) {
			swap(arr, right, mid);
		}
		swap(arr, right - 1, mid);
	}

	/**
	 * 交换元素通用处理
	 *
	 * @param r
	 * @param a
	 * @param b
	 */
	private static void swap(int[] r, int a, int b) {
		/*int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;*/
		if (r[a] == r[b]) {
			//注意此处的陷阱
			return;
		}
		r[a] = r[a] ^ r[b];
		r[b] = r[a] ^ r[b];
		r[a] = r[a] ^ r[b];
	}
}
