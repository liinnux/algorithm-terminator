/**
 * 
 */
package com.tompai;

import java.util.Arrays;

/**
 * @author Administrator
 *
 */
public class Test {

	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	class Solution {
		public ListNode reverseBetween(ListNode head, int m, int n) {
			ListNode res = new ListNode(0);
			res.next = head;
			ListNode node = res;
			// 1.m之前的节点构成链表
			for (int i = 1; i < m; i++) {
				node = node.next;
			}
			// 待反转的起点
			ListNode nextHead = node.next;
			ListNode pre = null;
			ListNode next = null;
			// 2.反转m->n链表
			for (int i = m; i <= n; i++) {
				next = nextHead.next;
				nextHead.next = pre;
				pre = nextHead;
				nextHead = next;
			}
			// 3.改变指针指向
			node.next.next = next;
			node.next = pre;
			return res.next;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 0 < N <= 30000,0 < M < 5000
		 LinkedListCreator linkedListCreator = new LinkedListCreator();
		    Node node = linkedListCreator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5));
		    Node node2 = linkedListCreator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5));
		    Node node3 = linkedListCreator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5));
		    LinkedListReverser linkedListReverser = new LinkedListReverser();

		    Node res = linkedListReverser.reverserLinkedList(node);
		    Node res2 = linkedListReverser.reverserLinkedList2(node2);
		    Node res3 = linkedListReverser.reverserLinkedList3(node3);

		    linkedListCreator.printList(res);
		    linkedListCreator.printList(res2);
		    linkedListCreator.printList(res3);

		
	}

}

//Definition for singly-linked list.
/*
 * public class ListNode { int val; ListNode next; ListNode(int x) { val = x; }
 * }
 */
