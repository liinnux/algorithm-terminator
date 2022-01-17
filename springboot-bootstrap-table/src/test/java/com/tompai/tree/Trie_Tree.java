package com.tompai.tree;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
public class Trie_Tree {
	// 根节点
	private Node root;
	public Trie_Tree() {
		root = new Node();
	}
	static class Node {
		// 当单词相同时,wordCount++,用于计算相同的单词个数
		private int dumpli_num;
		// 以该字串为前缀的字串数， 应该包括该字串本身！！！！！
		private int prefix_num;
		// 是否叶子节点
		private boolean isLeaf;
		// 子节点集合
		private List<Node> childs;
		Node() {
			dumpli_num = 0;
			prefix_num = 0;
			isLeaf = false;
			childs = Arrays.asList(new Node[26]);
		}
	}
	/**
	 * 插入字符串
	 */
	public void insert(String words) {
		insert(this.root, words);
	}
	/**
	 * 循环插入字符串
	 * 
	 * @param root
	 * @param word
	 */
	private void insert(Node root, String words) {
		// 此处不考虑大小写
		words = words.toLowerCase();
		char[] chrs = words.toCharArray();
		for (int i = 0; i < chrs.length; i++) {
			// 用相对于a字母的值作为下标索引，也隐式地记录了该字母的值
			int index = chrs[i] - 'a';
			if (root.childs.get(index) == null) {
				root.childs.set(index, new Node());
			}
			Node current = root.childs.get(index);
			current.prefix_num++;
			if (i == chrs.length - 1) {
				current.isLeaf = true;
				current.dumpli_num++;
			}
			root = current;
		}
	}
	/**
	 * 判断某个单词是否存在
	 * 
	 * @param word
	 * @return
	 */
	public boolean isExist(String word) {
		return this.search(this.root, word);
	}
	/**
	 * 判断某个单词是否存在
	 * 
	 * @param root
	 * @param word
	 * @return
	 */
	private boolean search(Node root, String word) {
		char[] chrs = word.toLowerCase().toCharArray();
		for (int i = 0; i < chrs.length; i++) {
			int index = chrs[i] - 'a';
			if (root.childs.get(index) == null)
				return false;
			root = root.childs.get(index);
		}
		return true;
	}
	/**
	 * 得到以某字串为前缀的字串集，包括字串本身！ 类似单词输入法的联想功能
	 * 
	 * @param prefix 前缀
	 * @return 字串集以及出现次数，如果不存在则返回null
	 */
	public HashMap<String, Integer> getWordsForPrefix(String prefix) {
		return getWordsForPrefix(this.root, prefix);
	}
	/**
	 * 得到以某字串为前缀的字串集，包括字串本身！
	 * 
	 * @param root
	 * @param prefix
	 * @return 字串集以及出现次数
	 */
	private HashMap<String, Integer> getWordsForPrefix(Node root, String prefix) {
		char[] chrs = prefix.toLowerCase().toCharArray();
		for (int i = 0, length = chrs.length; i < length; i++) {
			int index = chrs[i] - 'a';
			if (root.childs.get(index) == null) {
				return null;
			}
			root = root.childs.get(index);
		}
		/// 结果包括该前缀本身
		/// 此处利用之前的前序搜索方法进行搜索
		return preTraversal(root, prefix);
	}
	/**
	 * 前序遍历
	 * 
	 * @param root   根节点
	 * @param prefix 查询到该节点前所遍历过的前缀
	 * @return
	 */
	private HashMap<String, Integer> preTraversal(Node root, String prefix) {
		if (root == null)
			return null;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		// 如果root节点为子节点，说明此节点为单词
		if (root.isLeaf)
			map.put(prefix, root.dumpli_num);
		for (int i = 0; i < root.childs.size(); i++) {
			if (root.childs.get(i) != null) {
				char chr = (char) (i + 'a');
				String tempStr = prefix + chr;
				map.putAll(preTraversal(root.childs.get(i), tempStr));
			}
		}
		return map;
	}
	public static void main(String[] args) {
		
		
		Trie_Tree tree = new Trie_Tree();
		tree.insert("th");
		tree.insert("th");
		tree.insert("tha");
		tree.insert("thb");
		tree.insert("tha");
		tree.insert("tt");
		tree.insert("china");
		tree.insert("bbb");
		HashMap<String, Integer> map = tree.getWordsForPrefix("th");
		for (String key : map.keySet()) {
			System.out.println(key + "出现 " + map.get(key) + " 次");
		}
		System.out.println("是否存在tt:" + tree.isExist("tt"));
	}
}
