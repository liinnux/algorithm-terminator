/**
 * 
 */
package com.tompai.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class Combine {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<List<Integer>> result1 = combine(5, 3);
		
		
		for (List<Integer> list : result1) {

			System.out.println(list);
		}
		int a[] = { 1, 3, 7 };
		List<List<Integer>> result2 = permute(a);
		for (List<Integer> list : result2) {

			System.out.println(list);
		}
	}

	//要求给出对于序列1~n 的取出k个元素的各种取法。采用DFS模拟组合时，可看做节点i与节点j(j = i+1, … , n)都相连接，然后DFS遍历整张有向图
	public static List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> result = new ArrayList<>();
		if (n <= 0 || n < k) {
			return result;
		}
		List<Integer> tmp = new ArrayList<>();
		dfs(n, k, 1, tmp, result);
		return result;
	}

	// DFS for combination
	private static void dfs(int n, int k, int start, List<Integer> tmp, List<List<Integer>> result) {
		if (tmp.size() == k) {
			result.add(new ArrayList<Integer>(tmp));
			return;
		}
		for (int i = start; i <= n; i++) {
			tmp.add(i);
			dfs(n, k, i + 1, tmp, result);
			tmp.remove(tmp.size() - 1); // remove the last
		}
	}

	//DFS实现排列与组合相类似，唯一不同之处在于，节点i与其他所有节点都连接。因此，所构造的图是一个完全连通图。DFS实现排列如下
	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		if (nums.length == 0) {
			return result;
		}
		List<Integer> tmp = new ArrayList<>();
		dfs(nums, tmp, result);
		return result;
	}

	// DFS for permutation
	private static void dfs(int[] nums, List<Integer> tmp, List<List<Integer>> result) {
		int n = nums.length;
		if (tmp.size() == n) {
			result.add(new ArrayList<>(tmp));
			return;
		}
		for (int i = 0; i < n; i++) {
			// nums[i] has not been visited
			if (!tmp.contains(nums[i])) {
				tmp.add(nums[i]);
				dfs(nums, tmp, result);
				tmp.remove(tmp.size() - 1);
			}
		}
	}
	
	/*集合的所有子集(一)组合问题
	现在有一个没有重复元素的整数集合S，求S的所有子集
	注意：
	你给出的子集中的元素必须按升序排列
	给出的解集中不能出现重复的元素*/
	int length;
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    ArrayList<Integer> record = new ArrayList<>();
     
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        length = S.length;
        dfs(S, 0);
        return res;
    }
    private void dfs(int[] s, int position) {
        // 当前位置的索引越过数组的索引，则将子集添加到结果集合中
        if (position > length - 1) {
            res.add(new ArrayList<>(record));
            return;
        }
        // 子集不包含当前的数字
        dfs(s, position + 1);
        // 子集包含当前的数字
        record.add(s[position]);
        dfs(s, position + 1);
        // 复原回溯
        record.remove(record.size() - 1);
    }
    
	/*存所有排列的集合
	给出一组数字，返回该组数字的所有排列
	例如：
	[1,2,3]的所有排列如下
	[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2], [3,2,1].
	（以数字在数组中的位置靠前为优先级，按字典序排列输出。）*/
    ArrayList<ArrayList<Integer>> res2 = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> permute2(int[] num) {
        // 存一种排列
        LinkedList<Integer> list = new LinkedList<>();
        // 递归进行
        backTrack(num,list);
        return res2;
    }
 
    public void backTrack(int[] num, LinkedList<Integer> list){
        // 当list中的长度等于数组的长度，则证明此时已经找到一种排列了
        if(list.size() == num.length){
            // add进返回结果集中
            res2.add(new ArrayList<>(list));
            return;
        }
        // 遍历num数组
        for(int i = 0; i < num.length; i++){
            // 若当前位置中的数已经添加过了则跳过
            if(list.contains(num[i]))
                continue;
            // 选择该数
            list.add(num[i]);
            // 继续寻找
            backTrack(num,list);
            // 撤销最后一个
            list.removeLast();
        }
    }
}
