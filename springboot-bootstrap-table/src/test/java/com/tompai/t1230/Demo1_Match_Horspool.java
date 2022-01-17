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
public class Demo1_Match_Horspool {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*String source = "klmnopabcdefghijqrst";
		String pattern = "mnop";*/

		String source = "baaaabaaaabaaaabaaaaa";
		String pattern = "aaaaa";

		horspool(source, pattern);
		System.out.println();
	}

	public static List<Integer> horspool(String source, String pattern) {
		if (pattern.length() >= source.length() || pattern.length() == 0 || source.length() == 0)
			return null;
		List<Integer> startpos = new ArrayList<>();
		if (pattern.length() == 1) {
			char c = pattern.charAt(0);
			for (int i = 0; i < source.length(); i++) {
				if (source.charAt(i) == c)
					startpos.add(i);
			}
			return startpos;
		}

		char[] arrB = source.toCharArray();
		int i = pattern.length() - 1;
		int step = 0;
		
		while (i < source.length()) {
			step = moveLen(i, arrB, pattern);
			//System.out.println("i----" + i);
			//System.out.println("step---" + step);
			if (step == 0) {
				startpos.add(i - pattern.length() + 1);
				i++;
			} else {
				i = i + step;
			}
		}
		System.out.println("index---" + startpos);
		return startpos;
	}

	public static int moveLen(int i, char[] arrB, String s) {
		if (!s.contains(arrB[i] + ""))
			return s.length();
		else {
			if (s.charAt(s.length() - 1) != arrB[i]) {
				int j = s.length() - 1;
				while (j >= 0) {
					if (s.charAt(j) == arrB[i])
						return s.length() - 1 - j;
					else
						j--;
				}
			} else {
				if (!s.substring(0, s.length() - 1).contains(arrB[i] + "")) {
					for (int k = 1; k < s.length(); k++) {
						if (!(s.charAt(s.length() - 1 - k) == arrB[i - k]))
							return s.length();
						return 0;
					}
				} else {
					for (int k = 1; k < s.length(); k++) {
						if (!(s.charAt(s.length() - 1 - k) == arrB[i - k])) {
							int j = s.length() - 2;
							while (j >= 0) {
								if (s.charAt(j) == arrB[i])
									return s.length() - j;
								else
									j--;
							}
						}
						return 0;
					}

				}
			}

		}
		return -10000000;
	}

}