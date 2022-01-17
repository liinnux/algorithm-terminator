package com.tompai.t1236;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Demo5_LRUCache<K, V> {

	/**
	 * lru design
	 * 
	 * @param operators int整型二维数组 the ops
	 * @param k         int整型 the k
	 * @return int整型一维数组
	 */
	public int[] LRU(int[][] operators, int k) {
		// write code here
		LruCache cache = new LruCache(k);
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < operators.length; i++) {
			int op = operators[i][0];
			int key = operators[i][1];
			if (op == 1) {
				cache.set(key, operators[i][2]);
			}
			if (op == 2) {
				Node val = cache.get(key);
				result.add(val == null ? -1 : val.value);
			}
			int x = 1;
		}

		int[] ra = new int[result.size()];
		for (int j = 0; j < result.size(); j++) {
			ra[j] = result.get(j);
		}
		return ra;
	}

	public class LruCache {
		private final Node[] elements;
		private final LinkedList lruList;
		private final int cap;
		private int size = 0;

		public LruCache(int cap) {
			this.cap = cap;
			this.elements = new Node[cap];
			lruList = new LinkedList();
		}

		public void set(int key, int value) {
			int hash = hash(key);
			// 是否已存在？
			Node existedNode = get(key);
			if (existedNode != null) {
				existedNode.setValue(value);
				return;
			}

			// 是否需要淘汰
			boolean needRetire = false;
			if (size < cap) {
				size++;
			} else {
				needRetire = true;
			}

			Node head = elements[hash];
			Node node = new Node(key, value);
			node.setNext(head);
			elements[hash] = node;
			this.lruList.add(node.getLinkedNode());
			if (needRetire) {
				Node retireNode = this.lruList.tail.getVal();
				this.del(retireNode);
				this.lruList.retireTail();
			}
		}

		public Node get(int key) {
			int hash = hash(key);
			Node head = elements[hash];
			if (head == null) {
				return null;
			}

			Node cur = head;
			while (cur != null) {
				if (cur.key == key) {
					break;
				}
				cur = cur.next;
			}

			if (cur != null) {
				this.lruList.add(cur.getLinkedNode());
			}

			return cur;
		}

		private void del(Node node) {
			int key = node.getKey();
			int hash = hash(key);
			Node cur = elements[hash];
			if (cur == node) {
				elements[hash] = cur.getNext();
				cur.setNext(null);
				return;
			}

			Node prev = null;
			while (cur != null) {
				if (cur.key == key) {
					break;
				}
				prev = cur;
				cur = cur.next;
			}

			if (cur == null) {
				return;
			}

			if (prev != null) {
				prev.setNext(cur.getNext());
			}
			cur.setNext(null);
		}

		private int hash(int key) {
			return Math.abs(key) % cap;
		}

	}

	public static class LinkedList {

		private LinkedNode head = null;
		private LinkedNode tail = null;

		public LinkedList() {

		}

		public void add(LinkedNode nh) {
			if (head == null) {
				this.head = nh;
				this.tail = nh;
				return;
			}

			if (head == nh) {
				return;
			}

			// 自我从链表中删除
			del(nh);

			// 更新head
			nh.setNext(head);
			head.setPrev(nh);
			this.head = nh;
		}

		public void del(LinkedNode node) {
			if (node == tail) {
				retireTail();
			} else {
				node.delItself();
			}
		}

		public void retireTail() {
			LinkedNode nt = tail.getPrev();
			tail.delItself();
			nt.setNext(null);
			this.tail = nt;
		}

	}

	private static class Node {
		private int key;
		private int value;

		private Node next;
		private LinkedNode linkedNode;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
			this.linkedNode = new LinkedNode(this);
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public LinkedNode getLinkedNode() {
			return linkedNode;
		}

		public void setLinkedNode(LinkedNode linkedNode) {
			this.linkedNode = linkedNode;
		}

		public int getKey() {
			return key;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	private static class LinkedNode {
		private Node val;
		LinkedNode next;
		LinkedNode prev;

		public LinkedNode(Node val) {
			this.val = val;
		}

		public Node getVal() {
			return val;
		}

		public void setVal(Node val) {
			this.val = val;
		}

		public LinkedNode getNext() {
			return next;
		}

		public void setNext(LinkedNode next) {
			this.next = next;
		}

		public LinkedNode getPrev() {
			return prev;
		}

		public void setPrev(LinkedNode prev) {
			this.prev = prev;
		}

		public void delItself() {
			LinkedNode prev = getPrev();
			LinkedNode next = getNext();
			setPrev(null);
			setNext(null);
			if (prev != null) {
				prev.setNext(next);
			}
			if (next != null) {
				next.setPrev(prev);
			}
		}
	}

}