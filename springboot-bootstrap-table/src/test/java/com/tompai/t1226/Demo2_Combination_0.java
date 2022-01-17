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
public class Demo2_Combination_0 {
	public static void main(String[] args) {
		char[] chs = "abcde".toCharArray();
		comb(chs);
	}
	public static void comb(char[] chs) {
		int len = chs.length;
		int nbits = 1 << len;
		for (int i = 0; i < nbits; ++i) {
			int t;
			for (int j = 0; j < len; j++) {
				t = 1 << j;
				if ((t & i) != 0) { // 与运算，同为1时才会是1
					System.out.print(chs[j]);
				}
			}
			System.out.println();
		}
	}
}
