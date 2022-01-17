/**
 * 
 */
package com.tompai.t1236;

/**
 * @author Administrator
 *
 */
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo2_SortArray {
	/**
	 * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可 将给定数组排序
	 * 
	 * @param arr int整型一维数组 待排序的数组
	 * @return int整型一维数组
	 */
	public static void main(String[] args) throws IOException {
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

			Arrays.sort(resultIntArr);

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
}