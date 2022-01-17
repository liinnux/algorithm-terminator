/**
 * 
 */
package com.tompai.t1226;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class Demo1_ReverseString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String string="abcdefa";
		
		List<Character> list1=new ArrayList<Character>();
		char[] cs=string.toCharArray();
		for(Character c:cs) {
			list1.add(c);
		}
		
		Collections.rotate(list1, 3);
		
		StringBuffer sBuffer=new StringBuffer();
		for(Character c:list1) {
			sBuffer.append(c);
		}
		System.out.println(sBuffer.toString());
		System.out.println(Collections.frequency(list1, 'a'));
		System.out.println(string.contains("amef"));
		
	}

}
