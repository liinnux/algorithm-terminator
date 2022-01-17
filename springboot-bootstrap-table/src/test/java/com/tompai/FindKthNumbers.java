/**
 * 
 */
package com.tompai;

import java.util.PriorityQueue;

/**
 * @author Administrator
 *
 */
public class FindKthNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[]{4, 1, 8, 2, 5, 9, 6, 12, 3};
		//找第2大的数
		int k = 1;
        int length=arr.length;
        //int s=findKth(arr,length,k);
        int s=findKthByQueue(arr,length,k);
        System.out.println("s="+s);
	}

	public static int findKth(int[] a, int n, int K) {
		return findKth(a, 0, n - 1, K);
	}

	public static int findKth(int[] a, int low, int high, int k) {
		int part = partation(a, low, high);
		if (k == part - low + 1)
			return a[part];
		else if (k > part - low + 1)
			return findKth(a, part + 1, high, k - part + low - 1);
		else
			return findKth(a, low, part - 1, k);
	}

	public static int partation(int[] a, int low, int high) {
		int key = a[low];
		while (low < high) {
			while (low < high && a[high] <= key)
				high--;
			a[low] = a[high];
			while (low < high && a[low] >= key)
				low++;
			a[high] = a[low];
		}
		a[low] = key;
		return low;
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
				minHeap.remove();
				minHeap.add(t);
			}
		}
		return minHeap.element();
	}
}
