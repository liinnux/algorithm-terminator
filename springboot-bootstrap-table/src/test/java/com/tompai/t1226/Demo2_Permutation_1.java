/**
 * 
 */
package com.tompai.t1226;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author Administrator
 * 
 */
public class Demo2_Permutation_1 {

	public static void Permutation(String str) {
		char[] arrays = str.toCharArray();
		permutation(arrays, 0, arrays.length);
	}

	// 方法参数 1数组  2起始位置  3数据长度
	private static void permutation(char[] arrays, int start, int length) {
		if (length == 1) //数据长度为1时，数组输出
		{
			System.out.println(Arrays.toString(arrays));
		}

		else {
			for (int i = start; i < start + length; i++) {

				swap(arrays, start, i);
				permutation(arrays, start + 1, length - 1);
				swap(arrays, start, i);//切记一定要换回，否则影响下次的循环交换。
			}
		}
	}

	private static void swap(char[] arrays, int start, int i) {
		// TODO Auto-generated method stub
		char temp = arrays[start];
		arrays[start] = arrays[i];
		arrays[i] = temp;
	}

	public static void main(String[] args) {

		Permutation("abc");
	}
}
