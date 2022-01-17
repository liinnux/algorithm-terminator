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

public class Demo7_ReversKArray<K, V> {

	//{1,2,3,4,5},2

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {
			{
				while (scanner.hasNext()) {

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 *
	 * @param head ListNode类
	 * @param k    int整型
	 * @return ListNode类
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
		// write code here
		if (head == null || head.next == null || k == 1)
			return head;
		int t = k - 1;
		ListNode p0 = null, p1 = head, p2 = head;
		while (p2 != null) {
			if (t != 0) {
				p2 = p2.next;
				t--;
			}
			if (t == 0 && p2 != null) {
				reverse(p1, p2);
				if (p0 == null) {
					head = p2;
				} else {
					p0.next = p2;
				}
				p0 = p1;
				p1 = p1.next;
				p2 = p1;
				t = k - 1;
			}
		}
		return head;
	}

	public void reverse(ListNode head, ListNode tail) {
		ListNode p1 = head, p2 = head.next, p = head, last = tail.next;
		while (p2 != last && p2 != null) {
			p1.next = p2.next;
			p2.next = p;
			p = p2;
			p2 = p1.next;
		}
	}

	public class ListNode {
		int val;
		ListNode next = null;
	}

}
