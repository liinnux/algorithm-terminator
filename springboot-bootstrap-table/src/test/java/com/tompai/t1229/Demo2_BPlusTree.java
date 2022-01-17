/**
 * 
 */
package com.tompai.t1229;

import junit.framework.Assert;

/**
 * @author Administrator
 *
 */
public class Demo2_BPlusTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BPlusTree<Integer, String> bpt = new BPlusTree<Integer, String>(4);
		bpt.insert(0, "a");
		bpt.insert(1, "b");
		bpt.insert(2, "c");
		bpt.insert(3, "d");
		bpt.insert(4, "e");
		bpt.insert(5, "f");
		bpt.insert(6, "g");
		bpt.insert(7, "h");
		bpt.insert(8, "i");
		bpt.insert(9, "j");
		bpt.delete(1);
		bpt.delete(3);
		bpt.delete(5);
		bpt.delete(7);
		bpt.delete(9);
		Assert.assertEquals(bpt.search(0), "a");
		Assert.assertEquals(bpt.search(1), null);
		Assert.assertEquals(bpt.search(2), "c");
		Assert.assertEquals(bpt.search(3), null);
		Assert.assertEquals(bpt.search(4), "e");
		Assert.assertEquals(bpt.search(5), null);
		Assert.assertEquals(bpt.search(6), "g");
		Assert.assertEquals(bpt.search(7), null);
		Assert.assertEquals(bpt.search(8), "i");
		Assert.assertEquals(bpt.search(9), null);
		System.out.println(bpt.search(0));
		System.out.println(bpt.search(1));
		System.out.println(bpt.search(2));
		System.out.println(bpt.search(3));
		System.out.println(bpt.search(4));
		System.out.println(bpt.search(5));
		System.out.println(bpt.search(6));
		System.out.println(bpt.search(7));
		System.out.println(bpt.search(8));
		System.out.println(bpt.search(9));
	}

}
