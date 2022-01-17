/**
 * 
 */
package com.tompai.t1229;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

/**
 * @author Administrator
 * 大量数据中，统计出现次数最多的N个数据
 */
public class Demo1_MaxStatNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int N = 100;
		int[] arrs = new int[N];
		try {
		
			SecureRandom random = SecureRandom.getInstanceStrong();
			for (int i = 0; i < N; i++) {
				arrs[i] = random.nextInt(N % 101);
			}
		
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//int[] arrs= {1,2,1,3};
		Map<Integer, Integer> map = new TreeMap<>();
		for (int i = 0; i < arrs.length; i++) {
			map.merge(arrs[i], 1, (oldVal, newVal) -> oldVal + newVal);
		}
		treeMapSortByValue(map);
	}

	public static void treeMapSortByValue(Map<Integer, Integer> map) {

		List<Entry<Integer, Integer>> list = new ArrayList<Entry<Integer, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			/*// 升序排序
			public int compare(Entry<String, String> o1, Entry<String, String> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}*/

			// jiang序排序
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		for (Entry<Integer, Integer> e : list) {
			System.out.println(e.getKey() + ":" + e.getValue());
		}
	}

	public static void treeMapSortByValue2() {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("a", "dddd");
		map.put("d", "aaaa");
		map.put("b", "cccc");
		map.put("c", "bbbb");

		List<Entry<String, String>> list = new ArrayList<Entry<String, String>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
			/*// 升序排序
			public int compare(Entry<String, String> o1, Entry<String, String> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}*/

			// jiang序排序
			public int compare(Entry<String, String> o1, Entry<String, String> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		for (Entry<String, String> e : list) {
			System.out.println(e.getKey() + ":" + e.getValue());
		}
	}
}
