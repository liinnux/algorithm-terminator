/**
 * 
 */
package com.tompai;

import java.util.Scanner;

/**
 * @author Administrator
 *
 */
public class CharSets {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String str = in.next();
			int[] A = new int[256];
			for (int i = 0; i < str.length(); i++) {
				char ch = str.charAt(i);
				A[ch]++;
			}
			String res = "";
			for (int i = 0; i < str.length(); i++) {
				char ch = str.charAt(i);
				if (A[ch] != 0) {
					res += ch;
					A[ch] = 0;
				}
			}
			System.out.println(res);
		}
	}
}
