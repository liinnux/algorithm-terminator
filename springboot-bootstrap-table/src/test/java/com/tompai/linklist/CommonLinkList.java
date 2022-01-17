/**
 * 
 */
package com.tompai.linklist;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

/**
 * @author Administrator 将给出的链表中的节点每 k 个一组翻转，返回翻转后的链表 如果链表中的节点数不是 k
 *         的倍数，将最后剩下的节点保持原样 你不能更改节点中的值，只能更改节点本身。
 * 
 *         数据范围： \ 0 \le n \le 2000 0≤n≤2000 ， 1 \le k \le 20001≤k≤2000
 *         ，链表中每个元素都满足 0 \le val \le 10000≤val≤1000 要求空间复杂度 O(1)O(1)，时间复杂度
 *         O(n)O(n) 例如： 给定的链表是 1\to2\to3\to4\to51→2→3→4→5 对于 k = 2k=2 , 你应该返回
 *         2\to 1\to 4\to 3\to 52→1→4→3→5 对于 k = 3k=3 , 你应该返回 3\to2 \to1 \to
 *         4\to 53→2→1→4→5
 */
public class CommonLinkList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CommonLinkList test = new CommonLinkList();
		int[] arrs = { 0, 8, 9, 1, 2, 3, 4, 5 };
		int k = 2;
		ListNode listNode = test.arrayToListNode(arrs);
		test.printListNodes(listNode);
		System.out.println(" ");
		ListNode listNode2 = test.reverseKGroup(listNode, k);
		test.printListNodes(listNode2);
	}

	//用数组构造链表
	public ListNode arrayToListNode(int[] s) {
		ListNode root = new ListNode(s[0]);
		ListNode other = root;
		for (int i = 1; i < s.length; i++) {
			ListNode temp = new ListNode(s[i]);
			other.next = temp;
			other = temp;
		}
		return root;
	}

	//打印链表
	public List<Integer> printListNodes(ListNode node) {
		List<Integer> res = new ArrayList<>();

		if (node == null) {
			return res;
		}
		ArrayDeque<ListNode> nodes = new ArrayDeque<>();
		ListNode head = node;
		while (head != null) {
			nodes.push(head);
			head = head.next;
		}

		while (!nodes.isEmpty()) {
			int value = nodes.pollLast().val;
			System.out.print(value + " ");
			res.add(value);
		}
		return res;
	}

	/**
	 * NC53 删除链表的倒数第n个节点 给定一个链表，删除链表的倒数第 n 个节点并返回链表的头指针
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		// write code here
		int length = 0;
		ListNode p = head;
		ListNode q = head;
		// 获取链表的长度
		while (head != null) {
			length++;
			head = head.next;
		}
		if (length < 2) {
			return null;
		}
		// 特殊情况
		if (n == length) {
			return q.next;
		}
		int i = 0;
		while (p != null) {
			i++;
			if (i == length - n) {
				// 删除结点
				p.next = p.next.next;
			}
			p = p.next;
		}
		return q;
	}

	//链表中的节点每k个一组翻转
	public ListNode reverseKGroup2(ListNode head, int k) {
		// write code here
		if (head == null || head.next == null || k == 1)
			return head;
		int t = k - 1;
		ListNode p0 = null, p1 = head, p2 = head;
		while (p2 != null) {
			if (t != 0) {
				p2 = p2.next;
				t--;
			}
			if (t == 0 && p2 != null) {
				reverse(p1, p2);

				if (p0 == null) {
					head = p2;
				} else {
					p0.next = p2;
				}
				p0 = p1;
				p1 = p1.next;
				p2 = p1;
				t = k - 1;
			}
		}
		return head;
	}

	public void reverse(ListNode head, ListNode tail) {
		ListNode p1 = head, p2 = head.next, p = head, last = tail.next;
		while (p2 != last && p2 != null) {
			p1.next = p2.next;
			p2.next = p;
			p = p2;
			p2 = p1.next;
		}
	}

	public ListNode reverseKGroup(ListNode head, int k) {
		// write code here
		if (k <= 1 || head == null)
			return head;
		Deque<ListNode> st = new ArrayDeque<ListNode>(); //模拟栈
		ListNode result = new ListNode(0);
		ListNode now = result;
		int cnt = 0;
		while (true) {
			for (int i = 0; i < k; i++) { //将当前链表前k个存入栈中
				st.push(head);
				head = head.next;
				cnt++;
				if (head == null)
					break;
			}
			if (cnt == k) { //如果当前栈中有k个元素则一一取出存入链表
				while (!st.isEmpty()) {
					now.next = st.pop();
					now = now.next;
					now.next = null;
				}
			}
			if (head == null)
				break; //如果链表取完了跳出循环
			cnt = 0;
		}
		ListNode end = null;
		while (!st.isEmpty()) { //如果栈中还有剩下的就说明是最后的一块直接取栈底即可
			end = st.pop();
		}
		now.next = end;
		return result.next;
	}

	/*
	 链表内指定区间反转
	 描述
	将一个节点数为 size 链表 m 位置到 n 位置之间的区间反转，要求时间复杂度 O(n)O(n)，空间复杂度 O(1)O(1)。
	例如：
	给出的链表为 1\to 2 \to 3 \to 4 \to 5 \to NULL1→2→3→4→5→NULL, m=2,n=4m=2,n=4,
	返回 1\to 4\to 3\to 2\to 5\to NULL1→4→3→2→5→NULL.
	
	数据范围： 链表长度 0 < size \le 10000<size≤1000，0 < m \le n \le size0<m≤n≤size，链表中每个节点的值满足 |val| \le 1000∣val∣≤1000
	要求：时间复杂度 O(n)O(n) ，空间复杂度 O(n)O(n)
	进阶：时间复杂度 O(n)O(n)，空间复杂度 O(1)O(1)
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
		//设置虚拟头节点
		ListNode dummyNode = new ListNode(-1);
		dummyNode.next = head;
		ListNode pre = dummyNode;
		for (int i = 0; i < m - 1; i++) {
			pre = pre.next;
		}

		ListNode cur = pre.next;
		ListNode cur_next;
		for (int i = 0; i < n - m; i++) {
			cur_next = cur.next;
			cur.next = cur_next.next;
			cur_next.next = pre.next;
			pre.next = cur_next;
		}
		return dummyNode.next;
	}

	/*
	给出一个升序排序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素（重复的元素一个也不保留）。
	例如：
	给出的链表为1 \to 2\to 3\to 3\to 4\to 4\to51→2→3→3→4→4→5, 返回1\to 2\to51→2→5.
	给出的链表为1\to1 \to 1\to 2 \to 31→1→1→2→3, 返回2\to 32→3.
	 */
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null) {
			return null;
		}
		if (head.next != null && head.val == head.next.val) {//发现有重复值
			while (head.next != null && head.val == head.next.val) {
				head = head.next;//删除
			}
			return deleteDuplicates(head.next);//把与删除的那个结点相同的结点也进行删除
		}
		head.next = deleteDuplicates(head.next);//当没有发现重复值的情况，就一直进行递归操作
		return head;
	}

	/*删除给出链表中的重复元素（链表中元素从小到大有序），使链表中的所有元素都只出现一次（（重复的元素保留））
	例如：
	给出的链表为1\to1\to21→1→2,返回1 \to 21→2.
	给出的链表为1\to1\to 2 \to 3 \to 31→1→2→3→3,返回1\to 2 \to 31→2→3.*/
	public ListNode deleteDuplicates2(ListNode head) {
		ListNode cur = head;
		while (cur != null) {
			while (cur.next != null && cur.val == cur.next.val) {
				cur.next = cur.next.next;
			}
			cur = cur.next;
		}
		return head;
	}

	/*
	 给定一个单链表，请设定一个函数，将链表的奇数位节点和偶数位节点分别放在一起，重排后输出。
	注意是节点的编号而非节点的数值。
	 */
	public ListNode oddEvenList(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode evenHead = head.next;
		ListNode odd = head, even = evenHead;
		while (even != null && even.next != null) {
			odd.next = even.next;
			odd = odd.next;
			even.next = odd.next;
			even = even.next;
		}
		odd.next = evenHead;
		return head;
	}

	/*
	重排链表 要求使用原地算法，不能只改变节点内部的值，需要对实际的节点进行交换。
	输入：{1,2,3,4}
	返回值：{1,4,2,3}
	说明：给定head链表1->2->3->4, 重新排列为 1->4->2->3,会取head链表里面的值打印输出 1  
	 */
	public void reorderList(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return;
		}
		//找中点，链表分成两个
		ListNode slow = head;
		ListNode fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		ListNode newHead = slow.next;
		slow.next = null;

		//第二个链表倒置，反转后半部分链表
		newHead = reverseList(newHead);

		//链表节点依次连接
		while (newHead != null) {
			ListNode temp = newHead.next;
			newHead.next = head.next;
			head.next = newHead;
			head = newHead.next;
			newHead = temp;
		}
	}

	private ListNode reverseList(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode tail = head;
		head = head.next;
		tail.next = null;
		while (head != null) {
			ListNode temp = head.next;
			head.next = tail;
			tail = head;
			head = temp;
		}
		return tail;
	}

	public void reorderList2(ListNode head) {
		if (head == null)
			return;
		List<ListNode> list = new ArrayList<>();
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		int i = 0;
		int j = list.size() - 1;
		while (i < j) {
			list.get(i++).next = list.get(j);
			if (i == j)
				break;
			list.get(j--).next = list.get(i);
		}
		list.get(i).next = null;
	}

	/*链表中倒数最后k个结点
	输入一个长度为 n 的链表，设链表中的元素的值为 ai ，返回该链表中倒数第k个节点。
	如果该链表长度小于k，请返回一个长度为 0 的链表。
	其中蓝色部分为该链表的最后2个结点，所以返回倒数第2个结点（也即结点值为4的结点）即可，系统会打印后面所有的节点来比较。*/
	public ListNode findKthToTail(ListNode pHead, int k) {
		if (pHead == null || k == 0) {
			return null;
		}
		Stack<ListNode> stack = new Stack<>();
		//链表节点压栈
		while (pHead != null) {
			stack.push(pHead);
			pHead = pHead.next;
		}
		// 判断栈的元素是否小于k
		if (stack.size() < k) {
			return null;
		}
		//在出栈串成新的链表
		ListNode firstNode = stack.pop();
		while (--k > 0) {
			// 将出栈的元素重新连接成为链表
			ListNode temp = stack.pop();
			temp.next = firstNode;
			firstNode = temp;
		}
		return firstNode;
	}

	/*划分链表
	给出一个长度为 n 的单链表和一个值 x ，单链表的每一个值为 listi ，
	请返回一个链表的头结点，要求新链表中小于 x 的节点全部在大于等于 x 的节点左侧，
	并且两个部分之内的节点之间与原来的链表要保持相对顺序不变。*/
	public ListNode ListPartition(ListNode head, int x) {
		ListNode leftPtr = new ListNode(0);
		leftPtr.next = head;
		ListNode dummyHead = leftPtr;//辅助节点，用于输出结果
		while (leftPtr.next != null && leftPtr.next.val < x) {
			leftPtr = leftPtr.next;
		}
		//插入点的next
		ListNode nextPtr = leftPtr.next;
		//右指针，寻找需要前移的结点
		ListNode rightPtr = nextPtr;
		while (rightPtr != null && rightPtr.next != null) {
			//找到需要前移的结点，执行插入操作
			if (rightPtr.next.val < x) {
				leftPtr.next = rightPtr.next;
				rightPtr.next = rightPtr.next.next;
				leftPtr.next.next = nextPtr;
				leftPtr = leftPtr.next;
			} else {
				//右指针继续寻找
				rightPtr = rightPtr.next;
			}
		}
		return dummyHead.next;//返回结果
	}

	/*NC244 对链表进行插入排序
	对链表进行插入排序，
	从链表第一个元素开始可以视为部分已排序，每次操作从链表中移除一个元素，然后原地将移除的元素插入到已排好序的部分。*/
	public ListNode insertionSortList(ListNode head) {
		ListNode tag = head;
		ListNode tmp = new ListNode(Integer.MIN_VALUE);
		ListNode cur = head, pre = tmp, next = null;
		while (tag != null) {
			cur = tmp;
			while (cur != null && cur.val < tag.val) {
				pre = cur;
				cur = cur.next;
			}
			next = tag.next;
			tag.next = pre.next;
			pre.next = tag;
			tag = next;
		}
		return tmp.next;
	}
	/*NC207 排序奇升偶降链表
	给定一个奇数位升序，偶数位降序的链表，返回对其排序后的链表。
	题面解释：例如链表 1->3->2->2->3->1 是奇数位升序偶数位降序的链表，而 1->3->2->2->3->2 则不符合题目要求。*/

	public ListNode sortLinkedList(ListNode head) {
		// write code here
		ListNode oddCur = head;
		ListNode evenCur = head.next;
		ListNode oddHead = head, evenHead = head.next;
		while (oddCur.next != null && evenCur.next != null) {
			oddCur.next = evenCur.next;
			oddCur = oddCur.next;
			evenCur.next = oddCur.next;
			evenCur = evenCur.next;
		}
		//这里一定要拆开
		if (oddCur.next != null)
			oddCur.next = null;
		return merge(oddHead, reverse(evenHead));
	}

	public ListNode merge(ListNode head1, ListNode head2) {
		ListNode dummy = new ListNode(-1);
		ListNode cur = dummy;
		while (head1 != null && head2 != null) {
			if (head1.val < head2.val) {
				cur.next = head1;
				head1 = head1.next;
			} else {
				cur.next = head2;
				head2 = head2.next;
			}
			cur = cur.next;
		}
		if (head1 != null) {
			cur.next = head1;
		}
		if (head2 != null) {
			cur.next = head2;
		}
		return dummy.next;
	}

	public ListNode reverse(ListNode head) {
		ListNode pre = null;
		while (head != null) {
			ListNode tmp = head.next;
			head.next = pre;
			pre = head;
			head = tmp;
		}
		return pre;
	}

	/*NC70 单链表的排序
	给定一个节点数为n的无序单链表，对其按升序排序。*/
	public ListNode sortInList(ListNode head) {
		// write code here
		if (head == null || head.next == null) {
			return head;
		}
		ListNode slow = head;
		ListNode faster = head.next;
		while (faster != null && faster.next != null) {
			slow = slow.next;
			faster = faster.next.next;
		}
		ListNode mid = slow.next;
		slow.next = null;

		ListNode left = sortInList(head);
		ListNode right = sortInList(mid);
		ListNode tail = new ListNode(-1);
		ListNode temp = tail;
		while (left != null && right != null) {
			if (left.val >= right.val) {
				temp.next = right;
				right = right.next;
			} else {
				temp.next = left;

				left = left.next;

			}
			temp = temp.next;
		}
		if (temp != null)
			temp.next = (left != null) ? left : right;
		return tail.next;
	}

	/*NC211 旋转链表
	给定链表的头节点，旋转链表，将链表每个节点往右移动 k 个位置，原链表后 k 个位置的节点则依次移动到链表头。
	即，例如链表 ： 1->2->3->4->5 k=2 则返回链表 4->5->1->2->3*/
	public ListNode rotateLinkedList(ListNode head, int k) {
		if (head == null)
			return null;
		ListNode slow = head, fast = head, pref = head, pres = head;
		int len = 0;
		while (slow != null) {
			slow = slow.next;
			len++;
		}
		k %= len; // 长度处理
		slow = head;
		// 快慢指针找倒数第k个结点 记录快慢指针的前驱
		while (k-- > 0)
			fast = fast.next;
		while (fast != null) {
			pref = fast;
			pres = slow;
			fast = fast.next;
			slow = slow.next;
		}
		// 处理一下重新连接
		pres.next = null;
		pref.next = head;
		return slow;
	}
}
