/**
 * 
 */
package com.tompai.lru;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map.Entry;

/**
 * @author Administrator 一个缓存结构需要实现如下功能。 set(key, value)：将记录(key, value)插入该结构
 *         get(key)：返回key对应的value值 但是缓存结构中最多放K条记录，如果新的第K+1条记录要加入，
 *         就需要根据策略删掉一条记录，然后才能把新记录加入。
 *         这个策略为：在缓存结构的K条记录中，哪一个key从进入缓存结构的时刻开始，被调用set或者get的次数最少，就删掉这个key的记录；
 *         如果调用次数最少的key有多个，上次调用发生最早的key被删除 这就是LFU缓存替换算法。实现这个结构，K作为参数给出
 * 
 *         数据范围：0 < k \le 10^50<k≤10 5 ，|val| \le 2 \times 10^9∣val∣≤2×10 9
 * 
 *         要求：get和set的时间复杂度都是 O(logn)O(logn)，空间复杂度是 O(n)O(n)
 * 
 * 
 *         若opt=1，接下来两个整数x, y，表示set(x, y)
 *         若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1
 * 
 *         对于每个操作2，返回一个答案
 */
public class LFUCache2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[] LFU(int[][] operators, int k) {
		// write code here
		LFUcache lru = new LFUcache(k);
		int len = 0;
		for (int i = 0; i < operators.length; i++) {
			if (operators[i][0] == 2)
				len++;
		}
		// 返回数组
		int[] res = new int[len];
		len = 0;
		for (int i = 0; i < operators.length; i++) {
			if (operators[i][0] == 1) {
				lru.set(operators[i][1], operators[i][2]);
			} else {
				res[len++] = lru.get(operators[i][1]);
			}
		}
		return res;
	}
}

class LFUcache {
	int capacity;
	HashMap<Integer, Integer> kv;
	HashMap<Integer, Integer> kt;
	HashMap<Integer, LinkedHashSet<Integer>> tk;
	int minTimes;

	public LFUcache(int capacity) {
		this.kv = new HashMap<>();
		this.kt = new HashMap<>();
		this.tk = new HashMap<>();
		this.minTimes = 0; //刚开始的次数为0
		this.capacity = capacity;
	}

	public int get(int key) {
		if (!kv.containsKey(key)) {
			return -1;
		}
		// 该key访问次数要加1
		increaseTimes(key);
		return kv.get(key);
	}

	public void set(int key, int value) {
		// 容量不合法
		if (this.capacity <= 0)
			return;

		/* key存在 */
		// 1.覆盖value
		if (kv.containsKey(key)) {
			kv.put(key, value);
			//2.访问次数+1
			increaseTimes(key);
			return;
		}

		/* key不存在 */
		// 1、cache满了
		if (this.capacity <= kv.size()) {
			// 淘汰Times最小的
			removeMinTimes();
		}

		// 2、cache没满
		// 先插入kv表
		kv.put(key, value);
		// kt表 首次插入，访问次数为1
		kt.put(key, 1);
		// 插入tk表
		// 当times为1的所有的key的集合不存在时，就新建一个
		tk.putIfAbsent(1, new LinkedHashSet<Integer>());
		// 否则，则直接插入
		tk.get(1).add(key);
		// 插入后此时最小的times肯定就是1了
		this.minTimes = 1;
	}

	private void increaseTimes(int key) {
		// 拿到原来的times
		int times = kt.get(key);
		// 更新kt表
		kt.put(key, times + 1);

		// 更新tk表
		// 将key从原来的times,set中去除
		tk.get(times).remove(key);
		// 将key加入到times+1,set中去
		// 但是还需要考虑times+1对应的set集合是否存在
		tk.putIfAbsent(times + 1, new LinkedHashSet<Integer>());
		tk.get(times + 1).add(key);
		// 此时基本已经完成了，但是我们还需要做一些细节处理
		// 当我们从原来的set集合中移除的时候，我们得考虑一下这个key是否为其最后一个
		if (tk.get(times).isEmpty()) {
			// 如果是的话，则我们需要销毁对应的set集合
			tk.remove(times);
			// 如果times刚好为最小的，则需要更新minTimes
			if (times == this.minTimes) {
				this.minTimes++;
			}
		}

	}

	private void removeMinTimes() {
		// 获取最小的times的set集合
		LinkedHashSet<Integer> set = tk.get(this.minTimes);

		// 这里是直接将两个步骤结合起来，利用了LinkedHashSet这个数据结构底层的设计
		// LinkedHashSet底层是按照key放进去的时间先后进行排序的
		// 所以此时set集合中的第一个即为需要删除的key
		int delKey = set.iterator().next();

		// 移除掉
		set.remove(delKey);
		if (set.isEmpty()) {
			tk.remove(this.minTimes);
		}
		// 更新kv表
		kv.remove(delKey);
		// 更新tk表
		tk.remove(delKey);

	}
}
