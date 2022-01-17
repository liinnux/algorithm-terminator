package com.tompai.t1236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Demo8_ListIsRing<K, V> {

	//判断给定的链表中是否有环。如果有环则返回true，否则返回false。
	//{3,2,0,-4},1
	

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {
			{
				while (scanner.hasNext()) {

					String input = scanner.nextLine();
					String[] ss = input.split("}");
					int last = Integer.parseInt(ss[1].split(",")[1]);
					System.out.println(last != -1);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	 public class ListNode {
		    int val;
		    ListNode next = null;
		 
		    ListNode(int val) {
		        this.val = val;
		    }
		}
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead==null || pHead.next==null) return null;
        ListNode fast = pHead;
        ListNode slow = pHead;
        while (fast!=null && fast.next!=null) {
            fast=fast.next.next;
            slow=slow.next;
            // 快慢相遇，说明成环
            if (fast==slow) {
                ListNode tmp = pHead;
                while (tmp!=slow) {
                    tmp=tmp.next;
                    slow=slow.next;
                }
                return tmp;
            }
        }
        return null;
    }
}
