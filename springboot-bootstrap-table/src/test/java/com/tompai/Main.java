/**
 * 
 */
package com.tompai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author Administrator
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (Scanner scan = new Scanner(System.in);) {
			while (scan.hasNext()) {
				String line = scan.nextLine();
				//[5,2,3,1,4]
				line = line.trim();
				String[] numbers = line.split(",");
				if (numbers.length <= 0) {
					System.err.println("[]");
					continue;
				} else {
					List<Integer> inputLists = new ArrayList<>();
					for (String s : numbers) {
						//System.out.println(s);
						s = fixNumber(s);
						if (s.length() > 0) {
							//System.out.println(s);
							inputLists.add(Integer.parseInt(s));
						}
					}
					int size = inputLists.size();
					//int sum = inputLists.get(size - 1);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}

	}

	/**
	 * 输入去掉多余字符
	 * @param s
	 * @return
	 */
	private static String fixNumber(String s) {
		if (s.contains("[")) {
			s = s.replace("[", "");
		}
		if (s.contains("]")) {
			s = s.replace("]", "");
		}
		return s;
	}
}
