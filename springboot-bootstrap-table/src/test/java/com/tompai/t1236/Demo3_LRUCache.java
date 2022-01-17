package com.tompai.t1236;

public class Demo3_LRUCache {

	
	/**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    static class Node{
        public int key, value;
        public Node pre, next;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
            this.pre = null;
            this.next = null;
        }
    }
    public int[] LRU (int[][] operators, int k) {
        // write code here
        Node headNode = new Node(-1,-1);
        headNode.pre = headNode;
        headNode.next = headNode;
        int nodeTotal = 0;
        int[] arr = new int[operators.length];
        int index = 0;
        for(int i = 0; i < operators.length; ++i){
            int[] ops = operators[i];
            if(ops[0] == 1){
                if(nodeTotal < k){
                    Node node = new Node(ops[1], ops[2]);
                    node.pre = headNode;
                    node.next = headNode.next;
                    headNode.next.pre = node;
                    headNode.next = node;
                    nodeTotal += 1;
                }else if(nodeTotal == k){
                    Node node = new Node(ops[1], ops[2]);
                    node.pre = headNode;
                    node.next = headNode.next;
                    headNode.next.pre = node;
                    headNode.next = node;
                      
                    Node delNode = headNode.pre;
                    headNode.pre = delNode.pre;
                    delNode.pre.next = headNode;
                }
            }
            else if(ops[0] == 2){
                Node node = headNode.next;
                int value = -1;
                while(node != headNode){
                    if(node.key != ops[1]){
                        node = node.next;
                    }else{
                        value = node.value;
                        node.pre.next = node.next;
                        node.next.pre = node.pre;
                        node.pre = headNode;
                        node.next = headNode.next;
                        headNode.next.pre = node;
                        headNode.next = node;
                        break;
                    }
                }
                arr[index] = value;
                index += 1;
            }
        }
        int[] reArr = new int[index];
        for(int i = 0; i < index; ++i){
            reArr[i] = arr[i];
        }
        return reArr;
    }
}
