/**
 * 
 */
package com.tompai.linklist;

import java.util.Stack;

/**
NC76 用两个栈实现队列
用两个栈来实现一个队列，使用n个元素来完成 n
 次在队列尾部插入整数(push)和n次在队列头部删除整数(pop)的功能。
 队列中的元素为int类型。保证操作合法，即保证pop操作时队列内已有元素。
 
 数据范围： n\le1000n≤1000 要求：存储n个元素的空间复杂度为 O(n)O(n) ，插入与删除的时间复杂度都是
 O(1)O(1)
 
 push操作就直接往stack1中push， 
 pop操作需要分类一下：如果stack2为空，
 那么需要将stack1中的数据转移到stack2中，
 然后在对stack2进行pop，如果stack2不为空，直接pop就ok。
 */
public class TwoStackToQueue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();

	public void push(int node) {
		stack1.push(node);
	}

	public int pop() {
		if (!stack2.isEmpty()) { // 当stack2不为空的时候，直接出栈
			return stack2.pop();
		} else { // 当stack2为空的时候，就把stack1里面的数据pop出来存在stack2中
			while (!stack1.isEmpty()) {
				int node = stack1.pop();
				stack2.push(node);
			}
			return stack2.pop();
		}
	}
}
