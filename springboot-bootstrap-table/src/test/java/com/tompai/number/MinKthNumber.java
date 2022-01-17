/**
 * 
 */
package com.tompai.number;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Administrator 给定一个长度为 n 的可能有重复值的数组，找出其中不去重的最小的 k 个数。
 *         例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4(任意顺序皆可)。 数据范围：0\le k,n
 *         \le 100000≤k,n≤10000，数组中每个数的大小0 \le val \le 10000≤val≤1000 要求：空间复杂度
 *         O(n)O(n) ，时间复杂度 O(nlogn)O(nlogn)
 * 
 *         示例1 输入： [4,5,1,6,2,7,3,8],4 返回值： [1,2,3,4] 说明：
 *         返回最小的4个数即可，返回[1,3,2,4]也可以
 */
public class MinKthNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 4, 5, 1, 6, 2, 7, 3, 8 };
		int k = 4;

		MinKthNumber test = new MinKthNumber();

		test.findKth(a, k);
	}

	private ArrayList<Integer> findKth(int[] input, int k) {
		ArrayList<Integer> list = new ArrayList<>();
	    if (k > input.length || k == 0) {
	        return list;
	    }
		PriorityQueue<Integer> stack = new PriorityQueue<>((num1, num2) -> num1.compareTo(num2));

		for (int i = 0; i < input.length; i++) {

			stack.offer(input[i]);
			if (stack.size() > k) {
				if (stack.peek() > input[i]) {

					stack.poll();
				}
			}

		}
		for (int i = 0; i < k; i++) {
			int m = stack.poll();
			System.out.print(m + " ");
			list.add(m);
		}
		return list;
	}
}
