/**
 * 
 */
package com.tompai.t1226;

import java.util.Arrays;

/**
 * @author Administrator
 * 
 *         字典序全排列 字符串的全排列 比如单词"too" 它的全排列是"oot","oto","too"
 *         1，从右端开始扫描，若出现前一个比后一个小，记录前一个的元素下表index 2,再找出index以后比该元素大的中的最小值的下标，(实现见
 *         下面的getMin方法) 3,index以后的元素实现反转（实现 见下面的reverse方法） 结束条件：前一个都比后一个大的情况
 * 
 */
public class Demo2_Permutation_0 {

	public static void main(String[] args) {
		String input = "abc";
		char[] c = input.toCharArray();
		Arrays.sort(c);
		getDictionary(c);
	}

	/**
	 * @param args
	 */
	static int getMin(char[] input, int index) {
		char min = input[index];
		int minIndex = index + 1;
		char result = 'z';
		for (int i = index + 1; i < input.length; i++) {
			if (input[i] > min && input[i] < result) {
				result = input[i];
				minIndex = i;
			}
		}
		return minIndex;
	}

	static void exchange(char[] input, int index, int minIndex) {
		char temp = input[index];
		input[index] = input[minIndex];
		input[minIndex] = temp;
	}

	static void reverse(char input[], int first, int end) {
		while (first < end) {
			exchange(input, first, end);
			first++;
			end--;
		}
	}

	static void getDictionary(char c[]) {
		System.out.println(new String(c));
		//boolean flag=true;
		int i = 0;
		while (true) {
			i = c.length - 1;
			for (; i > 0; i--) {
				if (c[i - 1] < c[i])
					break;
			}
			if (i == 0)
				break;
			int minIndex = getMin(c, i - 1);
			exchange(c, i - 1, minIndex);
			reverse(c, i, c.length - 1);
			System.out.println(new String(c));
		}

	}

}
