/**
 * 
 */
package com.tompai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Administrator
 *
 */
public class QuickSort {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			String line = scan.nextLine();
			//[5,2,3,1,4]

			line = line.trim();
			String[] numbers = line.split("\\D");

			if (numbers.length <= 0) {
				System.out.print("[]");
				continue;
			} else {
				List<Integer> lists=new ArrayList<>();
				
				for (String s : numbers) {
					if (s.length() > 0) {
						lists.add(Integer.parseInt(s));
					}
				}
				int[] arr = new int[lists.size()];
				int k = 0;
				for(int m:lists) {
					arr[k++]=m;
				}
				quickSort(arr, 0, arr.length - 1);
				System.out.print("[");
				for (int i = 0; i < arr.length - 1; i++) {
					System.out.print(arr[i] + ",");
				}
				System.out.print(arr[arr.length - 1]);
				System.out.print("]");
			}
		}
		scan.close();
	}

	/**
	 * @param arr        待排序列
	 * @param leftIndex  待排序列起始位置
	 * @param rightIndex 待排序列结束位置
	 */
	private static void quickSort(int[] arr, int leftIndex, int rightIndex) {
		if (leftIndex >= rightIndex) {
			return;
		}

		int left = leftIndex;
		int right = rightIndex;
		//待排序的第一个元素作为基准值
		int key = arr[left];

		//从左右两边交替扫描，直到left = right
		while (left < right) {
			while (right > left && arr[right] >= key) {
				//从右往左扫描，找到第一个比基准值小的元素
				right--;
			}

			//找到这种元素将arr[right]放入arr[left]中
			arr[left] = arr[right];

			while (left < right && arr[left] <= key) {
				//从左往右扫描，找到第一个比基准值大的元素
				left++;
			}

			//找到这种元素将arr[left]放入arr[right]中
			arr[right] = arr[left];
		}
		//基准值归位
		arr[left] = key;
		//对基准值左边的元素进行递归排序
		quickSort(arr, leftIndex, left - 1);
		//对基准值右边的元素进行递归排序。
		quickSort(arr, right + 1, rightIndex);
	}
}
