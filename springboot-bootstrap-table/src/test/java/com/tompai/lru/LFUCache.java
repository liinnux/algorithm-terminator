/**
 * 
 */
package com.tompai.lru;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
NC94 设计LFU缓存结构
一个缓存结构需要实现如下功能。
set(key, value)：将记录(key, value)插入该结构
get(key)：返回key对应的value值
但是缓存结构中最多放K条记录，如果新的第K+1条记录要加入，就需要根据策略删掉一条记录，然后才能把新记录加入。
这个策略为：在缓存结构的K条记录中，哪一个key从进入缓存结构的时刻开始，被调用set或者get的次数最少，就删掉这个key的记录；
如果调用次数最少的key有多个，上次调用发生最早的key被删除
这就是LFU缓存替换算法。实现这个结构，K作为参数给出

若opt=1，接下来两个整数x, y，表示set(x, y)
若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1

对于每个操作2，返回一个答案

输入：
[[1,1,1],[1,2,2],[1,3,2],[1,2,4],[1,3,5],[2,2],[1,4,4],[2,1]],3
返回值：[4,-1]
说明：
在执行"1 4 4"后，"1 1 1"被删除。因此第二次询问的答案为-1   
 */
public class LFUCache {

	/**
LFU算法：least frequently used，最近最不经常使用算法；
如果一个数据在最近一段时间很少被访问到，那么可以认为在将来它被访问的可能性也很小。因此，当空间满时，最小频率访问的数据最先被淘汰。

set(key,value)：将记录(key,value)插入该结构。当缓存满时，将访问频率最低的数据置换掉。
get(key)：返回key对应的value值。
一般会维护两个数据结构：

哈希：用来提供对外部的访问，查询效率更高；
双向链表或队列：维护了对元素访问次数的排序
缓存操作导致的链表变化：

添加新元素：新元素访问次数为1，放到队尾；
缓存淘汰：从队尾开始淘汰，因为队尾元素的访问次数最少；
访问缓存：访问缓存会增加元素的访问次数，所以元素在队列或双向链表中的位置会重新排序
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	class LFUCache_2{
        class Node{
            int k;
            int val;
            int freq;
            public Node(){}
            public Node(int k, int val, int freq){
                this.k = k;
                this.val = val;
                this.freq = freq;
            }
        }
        private int capacity;
        private Map<Integer, Node> map = new HashMap<>();
        private Map<Integer, LinkedList<Node>> freqMap = new HashMap<>();
        private int minFreq;
        public LFUCache_2(int capacity){
            this.capacity = capacity;
            this.minFreq = 1;
        }
        public void update(Node node){
            LinkedList<Node> list = freqMap.get(node.freq);
            list.remove(node);
            if (list.isEmpty() && node.freq == minFreq){
                minFreq++;
            }
            node.freq++;
            if (!freqMap.containsKey(node.freq)){
                freqMap.put(node.freq, new LinkedList<>());
            }
            freqMap.get(node.freq).addLast(node);
            
        }
        public int get(int k){
            if (!map.containsKey(k)){
                return -1;
            }
            Node node = map.get(k);
            update(node);
            return node.val;
        }
        public void put(int k, int val){
            if (map.containsKey(k)){
                Node node = map.get(k);
                node.val = val;
                update(node);
                map.put(k, node);
                return;
            }
            if (map.size() == capacity){
                Node node = freqMap.get(minFreq).removeFirst();
                map.remove(node.k);
            }
            Node node = new Node(k, val, 1);
            map.put(k, node);
            if (!freqMap.containsKey(1)){
                freqMap.put(1, new LinkedList<>());
            }
            freqMap.get(1).addLast(node);
            minFreq = 1;
        }
    }
      
    public int[] LFU (int[][] operators, int k) {
        // write code here
    	LFUCache_2 cache = new LFUCache_2(k);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < operators.length; i++){
            if (operators[i][0] == 1){
                cache.put(operators[i][1], operators[i][2]);
            }else{
                res.add(cache.get(operators[i][1]));
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < ans.length; i++){
            ans[i] = res.get(i);
        }
        return ans;
    }
}
