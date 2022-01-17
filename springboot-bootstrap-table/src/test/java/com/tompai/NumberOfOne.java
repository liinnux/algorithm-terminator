/**
 * 
 */
package com.tompai;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Administrator
 *
 */
public class NumberOfOne {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (Scanner sc = new Scanner(System.in)) {

			while (sc.hasNext()) {
				int num = sc.nextInt();
				//去掉非数字字符
				System.out.println(NumberOf1(num));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	public static int NumberOf1(int n) {
        int count = 0;
        int flag = 1;
        while (n != 0 ) {
            count++;
            n = (n - 1) & n;
        }
        return count;
    }

}
