/**
 * 
 */
package com.tompai.number;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

/**
 * @author Administrator
 *
 */
public class MaxLengthNoDuplicatArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
    *
    * @param arr int整型一维数组 the array
    * @return int整型
    */
   public int maxLength (int[] arr) {
       // write code here
       if(arr.length < 2){
           return arr.length;
       }

       HashMap<Integer, Integer> windows = new HashMap<>();
       int res = 0;
       //用双指针来模拟一个滑动窗口
       int left = -1;
       //窗口向右滑动
       for(int right = 0; right < arr.length; right++){
           //遇到重复数字
           if(windows.containsKey(arr[right])){
               //因为有可能遇到的重复数字的位置 比 left还要前
               //所以不能把left置于该位置前一位， 而是比较哪个最大，目的还是为了缩小窗口
               //确保窗口内全是不重复的数字
               left = Math.max(left, windows.get(arr[right]));
           }
           //每次更新窗口大小
           res = Math.max(res, right-left);
           //将数字位置更新到windows中
           windows.put(arr[right], right);
       }
       return res;
   }
   
   /**
   *
   * @param arr int整型一维数组 the array
   * @return int整型
   */
  public int maxLength2 (int[] arr) {
      // write code here
              // write code here
      int result = 0,start = 0;
      int[] end = new int[40000];
      for(int i = 0; i < arr.length; i++) {
          int num=arr[i];
          if(start<end[num]){
              start=end[num];
          }
          if(result<i-start+1){
              result=i-start+1;
          }
          end[num] = i+1;
      }
      return result;
  }
  
  public int maxLength3(int[] arr) {
	    //用链表实现队列，队列是先进先出的
	    Queue<Integer> queue = new ArrayDeque<>();
	    int res = 0;
	    for (int c : arr) {
	        while (queue.contains(c)) {
	            //如果有重复的，队头出队
	            queue.poll();
	        }
	        //添加到队尾
	        queue.offer(c);
	        res = Math.max(res, queue.size());
	    }
	    return res;
	}
}
