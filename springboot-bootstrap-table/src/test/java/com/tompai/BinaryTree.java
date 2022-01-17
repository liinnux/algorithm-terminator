/**
 * 
 */
package com.tompai;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Administrator
 *
 */
public class BinaryTree {

	public static void main(String[] args) {  
        BinaryTree tree = new BinaryTree();  
        Node root = tree.init();  
        System.out.println("先序遍历");  
        tree.theFirstTraversal(root);  
        System.out.println("");  
        System.out.println("中序遍历");  
        tree.theInOrderTraversal(root);  
        System.out.println("");  
        System.out.println("后序遍历");  
        tree.thePostOrderTraversal(root);  
        System.out.println("");  
        System.out.println("之字形层序遍历");  
        tree.zigzagLevelOrder(root);  
        System.out.println("");  
    }  
	
	class Node {  
	    private int data;  
	    private Node leftNode;  
	    private Node rightNode;  
	    public Node(int data, Node leftNode, Node rightNode){  
	        this.data = data;  
	        this.leftNode = leftNode;  
	        this.rightNode = rightNode;  
	    }  
	  
	    public int getData() {  
	        return data;  
	    }  
	    public void setData(int data) {  
	        this.data = data;  
	    }  
	    public Node getLeftNode() {  
	        return leftNode;  
	    }  
	    public void setLeftNode(Node leftNode) {  
	        this.leftNode = leftNode;  
	    }  
	    public Node getRightNode() {  
	        return rightNode;  
	    }  
	    public void setRightNode(Node rightNode) {  
	        this.rightNode = rightNode;  
	    }  
	}  
	 /** 
     * @author yaobo
     * 二叉树的先序中序后序排序 
     */  
    public Node init() {
    	//注意必须逆序建立，先建立子节点，再逆序往上建立，因为非叶子结点会使用到下面的节点，而初始化是按顺序初始化的，不逆序建立会报错  
        Node J = new Node(8, null, null);  
        Node H = new Node(4, null, null);  
        Node G = new Node(2, null, null);  
        Node F = new Node(7, null, J);  
        Node E = new Node(5, H, null);  
        Node D = new Node(1, null, G);  
        Node C = new Node(9, F, null);  
        Node B = new Node(3, D, E);  
        Node A = new Node(6, B, C);  
        return A;   //返回根节点  
    }
    
    public void printNode(Node node){  
        System.out.print(node.getData());  
    }  
    public void theFirstTraversal(Node root) { 
    	//先序遍历  
        printNode(root);  
        if (root.getLeftNode() != null) {  //使用递归进行遍历左孩子  
            theFirstTraversal(root.getLeftNode());  
        }  
        if (root.getRightNode() != null) {  //递归遍历右孩子  
            theFirstTraversal(root.getRightNode());  
        }  
    }  
    public void theInOrderTraversal(Node root) {  
    	//中序遍历  
        if (root.getLeftNode() != null) {  
            theInOrderTraversal(root.getLeftNode());  
        }  
        printNode(root);  
        if (root.getRightNode() != null) {  
            theInOrderTraversal(root.getRightNode());  
        }  
    }
    
    
    public void thePostOrderTraversal(Node root) {  
    	//后序遍历  
        if (root.getLeftNode() != null) {  
            thePostOrderTraversal(root.getLeftNode());  
        }  
        if(root.getRightNode() != null) {  
            thePostOrderTraversal(root.getRightNode());  
        }  
        printNode(root);  
    }  
    
    //之字形层序遍历
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(Node root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        //创建队列，保存节点
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);//先把节点加入到队列中
        boolean leftToRight = true;//第一步先从左边开始打印
        while (!queue.isEmpty()) {
            //记录每层节点的值
            ArrayList<Integer> level = new ArrayList<>();
            //统计这一层有多少个节点
            int count = queue.size();
            //遍历这一层的所有节点，把他们全部从队列中移出来，顺便
            //把他们的值加入到集合level中，接着再把他们的子节点（如果有）
            //加入到队列中
            for (int i = 0; i < count; i++) {
                //poll移除队列头部元素（队列在头部移除，尾部添加）
            	Node node = queue.poll();
                //判断是从左往右打印还是从右往左打印。
                if (leftToRight) {
                    //如果从左边打印，直接把访问的节点值加入到列表level的末尾即可
                    level.add(node.data);
                } else {
                    //如果是从右边开始打印，每次要把访问的节点值
                    //加入到列表的最前面
                    level.add(0, node.data);
                }
                //左右子节点如果不为空会被加入到队列中
                if (node.leftNode != null)
                    queue.add(node.leftNode);
                if (node.rightNode != null)
                    queue.add(node.rightNode);
                
                printNode(node);  
            }
            //把这一层的节点值加入到集合res中
            res.add(level);
            
            //改变下次访问的方向
            leftToRight = !leftToRight;
        }
        return res;
    }
}
