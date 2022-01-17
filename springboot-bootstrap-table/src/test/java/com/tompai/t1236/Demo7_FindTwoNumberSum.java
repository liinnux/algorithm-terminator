package com.tompai.t1236;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

public class Demo7_FindTwoNumberSum<K, V> {

	/**
    *
    * @param numbers int整型一维数组
    * @param target int整型
    * @return int整型一维数组
    */
   public int[] twoSum (int[] numbers, int target) {
       // write code here
       HashMap<Integer , Integer> map = new HashMap<>();
       for(int i = 0; i < numbers.length; i++){
           if(map.containsKey(target - numbers[i])){
               return new int[]{map.get(target - numbers[i]) , i+1};
           }
           else{
               map.put(numbers[i] , i+1);
           }
       }
       return new int[]{-1,-1};
   }
}
