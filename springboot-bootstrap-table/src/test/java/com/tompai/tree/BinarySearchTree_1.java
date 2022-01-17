/**
 * 
 */
package com.tompai.tree;
import java.util.ArrayList;
import java.util.List;
 
/**
 * @Description: 二叉搜索树java实现
 */
public class BinarySearchTree_1<E extends Comparable> {
 
    /**
     * 根节点
     */
    private Node root;
 
    public BinarySearchTree_1() {
    }
 
    public BinarySearchTree_1(E e) {
        this.root = new Node(e, null, null, null);
    }
 
    /**
     * 增加一个节点
     *
     * @param e 节点的值
     */
    public void add(E e) {
        // 根节点为空
        if (root == null) {
            root = new Node(e, null, null, null);
        } else {
            Node current = root;
            Node parent;
            boolean lessThanLeft;
            do {
                parent = current;
                lessThanLeft = e.compareTo(current.data) < 0 ? true : false;
                // 如果新增节点小于当前节点的左子节点，则以当前节点的左子节点作为当前节点继续搜索
                if (lessThanLeft) {
                    current = parent.left;
                } else {
                    // 如果新增节点不小于当前节点的左子节点，则以当前节点的右子节点作为当前节点继续搜索
                    current = parent.right;
                }
            } while (current != null);
            Node newNode = new Node(e, parent, null, null);
            if (lessThanLeft) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }
    }
 
    /**
     * 根据输入的值删除指定节点
     *
     * @param e 要删除的节点的值
     */
    public void remove(E e) {
        // 获取要删除的节点
        Node delNode = getNode(e);
        while (delNode != null) {
            // 如果删除的节点为叶子节点，直接移除即可
            if (delNode.left == null && delNode.right == null) {
                if (delNode == root) {
                    root = null;
                } else {
                    // 如果被删除节点是左子节点，移除其父节点的左子节点，否则移除父节点的右子节点
                    if (delNode == delNode.parent.left) {
                        delNode.parent.left = null;
                    } else {
                        delNode.parent.right = null;
                    }
                    delNode.parent = null;
                }
                delNode = null;
            } else if (delNode.left != null && delNode.right == null) {
                // 被删除节点只有左子节点
                if (delNode == root) {
                    // 如果是根节点，让左子节点作为根节点
                    root = delNode.left;
                    root.parent = null;
                    delNode = null;
                } else {
                    delNode.left.parent = delNode.parent;
                    delNode.parent.left = delNode.left;
                    delNode = null;
                }
            } else if (delNode.left == null && delNode.right != null) {
                // 被删除节点只有右子节点
                if (delNode == root) {
                    // 如果是根节点，让右子节点作为根节点
                    root = delNode.right;
                    root.parent = null;
                    delNode.right = null;
                    delNode = null;
                } else {
                    delNode.right.parent = delNode.parent;
                    delNode.parent.right = delNode.right;
                    delNode = null;
                }
            } else {
                // 被删除节点的左、右子节点都存在
                // 找到被删除节点的左子树的最大节点，也就是左子树中最右边的节点
                Node leftMaxNode = delNode.left;
                while (leftMaxNode.right != null) {
                    leftMaxNode = leftMaxNode.right;
                }
                delNode.data = leftMaxNode.data;
                delNode = leftMaxNode;
            }
        }
    }
 
    /**
     * 查找指定值的节点
     *
     * @param e 节点的值
     * @return 符合条件的节点或null
     */
    public Node getNode(E e) {
        Node p = root;
        int cmp;
        while (p != null) {
            cmp = e.compareTo(p.data);
            if (cmp < 0) {
                p = p.left;
            } else if (cmp > 0) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }
 
    // 以中序遍历的方式输出树的每个节点的值
    public void print() {
        List<Node> nodes = new ArrayList<Node>();
        if (root != null) {
            inorderTraversal(root, nodes);
        }
        System.out.println(nodes);
    }
 
    // 中序遍历
    private List<Node> inorderTraversal(Node node, List<Node> nodes) {
        if (node.left != null) {
            inorderTraversal(node.left, nodes);
        }
        nodes.add(node);
        if (node.right != null) {
            inorderTraversal(node.right, nodes);
        }
        return nodes;
    }
 
    /**
     * 节点类
     */
    class Node {
        private Object data;
        private Node parent;
        private Node left;
        private Node right;
 
        public Node(Object data) {
            this.data = data;
        }
 
        public Node(Object data, Node parent, Node left, Node right) {
            this.data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
 
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (this.getClass() == obj.getClass()) {
                Node target = (Node) obj;
                return data.equals(target.data) && parent == target.parent && left == target.left && right == target.right;
            }
            return false;
        }
 
        @Override
        public String toString() {
            return data.toString();
        }
    }
 
    public static void main(String[] args) {
        BinarySearchTree_1<Integer> tree = new BinarySearchTree_1<Integer>();
        Integer[] integers = {18, 5, 4, 66, 32, 78};
        for (Integer i :
                integers) {
            tree.add(i);
        }
        tree.print();
        for (Integer i :
                integers) {
            tree.remove(i);
            tree.print();
        }
    }

}
