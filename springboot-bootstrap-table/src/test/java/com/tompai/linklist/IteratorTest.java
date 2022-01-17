/**
 * 
 */
package com.tompai.linklist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 *
 */
public class IteratorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IteratorTest test = new IteratorTest();
		test.test03();
	}

	//Error,错误的方法
	public void test00() {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		for (String str : list) {
			if (str.equals("c")) {
				list.remove(str);
			}
		}
		System.out.println(list);
	}

	public void test01() {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("c");
		list.add("c");
		list.add("a");
		list.add("b");
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String str = iterator.next();
			if (str.equals("c")) {
				iterator.remove();
			}
		}
		System.out.println(list);
	}

	public void test02() {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("c");
		list.add("c");
		list.add("a");
		list.add("b");
		for (int i = 0; i < list.size(); i++) {
			String tmp = list.get(i);
			if (tmp.equals("c")) {
				list.remove(tmp);
				//i--;
			}else {
				list.add("d");
			}
		}
		System.out.println(list);
	}

	public void test03() {
		// TODO Auto-generated method stub
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "one");
		map.put(2, "two");
		map.put(3, "three");
		map.put(4, "four");
		map.put(5, "five");
		map.put(6, "six");
		map.put(7, "seven");
		map.put(8, "eight");
		map.put(5, "five");
		map.put(9, "nine");
		map.put(10, "ten");
		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, String> entry = it.next();
			int key = entry.getKey();
			if (key % 2 == 1) {
				System.out.println("delete this: " + key + " = " + key);
				//map.put(key, "奇数");   //ConcurrentModificationException  
				//map.remove(key);      //ConcurrentModificationException  
				it.remove(); //OK 
			}
		}
		//遍历当前的map；这种新的for循环无法修改map内容，因为不通过迭代器。  
		System.out.println("-------\n\t最终的map的元素遍历：");
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			int k = entry.getKey();
			String v = entry.getValue();
			if (k % 2 == 1) {
				System.out.println("delete this: " + k + " = " + v);
				//map.put(key, "奇数");   //ConcurrentModificationException  
				//map.remove(key);      //ConcurrentModificationException  
				map.remove(k); //OK 
			}
			
			System.out.println(k + " = " + v);
		}
	}
	
	public void test05(){
		String mapKey = "a";
        Map<String, Map<String, Integer>> maps = new ConcurrentHashMap<String,Map<String, Integer>>();
        Map<String, Integer> map = new HashMap<String,Integer>();
        map.put("aaaa", 1);
        map.put("bbbb", 2);
        map.put("cccc", 3);

        maps.put("a", map);

        if(maps.containsKey(mapKey)){
            Map<String, Integer> tmpMap = maps.get(mapKey);
            tmpMap.put("dddd", 4);
            maps.put(mapKey, tmpMap);
        }

        //遍历maps
        Iterator<String> it = maps.keySet().iterator();
        while(it.hasNext()){
            String mapsKey = it.next();
            Map<String, Integer> mapValue = maps.get(mapsKey);
            System.out.println(mapValue);
        }
	}

}
