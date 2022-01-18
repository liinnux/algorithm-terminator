/**
 * 
 */
package com.tompai.lru;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**

LRU关键是看页面最后一次被使用到发生调度的时间长短;(最近最少使用:时间)
而LFU关键是看一定时间段内页面被使用的频率;(最近最不常用：频次)

设计LRU缓存结构
设计LRU(最近最少使用)缓存结构，该结构在构造时确定大小，假设大小为 k ，并有如下两个功能
1. set(key, value)：将记录(key, value)插入该结构
2. get(key)：返回key对应的value值

提示:
1.某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的，然后都会刷新缓存。
2.当缓存的大小超过k时，移除最不经常使用的记录。
3.输入一个二维数组与k，二维数组每一维有2个或者3个数字，第1个数字为opt，第2，3个数字为key，value
若opt=1，接下来两个整数key, value，表示set(key, value)
若opt=2，接下来一个整数key，表示get(key)，若key未出现过或已被移除，则返回-1
对于每个opt=2，输出一个答案
4.为了方便区分缓存里key与value，下面说明的缓存里key用""号包裹

要求：set和get操作复杂度均为 O(1)O(1)

输入：
[[1,1,1],[1,2,2],[1,3,2],[2,1],[1,4,4],[2,2]],3
返回值：[1,-1]
说明：
[1,1,1]，第一个1表示opt=1，要set(1,1)，即将(1,1)插入缓存，缓存是{"1"=1}
[1,2,2]，第一个1表示opt=1，要set(2,2)，即将(2,2)插入缓存，缓存是{"1"=1,"2"=2}
[1,3,2]，第一个1表示opt=1，要set(3,2)，即将(3,2)插入缓存，缓存是{"1"=1,"2"=2,"3"=2}
[2,1]，第一个2表示opt=2，要get(1)，返回是[1]，因为get(1)操作，缓存更新，缓存是{"2"=2,"3"=2,"1"=1}
[1,4,4]，第一个1表示opt=1，要set(4,4)，即将(4,4)插入缓存，但是缓存已经达到最大容量3，移除最不经常使用的{"2"=2}，插入{"4"=4}，缓存是{"3"=2,"1"=1,"4"=4}
[2,2]，第一个2表示opt=2，要get(2)，查找不到，返回是[1,-1]     
 */
public class LRUCahe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * lru design
	 * 
	 * @param operators int整型二维数组 the ops
	 * @param k         int整型 the k
	 * @return int整型一维数组
	 */
	public int[] LRU(int[][] operators, int k) {
		// write code here
		int resultLength = 0;
		for (int[] operator : operators) {
			if (operator[0] == 2) {
				resultLength++;
			}
		}
		int[] results = new int[resultLength];
		int index = 0;

		LruCache cache = new LruCache(k);
		//List<Integer> resultList = new LinkedList<>();
		for (int[] operator : operators) {
			switch (operator[0]) {
			case 1:
				//set
				cache.set(operator[1], operator[2]);
				break;
			case 2:
				//get
				Integer value = cache.get(operator[1]);
				results[index++] = value == null ? -1 : value;
			}
		}
		return results;
	}
}

class LruCache {
	private final int capacity;
	private final Node[] hashTable;
	private int nodeCount;
	private Node lruHead;
	private Node lruTail;
	private Node eliminateNode;

	LruCache(int capacity) {
		if (capacity < 1) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
		this.hashTable = new Node[(int) (capacity / 0.75)];
	}

	public Integer set(int key, int value) {
		int index = hashIndex(key);
		Node node = hashTable[index];
		while (node != null && node.key != key) {
			node = node.hashLink;
		}

		if (node != null) {
			int oldValue = node.value;
			node.value = value;
			touch(node);
			return oldValue;
		}

		if (eliminateNode == null) {
			node = new Node();
		} else {
			node = eliminateNode;
			eliminateNode = null;
		}
		node.key = key;
		node.value = value;
		node.hashLink = hashTable[index];
		hashTable[index] = node;

		if (nodeCount++ == 0) {
			lruHead = node;
			lruTail = node;
		} else {
			node.lruPrev = lruTail;
			lruTail.lruNext = node;
			lruTail = node;
		}

		if (nodeCount > capacity) {
			eliminate();
		}

		return null;
	}

	public Integer get(int key) {
		int index = hashIndex(key);
		Node node = hashTable[index];
		while (node != null && node.key != key) {
			node = node.hashLink;
		}
		if (node == null) {
			return null;
		}

		touch(node);

		return node.value;
	}

	//命中元素
	private void touch(Node node) {
		if (node.lruNext == null) {
			return;
		}

		if (node.lruPrev == null) {
			lruHead = node.lruNext;
			lruHead.lruPrev = null;
		} else {
			node.lruPrev.lruNext = node.lruNext;
			node.lruNext.lruPrev = node.lruPrev;
		}
		node.lruNext = null;
		node.lruPrev = lruTail;
		lruTail.lruNext = node;
		lruTail = node;
	}

	//删除元素
	private void eliminate() {
		Node node = lruHead;
		if (node == null) {
			return;
		}
		lruHead = node.lruNext;
		lruHead.lruPrev = null;
		node.lruNext = null;

		int index = hashIndex(node.key);
		Node curr = hashTable[index];
		Node last = null;
		while (curr != null && curr != node) {
			last = curr;
			curr = curr.hashLink;
		}

		if (last == null) {
			hashTable[index] = node.hashLink;
		} else {
			last.hashLink = node.hashLink;
		}

		node.hashLink = null;
		eliminateNode = node;
		nodeCount--;
	}

	private int hashIndex(int key) {
		return (key < 0 ? (key == Integer.MIN_VALUE ? 0 : -key) : key) % hashTable.length;
	}

	static class Node {
		int key;
		int value;
		Node hashLink;
		Node lruPrev;
		Node lruNext;
	}
}
