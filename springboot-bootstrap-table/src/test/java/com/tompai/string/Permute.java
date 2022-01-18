/**
 * 
 */
package com.tompai.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 NC42 有重复项数字的所有排列
 给出一组可能包含重复项的数字，返回该组数字的所有排列。结果以字典序升序排列。
 */
public class Permute {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        //排序
        Arrays.sort(num);
        boolean[] mark = new boolean[num.length];
        LinkedList<Integer> track = new LinkedList<>();
        backTrack(num,mark,track);
        return result;
    }
 
    public void backTrack(int[] num, boolean[] mark, LinkedList<Integer> track) {
        if(track.size() == num.length){
            result.add(new ArrayList<Integer>(track));
            return;
        }
        for(int i=0;i<num.length;i++){
            //该数已经标记过，遍历下一个数
            if(mark[i]){
                continue;
            }
 
            //之前重复色数据没有被使用
            if(i>0 && num[i] == num[i-1] && !mark[i-1]){
                continue;
            }
 
            //符合条件的数据添加进来
            mark[i] = true;
            track.add(num[i]);
 
            //递归调用
            backTrack(num,mark,track);
            //回溯
            track.removeLast();
            mark[i] = false;
        }
 
    }
}
