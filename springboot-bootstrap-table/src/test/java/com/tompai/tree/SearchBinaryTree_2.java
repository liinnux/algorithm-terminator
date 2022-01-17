package com.tompai.tree;

class Node {
	Node parent;
	Node leftChild;
	Node rightChild;
	int val;

	public Node(Node parent, Node leftChild, Node rightChild, int val) {
		super();
		this.parent = parent;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.val = val;
	}

	public Node(int val) {
		this(null, null, null, val);
	}

	public Node(Node node, int val) {
		this(node, null, null, val);
	}

}

public class SearchBinaryTree_2 {

	private Node root;
	private int size;

	public SearchBinaryTree_2() {
		super();
	}

	public boolean add(int val) {
		if (root == null) {
			root = new Node(val);
			size++;
			return true;
		}
		Node node = getAdapterNode(root, val);
		Node newNode = new Node(val);
		if (node.val > val) {
			node.leftChild = newNode;
			newNode.parent = node;
		} else if (node.val < val) {
			node.rightChild = newNode;
			newNode.parent = node;
		} else {
			// 暂不做处理
		}
		size++;
		return true;
	}

	/**
	 * 获取要插入的节点的父节点，该父节点满足以下几种状态之一 1、父节点为子节点 2、插入节点值比父节点小，但父节点没有左子节点
	 * 3、插入节点值比父节点大，但父节点没有右子节点 4、插入节点值和父节点相等。 5、父节点为空 如果满足以上5种情况之一，则递归停止。
	 * 
	 * @param node
	 * @param val
	 * @return
	 */
	private Node getAdapterNode(Node node, int val) {
		if (node == null) {
			return node;
		}
		// 往左子树中插入，但没左子树，则返回
		if (node.val > val && node.leftChild == null) {
			return node;
		}
		// 往右子树中插入，但没右子树，也返回
		if (node.val < val && node.rightChild == null) {
			return node;
		}
		// 该节点是叶子节点，则返回
		if (node.leftChild == null && node.rightChild == null) {
			return node;
		}

		if (node.val > val && node.leftChild != null) {
			return getAdapterNode(node.leftChild, val);
		} else if (node.val < val && node.rightChild != null) {
			return getAdapterNode(node.rightChild, val);
		} else {
			return node;
		}
	}

	public boolean put(int val) {
		return putVal(root, val);
	}

	private boolean putVal(Node node, int val) {
		if (node == null) {// 初始化根节点
			node = new Node(val);
			root = node;
			size++;
			return true;
		}
		Node temp = node;
		Node p;
		int t;
		/**
		 * 通过do while循环迭代获取最佳节点，
		 */
		do {
			p = temp;
			t = temp.val - val;
			if (t > 0) {
				temp = temp.leftChild;
			} else if (t < 0) {
				temp = temp.rightChild;
			} else {
				temp.val = val;
				return false;
			}
		} while (temp != null);
		Node newNode = new Node(p, val);
		if (t > 0) {
			p.leftChild = newNode;
		} else if (t < 0) {
			p.rightChild = newNode;
		}
		size++;
		return true;
	}

	public boolean delete(int val) {
		Node node = getNode(val);
		if (node == null) {
			return false;
		}
		Node parent = node.parent;
		Node leftChild = node.leftChild;
		Node rightChild = node.rightChild;
		//以下所有父节点为空的情况，则表明删除的节点是根节点
		if (leftChild == null && rightChild == null) {//没有子节点
			if (parent != null) {
				if (parent.leftChild == node) {
					parent.leftChild = null;
				} else if (parent.rightChild == node) {
					parent.rightChild = null;
				}
			} else {//不存在父节点，则表明删除节点为根节点
				root = null;
			}
			node = null;
			return true;
		} else if (leftChild == null && rightChild != null) {// 只有右节点
			if (parent != null && parent.val > val) {// 存在父节点，且node位置为父节点的左边
				parent.leftChild = rightChild;
			} else if (parent != null && parent.val < val) {// 存在父节点，且node位置为父节点的右边
				parent.rightChild = rightChild;
			} else {
				root = rightChild;
			}
			node = null;
			return true;
		} else if (leftChild != null && rightChild == null) {// 只有左节点
			if (parent != null && parent.val > val) {// 存在父节点，且node位置为父节点的左边
				parent.leftChild = leftChild;
			} else if (parent != null && parent.val < val) {// 存在父节点，且node位置为父节点的右边
				parent.rightChild = leftChild;
			} else {
				root = leftChild;
			}
			return true;
		} else if (leftChild != null && rightChild != null) {// 两个子节点都存在
			Node successor = getSuccessor(node);// 这种情况，一定存在后继节点
			int temp = successor.val;
			boolean delete = delete(temp);
			if (delete) {
				node.val = temp;
			}
			successor = null;
			return true;
		}
		return false;
	}

	/**
	 * 找到node节点的后继节点 1、先判断该节点有没有右子树，如果有，则从右节点的左子树中寻找后继节点，没有则进行下一步
	 * 2、查找该节点的父节点，若该父节点的右节点等于该节点，则继续寻找父节点， 直至父节点为Null或找到不等于该节点的右节点。
	 * 理由，后继节点一定比该节点大，若存在右子树，则后继节点一定存在右子树中，这是第一步的理由
	 * 若不存在右子树，则也可能存在该节点的某个祖父节点(即该节点的父节点，或更上层父节点)的右子树中， 对其迭代查找，若有，则返回该节点，没有则返回null
	 * 
	 * @param node
	 * @return
	 */
	private Node getSuccessor(Node node) {
		if (node.rightChild != null) {
			Node rightChild = node.rightChild;
			while (rightChild.leftChild != null) {
				rightChild = rightChild.leftChild;
			}
			return rightChild;
		}
		Node parent = node.parent;
		while (parent != null && (node == parent.rightChild)) {
			node = parent;
			parent = parent.parent;
		}
		return parent;
	}
	
	public boolean remove(int val){
        Node node = getNode(val);
        if(node == null){
            return false;
        }
        if(node.leftChild == null){// 1、左节点不存在，右节点可能存在，包含两种情况  ，两个节点都不存在和只存在右节点
            transplant(node, node.rightChild);
        }else if(node.rightChild == null){//2、左孩子存在，右节点不存在
            transplant(node, node.leftChild);
        }else{// 3、两个节点都存在
            Node successor = getSuccessor(node);// 得到node后继节点
            if(successor.parent != node){// 后继节点存在node的右子树中。
                transplant(successor, successor.rightChild);// 用后继节点的右子节点替换该后继节点
                successor.rightChild = node.rightChild;// 将node节点的右子树赋给后继节点的右节点，即类似后继与node节点调换位置
                successor.rightChild.parent = successor;// 接着上一步  给接过来的右节点的父引用复制
            }
            transplant(node, successor);
            successor.leftChild = node.leftChild;
            successor.leftChild.parent = successor;
        }
        return true;
    }
    /**
     * 将child节点替换node节点
     * @param root    根节点
     * @param node    要删除的节点
     * @param child   node节点的子节点
     */
    private void transplant(Node node,Node child){
        /**
         * 1、先判断 node是否存在父节点
         *    1、不存在，则child替换为根节点
         *    2、存在，则继续下一步
         * 2、判断node节点是父节点的那个孩子(即判断出 node是右节点还是左节点)，
         *    得出结果后，将child节点替换node节点 ，即若node节点是左节点 则child替换后 也为左节点，否则为右节点
         * 3、将node节点的父节点置为child节点的父节点
         */

        if(node.parent == null){
            this.root = child;
        }else if(node.parent.leftChild == node){
            node.parent.leftChild = child;
        }else if(node.parent.rightChild == node){
            node.parent.rightChild = child;
        }
        if(child != null){
            child.parent = node.parent;
        }
    }
    
    public Node getNode(int val){
        Node temp = root;
        int t;
        do{
            t = temp.val-val;
            if(t > 0){
                temp = temp.leftChild;
            }else if(t < 0){
                temp = temp.rightChild;
            }else{
                return temp;
            }
        }while(temp != null);
        return null;
    }
    //三种遍历方式
    public void print(){
        print(root);
    }
    private void print(Node root){
        if(root != null){
        	System.out.println(root.val);// 位置在中间，则中序，若在前面，则为先序，否则为后续
            print(root.leftChild);
            print(root.rightChild);
        }
        
        if(root != null){
            print(root.leftChild);
            System.out.println(root.val);// 位置在中间，则中序，若在前面，则为先序，否则为后续
            print(root.rightChild);
        }
        
        if(root != null){
            print(root.leftChild);
            print(root.rightChild);
            System.out.println(root.val);// 位置在中间，则中序，若在前面，则为先序，否则为后续
        }
    }

}
