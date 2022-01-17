package com.tompai.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DoubleArrayTrie_Test {

	private DoubleArrayTrie dat = new DoubleArrayTrie();
	
	public static void main(String[] args) {

		DoubleArrayTrie_Test test = new DoubleArrayTrie_Test();
		test.testPrefixMatch();
		test.testFullMatch();
	}

	/**
	 * 检索key的前缀命中了词典中的哪些词<br>
	 * key的前缀有多个，所以有可能命中词典中的多个词
	 */
	public void testPrefixMatch() {
		List<String> list = new ArrayList<String>();
		list.add("阿胶");
		list.add("阿拉伯");
		list.add("阿拉伯人");
		list.add("埃及");
		// 所有词必须先排序
		Collections.sort(list);
		// 构建DoubleArrayTrie
		dat.build(list);
		String key = "阿拉伯人";
		// 检索key的前缀命中了词典中的哪些词
		List<Integer> rect = dat.commonPrefixSearch(key);
		for (int index : rect) {
			System.out.println("前缀  " + list.get(index) + " matched");
		}
		
		System.out.println("=================");
	}

	/**
	 * 检索key是否完全命中了词典中的某个词
	 */
	public void testFullMatch() {//
		
		List<String> list = new ArrayList<String>();
		list.add("阿胶");
		list.add("阿拉伯");
		list.add("阿拉伯人");
		list.add("埃及");
		// 所有词必须先排序
		Collections.sort(list);
		// 构建DoubleArrayTrie
		dat.build(list);
		String key = "阿拉";
		// 检索key是否完全命中了词典中的某个词
		int index = dat.exactMatchSearch(key);
		if (index >= 0) {
			System.out.println(key + " match " + list.get(index));
		} else {
			System.out.println(key + " not match any term");
		}
		key = "阿拉伯";
		index = dat.exactMatchSearch(key);
		if (index >= 0) {
			System.out.println(key + " match " + list.get(index));
		} else {
			System.out.println(key + " not match any term");
		}
		key = "阿拉伯人";
		index = dat.exactMatchSearch(key);
		if (index >= 0) {
			System.out.println(key + " match " + list.get(index));
		} else {
			System.out.println(key + " not match any term");
		}
		dat.clear();
		System.out.println("=================");
	}
}