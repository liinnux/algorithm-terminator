/**
 * 
 */
package com.tompai.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * @author Administrator 给定一棵二叉树，分别按照二叉树先序，中序和后序打印所有的节点。
 * 
 *         数据范围：0 \le n \le 10000≤n≤1000，树上每个节点的val值满足 0 \le val \le
 *         1000≤val≤100 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n) 样例解释：
 * 
 */
public class Tree {

	class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		public TreeNode(int value) {
			this.val = value;
		}
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

	public void preOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		list.add(root.val);
		preOrder(root.left);
		preOrder(root.right);
	}
	/*NC193 二叉树的前序遍历
	给你二叉树的根节点 root ，返回它节点值的 前序 遍历。*/
	public int[] preorderTraversal (TreeNode root) {
		if(root == null){
			return new int[0];
		}

		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();

		TreeNode node = root;
		while(node != null || !stack.isEmpty()){
			while(node != null){
				list.add(node.val);
				stack.push(node);
				node = node.left;
			}

			if(!stack.isEmpty()){
				node = stack.pop();
				node = node.right;
			}
		}
		int[] arr = new int[list.size()];
		for(int i = 0; i < list.size();i++){
			arr[i] = list.get(i);
		}
		return arr;
	}

	public void inOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		inOrder(root.left);
		list.add(root.val);
		inOrder(root.right);
	}

	/*NC161 二叉树的中序遍历(左-中-右)
	给定一个二叉树的根节点root，返回它的中序遍历结果。*/
	public int[] inorderTraversal (TreeNode root) {
		// write code here
		List<Integer> list = new ArrayList<>();
		int[] ans = new int[list.size()];
		if(root == null) return ans;
		list = inOrder(root,list);
		int[] res = new int[list.size()];
		for(int i = 0;i < list.size();i++){
			res[i] = list.get(i);
		}
		return res;
	}
	private List<Integer> inOrder(TreeNode root,List<Integer> list){
		if(root == null) return null;
		inOrder(root.left,list);
		list.add(root.val);
		inOrder(root.right,list);
		return list;
	}

	public void afterOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		afterOrder(root.left);
		afterOrder(root.right);
		list.add(root.val);
	}

	/*NC192 二叉树的后序遍历(左-右-中)
	给定一个二叉树，返回他的后序遍历的序列。
	后序遍历是值按照 左节点->右节点->根节点 的顺序的遍历。*/
	public int[] afterOrderTraversal (TreeNode root) {
		// write code here
		if (root == null) {
			return new int[0];
		}
		List<Integer> list = new LinkedList<>();
		TreeNode pre = null;
		Stack<TreeNode> stack = new Stack<>();
		//设置一个前驱节点
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
				//如果没有到左子树最底下的点，继续入栈
			}
			//取出最后一个元素，就是我们的后序遍历的第一个元素
			root = stack.pop();
			if (root.right != null && root.right != pre) {
				stack.push(root);
				root = root.right;
				//我们这个点不空，然后且不是上一个节点，我们继续入栈
			} else {
				System.out.println(root.val);
				list.add(root.val);
				pre = root;
				root = null;
			}
		}
		return list.stream().mapToInt(Integer::intValue).toArray();
	}

	/**
	 * 给定一个二叉树，返回该二叉树层序遍历的结果，（从左到右，一层一层地遍历）
	 * 
	 * @param root TreeNode类
	 * @return int整型ArrayList<ArrayList<>>
	 */
	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> res = new ArrayList();
		Queue<TreeNode> queue = new ArrayDeque();
		if (root != null) {
			queue.add(root);
		}
		while (!queue.isEmpty()) {
			ArrayList<Integer> list = new ArrayList();
			int n = queue.size();
			for (int i = 0; i < n; i++) {
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

	/*把二叉树打印成多行:
	给定一个节点数为 n 二叉树，要求从上到下按层打印二叉树的 val 值，
	同一层结点从左至右输出，每一层输出一行，将输出的结果存放到一个二维数组中返回。*/
	ArrayList<ArrayList<Integer>> multilinePrint(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		if (pRoot == null) {
			return res;
		}
		// 队列每次只保存当前层的结点
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(pRoot);
		while (!queue.isEmpty()) {
			ArrayList<Integer> list = new ArrayList<>();
			// lo为每一层的左边结点指针，hi为右边结点指针
			int lo = 0, hi = queue.size();
			// 从左到右按顺序遍历当前层的每一个节点
			while (lo++ < hi) {
				TreeNode node = queue.poll();
				// 收集当前层每一个结点的值
				if (node != null) {
					list.add(node.val);
				}
				// 按顺序加入左右子节点，为下一层遍历做准备
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
			// 收集当前层的结果集
			res.add(list);
		}
		return res;
	}

	//给定一个二叉树，返回该二叉树的之字形层序遍历，（第一层从左向右，下一层从右向左，一直这样交替）
	public ArrayList<ArrayList<Integer>> zigzagPrint(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		depth(pRoot, 1, list);
		for (int i = 0; i < list.size(); i++) {
			if (i % 2 != 0) {
				Collections.reverse(list.get(i));
			}
		}
		return list;
	}

	private void depth(TreeNode root, int depth, ArrayList<ArrayList<Integer>> list) {
		if (root == null)
			return;
		if (depth > list.size()) {
			list.add(new ArrayList<Integer>());
		}
		list.get(depth - 1).add(root.val);
		depth(root.left, depth + 1, list);
		depth(root.right, depth + 1, list);
	}

	/*从下到上打印二叉树
	给定一棵二叉树，返回其自底向上的层序遍历。*/
	public int[][] levelOrderBottom(TreeNode root) {
		// write code here
		Deque<TreeNode> q = new LinkedList<>();
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		q.offer(root);
		TreeNode cur;
		int size;
		while (!q.isEmpty()) {
			size = q.size();
			ArrayList<Integer> list = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				cur = q.poll();
				list.add(cur.val);
				if (cur.left != null)
					q.offer(cur.left);
				if (cur.right != null)
					q.offer(cur.right);
			}
			ans.add(list);
		}
		int[][] res = new int[ans.size()][];
		for (int i = 0; i < ans.size(); i++) {
			ArrayList<Integer> l = ans.get(i);
			res[ans.size() - 1 - i] = new int[l.size()];
			for (int j = 0; j < l.size(); j++) {
				res[ans.size() - 1 - i][j] = l.get(j);
			}
		}
		return res;
	}

	//给定一棵二叉树(保证非空)以及这棵树上的两个节点对应的val值 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
	public int lowestCommonAncestor(TreeNode root, int o1, int o2) {
		return helper(root, o1, o2).val;
	}

	private TreeNode helper(TreeNode root, int o1, int o2) {
		if (root == null || root.val == o1 || root.val == o2)
			return root;
		TreeNode left = helper(root.left, o1, o2);
		TreeNode right = helper(root.right, o1, o2);
		//如果left为空，说明这两个节点在root结点的右子树上，我们只需要返回右子树查找的结果即可
		if (left == null)
			return right;
		//同上
		if (right == null)
			return left;
		//如果left和right都不为空，说明这两个节点一个在root的左子树上一个在root的右子树上，
		//我们只需要返回cur结点即可。
		return root;
	}

	/*
	给定一棵二叉树(保证非空)以及这棵树上的两个节点对应的val值 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
	数据范围：1 \le n \le 10001≤n≤1000，树上每个节点的val满足 0<val \le 1000<val≤100
	要求：时间复杂度 O(n)O(n)
	注：本题保证二叉树中每个节点的val值均不相同。
	 */
	public int lowestCommonAncestor2(TreeNode root, int o1, int o2) {
		//记录遍历到的每个节点的父节点。
		Map<Integer, Integer> parent = new HashMap<>();
		Queue<TreeNode> queue = new LinkedList<>();
		parent.put(root.val, Integer.MIN_VALUE);//根节点没有父节点，给他默认一个值
		queue.add(root);
		//直到两个节点都找到为止。
		while (!parent.containsKey(o1) || !parent.containsKey(o2)) {
			//队列是一边进一边出，这里poll方法是出队，
			TreeNode node = queue.poll();
			if (node.left != null) {
				//左子节点不为空，记录下他的父节点
				parent.put(node.left.val, node.val);
				//左子节点不为空，把它加入到队列中
				queue.add(node.left);
			}
			//右节点同上
			if (node.right != null) {
				parent.put(node.right.val, node.val);
				queue.add(node.right);
			}
		}
		Set<Integer> ancestors = new HashSet<>();
		//记录下o1和他的祖先节点，从o1节点开始一直到根节点。
		while (parent.containsKey(o1)) {
			ancestors.add(o1);
			o1 = parent.get(o1);
		}
		//查看o1和他的祖先节点是否包含o2节点，如果不包含再看是否包含o2的父节点……
		while (!ancestors.contains(o2))
			o2 = parent.get(o2);
		return o2;
	}

	//给定节点数为 n 二叉树的前序遍历和中序遍历结果，请重建出该二叉树并返回它的头结点。
	//例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建出如下图所示。
	public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		return dfs(0, 0, in.length - 1, pre, in);
	}

	public TreeNode dfs(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
		if (preStart > preorder.length - 1 || inStart > inEnd) {
			return null;
		}
		//创建结点
		TreeNode root = new TreeNode(preorder[preStart]);
		int index = 0;
		//找到当前节点root在中序遍历中的位置，然后再把数组分两半
		for (int i = inStart; i <= inEnd; i++) {
			if (inorder[i] == root.val) {
				index = i;
				break;
			}
		}
		root.left = dfs(preStart + 1, inStart, index - 1, preorder, inorder);
		root.right = dfs(preStart + index - inStart + 1, index + 1, inEnd, preorder, inorder);
		return root;
	}

	//给定节点数为 n 二叉树的前序遍历和中序遍历结果，请重建出该二叉树并返回它的头结点。
	public TreeNode reConstructBinaryTree2(int[] pre, int[] vin) {
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

	/*从中序与后序遍历序列构造二叉树
	给定一个二叉树的中序与后序遍历结果，请你根据两个序列构造符合这两个序列的二叉树。*/
	int[] postArray;
	HashMap<Integer, Integer> map = new HashMap<>();

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		// write code here
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		postArray = postorder;
		return build(0, inorder.length - 1, 0, postorder.length - 1);
	}

	public TreeNode build(int midOrderStart, int midOrderEnd, int postOrderStart, int postOrderEnd) {
		if (midOrderStart > midOrderEnd || postOrderStart > postOrderEnd) {
			return null;
		}
		int rootValue = postArray[postOrderEnd];
		int rootIndex = map.get(rootValue);
		TreeNode tree = new TreeNode(rootValue);
		tree.left = build(midOrderStart, midOrderStart + rootIndex - 1, postOrderStart,
				postOrderStart + rootIndex - midOrderStart - 1);
		tree.right = build(rootIndex + 1, midOrderEnd, postOrderStart + rootIndex - midOrderStart, postOrderEnd - 1);
		return tree;

	}

	/**
	 * 请根据二叉树的前序遍历，中序遍历恢复二叉树，并打印出二叉树的右视图
	 * 
	 * @param preOrder int整型一维数组 先序遍历
	 * @param midOrder int整型一维数组 中序遍历
	 * @return int整型一维数组
	 */
	public int[] rightSideView(int[] preOrder, int[] midOrder) {
		// write code here
		int len = preOrder.length;
		TreeNode root = buildTree(preOrder, 0, len - 1, midOrder, 0, len - 1);
		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
		List<Integer> list = new ArrayList<Integer>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; ++i) {
				TreeNode node = queue.poll();
				if (i == size - 1) {
					list.add(node.val);
				}
				if (node.left != null)
					queue.add(node.left);
				if (node.right != null)
					queue.add(node.right);
			}
		}
		int[] result = new int[list.size()];
		int index = 0;
		for (int num : list) {
			result[index++] = num;
		}
		return result;
	}

	private TreeNode buildTree(int[] preOrder, int startOfPre, int endOfPre, int[] midOrder, int startOfIn,
			int endOfIn) {
		if (startOfPre > endOfPre || startOfIn > endOfIn)
			return null;
		TreeNode root = new TreeNode(preOrder[startOfPre]);
		int index = startOfIn;
		while (index <= endOfIn && midOrder[index] != preOrder[startOfPre])
			index++;
		root.left = buildTree(preOrder, startOfPre + 1, index + startOfPre - startOfIn, midOrder, startOfIn, index - 1);
		root.right = buildTree(preOrder, index + startOfPre - startOfIn + 1, endOfPre, midOrder, index + 1, endOfIn);
		return root;
	}

	/*
	 求给定二叉树的最大深度，
	深度是指树的根节点到任一叶子节点路径上节点的数量。
	最大深度是所有叶子节点的深度的最大值。
	（注：叶子节点是指没有子节点的节点。）
	数据范围：0 \le n \le 1000000≤n≤100000，树上每个节点的val满足 |val| \le 100∣val∣≤100
	要求： 空间复杂度 O(1)O(1),时间复杂度 O(n)O(n)
	 */
	public int maxDepth(TreeNode root) {
		// write code here
		if (root == null) {
			return 0;
		}
		int lDepth = maxDepth(root.left);
		int rDepth = maxDepth(root.right);
		return 1 + Math.max(lDepth, rDepth);
	}

	/*
	输入一棵节点数为 n 二叉树，判断该二叉树是否是平衡二叉树。
	在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
	平衡二叉树（Balanced Binary Tree），
	具有以下性质：
	它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
	 */
	public boolean isBalancedTree(TreeNode root) {
		// 空树也是平衡二叉树
		if (root == null) {
			return true;
		}
		return getHeight(root) != -1;
	}

	public int getHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		// 递归计算当前root左右子树的高度差
		int left = getHeight(root.left);
		// 当前节点左子树不平衡,则该树不平衡，相当于可行性剪枝，没必要遍历所有节点
		if (left < 0) {
			return -1;
		}
		int right = getHeight(root.right);
		if (right < 0) {
			return -1;
		}
		// 自底向顶，每次+1，不断计算高度，直到递归到某个节点使得左右子树高度差大于1，则返回-1表示不平衡
		// 左右子树中高度较高的, 作为当前节点的高度, 用于向上递归判断是否平衡
		return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
	}

	/*
	二叉树根节点到叶子节点的所有路径和
	给定一个二叉树的根节点root，该树的节点值都在数字\ 0-9 0−9 之间，每一条从根节点到叶子节点的路径都可以用一个数字表示。
	1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
	2.叶子节点是指没有子节点的节点
	3.路径只能从父节点到子节点，不能从子节点到父节点
	4.总节点数目为n
	输入：{1,2,3}
	返回值：25
	 */
	public int sumNumbers(TreeNode root) {
		//如果根节点是空，直接返回0即可
		if (root == null)
			return 0;
		//两个栈，一个存储的是节点，一个存储的是节点对应的值
		Stack<TreeNode> nodeStack = new Stack<>();
		Stack<Integer> valueStack = new Stack<>();
		//全局的，统计所有路径的和
		int res = 0;
		nodeStack.add(root);
		valueStack.add(root.val);
		while (!nodeStack.isEmpty()) {
			//当前节点和当前节点的值同时出栈
			TreeNode node = nodeStack.pop();
			int value = valueStack.pop();
			if (node.left == null && node.right == null) {
				//如果当前节点是叶子结点，说明找到了一条路径，把这条
				//路径的值加入到全局变量res中
				res += value;
			} else {
				//如果不是叶子节点就执行下面的操作
				if (node.right != null) {
					//把子节点和子节点的值分别加入到栈中，这里子节点的值
					//就是父节点的值*10+当前节点的值
					nodeStack.push(node.right);
					valueStack.push(value * 10 + node.right.val);
				}
				if (node.left != null) {
					nodeStack.push(node.left);
					valueStack.push(value * 10 + node.left.val);
				}
			}
		}
		return res;
	}

	/*
	 二叉树中和为某一值的路径(二)
	输入一颗二叉树的根节点root和一个整数expectNumber，找出二叉树中结点值的和为expectNumber的所有路径。
	1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
	2.叶子节点是指没有子节点的节点
	3.路径只能从父节点到子节点，不能从子节点到父节点
	4.总节点数目为n
	
	如二叉树root为{10,5,12,4,7},expectNumber为22
	 */
	public void dfs(int expectNumber, TreeNode root, ArrayList<ArrayList<Integer>> ans, int count,
			ArrayList<Integer> ans_) {
		count += root.val;
		ans_.add(root.val);
		if (root.left == null && root.right == null) {
			if (count == expectNumber) {
				ArrayList<Integer> ans_temp = new ArrayList<>();
				ans_temp.addAll(ans_);
				ans.add(ans_temp);
			}
		}
		if (root.left != null)
			dfs(expectNumber, root.left, ans, count, ans_);
		if (root.right != null)
			dfs(expectNumber, root.right, ans, count, ans_);
		ans_.remove(ans_.size() - 1);
	}

	public ArrayList<ArrayList<Integer>> findPath(TreeNode root, int expectNumber) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		if (root != null)
			dfs(expectNumber, root, ans, 0, new ArrayList<>());
		return ans;
	}

	ArrayList res = new ArrayList<>();
	LinkedList path = new LinkedList<>();

	public ArrayList<ArrayList<Integer>> findPath2(TreeNode root, int expectNumber) {
		if (root == null) {
			return res;
		}
		dfs(root, expectNumber);
		return res;
	}

	public void dfs(TreeNode root, int sum) {
		if (root == null) {
			return;
		}
		sum -= root.val;
		path.add(root.val);
		if (root.left == null && root.right == null && sum == 0) {
			res.add(new ArrayList(path));
		}
		dfs(root.left, sum);
		dfs(root.right, sum);
		path.removeLast();
	}

	/*
	给定一棵二叉树，已知其中的节点没有重复值，请判断该二叉树是否为搜索二叉树和完全二叉树。
	输出描述：分别输出是否为搜索二叉树、完全二叉树。
	
	数据范围：二叉树节点数满足 0 \le n \le 5000000≤n≤500000 ，二叉树上的值满足 0 \le val \le 1000000≤val≤100000
	要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
	
	注意：空子树我们认为同时符合搜索二叉树和完全二叉树。
	 */
	public boolean[] judgeTree(TreeNode root) {
		// write code here
		boolean[] res = new boolean[2];

		res[0] = isSerachTreeBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
		res[1] = isAllTreeBST(root);

		return res;

	}

	//判断搜索树
	public boolean isSerachTreeBST(TreeNode root, long left, long right) {
		if (root == null)
			return true;

		if (root.val <= left || root.val >= right)
			return false;

		return isSerachTreeBST(root.left, left, root.val) && isSerachTreeBST(root.right, root.val, right);
	}

	//判断完全树
	public boolean isAllTreeBST(TreeNode root) {
		if (root == null)
			return true;
		Deque<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		TreeNode left = null;
		TreeNode right = null;
		boolean flag = false; //标记是否遇到节点不双全的节点
		while (!queue.isEmpty()) {
			root = queue.poll();
			left = root.left;
			right = root.right;
			//遇到左右孩子不双全的节点并且该节点不是叶子节点的时候就不是完全二叉树  //左孩子为空并且右孩子不为空的时候不是完全二叉树
			if ((flag && !(left == null && right == null)) || (left == null && right != null)) {
				return false;
			}
			if (left != null)
				queue.offer(left);
			if (right != null)
				queue.offer(right);
			if (left == null || right == null)
				flag = true;
		}
		return true;
	}

	/*
	二叉树里面的路径被定义为:从该树的任意节点出发，经过父=>子或者子=>父的连接，达到任意节点的序列。
	注意:
	1.同一个节点在一条二叉树路径里中最多出现一次
	2.一条路径至少包含一个节点，且不一定经过根节点
	
	给定一个二叉树的根节点root，请你计算它的最大路径和
	 */
	int maxSum = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode root) {
		// write code here
		maxGain(root);
		return maxSum;
	}

	public int maxGain(TreeNode node) {
		if (node == null) {
			return 0;
		}

		// 递归计算左右子节点的最大贡献值
		// 只有在最大贡献值大于 0 时，才会选取对应子节点
		int leftGain = Math.max(maxGain(node.left), 0);
		int rightGain = Math.max(maxGain(node.right), 0);

		// 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
		int priceNewpath = node.val + leftGain + rightGain;

		// 更新答案
		maxSum = Math.max(maxSum, priceNewpath);

		// 返回节点的最大贡献值
		return node.val + Math.max(leftGain, rightGain);
	}

	/*
	 给定一棵二叉树，判断其是否是自身的镜像（即：是否对称）
	 */
	boolean isSymmetrical(TreeNode pRoot) {
		if (pRoot == null) {
			return true;
		}
		return issame(pRoot.left, pRoot.right);
	}

	boolean issame(TreeNode left, TreeNode right) {
		if (left == null && right != null) {
			return false;
		}
		if (left != null && right == null) {
			return false;
		}
		if (left == null && right == null) {
			return true;
		}
		if (left.val != right.val) {
			return false;
		}
		boolean outside = issame(left.left, right.right);
		boolean inside = issame(left.right, right.left);
		return inside && outside;
	}

	/*
	给定一个二叉树root和一个值 sum ，判断是否有从根节点到叶子节点的节点值之和等于 sum 的路径。
	1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
	2.叶子节点是指没有子节点的节点
	3.路径只能从父节点到子节点，不能从子节点到父节点
	4.总节点数目为n
	*/
	public boolean hasPathSum(TreeNode root, int sum) {
		// write code here
		if (root == null) {
			return false;
		}
		// 深度优先遍历
		return dfs2(root, sum);
	}

	private boolean dfs2(TreeNode curNode, int target) {
		// 目标路径不存在，开始回溯
		if (curNode == null) {
			return false;
		}
		// 更新目标值
		target -= curNode.val;
		// 当当前节点为叶子节点并且目标路径存在时，返回 true
		if (curNode.left == null && curNode.right == null && target == 0) {
			return true;
		}
		// 对左右分支进行 dfs
		return dfs2(curNode.left, target) || dfs2(curNode.right, target);
	}

	/*
	前序遍历:这里采用的是前序遍历的递归实现方法，即：根节点-左儿子-右儿子。
	*/
	public boolean hasPathSum2(TreeNode root, int sum) {
		// 根节点为空，则直接返回false
		if (root == null) {
			return false;
		}
		// 只有根节点，且值满足要求，则返回true
		if (root.left == null && root.right == null && root.val == sum) {
			return true;
		}
		// 递归遍历
		return hasPathSum2(root.left, sum - root.val) || hasPathSum2(root.right, sum - root.val);
	}

	/*二叉树中和为某一值的路径(三)
	给定一个二叉树root和一个整数值 sum ，求该树有多少路径的的节点值之和等于 sum 。
	1.该题路径定义不需要从根节点开始，也不需要在叶子节点结束，但是一定是从父亲节点往下到孩子节点
	2.总节点数目为n
	3.保证最后返回的路径个数在整形范围内(即路径个数小于231-1)*/
	int count = 0;
	public int findPath5(TreeNode root, int sum) {
		if (root == null) {
			return count;
		}
		dfs5(root, sum);
		findPath5(root.left, sum);
		findPath5(root.right, sum);
		return count;
		// write code here
	}

	void dfs5(TreeNode root, int sum) {
		if (root == null) {
			return;
		}
		sum -= root.val;
		if (sum == 0) {
			count++;
		}
		dfs5(root.left, sum);
		dfs5(root.right, sum);
	}

	/*
	请实现两个函数，分别用来序列化和反序列化二叉树，不对序列化之后的字符串进行约束，但要求能够根据序列化之后的字符串重新构造出一棵与原二叉树相同的树。
	二叉树的序列化(Serialize)是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。
	序列化可以基于先序、中序、后序、层序的二叉树等遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#）
	二叉树的反序列化(Deserialize)是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
	例如，可以根据层序遍历的方案序列化，...层序序列化(即用函数Serialize转化)如上的二叉树转为"{1,2,3,#,#,6,7}"，
	再能够调用反序列化(Deserialize)将"{1,2,3,#,#,6,7}"构造成如上的二叉树。
	当然你也可以根据满二叉树结点位置的标号规律来序列化，还可以根据先序遍历和中序遍历的结果来序列化。
	不对序列化之后的字符串进行约束，所以欢迎各种奇思妙想。
	*/
	public String serializeTree(TreeNode root) {

		if (root == null) {
			return "{}";
		}
		ArrayList<TreeNode> list = new ArrayList();
		list.add(root);
		for (int i = 0; i < list.size(); i++) {
			TreeNode node = list.get(i);
			if (node != null) {
				list.add(node.left);
				list.add(node.right);
			}
		}

		int index = list.size() - 1;
		while (list.get(index) == null) {
			list.remove(index);
			index--;
		}
		String res = "{" + list.get(0).val;
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) == null) {
				res = res + ",#";
			} else {
				res = res + "," + list.get(i).val;
			}
		}

		return res + "}";

	}

	public TreeNode deserializeTree(String str) {

		if (str == null || str == "{}") {
			return null;
		}

		String[] s = str.substring(1, str.length() - 1).split(",");
		boolean isLeft = true;
		ArrayList<TreeNode> list = new ArrayList();
		int index = 0;
		list.add(new TreeNode(Integer.parseInt(s[0])));
		for (int i = 1; i < s.length; i++) {
			TreeNode father = list.get(index);
			if (!s[i].equals("#")) {
				TreeNode node = new TreeNode(Integer.parseInt(s[i]));
				if (isLeft) {
					father.left = node;
				} else {
					father.right = node;
				}
				list.add(node);
			}
			if (!isLeft) {
				index++;
			}
			isLeft = !isLeft;
		}
		return list.get(0);

	}

	/**
	 * 给定一棵结点数为n 二叉搜索树，请找出其中的第 k 小的TreeNode结点值。 1.返回第k小的节点值即可
	 * 2.不能查找的情况，如二叉树为空，则返回-1，或者k大于n等等，也返回-1 3.保证n个节点的值不一样
	 *
	 *
	 * @param proot TreeNode类
	 * @param k     int整型
	 * @return int整型
	 */
	public int KthNode(TreeNode proot, int k) {
		if (proot == null)
			return -1;
		//中序遍历，第k个节点
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(proot);
		TreeNode node = proot;
		int i = 0;
		while (!stack.isEmpty()) {
			//遍历node下的所有左节点
			while (node.left != null) {
				stack.push(node.left);
				node = node.left;
			}
			i++;
			if (i == k)
				return stack.pop().val;
			TreeNode tmp = stack.pop();
			//加入右子树
			if (tmp.right != null) {
				stack.push(tmp.right);
				node = tmp.right;
			}
		}
		return -1;
	}

	/*多叉树的直径
	给定一棵多叉树，求出这棵树的直径，即树上最远两点的距离。
	包含n个结点，n-1条边的连通图称为树。
	示例1的树如下图所示。其中4到5之间的路径最长，是树的直径，距离为5+2+4=11*/
	/**
	 * 树的直径
	 * 
	 * @param n          int整型 树的节点个数
	 * @param Tree_edge  Interval类一维数组 树的边
	 * @param Edge_value int整型一维数组 边的权值
	 * @return int整型
	 */

	public class Interval {
		int start;
		int end;
	}

	private class Edge {
		int neighbor;
		int value;

		Edge(int neighbor, int value) {
			this.neighbor = neighbor;
			this.value = value;
		}
	}

	int maxIndex = -1;
	int maxEdges = 0;

	/*输入：6,[[0,1],[1,5],[1,2],[2,3],[2,4]],[3,4,2,1,5]
	    返回值：11
	 */
	public int treeDiameter(int n, Interval[] Tree_edge, int[] Edge_value) {
		// write code here
		if (Tree_edge == null || Edge_value == null || Tree_edge.length != Edge_value.length) {
			return 0;
		}
		List<Edge>[] graph = new List[n];//邻接表数组+列表
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		// List<List<Edge>> graph = new ArrayList<>();
		for (int i = 0; i < Tree_edge.length; i++) {
			Interval interval = Tree_edge[i];
			int value = Edge_value[i];
			graph[interval.start].add(new Edge(interval.end, value));
			graph[interval.end].add(new Edge(interval.start, value));
		}
		dfs(graph, 0, -1, 0);
		dfs(graph, maxIndex, -1, 0);

		return maxEdges;
	}

	public void dfs(List[] graph, int index, int parent, int count) {
		List<Edge> cur = graph[index];
		for (int i = 0; i < cur.size(); i++) {
			if (cur.get(i).neighbor == parent) {
				continue;
			}
			dfs(graph, cur.get(i).neighbor, index, count + cur.get(i).value);
		}
		if (count > maxEdges) {
			maxEdges = count;
			maxIndex = index;
		}
	}

	/*二叉树的镜像
	操作给定的二叉树，将其变换为源二叉树的镜像。
	输入：{8,6,10,5,7,9,11}
	返回值:{8,10,6,11,9,7,5}*/
	public TreeNode mirrorTree(TreeNode pRoot) {
		// write code here
		if (pRoot == null)
			return null;
		// 构建辅助栈
		Stack<TreeNode> stack = new Stack<>();
		// 根节点入栈
		stack.add(pRoot);
		while (!stack.isEmpty()) {
			// 节点出栈
			TreeNode node = stack.pop();
			// 根节点的左右子树入栈
			if (node.left != null)
				stack.add(node.left);
			if (node.right != null)
				stack.add(node.right);
			// 左右子树交换
			TreeNode tmp = node.left;
			node.left = node.right;
			node.right = tmp;
		}
		return pRoot;
	}

	/*合并二叉树
	已知两颗二叉树，将它们合并成一颗二叉树。合并规则是：都存在的结点，就将结点值加起来，否则空的位置就由另一个树的结点来代替。*/
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if (t1 == null)
			return t2;
		if (t2 == null)
			return t1;
		t1.val += t2.val;
		t1.left = mergeTrees(t1.left, t2.left);
		t1.right = mergeTrees(t1.right, t2.right);
		return t1;
	}

	/*二叉搜索树与双向链表
	输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。如下图所示
	注意:
	1.要求不能创建任何新的结点，只能调整树中结点指针的指向。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继
	2.返回链表中的第一个节点的指针
	3.函数返回的TreeNode，有左右指针，其实可以看成一个双向链表的数据结构
	4.你不用输出双向链表，程序会根据你的返回值自动打印输出
	*/
	TreeNode head, pre01;

	public TreeNode Convert01(TreeNode pRootOfTree) {
		if (pRootOfTree == null)
			return null;
		dfs(pRootOfTree);
		return head;
	}

	public void dfs(TreeNode root) {
		if (root.left != null)
			dfs(root.left);
		if (pre01 != null)
			pre01.right = root;
		else
			head = root;
		root.left = pre01;
		pre01 = root;
		if (root.right != null)
			dfs(root.right);
	}

	TreeNode pre = null;
	TreeNode root = null;

	public TreeNode Convert(TreeNode pRootOfTree) {
		if (pRootOfTree == null)
			return null;
		// 递归遍历左子树
		Convert(pRootOfTree.left);
		// 判断特殊情况
		if (root == null) {
			root = pRootOfTree;
		}
		// 修改遍历的结点为双向链表
		if (pre01 != null) {
			pRootOfTree.left = pre01;
			pre01.right = pRootOfTree;
		}
		// 更新 pre
		pre01 = pRootOfTree;
		// 递归遍历右子树
		Convert(pRootOfTree.right);
		return root;
	}

	/*找到搜索二叉树中两个错误的节点
	一棵二叉树原本是搜索二叉树，但是其中有两个节点调换了位置，使得这棵二叉树不再是搜索二叉树，请按升序输出这两个错误节点的值。(每个节点的值各不相同)
	搜索二叉树：满足每个节点的左子节点小于当前节点，右子节点大于当前节点。*/

	int[] result = new int[2];
	int index = 1;
	TreeNode preNode;

	public int[] findError(TreeNode root) {
		// write code here
		if (root == null) {
			return result;
		}
		// 递归左子树
		findError(root.left);
		if (preNode == null) {
			preNode = root;
		}
		// 判断是否是出错的节点
		if (index == 1 && root.val < preNode.val) {
			result[index] = preNode.val;
			index--;
		}
		if (index == 0 && root.val < preNode.val) {
			result[index] = root.val;
		}
		preNode = root;
		// 递归右子树
		findError(root.right);
		return result;
	}

	/*将升序数组转化为平衡二叉搜索树
	给定一个升序排序的数组，将其转化为平衡二叉搜索树（BST）.
	平衡二叉搜索树指树上每个节点 node 都满足左子树中所有节点的的值都小于 node 的值，
	右子树中所有节点的值都大于 node 的值，并且左右子树的节点数量之差不大于1*/
	// 判断特殊情况， 数组为空，或数组上没有元素，直接返回 null
	public TreeNode sortedArrayToBST(int[] num) {
		// write code here
		if (num == null || num.length == 0)
			return null;
		int len = num.length;
		if (len == 1) {
			return new TreeNode(num[0]);
		}
		if (len == 2) {
			TreeNode root = new TreeNode(num[1]);
			root.left = new TreeNode(num[0]);
			return root;
		}
		int mid = len / 2;
		TreeNode root = new TreeNode(num[mid]);
		int[] left = Arrays.copyOfRange(num, 0, mid);
		int[] right = Arrays.copyOfRange(num, mid + 1, len);
		root.left = sortedArrayToBST(left);
		root.right = sortedArrayToBST(right);
		return root;
	}

	/*完全二叉树结点数
	给定一棵完全二叉树的头节点head，返回这棵树的节点个数。
	完全二叉树指：设二叉树的深度为h，则 [1,h-1] 层的节点数都满足 2^{i-1}2i−1个*/
	public int nodeNum(TreeNode head) {
		if (head == null)
			return 0;
		return nodeNum(head.left) + nodeNum(head.right) + 1;
	}

	/**
	 * 一个有 n 户人家的村庄，有 m 条路相互连接着。村里现在要修路，每条路都有一个成本价格， 现在请你帮忙计算下，最少需要花费多少钱，就能让这 n
	 * 户人家连接起来。
	 *
	 * 返回最小的花费代价使得这n户人家连接起来
	 * 
	 * @param n    int n户人家的村庄
	 * @param m    int m条路
	 * @param cost int二维数组 一维3个参数，表示连接1个村庄到另外1个村庄的花费的代价
	 * @return int
	 */
	public int miniSpanningTree(int n, int m, int[][] cost) {
		// write code here
		int[][] graph = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				graph[i][j] = Integer.MAX_VALUE;
			}
		}
		for (int[] edge : cost) {
			if (graph[edge[0]][edge[1]] < Integer.MAX_VALUE) {
				if (graph[edge[0]][edge[1]] > edge[2]) {
					graph[edge[0]][edge[1]] = edge[2];
					graph[edge[1]][edge[0]] = edge[2];
				}
			} else {
				graph[edge[0]][edge[1]] = edge[2];
				graph[edge[1]][edge[0]] = edge[2];
			}
		}

		boolean[] visited = new boolean[n + 1];

		int[] dis = new int[n + 1];
		visited[1] = true;
		for (int i = 0; i <= n; i++) {
			dis[i] = graph[1][i];
		}

		int res = 0;
		int next = -1;
		for (int i = 2; i <= n; i++) {
			int mini = Integer.MAX_VALUE;
			for (int j = 1; j <= n; j++) {
				if (!visited[j] && dis[j] < mini) {
					next = j;
					mini = dis[j];
				}
			}
			visited[next] = true;
			res += mini;

			for (int j = 1; j <= n; j++) {
				if (!visited[j] && dis[j] > graph[next][j]) {
					dis[j] = graph[next][j];
				}
			}
		}

		return res;
	}

	/*二叉树的最小深度:
	给定一颗节点数为N的二叉树，求其最小深度。最小深度是指树的根节点到最近叶子节点的最短路径上节点的数量。
	（注：叶子节点是指没有子节点的节点。）*/
	public int minDeepth(TreeNode root) {
		// write code here
		if (root == null)
			return 0;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int depth = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (node.left == null && node.right == null)
					return depth;
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			depth++;
		}
		return depth;
	}

	/*NC169 修剪叶子
	有一棵有\mathit nn个节点的二叉树，其根节点为\mathit rootroot。修剪规则如下:
	1.修剪掉当前二叉树的叶子节点，但是不能直接删除叶子节点
	2.只能修剪叶子节点的父节点，修剪了父节点之后，叶子节点也会对应删掉
	3.如果想在留下尽可能多的节点前提下，修剪掉所有的叶子节点。请你返回修剪后的二叉树。
	有如下二叉树:
	*/
	private TreeNode del(TreeNode root)
    {
        if(root.left==null&&root.right==null){
            root=null;
            return root;
        }
        if(root.left!=null){
            del(root.left);
        }
        if(root.right!=null){
            del(root.right);
        }
        root=null;
        return root;
    }
    public TreeNode pruneLeaves (TreeNode root) {
        // write code here
       if(root.left!=null){
         if(root.left.left==null&&root.left.right==null){
          root=del(root); 
          return root;
         }
         root.left=pruneLeaves(root.left);
       }
       if(root.right!=null){
         if(root.right.left==null&&root.right.right==null){
          root=del(root);
          return root;
         }
         root.right=pruneLeaves(root.right);
       }
       if(root==null)
       return new TreeNode(1);
       else
       return root;
    }
    
	/*循环右移二叉树
	现有一棵nn个节点构成的二叉树，请你将每一层的节点向右循环位移k位。某层向右位移一位(即k=1)的含义为：
	1.若当前节点为左孩子节点，会变成当前节点的双亲节点的右孩子节点。
	2.若当前节点为右儿子，会变成当前节点的双亲节点的右边相邻兄弟节点的左孩子节点。(如果当前节点的双亲节点已经是最右边的节点了，则会变成双亲节点同级的最左边的节点的左孩子节点)
	3.该层的每一个节点同时进行一次位移。
	4.是从最下面的层开始位移，位移完每一层之后，再向上，直到根节点，位移完毕。*/
    public TreeNode cyclicShiftTree (TreeNode root, int k) {
        // write code here
         // write code here
        if(k <= 0) return root;
        List<TreeNode> arr = new ArrayList<>();
        arr.add(root);
        List<Integer> indexArr = new ArrayList<>();
        indexArr.add(0);
        moveNode(arr,indexArr,k);
        return root;
    }
     
    private void moveNode(List<TreeNode> arr,List<Integer> indexArr,int k){
        List<TreeNode> a = new ArrayList<>();
        List<Integer> index = new ArrayList<>();
        int count = 0;
        for(TreeNode node : arr){
            if(node.left != null){
                a.add(node.left);
                index.add(count);
                node.left = null;
            }
            count++;
            if(node.right != null){
                a.add(node.right);
                index.add(count);
                node.right = null;
            }
            count++;
        }
        if(a.size() == 0) return;
        moveNode(a,index,k);
        //移动当前层的子结点
        for(int i=0;i<a.size();i++){
            int pindex = (index.get(i) + k)/2%arr.size();
            int isRight =(index.get(i) + k)%2;
            TreeNode parent = arr.get(pindex);
            if(isRight == 1) parent.right = a.get(i);
            else parent.left = a.get(i);
        }
    }
    
    /*NC178 打家劫舍(三)
    你是一个经验丰富的小偷，经过上次在街边和湖边得手后你准备挑战一次自己，
    你发现了一个结构如二叉树的小区，小区内每个房间都存有一定现金，
    你观察到除了小区入口的房间以外每个房间都有且仅有一个父房间和至多两个子房间。
    问，给定一个二叉树结构的小区，如之前两次行动一样，你无法在不触动警报的情况下同时偷窃两个相邻的房间，
    在不触动警报的情况下最多的偷窃金额。*/
    public int rob (TreeNode root) {
        // write code here
        int[] res = dfs06(root);
        return Math.max(res[0], res[1]);
    }
 
    private int[] dfs06(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        
        int[] left = dfs06(node.left);
        int[] right = dfs06(node.right);
 
      
        int[] dp = new int[2];
 
        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        dp[1] = node.val + left[0] + right[0];
        return dp;
    }
    
	/*NC184 判断是不是二叉搜索树
	给定一个二叉树根节点，请你判断这棵树是不是二叉搜索树。
	二叉搜索树满足每个节点的左子节点小于当前节点且右子节点大于当前节点。*/
    public boolean isValidBST (TreeNode root) {
        // write code here
        if (root == null) {
            return true;
        }
        if (root.left != null && root.left.val > root.val) {
            return false;
        }
        if (root.right != null && root.val > root.right.val) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }
	/*NC150 二叉树的个数
	已知一棵节点个数为 n 的二叉树的中序遍历单调递增, 求该二叉树能能有多少种树形, 输出答案对 10^9 取模*/
    public int numberOfTree (int n) {
        // write code here
        int res = 1;
        int p = (int)(1e9 + 7);
        for(int i = n + 1; i <= 2 * n; i ++){
            res = (int)((long)res * i % p);
        }
        res = (int)((long)res * qmi(n + 1, p - 2, p) % p);
        for(int i = 1; i <= n; i ++){
            res = (int)((long)res * qmi(i, p - 2, p) % p);
        }
        return res;
    }
    private int qmi(int a, int b, int p){
        int res = 1;
        while(b != 0){
            if(b % 2 == 1){
                res = (int)((long)res * a % p);
            }
            b = b / 2;
            a = (int)((long)a * a % p);
        }
        return res;
    }
    
	/*NC204 二叉树的最大宽度
	给定一个二叉树，请你求出此二叉树的最大宽度。
	本题中树第 i 层的宽度定义为：第 i 层最左边的节点到最右边之间的距离，中间空节点也计入距离。*/
    public int widthOfBinaryTree (TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);
        int result = 1;
        while (!q.isEmpty()) {
            while (q.size() > 0 && q.peekFirst() == null) {
                q.pollFirst();
            }
            while (q.size() > 0 && q.peekLast() == null) {
                q.pollLast();
            }
            int size = q.size();
            result = Math.max(result, size);
            TreeNode cur;
            for (int i = 0; i < size; i++) {
                cur = q.pollFirst();
                if (cur != null) {
                    q.offerLast(cur.left);
                    q.offerLast(cur.right);
                } else {
                    q.offerLast(null);
                    q.offerLast(null);
                }
            }
        }
        return result;
    }
	/*NC215 将二叉搜索树改为累加树
	给定一个二叉搜索树，树上的节点各不相同，请你将其修改为累加树，使每个节点的值变成原树中更大节点之和。
	二叉搜索树的定义是任一节点的左子树的任意节点的值小于根节点的值，右子树则相反。*/
	public TreeNode convertToAddBST(TreeNode root) {
		// write code here
		if (root == null)
			return null;
		dfs_add(root);
		return root;
	}
	int sum = 0;
	public void dfs_add(TreeNode root) {
		if (root == null)
			return;
		dfs_add(root.right);
		sum += root.val;
		root.val = sum;
		dfs_add(root.left);
	}
    
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
