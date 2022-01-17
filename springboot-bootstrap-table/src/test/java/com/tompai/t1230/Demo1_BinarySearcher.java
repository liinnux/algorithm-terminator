/**
 * 
 */
package com.tompai.t1230;

/**
 * @author Administrator
 *
 */
public class Demo1_BinarySearcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String source = "klmnopabcdefghijqrst";
		String pattern = "zyz";

		int[] arr = { 1, 2, 5, 7, 8, 10 };

		int index = binarySearch(arr, 8);

		System.out.print(index);

	}

	public static int binarySearch(int[] arr, int value) {

		int low = 0; // 开始位置
		int high = arr.length - 1; // 结束 位置

		while (low <= high) {
			int middle = low + ((high - low) >> 1);

			if (value == arr[middle]) {
				return middle; //返回查询到的索引位置
			}

			if (value > arr[middle]) {
				low = middle + 1;
			}

			if (value < arr[middle]) {
				high = middle - 1;
			}
		}
		//上面循环完毕，说明未找到，返回-1
		return -1;
	}

}
