/**
 * 
 */
package com.tompai.linklist;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;

/**
 * @author Administrator
 *
 */
public class IncludeMinStack {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	private Stack<Integer> s1 = new Stack();
	// 存储最小元素数组
	private Stack<Integer> s2 = new Stack();

	public void push(int node) {
		s1.push(node);
		if (s2.isEmpty()) {
			s2.push(node);
		} else {
			s2.push(Math.min(s2.peek(), node));
		}
	}

	public void pop() {
		s1.pop();
		s2.pop();
	}

	public int top() {
		return s1.peek();
	}

	public int min() {
		return s2.peek();
	}

	
}
