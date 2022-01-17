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
public class ReverseList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (Scanner sc = new Scanner(System.in)) {

			while (sc.hasNext()) {
				String line = sc.nextLine();
				//去掉非数字字符
				String[] numbers = line.split("\\D");
				if (numbers.length <= 0) {
					System.out.println("{}");
				}else {
					List<String> reverseList = new ArrayList<>();
					Collections.addAll(reverseList, numbers);
					//反转
					Collections.reverse(reverseList);
					String tmpString = reverseList.toString();
					int index = tmpString.lastIndexOf(',');
					tmpString = tmpString.substring(1, index);
					tmpString = "{" + tmpString;
					tmpString += "}";
					tmpString = tmpString.replaceAll("\\s+", "");
					System.out.println(tmpString);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
