/**
 * 
 */
package com.tompai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Administrator
 *
 */
public class MapSortTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// treeMapReverseOrder();
		// treeMapSortByValue();
		 treeMapSortByValue();
	}
 
	public static void treeMapReverseOrder() {
		// 默认的TreeMap升序排列
		TreeMap<Integer, Integer> map1 = new TreeMap<Integer, Integer>();
		map1.put(1, 2);
		map1.put(2, 4);
		map1.put(7, 1);
		map1.put(5, 2);
		System.out.println("map1=" + map1);
 
		// TreeMap降序排列
		Map<Integer, Integer> map2 = new TreeMap<>((o1, o2) -> o2.compareTo(o1));
		map2.put(1, 2);
		map2.put(2, 4);
		map2.put(7, 1);
		map2.put(5, 2);
		System.out.println("Map2=" + map2);
	}
 
	public static void treeMapSortByValue() {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("a", "dddd");
		map.put("d", "aaaa");
		map.put("b", "cccc");
		map.put("c", "bbbb");
 
		List<Entry<String, String>> list = new ArrayList<Entry<String, String>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
			// 升序排序
			public int compare(Entry<String, String> o1, Entry<String, String> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		for (Entry<String, String> e : list) {
			System.out.println(e.getKey() + ":" + e.getValue());
		}
	}
 
	public static void mapSortByKey1() {
		Map<String, String> result = new HashMap<>();
		result.put("1", "aaa");
		result.put("7", "bbb");
		result.put("22", "ccc");
		Map<String, String> finalMap = new LinkedHashMap<>();
		// Sort a map and add to finalMap
		result.entrySet().stream().sorted(Map.Entry.<String, String>comparingByKey().reversed())
				.forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));
		System.out.println(finalMap);
	}
 
	public static void mapSortByKey2() {
		Map<String, Integer> codes = new HashMap<>();
		codes.put("United", 1);
		codes.put("Germany", 49);
		codes.put("France", 33);
		codes.put("China", 86);
		codes.put("Pakistan", 92);
 
		// 按照Map的键进行排序
		Map<String, Integer> sortedMap = codes
				.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors
				.toMap(
					Map.Entry::getKey, 
					Map.Entry::getValue, 
					(oldVal, newVal) -> oldVal, 
					LinkedHashMap::new));
 
		// 将排序后的Map打印
		sortedMap.entrySet().forEach(System.out::println);
	}
 
	public static void mapSortByValue1() {
		Map<String, String> result = new HashMap<>();
		result.put("13", "aaa");
		result.put("37", "bbb");
		result.put("22", "ccc");
		Map<String, String> finalMap = new LinkedHashMap<>();
		// Sort a map and add to finalMap
		result.entrySet()
		.stream()
		.sorted(
				Map.Entry.<String, String>comparingByValue().reversed())
				.forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));
 
		System.out.println(finalMap);
	}
 
	public static void mapSortByValue2() {
		Map<String, String> codes = new HashMap<>();
		codes.put("13", "aaa");
		codes.put("37", "fff");
		codes.put("22", "bbb");
		// Sort a map and add to finalMap
		Map<String, String> sortedMap = codes
				.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(Collectors
				.toMap(
					Map.Entry::getKey, 
					Map.Entry::getValue, 
					(oldVal, newVal) -> oldVal,LinkedHashMap::new));
 
		sortedMap.entrySet().forEach(System.out::println);
	}
 
	public static void mapMerge1() {
		String k = "key";
		HashMap<String, Integer> map = new HashMap<String, Integer>() {
			{
				put(k, 1);
			}
		};
		map.merge(k, 2, (oldVal, newVal) -> oldVal + newVal);
 
		map.entrySet().forEach(System.out::println);
	}
 
	public static void mapMerge2() {
		// 创建一个HashMap
		HashMap<String, Integer> prices = new HashMap<>();
		// 往 HashMap 插入映射
		prices.put("Shoes", 200);
		prices.put("Bag", 300);
		prices.put("Pant", 150);
		prices.put("Shirt", 10);
		System.out.println("HashMap: " + prices);
		int returnedValue = prices.merge("Shirt", 100, (oldValue, newValue) -> oldValue + newValue);
		System.out.println("Price of Shirt: " + returnedValue);
		// 输出更新后的 HashMap
		System.out.println("Updated HashMap: " + prices);
	}
 
	public static void mapMerge3() {
		// 创建一个 HashMap
		HashMap<String, String> countries = new HashMap<>();
		// 往HashMap插入映射项
		countries.put("Washington", "America");
		countries.put("Canberra", "Australia");
		countries.put("Madrid", "Spain");
		System.out.println("HashMap: " + countries);
		// 合并 key为 Washington的映射
		String returnedValue = countries.merge("Washington", "USA", (oldValue, newValue) -> oldValue + "/" + newValue);
		System.out.println("Washington: " + returnedValue);
		// 输出更新后的HashMap
		System.out.println("Updated HashMap: " + countries);
	}
}
