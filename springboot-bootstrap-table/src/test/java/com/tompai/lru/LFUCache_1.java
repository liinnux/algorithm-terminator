/**
 * 
 */
package com.tompai.lru;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
构需要实现如下功能。 set(key, value)：将记录(key, value)插入该结构
get(key)：返回key对应的value值 但是缓存结构中最多放K条记录，如果新的第K+1条记录要加入，
就需要根据策略删掉一条记录，然后才能把新记录加入。
这个策略为：在缓存结构的K条记录中，哪一个key从进入缓存结构的时刻开始，被调用set或者get的次数最少，就删掉这个key的记录；
如果调用次数最少的key有多个，上次调用发生最早的key被删除 这就是LFU缓存替换算法。实现这个结构，K作为参数给出

数据范围：0 < k \le 10^50<k≤10 5 ，|val| \le 2 \times 10^9∣val∣≤2×10 9

要求：get和set的时间复杂度都是 O(logn)O(logn)，空间复杂度是 O(n)O(n)


若opt=1，接下来两个整数x, y，表示set(x, y)
若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1

对于每个操作2，返回一个答案
 */
public class LFUCache_1 {

	/**
LFU缓存结构设计
一个缓存结构需要实现如下功能。
set(key, value)：将记录(key, value)插入该结构
get(key)：返回key对应的value值
但是缓存结构中最多放K条记录，如果新的第K+1条记录要加入，就需要根据策略删掉一条记录，然后才能把新记录加入。这个策略为：在缓存结构的K条记录中，哪一个key从进入缓存结构的时刻开始，被调用set或者get的次数最少，就删掉这个key的记录；
如果调用次数最少的key有多个，上次调用发生最早的key被删除
这就是LFU缓存替换算法。

概念描述
简单来说，LFU缓存结构就是把数据按照访问的次数来进行排序，当缓存满的时候，则需要根据策略进行淘汰。

正常情况下，直接删除访问次数最少的那个数据；
当最少访问次数有多个不同的数据时，则删除最早没被访问的数据。
要想实现LFU缓存，其实并没有那么简单，需要注意的点很多，所以我们要想在面试过程中书写出来一个没有BUG的LFU缓存结构，就需要有一定的实现方向。从整体出发，再到具体实现。
那么现在就来带你一步一步实现LFU缓存结构。

算法实现
定义
先提取出我们需要注意的功能点：
1、容量有限。
2、需要通过key去访问。
3、对key操作时，key的访问次数会增加。
4、需要淘汰访问次数最少的那个key（有多个则淘汰掉最早没被访问的）。
5、题目要求 set和get方法的时间复杂度为O(1)。
所以，我们就可以根据上面的条件来定义出一些用到的属性了。
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 
若opt=1，接下来两个整数x, y，表示set(x, y)
若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1

对于每个操作2，返回一个答案
输入：
[[1,1,1],[1,2,2],[1,3,2],[1,2,4],[1,3,5],[2,2],[1,4,4],[2,1]],3
返回值：[4,-1]
说明：
在执行"1 4 4"后，"1 1 1"被删除。因此第二次询问的答案为-1
	 */
	public int[] LFU(int[][] operators, int k) {
		// write code here
		LFUcache_1 lru = new LFUcache_1(k);
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

class LFUcache_1 {
	int capacity; //限制缓存的最大容量
	HashMap<Integer,Integer> kv; //能够通过key获取到val
	HashMap<Integer,Integer> kt; //能够通过key获取访问的次数times
	/* 我们看到上面提出来的功能点4。
	我们需要淘汰times做少的key，所以我们需要有times到key的映射
	HashMap<Integer,Integer> tk;
	但是会有多个key的访问次数相同，并且此时我们需要淘汰那个最先放进去缓存中的key，
	这里我们可以利用LinkedHashSet，它会根据我们put进去的数据进行时间的排序。
	所以为了兼顾这两者我们定义出下面的属性tk。
	我们可以通过访问的次数times拿到所有访问次数为times的key的集合，这些key则会按照放进去的时间先后顺序存储，这就满足了我们的淘汰策略。
	*/
	HashMap<Integer,LinkedHashSet<Integer>> tk;
	// 我们还需要记录最小的访问次数times，方便淘汰此略的使用
	int minTimes;

	public LFUcache_1(int capacity) {
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

	/*
increaseTimes(int key)
这个方法是LFU中的核心方法，是将key的访问次数+1。
我们将所需要涉及到的集合先列出来:

kt表。需要将原来的key,times的映射改为key,times+1 。
tk表。需要将key从原来的times,set中去除，加入到times+1,set中去。（这里的set表示，访问次数为times的所有的key的集合）。
	 */
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

	/*
removeMinTimes()
在一开始我们就有说到淘汰的策略，主要分为两种情况：
1、当最小的times中，只有一个key时，则直接淘汰
2、否则，则需要淘汰最早没被访问的key
	 */
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
