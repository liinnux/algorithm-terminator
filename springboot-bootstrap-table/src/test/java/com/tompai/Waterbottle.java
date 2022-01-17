/**
 * 
 */
package com.tompai;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @author Administrator
 *
 */
public class Waterbottle {

	/**
	 * @param args
	 */
		// TODO Auto-generated method stub
		public static void main(String[] args) {
			Scanner sc = new Scanner(new BufferedInputStream(System.in));
			while (sc.hasNext()) {//不能用true
				int countOriginal = sc.nextInt();
				if (countOriginal == 0) {
					break;
				}
	 
				System.out.println(countPing(countOriginal));
			}
		}
	 
		private static int countPing(int countOriginal) {
			int count = 0;
			while (countOriginal >= 3) {
				int temp = countOriginal/3;//计算商
				count += temp;//兑换的瓶数加上商
				int yu = countOriginal%3;//计算余数你
				countOriginal = yu+temp;//空瓶数=余数+商
			}
	 
			
			if (countOriginal == 2) {
				count += 1;
			}
	 
			return count;
		}


}
