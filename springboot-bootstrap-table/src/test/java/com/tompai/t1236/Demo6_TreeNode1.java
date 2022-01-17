package com.tompai.t1236;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

public class Demo6_TreeNode1<K, V> {

	public class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;
	}

	/**
	 *
	 * @param root TreeNode类 the root of binary tree
	 * @return int整型二维数组
	 */
	private List<Integer> list = new ArrayList<>();

	public int[][] threeOrders(TreeNode root) {
		// write code here
		if (root == null) {
			return new int[3][0];
		}
		preOrder(root);
		inOrder(root);
		afterOrder(root);
		int[][] res = new int[3][list.size() / 3];
		int index = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < list.size() / 3; j++) {
				res[i][j] = list.get(index++);
			}
		}
		return res;
	}

	//前序
	public void preOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		list.add(root.val);
		preOrder(root.left);
		preOrder(root.right);
	}

	//中序
	public void inOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		inOrder(root.left);
		list.add(root.val);
		inOrder(root.right);
	}

	//后序
	public void afterOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		afterOrder(root.left);
		afterOrder(root.right);
		list.add(root.val);
	}
	
	//层序遍历
	 public ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {
	       ArrayList<ArrayList<Integer>> res = new ArrayList();
	       Queue<TreeNode> queue = new ArrayDeque();
	       if (root != null) {
	           queue.add(root);
	       }
	       while (!queue.isEmpty()) {
	           ArrayList<Integer> list = new ArrayList();
	           int n = queue.size();
	           for (int i=0; i<n; i++) {
	               TreeNode node = queue.poll();
	               list.add(node.val);
	               if (node.left != null) {
	                   queue.add(node.left);
	               }
	               if (node.right != null) {
	                   queue.add(node.right);
	               }
	           }
	           res.add(list);
	       }
	        return res;
	    }
}
