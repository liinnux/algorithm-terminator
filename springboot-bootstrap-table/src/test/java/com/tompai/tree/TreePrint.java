/**
 * 
 */
package com.tompai.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Administrator
 *
 */
public class TreePrint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 3, 2, 1, 6, 0, 5, 4, 7, 9, 8 };
		TreeNode root = constructMaximumBinaryTree(nums);
		TreeNode head = constructTree(new Integer[] { 5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1 });
		show(root);
		show(head);
		LaywerTraversal(root);
		System.out.println();
		prePrint(root);
		System.out.println();
		midPrint(root);
		System.out.println();
		backPrint(root);
	}

	//前序遍历
	public static void prePrint(TreeNode head) {
		if (head == null) {
			return;
		}
		System.out.print(head.val + " ");
		prePrint(head.left);
		prePrint(head.right);
	}

	//中序遍历
	public static void midPrint(TreeNode head) {
		if (head == null) {
			return;
		}
		midPrint(head.left);
		System.out.print(head.val + " ");
		midPrint(head.right);
	}

	//后序遍历
	public static void backPrint(TreeNode head) {
		if (head == null) {
			return;
		}
		backPrint(head.left);
		backPrint(head.right);
		System.out.print(head.val + " ");
	}

	//层次遍历用到的是BFS思想
	public static void LaywerTraversal(TreeNode root) {
		if (root == null)
			return;
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		list.add(root);
		TreeNode currentNode;
		int index = 0;
		while (!list.isEmpty()) {
			currentNode = list.poll();
			System.out.print(currentNode.val + " ");
			if (currentNode.left != null) {
				list.add(currentNode.left);
			}
			if (currentNode.right != null) {
				list.add(currentNode.right);
			}
		}
	}

	//按之字形顺序打印二叉树
	public static ArrayList<ArrayList<Integer>> zigzagPrint(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		depth(pRoot, 1, list);
		for (int i = 0; i < list.size(); i++) {
			if (i % 2 != 0) {
				Collections.reverse(list.get(i));
			}
		}
		return list;
	}

	private static void depth(TreeNode root, int depth, ArrayList<ArrayList<Integer>> list) {
		if (root == null)
			return;
		if (depth > list.size()) {
			list.add(new ArrayList<Integer>());
		}
		list.get(depth - 1).add(root.val);
		depth(root.left, depth + 1, list);
		depth(root.right, depth + 1, list);
	}

	/*
	 给定节点数为 n 二叉树的前序遍历和中序遍历结果，请重建出该二叉树并返回它的头结点。
	例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建
	[1,2,4,7,3,5,6,8],[4,7,2,1,5,3,8,6]
	{1,2,3,4,#,5,6,#,7,#,#,8}
	 */
	public TreeNode reConstructBinaryTree(int[] pre, int[] vin) {
		if (pre.length == 0 || vin.length == 0) {
			return null;
		}
		TreeNode root = new TreeNode(pre[0]);
		// 在中序中找到前序的根
		for (int i = 0; i < vin.length; i++) {
			if (vin[i] == pre[0]) {
				// 左子树，注意 copyOfRange 函数，左闭右开
				root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(vin, 0, i));
				// 右子树，注意 copyOfRange 函数，左闭右开
				root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length),
						Arrays.copyOfRange(vin, i + 1, vin.length));
				break;
			}
		}
		return root;
	}

	public static TreeNode constructTree(Integer[] nums) {
		if (nums.length == 0)
			return new TreeNode(0);
		Deque<TreeNode> nodeQueue = new LinkedList<>();
		// 创建一个根节点
		TreeNode root = new TreeNode(nums[0]);
		nodeQueue.offer(root);
		TreeNode cur;
		// 记录当前行节点的数量（注意不一定是2的幂，而是上一行中非空节点的数量乘2）
		int lineNodeNum = 2;
		// 记录当前行中数字在数组中的开始位置
		int startIndex = 1;
		// 记录数组中剩余的元素的数量
		int restLength = nums.length - 1;

		while (restLength > 0) {
			// 只有最后一行可以不满，其余行必须是满的
			//            // 若输入的数组的数量是错误的，直接跳出程序
			//            if (restLength < lineNodeNum) {
			//                System.out.println("Wrong Input!");
			//                return new TreeNode(0);
			//            }
			for (int i = startIndex; i < startIndex + lineNodeNum; i = i + 2) {
				// 说明已经将nums中的数字用完，此时应停止遍历，并可以直接返回root
				if (i == nums.length)
					return root;
				cur = nodeQueue.poll();
				if (nums[i] != null) {
					cur.left = new TreeNode(nums[i]);
					nodeQueue.offer(cur.left);
				}
				// 同上，说明已经将nums中的数字用完，此时应停止遍历，并可以直接返回root
				if (i + 1 == nums.length) {
					return root;
				}
				if (nums[i + 1] != null) {
					cur.right = new TreeNode(nums[i + 1]);
					nodeQueue.offer(cur.right);
				}
			}
			startIndex += lineNodeNum;
			restLength -= lineNodeNum;
			lineNodeNum = nodeQueue.size() * 2;
		}

		return root;
	}

	public static TreeNode constructMaximumBinaryTree(int[] nums) {
		return buildTree(nums, 0, nums.length - 1);
	}

	public static TreeNode buildTree(int[] nums, int startIndex, int endIndex) {
		if (nums == null || startIndex > endIndex)
			return null;

		int maxNum = 0; //找出当前区段最大值所在位置maxIndex
		int maxIndex = startIndex;
		for (int i = startIndex; i <= endIndex; i++) {
			if (maxNum < nums[i]) {
				maxNum = nums[i];
				maxIndex = i;
			}
		}

		//maxNum=Arrays.stream(nums).max().getAsInt();

		//建立当前根节点并进行嵌套实现
		TreeNode root = new TreeNode(maxNum);
		root.left = buildTree(nums, startIndex, maxIndex - 1);
		root.right = buildTree(nums, maxIndex + 1, endIndex);
		return root;
	}

	// 用于获得树的层数
	public static int getTreeDepth(TreeNode root) {
		return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
	}

	private static void writeArray(TreeNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
		// 保证输入的树不为空
		if (currNode == null)
			return;
		// 先将当前节点保存到二维数组中
		res[rowIndex][columnIndex] = String.valueOf(currNode.val);

		// 计算当前位于树的第几层
		int currLevel = ((rowIndex + 1) / 2);
		// 若到了最后一层，则返回
		if (currLevel == treeDepth)
			return;
		// 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
		int gap = treeDepth - currLevel - 1;

		// 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
		if (currNode.left != null) {
			res[rowIndex + 1][columnIndex - gap] = "/";
			writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
		}

		// 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
		if (currNode.right != null) {
			res[rowIndex + 1][columnIndex + gap] = "\\";
			writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
		}
	}

	//按照树形结构直观地打印出一棵二叉树
	public static void show(TreeNode root) {
		if (root == null)
			System.out.println("EMPTY!");
		// 得到树的深度
		int treeDepth = getTreeDepth(root);

		// 最后一行的宽度为2的（n - 1）次方乘3，再加1
		// 作为整个二维数组的宽度
		int arrayHeight = treeDepth * 2 - 1;
		int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
		// 用一个字符串数组来存储每个位置应显示的元素
		String[][] res = new String[arrayHeight][arrayWidth];
		// 对数组进行初始化，默认为一个空格
		for (int i = 0; i < arrayHeight; i++) {
			for (int j = 0; j < arrayWidth; j++) {
				res[i][j] = " ";
			}
		}

		// 从根节点开始，递归处理整个树
		// res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
		writeArray(root, 0, arrayWidth / 2, res, treeDepth);

		// 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
		for (String[] line : res) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < line.length; i++) {
				sb.append(line[i]);
				if (line[i].length() > 1 && i <= line.length - 1) {
					i += line[i].length() > 4 ? 2 : line[i].length() - 1;
				}
			}
			System.out.println(sb.toString());
		}
	}
}

class TreeNode {

	int val;
	TreeNode left;
	TreeNode right;

	public TreeNode(int x) {
		// TODO Auto-generated constructor stub
		val = x;
	}
}