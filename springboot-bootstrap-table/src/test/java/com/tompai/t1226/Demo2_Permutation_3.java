/**
 * 
 */
package com.tompai.t1226;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author Administrator
 * 
 */
public class Demo2_Permutation_3 {

	public static void PermutationHelper(char[] cs,int i,List<String> list) {
		if(i==cs.length-1) {
			String val = String.valueOf(cs); //字符数组---》字符串
			if(!list.contains(val))
				list.add(val);
		}else {
			for(int j=i;j<cs.length;j++) {
				swap(cs,i,j);
				PermutationHelper(cs,i+1,list);
				swap(cs,i,j); //交换以后还需要还原回来，以便for循环第二个遍历
			}
		}
	}
	public static void swap(char[] cs,int i,int j) {
		char temp = cs[i];
		cs[i] = cs[j];
		cs[j] = temp;
	}
	public static ArrayList<String> Permutation(String str){
		List<String> res = new ArrayList<String>();
		if(str!=null&&str.length()>0) {
			PermutationHelper(str.toCharArray(),0,res); //字符串----》字符数组
			Collections.sort(res);
		}
		return (ArrayList) res;
	}

	public static void main(String[] args) {
		
		
		System.out.println(Permutation("abc").toString());
	}

}
