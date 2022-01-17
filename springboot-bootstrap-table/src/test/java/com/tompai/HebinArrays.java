/**
 * 
 */
package com.tompai;

import java.util.Scanner;

/**
 * @author Administrator
 *
 */
public class HebinArrays {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try (Scanner sc = new Scanner(System.in)) {
			while (sc.hasNext()) {
				String line = sc.nextLine();
				line = line.trim();
				String[] numbers = line.split("\\D");
				if (numbers.length <= 0) {
					continue;
				} else {
					for(String s:numbers)
					System.out.print(s+" ");
				}
			}
		}
	}
}
