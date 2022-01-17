/**
 * 
 */
package com.tompai.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author Administrator
 *
 */
public class StringArrangement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringArrangement testArrangement=new StringArrangement();
		String input = "112";
		char[] c = input.toCharArray();
		Arrays.sort(c);
		testArrangement.getDictionary(c);
	}

	/**
	 * @param args
	 */
	private int getMin(char[] input, int index) {
		char min = input[index];
		int minIndex = index + 1;
		char result = 'z';
		for (int i = index + 1; i < input.length; i++) {
			if (input[i] > min && input[i] < result) {
				result = input[i];
				minIndex = i;
			}
		}
		return minIndex;
	}

	private void exchange(char[] input, int index, int minIndex) {
		char temp = input[index];
		input[index] = input[minIndex];
		input[minIndex] = temp;
	}

	private void reverse(char input[], int first, int end) {
		while (first < end) {
			exchange(input, first, end);
			first++;
			end--;
		}
	}

	private void getDictionary(char c[]) {
		System.out.println(new String(c));
		//boolean flag=true;
		int i = 0;
		while (true) {
			i = c.length - 1;
			for (; i > 0; i--) {
				if (c[i - 1] < c[i])
					break;
			}
			if (i == 0)
				break;
			int minIndex = getMin(c, i - 1);
			exchange(c, i - 1, minIndex);
			reverse(c, i, c.length - 1);
			System.out.println(new String(c));
		}

	}

	/*
输入一个长度为 n 字符串，打印出该字符串中字符的所有排列，你可以以任意顺序返回这个字符串数组。
例如输入字符串ABC,则输出由字符A,B,C所能排列出来的所有字符串ABC,ACB,BAC,BCA,CBA和CAB。
	 */
	public ArrayList<String> permutation(String str) {
        ArrayList<String> result = new ArrayList<String>();
        if(str == null || str.length() == 0) return result;
        char[] charStr = str.toCharArray();
        // 使用treeSet来保证结果是按照字典序排列的，不用事先排列
        TreeSet<String> resSet = new TreeSet<String>();
        perm(charStr,0,resSet);
        result.addAll(resSet);
        return result;
    }
 
    public void perm(char[] chars,int begin,TreeSet<String> treeSet){
        if(chars==null || chars.length==0 || begin<0 || begin>chars.length-1) { return ; }
        if(begin == chars.length-1){
            // 使用交换，可以节省原来使用的memo记录数据的空间
            treeSet.add(String.valueOf(chars));
        }else{
            for(int i=begin;i<chars.length;i++){
                swap(chars,begin,i);
                // 设定下标从上一次的下一个下标开始，可以减少循环次数
                perm(chars,begin+1,treeSet);
                swap(chars,begin,i);
            }
        }
    }
 
    public void swap(char[] chars,int begin,int i){
        char temp = chars[begin];
        chars[begin] = chars[i];
        chars[i] = temp;
    }
    
    /**
     字符串的组合
     * @param chs
     */
    public void comb(char[] chs) {
		int len = chs.length;
		int nbits = 1 << len;
		for (int i = 0; i < nbits; ++i) {
			int t;
			for (int j = 0; j < len; j++) {
				t = 1 << j;
				if ((t & i) != 0) { // 与运算，同为1时才会是1
					System.out.print(chs[j]);
				}
			}
			System.out.println();
		}
	}

}
