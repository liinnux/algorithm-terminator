package com.tompai.t1236;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Demo7_MaxNoDuplicateSubArray<K, V> {

	//[1,2,3,1,2,3,2,2]

	//

	/*
	 * 
	 * 给定一个长度为n的数组arr，返回arr的最长无重复元素子数组的长度，无重复指的是所有数字都不相同。
	子数组是连续的，比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组
	 */
	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {
			{
				while (scanner.hasNext()) {

					String s = scanner.nextLine();
					String[] res = s.substring(1, s.length() - 1).split(",", -1);
					int n = res.length;
					int[] arrs = new int[n];
					for (int i = 0; i < n; i++) {
						arrs[i] = Integer.parseInt(res[i]);
					}

					int k = maxLength2(arrs);
					System.out.println(k);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 *
	 * @param arr int整型一维数组 the array
	 * @return int整型
	 */
	public static int maxLength(int[] arr) {
		// write code here
		int result = 0, start = 0;
		int[] end = new int[40000];
		for (int i = 0; i < arr.length; i++) {
			int num = arr[i];
			if (start < end[num]) {
				start = end[num];
			}
			if (result < i - start + 1) {
				result = i - start + 1;
			}
			end[num] = i + 1;
		}
		return result;
	}

	//较好理解
	public static int maxLength2(int[] arr) {
		if (arr.length == 1)
			return 1;
		Set<Integer> numSet = new HashSet<>();
		int maxLen = 0, left = 0, right = 0;
		while (right < arr.length) {
			if (!numSet.contains(arr[right])) {
				numSet.add(arr[right++]);
				maxLen = Math.max(maxLen, right - left);
			} else {
				numSet.remove(arr[left++]);
			}
		}
		return maxLen;
	}

	
	 public static int maxLength3(int[] arr) {
	        //[2,2,3,4,3]  [2,2,3,4,2][2,2,3,4,3,2,5,1][1][1,2,3,3]
	        //从左到右遍历数组，设置一个起点值，将起点值放入map里，从起点值往右找，
	        //当数字在map中时，将中标数字的下标的下一位作为给起点值，由于起始值会一直往右移动，防止起始值因为中标而往前移动，这里会判断上一次和本次的大小，大的起始值
	        // 会优先，防止[3,3,2,1,3,3,3,1]这种数组的发生。
	        //取出map中对应的下标，将当前位置下标-起点值下标，得到长度后如果比最大的还大则将最大的值变成这个长度，
	        //将当前数字放入map里，key为值，value为下标（如果已经重复的数字，会更新下标值）
	        if(arr.length<2){
	            return arr.length;
	        }

	        int maxlength=0;
	        Map<Integer,Integer> map=new HashMap<>();
	        for(int i=0,start=0;i<arr.length;i++){
	            if(map.containsKey(arr[i])){
	                start=Math.max(map.get(arr[i])+1,start);
	            }
	            maxlength=Math.max(i-start+1,maxlength);
	            map.put(arr[i],i);
	        }
	        return maxlength;
	    }
}
