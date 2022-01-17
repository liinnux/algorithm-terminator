/**
 * 
 */
package com.tompai;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Administrator
 *
 */
public class MingRandomNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int num = sc.nextInt();
			Set<Integer> arrSets = new TreeSet<>();
			for (int i = 0; i < num; i++) {
				arrSets.add(sc.nextInt());
			}
			arrSets.forEach(o -> System.out.println(o));
		}
		sc.close();
	}

}
