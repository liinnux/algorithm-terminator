package com.tompai.linklist;

import java.util.ArrayList;
import java.util.List;

/*
输入两个递增的链表，单个链表的长度为n，合并这两个链表并使新链表中的节点仍然是递增排序的。
数据范围： 0 \le n \le 10000≤n≤1000，-1000 \le 节点值 \le 1000−1000≤节点值≤1000
要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)

如输入{1,3,5},{2,4,6}时，合并后的链表为{1,2,3,4,5,6}，所以对应的输出为{1,2,3,4,5,6}，转换过程如下图所示：
 */
public class MergeTwoSortList {

	class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ListNode Merge(ListNode list1, ListNode list2) {
		// list1 list2为空的情况
		if (list1 == null || list2 == null) {
			return list1 != null ? list1 : list2;
		}
		// 两个链表元素依次对比
		if (list1.val <= list2.val) {
			// 递归计算 list1.next, list2
			list1.next = Merge(list1.next, list2);
			return list1;
		} else {
			// 递归计算 list1, list2.next
			list2.next = Merge(list1, list2.next);
			return list2;
		}
	}
	//合并 k 个升序的链表并将结果作为一个升序的链表返回其头节点。
	public ListNode mergeKLists(List<ListNode> lists) {
        if(lists.size() == 0){
            return null;
        }
        ListNode head = null;
        for(int i =0;i<lists.size();i++){
            head = connectList(head,lists.get(i));
        }
        return head;
    }
    ListNode connectList(ListNode p1,ListNode p2){
        if(p1 == null || p2 == null){
            return p1 == null ? p2 : p1;
        }
        if(p1.val < p2.val){
            p1.next = connectList(p1.next,p2);
            return p1;
        }else{
            p2.next = connectList(p1,p2.next);
            return p2;
        }
    }

	
}
