/**
 * 
 */
package com.tompai.linklist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * 给定一个单链表的头结点pHead，长度为n，反转该链表后，返回新链表的表头。
 *
 */
public class ReverseLinkList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ReverseLinkList test=new ReverseLinkList();
		try {
			test.reverseList();
		} catch (IOException e) {
			// TODO Auto-generated catch blocka7
			e.printStackTrace();
		}

	}

	public void reverseList() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		while (true) {
			str = bf.readLine();
			if (str == null || str.length() == 0) {
				break;
			}
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			for (int i = str.length() - 2; i >= 1; i--) {
				sb.append(str.charAt(i));
			}
			sb.append("}");
			System.out.println(sb.toString());
		}
	}
	
	public void reversList2() {
		 // TODO Auto-generated method stub
        try (Scanner sc = new Scanner(System.in)) {
 
            while (sc.hasNext()) {
                String line = sc.nextLine();
                //去掉非数字字符
                String[] numbers = line.split("\\D");
                if (numbers.length <= 0) {
                    System.out.println("{}");
                }else {
                    List<String> reverseList = new ArrayList<>();
                    Collections.addAll(reverseList, numbers);
                    //反转
                    Collections.reverse(reverseList);
                    String tmpString = reverseList.toString();
                    int index = tmpString.lastIndexOf(',');
                    tmpString = tmpString.substring(1, index);
                    tmpString = "{" + tmpString;
                    tmpString += "}";
                    tmpString = tmpString.replaceAll("\\s+", "");
                    System.out.println(tmpString);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

	public ListNode ReverseList(ListNode head) {
		Stack<ListNode> stack = new Stack<>();
		//把链表节点全部摘掉放到栈中
		while (head != null) {
			stack.push(head);
			head = head.next;
		}
		if (stack.isEmpty())
			return null;
		ListNode node = stack.pop();
		ListNode dummy = node;
		//栈中的结点全部出栈，然后重新连成一个新的链表
		while (!stack.isEmpty()) {
			ListNode tempNode = stack.pop();
			node.next = tempNode;
			node = node.next;
		}
		//最后一个结点就是反转前的头结点，一定要让他的next
		//等于空，否则会构成环
		node.next = null;
		return dummy;
	}

	public ListNode ReverseList2(ListNode head) {
		//新链表
		ListNode newHead = null;
		while (head != null) {
			//先保存访问的节点的下一个节点，保存起来
			//留着下一步访问的
			ListNode temp = head.next;
			//每次访问的原链表节点都会成为新链表的头结点，
			//其实就是把新链表挂到访问的原链表节点的
			//后面就行了
			head.next = newHead;
			//更新新链表
			newHead = head;
			//重新赋值，继续访问
			head = temp;
		}
		//返回新链表
		return newHead;
	}

	public ListNode ReverseList3(ListNode head) {
		//终止条件
		if (head == null || head.next == null)
			return head;
		//保存当前节点的下一个结点
		ListNode next = head.next;
		//从当前节点的下一个结点开始递归调用
		ListNode reverse = ReverseList3(next);
		//reverse是反转之后的链表，因为函数reverseList
		// 表示的是对链表的反转，所以反转完之后next肯定
		// 是链表reverse的尾结点，然后我们再把当前节点
		//head挂到next节点的后面就完成了链表的反转。
		next.next = head;
		//这里head相当于变成了尾结点，尾结点都是为空的，
		//否则会构成环
		head.next = null;
		return reverse;
	}

	public ListNode ReverseList3_1(ListNode head) {
		return reverseListInt(head, null);
	}

	private ListNode reverseListInt(ListNode head, ListNode newHead) {
		if (head == null)
			return newHead;
		ListNode next = head.next;
		head.next = newHead;
		return reverseListInt(next, head);
	}

}

class ListNode {
	int val;
	ListNode next = null;

	ListNode(int val) {
		this.val = val;
	}
}
