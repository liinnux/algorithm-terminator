/**
 * 
 */
package com.tompai.linklist;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author Administrator
判断给定的链表中是否有环。如果有环则返回true，否则返回false。


数据范围：链表长度 0 \le n \le 100000≤n≤10000，链表中任意节点的值满足 |val| <= 100000∣val∣<=100000
要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)

输入分为2部分，第一部分为链表，第二部分代表是否有环，然后将组成的head头结点传入到函数里面。-1代表无环，其它的数字代表有环，这些参数解释仅仅是为了方便读者自测调试。实际在编程时读入的是链表的头节点。

 */
public class JudgeListIncludeRing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//判断给定的链表中是否有环。如果有环则返回true，否则返回false。
	public boolean hasCycle(ListNode head) {
        ListNode pos = head;
        // 哈希表记录访问过的结点
        Set<ListNode> visited = new HashSet<ListNode>();
        while (pos != null) {
            // 判断结点是否被访问
            if (visited.contains(pos)) {
                return true;
            } else {
                // 结点记录添加到哈希表中
                visited.add(pos);
            }
            // 遍历
            pos = pos.next;
        }
        return false;
    }
	
	//给一个长度为n链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。
	public ListNode entryNodeOfLoop(ListNode pHead) {
        // 使用set来记录出现的结点
        HashSet<ListNode> set = new HashSet<>();
        while(pHead != null){
           // 当set中包含结点，说明第一次出现重复的结点，即环的入口结点
            if(set.contains(pHead)){
                return pHead;
            }
            // set中加入未重复的结点
            set.add(pHead);
            pHead = pHead.next;
        }
        return null;
    }
	
	//给一个长度为n链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。
	public ListNode entryNodeOfLoop2(ListNode pHead) {
     //快慢指针
     ListNode fastNode = pHead;
     ListNode slowNode = pHead;
     while(fastNode != null && fastNode.next != null){
    	 
    	 slowNode = slowNode.next;
         fastNode = fastNode.next.next;
         
         if(fastNode == slowNode){  //快慢指针相遇
             ListNode n = pHead;
             while(slowNode != n){
                 slowNode = slowNode.next;
                 n = n.next;
             }
             return slowNode;
         }
     }
     return null;
	}
	/*
	 输入两个无环的单向链表，找出它们的第一个公共结点，如果没有公共节点则返回空。
	 （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
	 */
	public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode l1 = pHead1, l2 = pHead2;
        while(l1 != l2){
            l1 = (l1==null)?pHead2:l1.next;
            l2 = (l2==null)?pHead1:l2.next;
        }
        return l1;
    }
	/*
	 * NC40 两个链表生成相加链表
	 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
	给定两个这种链表，请生成代表两个整数相加值的结果链表。
	 */
	public ListNode addInList (ListNode head1, ListNode head2) {
        // write code here
 
        LinkedList<Integer> list1 = new LinkedList<>(); //list1栈
        LinkedList<Integer> list2 = new LinkedList<>(); //list2栈
        putData(list1, head1); //入栈
        putData(list2, head2);
        ListNode newNode = null;
        ListNode head = null;
        int carry = 0; //标记进位
        while(!list1.isEmpty() || ! list2.isEmpty() || carry != 0) {
            int x = (list1.isEmpty()) ? 0 : list1.pop();  //依次从栈中取出
            int y = (list2.isEmpty()) ? 0 : list2.pop();
            int sum = x + y + carry; //与进位一起相加
            carry = sum / 10; //更新进位
            //将计算值放入节点
            newNode = new ListNode(sum % 10);
                        //更新下一个节点的指向
            newNode.next = head;
            head = newNode;
        }
        return head;
 
    }
    private void putData(LinkedList<Integer> s1,ListNode head1) {
        if (s1 == null) s1 = new LinkedList<>();
                //遍历节点将其插入栈中
        while(head1 != null) {
            s1.push(head1.val);
            head1 = head1.next;
        }
    }
    /*
     * NC40 两个链表生成相加链表
	 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
	给定两个这种链表，请生成代表两个整数相加值的结果链表。
	 */
    public ListNode addInList2 (ListNode head1, ListNode head2) {
        // 进行判空处理
        if(head1 == null)
            return head2;
        if(head2 == null){
            return head1;
        }
        // 反转h1链表
        head1 = reverse(head1);
        // 反转h2链表
        head2 = reverse(head2);
        // 创建新的链表头节点
        ListNode head = new ListNode(-1);
        ListNode nHead = head;
        // 记录进位的数值
        int tmp = 0;
        while(head1 != null || head2 != null){
            // val用来累加此时的数值（加数+加数+上一位的进位=当前总的数值）
            int val = tmp;
            // 当节点不为空的时候，则需要加上当前节点的值
            if (head1 != null) {
                val += head1.val;
                head1 = head1.next;
            }
            // 当节点不为空的时候，则需要加上当前节点的值
            if (head2 != null) {
                val += head2.val;
                head2 = head2.next;
            }
            // 求出进位
            tmp = val/10;
            // 进位后剩下的数值即为当前节点的数值
            nHead.next = new ListNode(val%10);
            // 下一个节点
            nHead = nHead.next;
 
        }
        // 最后当两条链表都加完的时候，进位不为0的时候，则需要再加上这个进位
        if(tmp > 0){
            nHead.next = new ListNode(tmp);
        }
        // 重新反转回来返回
        return reverse(head.next);
    }
 
    // 反转链表
    ListNode reverse(ListNode head){
        if(head == null)
            return head;
        ListNode cur = head;
        ListNode node = null;
        while(cur != null){
            ListNode tail = cur.next;
            cur.next = node;
            node = cur;
            cur = tail;
        }
        return node;
    }
}
