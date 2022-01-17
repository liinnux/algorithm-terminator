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

public class Demo8_Sqrt1<K, V> {
	/*
	给定一个链表，删除链表的倒数第 n 个节点并返回链表的头指针
	例如，
	给出的链表为: 1\to 2\to 3\to 4\to 51→2→3→4→5, n= 2n=2.
	删除了链表的倒数第 nn 个节点之后,链表变为1\to 2\to 3\to 51→2→3→5.
	 */

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {
			{
				while (scanner.hasNext()) {

					String input = scanner.nextLine();
					int last = Integer.parseInt(input);
					System.out.println(sqrt2(last));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	//二分查找比a<=sqrt(x)<=b
	public static int sqrt(int x) {
		// write code here
		if (x <= 0)
			return x;
		long left = 1;
		long right = x;
		while (left < right) {
			long middle = (left + right) / 2;
			if (middle <= x / middle && (middle + 1) > x / (middle + 1)) {
				return (int) middle;
			} else if (middle < x / middle) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
		}
		return 0;
	}

	public static int sqrt2(int x) {
		if (x <= 0)
			return 0; //小于等于0 返回0
		int ans = 1;
		int num = 1;
		int i = 3;
		while (num + i <= x) {
			num += i;
			ans++; // 每加一个奇数，ans+1
			i += 2;
		}
		return ans;
	}
}
