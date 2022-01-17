package com.tompai.t1224;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Demo2_NoDuplicateSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try (Scanner scanner = new Scanner(System.in)) {
			while (scanner.hasNext()) {
				String s = scanner.next();
				if (s.length() > 100 || s.length() < 1) {
					continue;
				}
				char[] c1 = s.toCharArray();

				Set<Character> s1 = new LinkedHashSet<Character>();
				for (int i = 0; i < c1.length; i++) {
					s1.add(c1[i]);
				}
				for (Character c : s1) {
					System.out.print(c);
				}
				System.out.println();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
