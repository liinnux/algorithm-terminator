/**
 * 
 */
package com.tompai.t1229;

/**
 * @author Administrator
 *
 */
public class Demo3_Tree_LCA {
	
	public static void main(String[] args) {
		Demo3_Tree_LCA binaryTree = new Demo3_Tree_LCA();
		TreeNode root = binaryTree.buildTree();
		
		//这里打印出的是地址，可推理该公共祖先是A
		System.out.println("找两个结点最近的公共祖先：" + binaryTree.lowstCommonAncestor(root, root.left.right, root.left.right.right).val);
		//这个是根即A的地址，两者比较会发现是一致的
		System.out.println(root.val);
	}
	/**
	 * @param args
	 */
	// 1、构造二叉树
	public TreeNode buildTree() {
		TreeNode A = new TreeNode('3');
		TreeNode B = new TreeNode('5');
		TreeNode C = new TreeNode('1');
		TreeNode D = new TreeNode('6');
		TreeNode E = new TreeNode('2');
		TreeNode F = new TreeNode('0');
		TreeNode G = new TreeNode('8');
		TreeNode H = new TreeNode('7');
		TreeNode I = new TreeNode('4');
		A.left = B;
		A.right = C;
		B.left = D;
		B.right = E;//这里是构造二叉树的关键
		C.left = F;
		C.right = G;
		E.left = H;
		E.right = I;
		return A;
	}

	//找两个结点最近的公共祖先
	public TreeNode lowstCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}
		if (root == p || root == q) {
			return root;
		}
		TreeNode left = lowstCommonAncestor(root.left, p, q);
		TreeNode right = lowstCommonAncestor(root.right, p, q);
		if (left != null && right != null) {
			return root;
		} else if (left != null) {
			return left;
		} else {
			return right;
		}
	}

	
	public static TreeNode getParent(TreeNode root, TreeNode node1,TreeNode node2) {
	    if(root == null || node1 == null ||  node2 == null) return null;
	    //这里可以换成if(root == node1 || root == node2),我只是为了方便测试才这样写
	    if(root.val == node1.val || root.val == node2.val) return root;
	    TreeNode left = getParent(root.left,node1,node2);
	    TreeNode right = getParent(root.right,node1,node2);
	    //如果左右子树都能找到，那么当前节点就是最近的公共祖先节点
	    if(left != null && right != null) return root;
	    //如果左子树上没有，那么返回右子树的查找结果
	    if(left == null) return right;
	    //否则返回左子树的查找结果
	    else return left;
	}
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}
		if (root == p || root == q) {
			return root;
		}
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);

		if (left != null && right != null) {
			return root;
		}

		if (left == null && right == null) {
			return null;
		}

		return left == null ? right : left;
	}
}


//Definition for a binary tree node. 
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) {
		val = x;
	}
}
