/**
 * 
 */
package com.tompai.tree;

/**
 * @author Administrator
字典树又称为前缀树或者Trie树，是处理字符串常用的数据结构。
假设组成所有单词的字符仅是‘a’～‘z’，请实现字典树的结构，并包含以下四个主要的功能。
1. void insert(String word)：添加word，可重复添加；
2. void delete(String word)：删除word，如果word添加过多次，仅删除一次；
3. boolean search(String word)：查询word是否在字典树中出现过(完整的出现过，前缀式不算)；
4. int prefixNumber(String pre)：返回以字符串pre作为前缀的单词数量。

现在给定一个m，表示有m次操作，每次操作都为以上四种操作之一。
每次操作会给定一个整数op和一个字符串word，op代表一个操作码，如果op为1，则代表添加word，
op为2则代表删除word，op为3则代表查询word是否在字典树中，op为4代表返回以word为前缀的单词数量（数据保证不会删除不存在的word）。

对于每次操作，如果op为3时，如果word在字典树中，请输出“YES”，否则输出“NO”；如果op为4时，请输出返回以word为前缀的单词数量，其它情况不输出。
数据范围：操作数满足 0\le m \le 10^50≤m≤105，字符串长度都满足 0 \le n \le 200≤n≤20
进阶：所有操作的时间复杂度都满足 O(n)O(n)
 */
class Trie1Tree {
	//构建字典树节点
	class TrieNode {
		//child数组记录所有子节点
		TrieNode[] child;
		//pre_number表示插入单词时，当前节点被访问次数
		int pre_number;
		//end表示当前节点是否是某个单词的末尾
		boolean end;

		TrieNode() {
			child = new TrieNode[26];
			pre_number = 0;
			end = false;
		}
	}

	Trie1Tree() {
	}

	//初始化根节点
	TrieNode root = new TrieNode();

	//添加单词
	void insert(String word) {
		TrieNode node = root;
		char[] arr = word.toCharArray();
		for (char c : arr) {
			//如果子节点不存在，则新建
			if (node.child[c - 'a'] == null) {
				node.child[c - 'a'] = new TrieNode();
			}
			//往子节点方向移动
			node = node.child[c - 'a'];
			node.pre_number++;
		}
		node.end = true;
	}

	void delete(String word) {
		TrieNode node = root;
		char[] arr = word.toCharArray();
		for (char c : arr) {
			//往子节点方向移动，将访问次数减一
			node = node.child[c - 'a'];
			node.pre_number--;
		}
		//如果访问次数为0，说明不存在该单词为前缀的单词，以及该单词
		if (node.pre_number == 0) {
			node.end = false;
		}
	}

	boolean search(String word) {
		TrieNode node = root;
		char[] arr = word.toCharArray();
		for (char c : arr) {
			//如果子节点不存在，说明不存在该单词
			if (node.child[c - 'a'] == null) {
				return false;
			}
			node = node.child[c - 'a'];
		}

		//如果前面的节点都存在，并且该节点末尾标识为true，则存在该单词
		return node.end;
	}

	int prefixNumber(String pre) {
		TrieNode node = root;
		char[] arr = pre.toCharArray();
		for (char c : arr) {
			//如果子节点不存在，说明不存在该前缀
			if (node.child[c - 'a'] == null) {
				return 0;
			}
			node = node.child[c - 'a'];
		}

		//返回以该单词为前缀的数量
		return node.pre_number;
	}
}

public class TrieTree2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String[] trieU(String[][] operators) {
		//计算结果集长度，并进行初始化
		int len = 0;
		for (String[] opera : operators) {
			if (opera[0].equals("3") || opera[0].equals("4")) {
				len++;
			}
		}
		String[] res = new String[len];
		Trie1Tree trie = new Trie1Tree();
		int id = 0;

		for (String[] opera : operators) {
			if (opera[0].equals("1")) {
				//添加单词
				trie.insert(opera[1]);
			} else if (opera[0].equals("2")) {
				//删除单词
				trie.delete(opera[1]);
			} else if (opera[0].equals("3")) {
				//查询单词是否存在
				res[id++] = trie.search(opera[1]) ? "YES" : "NO";
			} else if (opera[0].equals("4")) {
				//查找以word为前缀的单词数量
				String preNumber = String.valueOf(trie.prefixNumber(opera[1]));
				res[id++] = preNumber;
			}
		}
		return res;
	}
}
