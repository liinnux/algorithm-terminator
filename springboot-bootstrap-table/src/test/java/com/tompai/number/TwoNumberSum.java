/**
 * 
 */
package com.tompai.number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 *
 */
public class TwoNumberSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 *
	 * @param numbers int整型一维数组
	 * @param target  int整型
	 * @return int整型一维数组
	 */
	public int[] twoSum(int[] numbers, int target) {
		// write code here
		HashMap<Integer, Integer> map = new HashMap<>();
		//遍历数组
		for (int i = 0; i < numbers.length; i++) {
			//将不包含target - numbers[i]，装入map中，包含的话直接返回下标
			if (map.containsKey(target - numbers[i]))
				return new int[] { map.get(target - numbers[i]) + 1, i + 1 };
			else
				map.put(numbers[i], i);
		}
		throw new IllegalArgumentException("No solution");
	}

	
	public int[] twoSum2(int[] numbers, int target) {
		int[] result = new int[2];
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < numbers.length; i++) {
			if (map.containsKey(target - numbers[i])) {
				result[0] = map.get(target - numbers[i]) + 1;
				result[1] = i + 1;
				return result;
			}else {
				map.put(numbers[i], i);
			}
		}
		return result;
	}
	
	////给出一个有n个元素的数组S，S中是否有元素a,b,c满足a+b+c=0？找出数组S中所有满足条件的三元组。
	 public ArrayList<ArrayList<Integer>> threeSum(int[] num){
	        Arrays.sort(num);
	        ArrayList<ArrayList<Integer>> list=new ArrayList<>();
	        for(int k=0;k<num.length;k++){
	            if(num[k]>0)break;
	            if(k>0&&num[k]==num[k-1])continue;
	            int i=k+1;
	            int j=num.length-1;
	            while(i<j){
	                int sum=num[k]+num[i]+num[j];
	                if(sum>0){
	                    while(i<j&&num[j]==num[--j]);
	                }else if(sum<0){
	                    while(i<j&&num[i]==num[++i]);
	                }else{
	                    list.add(new ArrayList(Arrays.asList(num[k],num[i],num[j])));
	                    while(i<j&&num[i]==num[++i]);
	                    while(i<j&&num[j]==num[--j]);
	                }
	            }
	        }
	        return list;
	    }
}
