/**
 * 
 */
package com.tompai;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author Administrator
 *
 */
public class RetainAll {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Set<Integer> set1=new HashSet<>();
		ConcurrentSkipListSet<Integer> set3=new ConcurrentSkipListSet<>((o1,o2)->o2.compareTo(o1));
		
		ConcurrentSkipListMap<Integer, Integer> map1=new ConcurrentSkipListMap<>();
		
		Random random=new Random(System.currentTimeMillis());
		for(int i=0;i<100;i++) {
			set1.add(random.nextInt(100));
		}
		
		Set<Integer> set2=new TreeSet<>();
		for(int i=0;i<150;i++) {
			set2.add(random.nextInt(100));
		}
		
		for(int i=0;i<150;i++) {
			set3.add(random.nextInt(10));
		}
		//取交集
		set2.retainAll(set1);
		Iterator<Integer> iterator=set3.iterator();
		while(iterator.hasNext()) {
			int t=iterator.next();
			//System.out.print(t+" ");
			//iterator.remove();
		}
		
		Stack<Integer> stack=new Stack<>();
		
		for(int i=0;i<15;i++) {
			
			int t=random.nextInt(10);
			System.out.print(t+" ");
			stack.add(t);
		}
		
		System.out.println();
		/*Iterator<Integer> stackIterator=stack.iterator();
		while(stackIterator.hasNext()) {
			int t=stackIterator.next();
			System.out.print(t+" ");
		}*/
		do {
			System.out.print(stack.pop()+" ");
		} while (!stack.isEmpty());
		/*for(int i:set3) {
			System.out.print(i+" ");
		}*/
	}

}
