/**
 * 
 */
package com.tompai.t1224;

import java.util.Scanner;

/**
 * @author Administrator
 *
 */
public class Demo9_NumberTransform {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner=new Scanner(System.in);
		while(scanner.hasNext()) {
			
			String s=scanner.nextLine();
			
			int n=Integer.parseInt(s.substring(2), 16);
			System.out.println(n);
		}
	}

}
