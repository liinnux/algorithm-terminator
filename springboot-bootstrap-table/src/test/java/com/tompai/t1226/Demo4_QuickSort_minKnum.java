/**
 * 
 */
package com.tompai.t1226;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最长回文子串判断
 * 
 * @author Administrator
 */
public class Demo4_QuickSort_minKnum {

	public static void main(String[] args) {
		//给出无序数组
		int arr[] = { 72, 6, 57, 88, 60, 42, 83, 73, 48, 85 };
		//输出无序数组
		System.out.println(Arrays.toString(arr));
		//快速排序
		ArrayList<Integer> list = GetLeastNumbers_QuickSort(arr, 3);
		ArrayList<Integer> list2 = GetLeastNumbers_Solution_maxStack(arr, 3);
		//输出有序数组
		System.out.println(list);
		System.out.println(list2);
	}

	public static ArrayList<Integer> GetLeastNumbers_Solution_maxStack(int[] input, int k) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int length = input.length;
		if (k > length || k == 0) {
			return result;
		}
		//PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});

		for (int i = 0; i < length; i++) {
			if (maxHeap.size() != k) {
				maxHeap.offer(input[i]);
			} else if (maxHeap.peek() > input[i]) {
				maxHeap.poll();
				maxHeap.offer(input[i]);
			}
		}
		for (Integer integer : maxHeap) {
			result.add(integer);
		}
		return result;
	}
	

	public static ArrayList<Integer> GetLeastNumbers_QuickSort(int[] input, int k) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (k <= 0) {
			return result;
		}
		if (input.length == 0 || input.length < k) {
			return result;
		}
		int start = 0;
		int end = input.length - 1;
		int index = Partition(input, start, end);
		while (index != k - 1) {
			if (index > k - 1) {
				end = index - 1;
				index = Partition(input, start, end);
			} else {
				start = index + 1;
				index = Partition(input, start, end);
			}
		}
		for (int i = 0; i < k; i++) {
			result.add(input[i]);
		}
		return result;
	}

	public static int Partition(int[] input, int start, int end) {
		int pivot = input[start];
		while (start < end) {
			while (start < end && input[end] >= pivot) {
				--end;
			}
			input[start] = input[end];
			while (start < end && input[start] <= pivot) {
				++start;
			}
			input[end] = input[start];
		}
		input[end] = pivot;
		return end;
	}

}
