/**
 * 
 */
package com.tompai;

import java.util.PriorityQueue;

/**
 * @author Administrator
 *
 */
public class FindKthByQueue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static int findKthByQueue(int[] a, int n, int K) {
		//小顶堆，默认容量为11
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(); 
		//大顶堆
		//PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((o1,o2)->o2.compareTo(o1));
		for(int i=0;i<K;i++) {
			minHeap.add(a[i]);
		}
		for(int i=K;i<n;i++) {
			int t=a[i];
			int m=minHeap.element();
			if (t>m) {
				minHeap.poll();
				minHeap.add(t);
			}
		}
		return minHeap.element();
	}
	//
	public static int findKth2(int[] a, int n, int K) {
		//小顶堆
	    PriorityQueue<Integer> queue = new PriorityQueue<>(K);
	    for (int num : a) {
	        if (queue.isEmpty() || num > queue.peek()) {
	            if (queue.size() >= K) {
	                queue.poll();
	            }
	            queue.add(num);
	        }
	    }
	    return queue.isEmpty() ? 0 : queue.peek();
	}
	
}
