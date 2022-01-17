/**
 * 
 */
package com.tompai.array;

/**
 * @author Administrator 给定数组 arr ，设长度为 n ，输出 arr 的最长上升子序列。（如果有多个答案，请输出其中
 *         按数值(注：区别于按单个字符的ASCII码值)进行比较的 字典序最小的那个）
 * 
 *         数据范围：0 \le n \le 200000 , 0 \le arr_i \le 10000000000≤n≤200000,0≤arr
 *         i ​ ≤1000000000 要求：空间复杂度 O(n)O(n)，时间复杂度 O(nlogn)O(nlogn) 示例1 输入：
 *         [2,1,5,3,6,4,8,9,7] 复制 返回值： [1,3,4,8,9]
 */
public class MaxLengthRiseSubList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[] LIS(int[] arr) {
		int[] nums = new int[arr.length];
		int[] temp = new int[arr.length];
		nums[0] = 1;
		int tempIndex = 0;
		temp[tempIndex] = arr[0];
		for (int i = 1; i < arr.length; ++i) {
			int left = 0, right = tempIndex;
			if (arr[i] > temp[tempIndex]) {
				++tempIndex;
				nums[i] = tempIndex + 1;
				temp[tempIndex] = arr[i];
			} else {
				while (left <= right) { // 注意这里 left <= right 而不是 left < right，我们要替换的是第一个比 arr[i] 大的元素
					int mid = (right + left) / 2;
					if (temp[mid] > arr[i]) {
						right = mid - 1;
					} else {
						left = mid + 1;
					}
				}
				temp[left] = arr[i];
				nums[i] = left + 1;
			}
		}

		int[] res = new int[tempIndex + 1];
		for (int i = nums.length - 1; i >= 0; --i) {
			if (nums[i] == tempIndex + 1) {
				res[tempIndex] = arr[i];
				--tempIndex;
			}
		}
		return res;
	}
	
	
	
	public int[] LIS2 (int[] arr) {
        // write code here
        if(arr.length <= 1|| arr == null) return arr;
        // 序列
        int[] end = new int[arr.length];
        // 最长子序列的长度len
        int[] indlen = new int[arr.length];
        end[0] = arr[0];
        // 序列的长度
        int len = 1;
        indlen[0] = len;
        for(int i=1; i<arr.length; i++){
            if(end[(len-1)] < arr[i]){
                // 如果大于就放在end后
                end[len++] = arr[i];
                indlen[i] = len;
            }else if(end[len-1] == arr[i]){
                indlen[i] = len;
            }else{
                // 替换第一个大于元素位置
                int index = findFirstIndex(end, len, arr[i]);
                end[index] = arr[i];
                indlen[i] = (index+1);
            }
        }
         
        int[] res = new int[len];
        for(int i=arr.length-1; i>=0; i--){
            if(indlen[i] == len){
                res[--len] = arr[i];
            }
        }
        return res;
    }
     
    public int findFirstIndex(int[] end, int len, int key){
        int left = 0;
        int right = len-1;
        while(left <= right){
            int mid = (left + right)>>1;
            if(end[mid] < key){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        //return end[left]<key ? (left+1):left;
        return left;
    }

}
