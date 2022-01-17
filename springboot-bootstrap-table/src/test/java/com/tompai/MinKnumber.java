package com.tompai;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class MinKnumber {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, -1, 0, 100, 34, -90, 390 };
		MinKnumber minKnumber = new MinKnumber();
		int[] result = minKnumber.getLeastNumbers_quickSelect(nums, 3);
		for (int i = 0; i < 3; i++) {
			System.out.print(result[i] + "-");
		}
	}

	public int[] getLeastNumbers_Queue(int[] arr, int k) {
		if (k == 0) {
			return new int[0];
		}
		// 使用一个最大堆（大顶堆）
		// Java 的 PriorityQueue 默认是小顶堆，添加 comparator 参数使其变成最大堆
		Queue<Integer> heap = new PriorityQueue<>(k, (i1, i2) -> Integer.compare(i2, i1));
		for (int e : arr) {
			// 当前数字小于堆顶元素才会入堆
			if (heap.isEmpty() || heap.size() < k || e < heap.peek()) {
				heap.offer(e);
			}
			if (heap.size() > k) {
				heap.poll(); // 删除堆顶最大元素
			}
		}
		// 将堆中的元素存入数组
		int[] res = new int[heap.size()];
		int j = 0;
		for (int e : heap) {
			res[j++] = e;
		}
		return res;
	}

	public int[] getLeastNumbers_treeBST(int[] arr, int k) {
		if (k == 0 || arr.length == 0) {
			return new int[0];
		}
		// TreeMap的key是数字, value是该数字的个数。
		// cnt表示当前map总共存了多少个数字。
		TreeMap<Integer, Integer> map = new TreeMap<>();
		int cnt = 0;
		for (int num : arr) {
			// 1. 遍历数组，若当前map中的数字个数小于k，则map中当前数字对应个数+1
			if (cnt < k) {
				map.put(num, map.getOrDefault(num, 0) + 1);
				cnt++;
				continue;
			}
			// 2. 否则，取出map中最大的Key（即最大的数字), 判断当前数字与map中最大数字的大小关系：
			//    若当前数字比map中最大的数字还大，就直接忽略；
			//    若当前数字比map中最大的数字小，则将当前数字加入map中，并将map中的最大数字的个数-1。
			Map.Entry<Integer, Integer> entry = map.lastEntry();
			if (entry.getKey() > num) {
				map.put(num, map.getOrDefault(num, 0) + 1);
				if (entry.getValue() == 1) {
					map.pollLastEntry();
				} else {
					map.put(entry.getKey(), entry.getValue() - 1);
				}
			}
		}
		// 最后返回map中的元素
		int[] res = new int[k];
		int idx = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int freq = entry.getValue();
			while (freq-- > 0) {
				res[idx++] = entry.getKey();
			}
		}
		return res;
	}

	public int[] getLeastNumbers_jishuSort(int[] arr, int k) {
		if (k == 0 || arr.length == 0) {
			return new int[0];
		}
		// 统计每个数字出现的次数
		int[] counter = new int[10001];
		for (int num : arr) {
			counter[num]++;
		}
		// 根据counter数组从头找出k个数作为返回结果
		int[] res = new int[k];
		int idx = 0;
		for (int num = 0; num < counter.length; num++) {
			while (counter[num]-- > 0 && idx < k) {
				res[idx++] = num;
			}
			if (idx == k) {
				break;
			}
		}
		return res;
	}

	public int[] getLeastNumbers_quickSelect(int[] arr, int k) {
		if (k == 0) {
			return new int[0];
		} else if (arr.length <= k) {
			return arr;
		}
		// 原地不断划分数组
		partitionArray(arr, 0, arr.length - 1, k);
		// 数组的前 k 个数此时就是最小的 k 个数，将其存入结果
		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	void partitionArray(int[] arr, int lo, int hi, int k) {
		// 做一次 partition 操作
		int m = partition(arr, lo, hi);
		// 此时数组前 m 个数，就是最小的 m 个数
		if (k == m) {
			// 正好找到最小的 k(m) 个数
			return;
		} else if (k < m) {
			// 最小的 k 个数一定在前 m 个数中，递归划分
			partitionArray(arr, lo, m - 1, k);
		} else {
			// 在右侧数组中寻找最小的 k-m 个数
			partitionArray(arr, m + 1, hi, k);
		}
	}

	// partition 函数和快速排序中相同，具体可参考快速排序相关的资料
	// 代码参考 Sedgewick 的《算法4》
	int partition(int[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		int v = a[lo];
		while (true) {
			while (a[++i] < v) {
				if (i == hi) {
					break;
				}
			}
			while (a[--j] > v) {
				if (j == lo) {
					break;
				}
			}
			if (i >= j) {
				break;
			}
			swap(a, i, j);
		}
		swap(a, lo, j);
		// a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
		return j;
	}

	void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
