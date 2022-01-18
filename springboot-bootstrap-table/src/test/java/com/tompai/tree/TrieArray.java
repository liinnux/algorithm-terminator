/**
 * 
 */
package com.tompai.tree;

/**
 * @author Administrator
 *
 */
public class TrieArray {

	/**
	 （数组实现）
1.解题思路
首先构建一个数组结构，包括一个二维整型trie数组，一个一维整型cnt数组，一个一维整型end数组，一个索引index。

添加word：将单词转为字符数组，从索引0行处出发，遍历输入的单词，如果子节点（一行相当于一个节点，行对应列的值为0，说明不包含）不包含当前字符，则新建对应子节点，如果包含，则跳到对应子节点，同时cnt[cur]加一。单词遍历完成后，end[cur]加一。
删除word：相当于添加的反向操作，不断往子节点方向移动，同时cnt[cur]加一。遍历完成后，end[cur]减一。
查询word：将单词转为字符数组，从根节点出发，遍历输入的单词，如果子节点不包含当前字符，说明不存在该单词，返回false，如果包含，就往子节点方向移动。遍历完成后，end[cur]大于0，说明存在该单词。
查询以pre为前缀的单词数量：将单词转为字符数组，从根节点出发，遍历输入的单词，如果子节点不包含当前字符，说明不存在该前缀，返回0，如果包含，就往子节点方向移动。遍历完成后，cnt[cur]的值即为所求的前缀数量。
关于二维数组总行数的估算，由于操作次数m<=100000，假设所有操作全为插入，考虑单词长度<=20，那么总行数至多是200万，按插入、删除、查找操作平均分配，那么总行数在200万/3左右，所以估算N=70万。

TrieNode方式实现的字典树可以最大限度地利用空间，但是插入单词时，存在着频繁new对象的开销，数据量大时，可能触发GC。数组实现方式的缺点在于限制了插入的行数，初始化空间大，但是比较稳定。
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public String[] trieU (String[][] operators) {
        //计算结果集长度，并进行初始化
        int len=0;
        for(String[] opera:operators){
            if(opera[0].equals("3")||opera[0].equals("4")){
                len++;
            }
        }
        String[] res=new String[len];
        Trie trie=new Trie();
        int id=0;
 
        for(String[] opera:operators){
            if(opera[0].equals("1")){
                //添加单词
                trie.insert(opera[1]);
            }
            else if(opera[0].equals("2")){
                //删除单词
                trie.delete(opera[1]);
            }
            else if(opera[0].equals("3")){
                //查询单词是否存在
                res[id++]=trie.search(opera[1])?"YES":"NO";
            }
            else if(opera[0].equals("4")){
                //查找以word为前缀的单词数量
                String preNumber=String.valueOf(trie.prefixNumber(opera[1]));
                res[id++]=preNumber;
            }
        }
        return res;
    }
 
    //数组结构字典树
    class Trie{
        //二维数组总行数
        private final static int N=700000;
        //每一行相当于一个节点，行与行之间存在映射关系
        int[][] trie;
        //当前行插入次数
        int[] cnt;
        //当前行被标记为结尾的次数
        int[] end;
        //当前行数
        int index;
 
        Trie(){
            trie=new int[N][26];
            cnt=new int[N];
            end=new int[N];
            index=0;
        }
 
 
        //添加单词
        void insert(String word){
            int cur=0;
            char[] arr=word.toCharArray();
            for(char c:arr){
                //如果子节点不存在，则新建
                if(trie[cur][c-'a']==0){
                    trie[cur][c-'a']=++index;
                }
                //往子节点方向移动
                cur=trie[cur][c-'a'];
                cnt[cur]++;
            }
            end[cur]++;
        }
 
        void delete(String word){
            int cur=0;
            char[] arr=word.toCharArray();
            for(char c:arr){
                //往子节点方向移动，将访问次数减一
                cur=trie[cur][c-'a'];
                cnt[cur]--;
            }
            end[cur]--;
 
        }
 
        boolean search(String word){
            int cur=0;
            char[] arr=word.toCharArray();
            for(char c:arr){
                //如果子节点不存在，说明不存在该单词
                if(trie[cur][c-'a']==0){
                    return false;
                }
                cur=trie[cur][c-'a'];
            }
 
            //如果前面的节点都存在，并且当前行之前被标记为结尾，则存在该单词
            return end[cur]>0;
        }
 
        int prefixNumber(String pre){
            int cur=0;
            char[] arr=pre.toCharArray();
            for(char c:arr){
                //如果子节点不存在，说明不存在该前缀
                if(trie[cur][c-'a']==0){
                    return 0;
                }
                cur=trie[cur][c-'a'];
            }
 
            //返回以该单词为前缀的数量
            return cnt[cur];
        }
    }
}
