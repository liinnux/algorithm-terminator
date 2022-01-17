/**
 * 
 */
package com.tompai.t1226;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Administrator
 */
public class Demo9_HollandFlagProblem {

	public static void main(String[] args) {
		Demo9_HollandFlagProblem test = new Demo9_HollandFlagProblem();
		int[] A = { 2, 0, 2, 0, 0, 2, 1, 1, 0, 2, 1, 0, 1, 2, 0, 1, 2, 0, 1, 0, 2, 1, 0, 2, 0, 1, 2, 0, 1, 2, 0, 2, 1,
				0 };
		test.getHollandSort(A);
	}

	//输出荷兰国旗问题后的排序结果，时间复杂度为O(n)，空间复杂度为O(1)
	public void getHollandSort(int[] A) {
		int begin = 0;
		int current = 0;
		int end = A.length - 1;
		while (current <= end) {
			//值得注意的是：此处if语句是使用if-else if-else if，而没有使用if-if-if。这样使用保证每一次循环只执行一个条件，
			//否则，若使用if-if-if，可能会形成一次循环执行两到三个if条件，造成最终结果错误（PS:即在循环结束前，发生current > end）
			if (A[current] == 0) {
				swap(A, begin, current);
				begin++;
				current++;
			} else if (A[current] == 1)
				current++;
			else if (A[current] == 2) {
				swap(A, current, end);
				end--;
			}
		}

		//输出排完序后的数组A相应元素
		System.out.println("对数组A进行划分后的元素顺序为：");
		for (int i = 0; i < A.length; i++)
			System.out.print(A[i] + " ");
	}

	//交换数组A中m位置和n位置上元素的值
	public void swap(int[] A, int m, int n) {
		int temp = A[m];
		A[m] = A[n];
		A[n] = temp;
	}

}
