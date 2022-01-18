/**
 * 
 */
package com.tompai.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class Trie_2 {

/**
NC124 字典树的实现
现在给定一个m，表示有m次操作，每次操作都为以上四种操作之一。
每次操作会给定一个整数op和一个字符串word，op代表一个操作码，
如果:
op为1，则代表添加word，
op为2则代表删除word，
op为3则代表查询word是否在字典树中，
op为4代表返回以word为前缀的单词数量（数据保证不会删除不存在的word）。

对于每次操作，如果op为3时，
如果word在字典树中，请输出“YES”，否则输出“NO”；
如果op为4时，请输出返回以word为前缀的单词数量，其它情况不输出。

输入：[["1","qwer"],["1","qwe"],["3","qwer"],["4","q"],["2","qwer"],["3","qwer"],["4","q"]]
返回值：["YES","2","NO","1"]

首先构建一个TrieNode结构，包括一个TrieNode类型的child数组，用于记录所有子节点，一个整型变量pre_number，用于表示插入单词时，当前节点被访问次数，一个boolean型变量end，用于标记当前节点是否是某个单词的结尾。
然后初始化一个根节点，根节点是空心的，即不包含任何字符。

添加word：将单词转为字符数组，从根节点出发，遍历输入的单词，如果子节点不包含当前字符，则新建对应子节点，如果包含，则跳到对应子节点，同时访问次数加一。单词遍历完成后，当前节点标识改为true。
删除word：相当于添加的反向操作，不断往子节点方向移动，同时访问次数减一。遍历完成后，如果访问次数为0，则将标识改为false。
查询word：将单词转为字符数组，从根节点出发，遍历输入的单词，如果子节点不包含当前字符，说明不存在该单词，返回false，如果包含，就往子节点方向移动。遍历完成后，标识为true，说明存在该单词。
查询以pre为前缀的单词数量：将单词转为字符数组，从根节点出发，遍历输入的单词，如果子节点不包含当前字符，说明不存在该前缀，返回0，如果包含，就往子节点方向移动。遍历完成后，pre_number的值即为所求的前缀数量（因为如果某个单词以pre为前缀，插入节点的时候，必然访问过pre结尾处节点）。
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	 /**
    *
    * @param operators string字符串二维数组 the ops
    * @return string字符串一维数组
    */
   class TrieNode{
       int wordCount;
       int prefixCount;
       TrieNode[] children;
         
       public TrieNode(){
           wordCount = 0;
           prefixCount = 0;
           children = new TrieNode[24];
       }
   }
   private TrieNode root;
   public String[] trieU (String[][] operators) {
       // write code here
       root = new TrieNode();
       List<String> res = new ArrayList<>();
       for (String[] op : operators){
           if (op[0].equals("1")){
               insert(op[1]);
           }
           else if (op[0].equals("2")){
               delete(op[1]);
           }
           else if (op[0].equals("3")){
               if (search(op[1])){
                   res.add("YES");
               }
               else{
                   res.add("NO");
               }
           }
           else{
               res.add(String.valueOf(prefixNumber(op[1])));
           }
       }
       return res.toArray(new String[res.size()]);
   }
     
   public void insert(String word){
       if (word == null || word.length() == 0)
           return;
       TrieNode p = root;
       for (char ch : word.toCharArray()){
           if (p.children[ch - 'a'] == null){
               p.children[ch - 'a'] = new TrieNode();
           }
           p = p.children[ch - 'a'];
           p.prefixCount++;
       }
       p.wordCount++;
   }
     
   public void delete(String word){
       if (word == null || word.length() == 0)
           return;
       TrieNode p = root;
       for (char ch : word.toCharArray()){
           p = p.children[ch - 'a'];
           p.prefixCount--;
       }
       p.wordCount--;
   }
     
   public boolean search(String word){
       if (word == null || word.length() == 0)
           return false;
       TrieNode p = root;
       for (char ch : word.toCharArray()){
           if (p.children[ch - 'a'] == null){
               return false;
           }
           p = p.children[ch - 'a'];
             
       }
       return p.wordCount != 0;
   }
     
   public int prefixNumber(String prefix){
       if (prefix == null || prefix.length() == 0)
           return 0;
       TrieNode p = root;
       for (char ch : prefix.toCharArray()){
           if (p.children[ch - 'a'] == null){
               return 0;
           }
           p = p.children[ch - 'a'];
             
       }
       return p.prefixCount;
   }
}
