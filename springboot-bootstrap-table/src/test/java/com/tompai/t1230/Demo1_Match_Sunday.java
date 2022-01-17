/**
 * 
 */
package com.tompai.t1230;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class Demo1_Match_Sunday {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//https://blog.csdn.net/qq_33515733/article/details/81163135
		/*String source = "klmnopabcdefghijqrst";
		String pattern = "mnop";*/
		
		String source = "baaaabaaaabaaaabaaaaa";
		String pattern = "aaaaa";
		
		Sunday(source, pattern);
		System.out.println();
	}

	public static void Sunday(String dest, String pattern) {

		char[] destchars = dest.toCharArray();
		char[] patternchars = pattern.toCharArray();

		int i = 0;
		int j = 0;

		while (i <= (dest.length() - pattern.length() + j)) {
			if (destchars[i] != patternchars[j]) {
				if (i == (dest.length() - pattern.length() + j)) {
					break;
				}
				int pos = contains(patternchars, destchars[i + pattern.length() - j]);
				if (pos == -1) {
					i = i + pattern.length() + 1 - j;
					j = 0;
				} else {
					i = i + pattern.length() - pos - j;
					j = 0;
				}
			} else {
				if (j == (pattern.length() - 1)) {
					System.out.println("the start pos is " + (i - j) + " the end pos is " + i);
					i = i - j + 1;
					j = 0;
				} else {
					i++;
					j++;
				}
			}
		}
	}

	private static int contains(char[] chars, char target) {
		for (int i = chars.length - 1; i >= 0; i--) {
			if (chars[i] == target) {
				return i;
			}
		}
		return -1;
	}

}