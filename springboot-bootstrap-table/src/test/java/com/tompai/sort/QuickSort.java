/**
 * 
 */
package com.tompai.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 给定一个长度为 n 的数组，请你编写一个函数，返回该数组按升序排序后的结果。
 *
[5,1,6,2,5]
 */
public class QuickSort {

	public static void main(String[] args) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = bf.readLine();

		if (str == null || str.length() == 0) {
			System.out.println("输入不合法");
		} else {
			String subStr = str.substring(1, str.length() - 1);
			String[] strArr = subStr.split(",");
			int[] resultIntArr = new int[strArr.length];
			for (int i = 0; i < strArr.length; i++) {
				resultIntArr[i] = Integer.parseInt(strArr[i]);
			}

			resultIntArr = sort(resultIntArr);

			StringBuffer strBuffer = new StringBuffer();
			strBuffer.append("[");
			for (int i = 0; i < resultIntArr.length; i++) {
				strBuffer.append(resultIntArr[i]);
				if (i < resultIntArr.length - 1) {
					strBuffer.append(",");
				}
			}
			strBuffer.append("]");

			System.out.println(strBuffer.toString());
		}
	}
	
	public static void quickSort2(int[] arr,int left,int right) {
	     if ( left < right) {
	         //只有当left < right的时候，排序才有意义
	         int i = left;//辅助左指针
	         int j = right;//辅助右
	         int pivot = arr[i]; // 枢轴，将所有小于pivot的数放在它左边，所有大于pivot的数放在它右边
	         while (i < j) {
	             //当 i == j 时，循环才结束
	             while (i < j && arr[j] >= pivot) {
	                 //从后往前找，找到比pivot小的数，或者 i == j
	                 j--;
	             }
	             if (i < j) {//如果退出上个循环后，i仍小于j，说明，找到了符合条件的数
	                 arr[i] = arr[j];
	                 //令i位置上的数等于j位置上的数
	                 //由于交换数据太耗费时间，所以用这种方法代替
	                 //最开始的i位置上的数已经通过pivot保存起来，此时为无效数据，可以用来保存j上的数据
	                 //而赋值完成后，j上的数据为无效数据，在下一个循环中会被修改
	             }
	             while(i < j && arr[i] <= pivot) {
	                 //此时再从前往后找，找到比pivot大的数，或者 i == j
	                 i++;
	             }
	             if (i < j) {
	                 //此时代表找到了这么个数，可以进行赋值
	                 arr[j] = arr[i];
	             }
	         }
	         //当循环退出后，i == j,此时i,j在同一位置，且为无效数据
	         //修改该位置的数据为pivot，并利用递归完成其余两部分数据的排序
	         arr[i] = pivot;
	         quickSort2(arr,left, i - 1);//从中间位置往左
	         quickSort2(arr,i + 1,right);//从中间位置往右
	     }
	}

	public static int[] sort(int[] sourceArray) throws Exception {
		// 对 arr 进行拷贝，不改变参数内容
		int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

		return quickSort(arr, 0, arr.length - 1);
	}

	private static int[] quickSort(int[] arr, int left, int right) {
		if (left < right) {
			int partitionIndex = partition(arr, left, right);
			quickSort(arr, left, partitionIndex - 1);
			quickSort(arr, partitionIndex + 1, right);
		}
		return arr;
	}

	private static int partition(int[] arr, int left, int right) {
		// 设定基准值（pivot）
		int pivot = left;
		int index = pivot + 1;
		for (int i = index; i <= right; i++) {
			if (arr[i] < arr[pivot]) {
				swap(arr, i, index);
				index++;
			}
		}
		swap(arr, pivot, index - 1);
		return index - 1;
	}

	//交换两个数的值
	private static void swap(int[] A, int i, int j) {
	    if (i != j) {
	        A[i] ^= A[j];
	        A[j] ^= A[i];
	        A[i] ^= A[j];
	    }
	}

}
