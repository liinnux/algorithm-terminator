/**
 * 
 */
package com.tompai.lru;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator 设计LRU(最近最少使用)缓存结构，该结构在构造时确定大小，假设大小为 k ，并有如下两个功能 1.
 *         set(key, value)：将记录(key, value)插入该结构 2. get(key)：返回key对应的value值
 * 
 *         提示: 1.某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的，然后都会刷新缓存。
 *         2.当缓存的大小超过k时，移除最不经常使用的记录。
 *         3.输入一个二维数组与k，二维数组每一维有2个或者3个数字，第1个数字为opt，第2，3个数字为key，value
 *         若opt=1，接下来两个整数key, value，表示set(key, value)
 *         若opt=2，接下来一个整数key，表示get(key)，若key未出现过或已被移除，则返回-1 对于每个opt=2，输出一个答案
 *         4.为了方便区分缓存里key与value，下面说明的缓存里key用""号包裹
 * 
 *         要求：set和get操作复杂度均为 O(1)O(1) 输入：
 *         [[1,1,1],[1,2,2],[2,1],[1,3,3],[2,2],[1,4,4],[2,1],[2,3],[2,4]],2
 *         返回值：[1,-1,-1,3,4]
 */
public class LRUCahe_1 {

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
		List<Integer> list = new ArrayList<>();
		LRUListCache lru = new LRUListCache(k);
		for (int[] opt : operators) {
			if (opt[0] == 1) {
				lru.put(opt[1], opt[2]);
			} else {
				list.add(lru.get(opt[1]));
			}
		}
		int[] res = new int[list.size()];
		int i = 0;
		for (int val : list) {
			res[i++] = val;
		}
		return res;
	}
}

//设置LRU缓存结构
class LRUListCache {
	int cap;
	LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

	public LRUListCache(int capactity) {
		this.cap = capactity;
	}

	// 将key变为最近使用
	private void touch(int key) {
		int val = cache.get(key);
		//删除key，重新插入到队尾
		cache.remove(key);
		cache.put(key, val);
	}

	//获取值
	public int get(int key) {
		if (!cache.containsKey(key)) {
			return -1;
		}
		//将这个key变为最近使用的
		touch(key);
		return cache.get(key);
	}

	//存进值
	public void put(int key, int val) {
		if (cache.containsKey(key)) {
			cache.put(key, val);
			//设置为最近使用
			touch(key);
			return;
		}
		//超出缓存的大小
		if (cache.size() >= this.cap) {
			//拿到链表头部的key（其最久未使用的key）
			int oldstKet = cache.keySet().iterator().next();
			cache.remove(oldstKet);
		}
		//将新的key添加到链表尾部
		cache.put(key, val);
	}

}
