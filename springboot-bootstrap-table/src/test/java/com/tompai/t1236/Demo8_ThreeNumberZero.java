package com.tompai.t1236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Demo8_ThreeNumberZero<K, V> {
	/*
	给出一个有n个元素的数组S，S中是否有元素a,b,c满足a+b+c=0？找出数组S中所有满足条件的三元组。
	
	[-2,0,1,1,2]
	 */

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {
			{
				while (scanner.hasNext()) {

					String input = scanner.nextLine();
					input = input.substring(1, input.length() - 1);
					String[] res = input.split(",", -1);
					int[] arrs = new int[res.length];
					for (int i = 0; i < res.length; i++) {
						arrs[i] = Integer.parseInt(res[i]);
					}
					System.out.println(threeSum(arrs));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	//二分查找比a<=sqrt(x)<=b
	public static ArrayList<ArrayList<Integer>> threeSum(int[] num) {
		Arrays.sort(num);
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		for (int k = 0; k < num.length; k++) {
			if (num[k] > 0)
				break;
			if (k > 0 && num[k] == num[k - 1])
				continue;
			int i = k + 1;
			int j = num.length - 1;
			while (i < j) {
				int sum = num[k] + num[i] + num[j];
				if (sum > 0) {
					while (i < j && num[j] == num[--j])
						;
				} else if (sum < 0) {
					while (i < j && num[i] == num[++i])
						;
				} else {
					list.add(new ArrayList(Arrays.asList(num[k], num[i], num[j])));
					while (i < j && num[i] == num[++i])
						;
					while (i < j && num[j] == num[--j])
						;
				}
			}
		}
		return list;
	}
}
