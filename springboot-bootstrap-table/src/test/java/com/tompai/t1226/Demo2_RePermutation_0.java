/**
 * 
 */
package com.tompai.t1226;

import java.util.Arrays;

/**
 * 可重复全排列
 * @author Administrator
 */
public class Demo2_RePermutation_0 {
	public static void main(String[] args) {
		char[] chs = { 'a', 'b', 'c' };
		per(new char[3], chs, 3 - 1);
	}

	public static void per(char[] buf, char[] chs, int len) {
		if (len == -1) {
			for (int i = buf.length - 1; i >= 0; --i)
				System.out.print(buf[i]);
			System.out.println();
			return;
		}
		for (int i = 0; i < chs.length; i++) {
			buf[len] = chs[i];
			per(buf, chs, len - 1);
		}
	}
}
