/**
 * 
 */
package com.tompai.t1226;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Administrator
 */
public class Demo7_CoinChange_1 {

	public static int count = 0;
	//public static int[] ns = new int[4];
	public static List<int[]> list = new ArrayList<int[]>();

	public static void main(String[] args) {
		/*int[] arr = { 1,2,5,10 };
		int left = 0, right = 0;
		int sum = coinChange(arr, 100);
		System.out.println("sum=" + sum);*/
		/*for(int m=1;m<101;m++) {
			
		}*/
		int m=100;
		exchangeCoins(m, -1, -1, -1, -1);
		for (int i = 0; i < list.size(); i++) {
			for (int n : list.get(i)) {
				System.out.print(n + "\t");
			}
			System.out.println();
		}
		System.out.println(m+"-"+count);
		count=0;

		

	}

	/**
	 * 兑换硬币
	 * 
	 * @param x
	 * @param n10 10的数量
	 * @param n5  5的数量
	 * @param n2  2的数量
	 * @param n1  1的数量
	 */
	public static void exchangeCoins(int x, int n10, int n5, int n2, int n1) {

		if (n10 < 0) {
			for (int i = 0; i < x / 10 + 1; i++) {
				exchangeCoins(x - 10 * i, i, n5, n2, n1);
			}
		} else if (n5 < 0) {
			for (int i = 0; i < x / 5 + 1; i++) {
				exchangeCoins(x - 5 * i, n10, i, n2, n1);
			}
		} else if (n2 < 0) {
			for (int i = 0; i < x / 2 + 1; i++) {
				exchangeCoins(x - 2 * i, n10, n5, i, n1);
			}
		} else {
			n1 = x;
			int[] ns = new int[4];
			ns[0] = n1;
			ns[1] = n2;
			ns[2] = n5;
			ns[3] = n10;
			for (int i = 0; i < ns.length; i++) {
				if (ns[i] < 0) {
					ns[i] = 0;
				}
			}

			list.add(ns);
			count = count + 1;
		}

	}
}
